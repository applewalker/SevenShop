package com.eromanga.utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.eromanga.bean.Answer;
import com.eromanga.bean.News;
import com.eromanga.bean.Recipes;
import com.eromanga.bean.TimeMessage;
import com.eromanga.bean.Url;
import com.eromanga.bean.UserRequest;
import com.eromanga.robot.RobotActivity;
import com.eromanga.bean.News.Article;
import com.eromanga.bean.Recipes.Course;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.util.Log;

public class PostMsg {
	public static TimeMessage dopost(UserRequest ur) throws JSONException{
		TimeMessage mObject = null;
		String answer = post(ur);
		JSONObject tempjson = new JSONObject(answer);
		Gson gson = new Gson();
		switch (tempjson.getInt("code")) {
		case 100000://文本类
		case 40001://参数key错误
		case 40002://请求内容info为空
		case 40004://当天请求次数已使用完
		case 40007://数据格式异常		
			mObject = gson.fromJson(answer, Answer.class);
			break;
		case 200000://链接类
			mObject = gson.fromJson(answer, Url.class);
			break;
		case 302000://新闻类
			News n = new News();
			n = gson.fromJson(answer, News.class);
			List<Article> nList = n.getList();
			String newshtml = "<body>"+n.getText()+"<br>";
			for(int i = 0;i<nList.size();i++)
			{
				Article article = nList.get(i);
				newshtml += "<h4>" +article.article +"</h4>"
						+"(来源："+article.source+")<br>";
				if(!article.icon.isEmpty())				
					newshtml +="<img src=\""+article.icon+"\"/><br>";
				newshtml +="<a href=\""+article.detailurl+"\">新闻链接</a></body>";

			}
			n.setChs(ChangeforView(newshtml));
			mObject = n;
			break;
		case 308000://菜谱类
			RobotActivity.MsgofWait();
			Recipes r = new Recipes();
			r = gson.fromJson(answer, Recipes.class);
			List<Course> rList = r.getList();
			String recihtml = "<body>"+r.getText()+"<br>";
			for(int i=0;i<rList.size();i++)
			{
				Course course = rList.get(i);
				recihtml +="<h6>"+course.name+"</h6>";
				if(!course.icon.isEmpty())
					recihtml +="<img src=\""+course.icon+"\"/><br>";
				//Environment.getExternalStorageDirectory().toString()+"/shareswip/sharepic.png"
				recihtml += course.info+"<br><a href=\"" +course.detailurl+"\">菜谱链接</a></body>";
			}
			r.setChs(ChangeforView(recihtml));
			mObject = r;
			break;					
		default:
			break;
		}
		return mObject;
	}
	public static  String post(UserRequest ur){
		String answer = null;
		final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//		UserRequest ur = new UserRequest();
//		ur.setInfo(msg);
		
		Gson gson = new Gson();  
	    String json = gson.toJson(ur); 
	    //创建一个OkHttpClient对象
	    OkHttpClient okHttpClient = new OkHttpClient();
	    //创建一个RequestBody(数据类型 , JSON)
	    RequestBody requestBody = RequestBody.create(mediaType,json);
	    //创建一个请求对象
	    Request request = new Request.Builder()
	            .url(Prefs.API_URL)
	            .post(requestBody)
	            .build();
	    //获取响应
	    try {
	    Response response=okHttpClient.newCall(request).execute();
	        //判断是否成功
	        if(response.isSuccessful()){
	          
	        	
//	            Log.d("PostMsg",response.body().string());
	            answer = response.body().string();
	            Log.d("PostMsg", answer);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        
	    }
		return answer;
	}
	private static CharSequence ChangeforView(String html) {   

		
				// TODO Auto-generated method stub			
				ImageGetter imageGetter = new Html.ImageGetter() {  


					public Drawable getDrawable(String source) {
						// TODO Auto-generated method stub
						URL url;
						Drawable drawable = null;
						try {
							url = new URL(source);
							drawable = Drawable.createFromStream(
									url.openStream(), null);
							drawable.setBounds(0, 0,
									drawable.getIntrinsicWidth(),
									drawable.getIntrinsicHeight());
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return drawable;
					}
				};  
				CharSequence chs = Html.fromHtml(html, imageGetter, null);
				return chs;
	}
	
}
