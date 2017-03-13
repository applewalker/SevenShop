package com.http.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.os.Handler;
import android.os.Looper;

public class HttpUtil1 {
	 /**
     * 静态实例
     */
    private static HttpUtil1 sHttpUtil;

    /**
     * okhttpclient实例
     */
    private OkHttpClient mClient;

    /**
     * 因为我们请求数据一般都是子线程中请求，在这里我们使用了handler
     */
    private Handler mHandler;
    
    
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    /**
     * 构造方法
     */
    private HttpUtil1() {

        mClient = new OkHttpClient();

        /**
         * 在这里直接设置连接超时.读取超时，写入超时
         */
       /* mClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);*/

        /**
         * 如果是用的3.0之前的版本  使用以下直接设置连接超时.读取超时，写入超时
         */

        mClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mClient.setReadTimeout(30, TimeUnit.SECONDS);


        /**
         * 初始化handler
         */
        mHandler = new Handler(Looper.getMainLooper());
    }


    /**
     * 单例模式  获取HttpUtil实例
     *
     * @return
     */
    public static HttpUtil1 getInstance() {

        if (sHttpUtil == null) {
            sHttpUtil = new HttpUtil1();
        }
        return sHttpUtil;
    }

    //-------------------------同步的方式请求数据--------------------------

    /**
     * 对外提供的get方法,同步的方式
     *
     * @param url 传入的地址
     * @return
     */
    public static Response getSync(String url) {

        //通过获取到的实例来调用内部方法
        return sHttpUtil.inner_getSync(url);
    }

    /**
     * GET方式请求的内部逻辑处理方式，同步的方式
     *
     * @param url
     * @return
     */
    private Response inner_getSync(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            //同步请求返回的是response对象
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 对外提供的同步获取String的方法
     *
     * @param url
     * @return
     */
    public static String getSyncString(String url) {
        return sHttpUtil.inner_getSyncString(url);
    }


    /**
     * 同步方法
     */
    private String inner_getSyncString(String url) {
        String result = null;
        try {
            /**
             * 把取得到的结果转为字符串，这里最好用string()
             */
            result = inner_getSync(url).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //-------------------------异步的方式请求数据--------------------------
    public static void getAsync(String url, DataCallBack callBack) {
        getInstance().inner_getAsync(url, callBack);
    }

    /**
     * 内部逻辑请求的方法
     *
     * @param url
     * @param callBack
     * @return
     */
    private void inner_getAsync(String url, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();

        mClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub
				deliverDataFailure(request, arg1, callBack);
			}

			@Override
			public void onResponse(Response arg0) throws IOException {
				// TODO Auto-generated method stub
				 String result = null;
	                try {
	                    result = arg0.body().string();
	                } catch (IOException e) {
	                    deliverDataFailure(request, e, callBack);
	                }
	                deliverDataSuccess(result, callBack);
			}
        });
    }


    /**
     * 分发失败的时候调用
     *
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final IOException e, final DataCallBack callBack) {
        /**
         * 在这里使用异步处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    /**
     * 分发成功的时候调用
     *
     * @param result
     * @param callBack
     */
    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        /**
         * 在这里使用异步线程处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.requestSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 数据回调接口
     */
    public interface DataCallBack {
        void requestFailure(Request request, IOException e);

        void requestSuccess(String result) throws Exception;
    }

      //-------------------------提交表单--------------------------
    static String url0 = "http://192.168.1.200:80/test/";
    public static void postAsync(String url1, Object params, DataCallBack callBack) {
    	String url = url0+url1;
    	System.out.println("wo shi url:"+url);
    	String jstring = getInstance().objectTOJson(params); 
    	System.out.println(jstring);
        getInstance().inner_postAsync(url, jstring, callBack);
    }
    
    private String objectTOJson(Object params){
    	
    	if (params == null){
    		return "";
    	}else {
	    	JsonUtil jsonUtil = new JsonUtil();
	    	return jsonUtil.toJson(params); 	
    	}
    }
    
    private void inner_postAsync(String url, String jstring, final DataCallBack callBack) {

    	 RequestBody requestBody = RequestBody.create(JSON, jstring);
        //结果返回
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        mClient.newCall(request).enqueue(new Callback() {


			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub
				deliverDataFailure(request, arg1, callBack);
			}

			@Override
			public void onResponse(Response arg0) throws IOException {
				// TODO Auto-generated method stub
				String result = arg0.body().string();
                deliverDataSuccess(result, callBack);
			}


        });
    }


    //-------------------------文件下载--------------------------
    public static void downloadAsync(String url, String desDir, DataCallBack callBack) {
        getInstance().inner_downloadAsync(url, desDir, callBack);
    }

    /**
     * 下载文件的内部逻辑处理类
     *
     * @param url      下载地址
     * @param desDir   目标地址
     * @param callBack
     */
    private void inner_downloadAsync(final String url, final String desDir, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub
				deliverDataFailure(request, arg1, callBack);
			}

			@Override
			public void onResponse(Response response) throws IOException {
				// TODO Auto-generated method stub
                /**
                 * 在这里进行文件的下载处理
                 */
                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;
                try {
                    //文件名和目标地址
                    File file = new File(desDir, getFileName(url));
                    //把请求回来的response对象装换为字节流
                    inputStream = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] bytes = new byte[2048];
                    //循环读取数据
                    while ((len = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, len);
                    }
                    //关闭文件输出流
                    fileOutputStream.flush();
                    //调用分发数据成功的方法
                    deliverDataSuccess(file.getAbsolutePath(), callBack);
                } catch (IOException e) {
                    //如果失败，调用此方法
                    deliverDataFailure(request, e, callBack);
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                }
			}

        });
    }

    
    
    
    public void uplodeImage(File mFile) {  
    	final MediaType MEDIA_TYPE_MARKDOWN
    	  = MediaType.parse("text/x-markdown; charset=utf-8");
    	 
    	 
    	    Request request = new Request.Builder()
    	        .url("https://api.github.com/markdown/raw")
    	        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, mFile))
    	        .build();
    	 
    	    try {
				Response response = mClient.newCall(request).execute();
				if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
  	 
				System.out.println(response.body().string());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }  


    /**
     * 根据文件url获取文件的路径名字
     *
     * @param url
     * @return
     */
    private String getFileName(String url) {
        int separatorIndex = url.lastIndexOf("/");
        String path = (separatorIndex < 0) ? url : url.substring(separatorIndex + 1, url.length());
        return path;
    }


}
