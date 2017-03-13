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
     * ��̬ʵ��
     */
    private static HttpUtil1 sHttpUtil;

    /**
     * okhttpclientʵ��
     */
    private OkHttpClient mClient;

    /**
     * ��Ϊ������������һ�㶼�����߳�����������������ʹ����handler
     */
    private Handler mHandler;
    
    
    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    /**
     * ���췽��
     */
    private HttpUtil1() {

        mClient = new OkHttpClient();

        /**
         * ������ֱ���������ӳ�ʱ.��ȡ��ʱ��д�볬ʱ
         */
       /* mClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);*/

        /**
         * ������õ�3.0֮ǰ�İ汾  ʹ������ֱ���������ӳ�ʱ.��ȡ��ʱ��д�볬ʱ
         */

        mClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mClient.setReadTimeout(30, TimeUnit.SECONDS);


        /**
         * ��ʼ��handler
         */
        mHandler = new Handler(Looper.getMainLooper());
    }


    /**
     * ����ģʽ  ��ȡHttpUtilʵ��
     *
     * @return
     */
    public static HttpUtil1 getInstance() {

        if (sHttpUtil == null) {
            sHttpUtil = new HttpUtil1();
        }
        return sHttpUtil;
    }

    //-------------------------ͬ���ķ�ʽ��������--------------------------

    /**
     * �����ṩ��get����,ͬ���ķ�ʽ
     *
     * @param url ����ĵ�ַ
     * @return
     */
    public static Response getSync(String url) {

        //ͨ����ȡ����ʵ���������ڲ�����
        return sHttpUtil.inner_getSync(url);
    }

    /**
     * GET��ʽ������ڲ��߼�����ʽ��ͬ���ķ�ʽ
     *
     * @param url
     * @return
     */
    private Response inner_getSync(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            //ͬ�����󷵻ص���response����
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * �����ṩ��ͬ����ȡString�ķ���
     *
     * @param url
     * @return
     */
    public static String getSyncString(String url) {
        return sHttpUtil.inner_getSyncString(url);
    }


    /**
     * ͬ������
     */
    private String inner_getSyncString(String url) {
        String result = null;
        try {
            /**
             * ��ȡ�õ��Ľ��תΪ�ַ��������������string()
             */
            result = inner_getSync(url).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //-------------------------�첽�ķ�ʽ��������--------------------------
    public static void getAsync(String url, DataCallBack callBack) {
        getInstance().inner_getAsync(url, callBack);
    }

    /**
     * �ڲ��߼�����ķ���
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
     * �ַ�ʧ�ܵ�ʱ�����
     *
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final IOException e, final DataCallBack callBack) {
        /**
         * ������ʹ���첽����
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
     * �ַ��ɹ���ʱ�����
     *
     * @param result
     * @param callBack
     */
    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        /**
         * ������ʹ���첽�̴߳���
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
     * ���ݻص��ӿ�
     */
    public interface DataCallBack {
        void requestFailure(Request request, IOException e);

        void requestSuccess(String result) throws Exception;
    }

      //-------------------------�ύ��--------------------------
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
        //�������
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


    //-------------------------�ļ�����--------------------------
    public static void downloadAsync(String url, String desDir, DataCallBack callBack) {
        getInstance().inner_downloadAsync(url, desDir, callBack);
    }

    /**
     * �����ļ����ڲ��߼�������
     *
     * @param url      ���ص�ַ
     * @param desDir   Ŀ���ַ
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
                 * ����������ļ������ش���
                 */
                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;
                try {
                    //�ļ�����Ŀ���ַ
                    File file = new File(desDir, getFileName(url));
                    //�����������response����װ��Ϊ�ֽ���
                    inputStream = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] bytes = new byte[2048];
                    //ѭ����ȡ����
                    while ((len = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, len);
                    }
                    //�ر��ļ������
                    fileOutputStream.flush();
                    //���÷ַ����ݳɹ��ķ���
                    deliverDataSuccess(file.getAbsolutePath(), callBack);
                } catch (IOException e) {
                    //���ʧ�ܣ����ô˷���
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
     * �����ļ�url��ȡ�ļ���·������
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
