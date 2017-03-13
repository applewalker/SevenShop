package com.view.homepage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.Seven.Shop.R;
import com.commodity.CommodityActivity;
import com.fenlei.ScrollGridActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.http.util.HttpUtil1;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.search.show.Search_showActivity;
import com.shop.entity.Recommend;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.tejia_like.likeActivity;
import com.view.DepthPageTransformer;
import com.view.ZoomOutPageTransformer;
import com.view.homeSearch.SearchActivity;
import com.zbar.lib.CaptureActivity;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

public class tab1 extends Fragment{

	private LinearLayout menu_tejia;
	private LinearLayout menu_like;
	private LinearLayout menu_fenlei;
	
	private ImageView search_et;
	private ImageView tab01_search;
	private ImageView scan;
	private static String[] imageUrls = {
			"http://img008.hc360.cn/y5/M07/27/26/wKhQUVXJ1bWEVPu_AAAAAGGGZww400.jpg",
			"http://img007.hc360.cn/g8/M09/94/48/wKhQtVQFfESEYIzRAAAAANXrKno437.jpg",
			"http://img1.imgtn.bdimg.com/it/u=1652242666,1134293355&fm=21&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=2937084241,3890891486&fm=21&gp=0.jpg",
			"http://img30.360buyimg.com/da/jfs/t2617/222/3160756404/149104/7ab5ed80/57834089N934dc6b5.jpg"};

	//ͼƬ����·��
		public static String IMAGE_CACHE_PATH = "imageloader/Cache"; 
		
		//ʵ����ViewPager
		private ViewPager myViewPager;
		
		//����ͼƬ�� ����
		private List<ImageView> imageViews;
		
		//ͼƬ��Ӧ��ָʾ��
		private List<View> dots; 
		private List<View> dotList;
		
		//��ǰͼƬ��������
		private int currentItem = 0; 
		
		//ʵ����5��ָʾ��
		private View dot0;
		private View dot1;
		private View dot2;
		private View dot3;
		private View dot4;

		//��ʱ����
		private ScheduledExecutorService scheduledExecutorService;

		//�첽����ͼƬ
		private ImageLoader myImageLoader;
		
		//ͼƬ����
		private DisplayImageOptions myImageOptions;

		//�ֲ�banner������
		private List<Banner> adList; 
		
		//�ֲ��㲥����
		public static List<Banner> getBannerAd() {
			List<Banner> adList = new ArrayList<Banner>();
		
			Banner banner1 = new Banner();
			banner1.setId("1");
			banner1.setImgUrl(imageUrls[0]);
			adList.add(banner1);
		
			Banner banner2 = new Banner();
			banner2.setId("2");
			banner2.setImgUrl(imageUrls[1]);
			adList.add(banner2);
		
			Banner banner3 = new Banner();
			banner3 .setId("3");
			banner3 .setImgUrl(imageUrls[2]);
			adList.add(banner3 );
		
			Banner banner4  = new Banner();
			banner4.setId("4");
			banner4.setImgUrl(imageUrls[3]);
			adList.add(banner4);
		
			Banner banner5 = new Banner();
			banner5.setId("5");
			banner5.setImgUrl(imageUrls[4]);
			adList.add(banner5);
			
			return adList;
		}
		
		private List<Recommend> reclist;  // �Ƽ�����
		
		private MyAdapter myAdapter;
		
		//֪ͨViewPager������ͼ�л�
		@SuppressLint("HandlerLeak")
		private Handler handler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				myViewPager.setCurrentItem(currentItem);
			};
		};
		
		//��ʼ��ImageLoader
		private void initImageLoader() {
			File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
					.getOwnCacheDirectory(getContext(),IMAGE_CACHE_PATH);

			DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
					.cacheInMemory(true).cacheOnDisc(true).build();

			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					getContext()).defaultDisplayImageOptions(defaultOptions)
					.memoryCache(new LruMemoryCache(12 * 1024 * 1024))
					.memoryCacheSize(12 * 1024 * 1024)
					.discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
					.discCache(new UnlimitedDiscCache(cacheDir))
					.threadPriority(Thread.NORM_PRIORITY - 2)
					.tasksProcessingOrder(QueueProcessingType.LIFO).build();

			ImageLoader.getInstance().init(config);
		}

		//��ʼ���ֲ��������
		@SuppressWarnings("deprecation")
		private void initBannerData(View view) {
			//�ֲ��������
			adList = getBannerAd();

			imageViews = new ArrayList<ImageView>();

			//ָʾ��
			dots = new ArrayList<View>();
			dotList = new ArrayList<View>();
			dot0 = view.findViewById(R.id.v_dot0);
			dot1 = view.findViewById(R.id.v_dot1);
			dot2 = view.findViewById(R.id.v_dot2);
			dot3 = view.findViewById(R.id.v_dot3);
			dot4 = view.findViewById(R.id.v_dot4);
			dots.add(dot0);
			dots.add(dot1);
			dots.add(dot2);
			dots.add(dot3);
			dots.add(dot4);

			myViewPager = (ViewPager) view.findViewById(R.id.vp);
		//	myViewPager.setPageTransformer(true, new ZoomOutPageTransformer());//����1
		    myViewPager.setPageTransformer(true, new DepthPageTransformer());//����2 
			//�������ViewPagerҳ���������
			myViewPager.setAdapter(new myBannerAdapter());
			//����һ������������ViewPager�е�ҳ��ı�ʱ����
			myViewPager.setOnPageChangeListener(new myPageChangeListener());
			addDynamicView();
		}

		private void addDynamicView() {
			// ��̬���ͼƬ������ָʾ��Բ��
			// ��ʼ��ͼƬ��Դ
			for (int i = 0; i < adList.size(); i++) {
				ImageView imageView = new ImageView(getContext());
				// �첽����ͼƬ
				myImageLoader.displayImage(adList.get(i).getImgUrl(), imageView, myImageOptions);
				imageView.setScaleType(ScaleType.CENTER_CROP);
				imageViews.add(imageView);
				dots.get(i).setVisibility(View.VISIBLE);
				dotList.add(dots.get(i));
			}
		}

		@Override
		public void onResume() {
			super.onResume();
		}

		private void startBanner() {
			scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
			// ��Activity��ʾ������ÿ2���л�һ��ͼƬ��ʾ
			scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
					TimeUnit.SECONDS);
		}
	    
		//�����߳�
		private class ScrollTask implements Runnable {
			@Override
			public void run() {
				synchronized (myViewPager) {
					currentItem = (currentItem + 1) % imageViews.size();
					handler.obtainMessage().sendToTarget();
				}
			}
		}
		
		//ֹͣ�л�
		@Override
		public void onStop() {
			super.onStop();
			// ��Activity���ɼ���ʱ��ֹͣ�л�
			scheduledExecutorService.shutdown();
		}
	

	
	@SuppressWarnings("deprecation")
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.tab01, null);
		tab01_search=(ImageView) view.findViewById(R.id.tab01_search);
		scan=(ImageView) view.findViewById(R.id.tab01_scan);
		
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),CaptureActivity.class);
				getActivity().startActivityForResult(intent, 0);;
				
			}
		});
		
		
		tab01_search.setOnClickListener(new OnClickListener() {//����ͼ��
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),SearchActivity.class);
				getActivity().startActivity(intent);
				search_et.clearFocus();
			}
		});
		
		//����������		
				search_et = (ImageView)view.findViewById(R.id.search_et);
				//search_bn.setFocusable(false);
				//search_bn.setFocusableInTouchMode(true);
				//search_bn.clearFocus();
				//search_bn.setFocusable(false);
				search_et.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(),SearchActivity.class);
						getActivity().startActivity(intent);
						search_et.clearFocus();
					}
				});
				
		
		//ʹ��ImageLoader֮ǰ��ʼ��
		initImageLoader();

		//����ͼƬʵ��
		myImageLoader = ImageLoader.getInstance();
		myImageOptions = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.top_banner_android)
				.showImageForEmptyUri(R.drawable.top_banner_android)
				.showImageOnFail(R.drawable.top_banner_android)
				.cacheInMemory(true).cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.EXACTLY).build();

		initBannerData(view);
		startBanner();

		
		//center_menu
		menu_tejia = (LinearLayout) view.findViewById(R.id.menu_tejia);
		menu_like = (LinearLayout) view.findViewById(R.id.menu_like);
		menu_fenlei = (LinearLayout) view.findViewById(R.id.menu_fenlei);

		menu_tejia.setOnClickListener(menu_click);
		menu_like.setOnClickListener(menu_click);
		menu_fenlei.setOnClickListener(menu_click);
		//
		
        MyGridView gv = (MyGridView)view.findViewById(R.id.grid); //-------��Ʒ�б�
        //ΪGridView���������� 
        
        GetRecommend getRecommend  = new GetRecommend();
	    reclist = getRecommend.getRecommend();
        
	    
	    myAdapter = new MyAdapter(getContext());
	    
        gv.setAdapter(myAdapter); 
        //ע������¼� 
        gv.setOnItemClickListener(new OnItemClickListener() 
        { 
            @Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            { 
            	Intent intent = new Intent(getActivity(),CommodityActivity.class);          
                intent.putExtra("id", Integer.valueOf(reclist.get(position).getGoods_id()));
            	getActivity().startActivityForResult(intent, 0);
            } 
        }); 
	
		return view;
	}

	OnClickListener menu_click = new OnClickListener() {
		@Override
		public void onClick(View v){
			switch(v.getId()){
			case R.id.menu_tejia:
				//Toast.makeText(getContext(), "�����ؼ�" , Toast.LENGTH_SHORT).show();
				Intent intent1 = new Intent(getContext(), Search_showActivity.class);
				intent1.putExtra("search_name", "�ؼ�");
				getActivity().startActivity(intent1);
				break;
			case R.id.menu_like:
				//Toast.makeText(getContext(), "�������ϲ��" , Toast.LENGTH_SHORT).show();
				Intent intent2 = new Intent(getContext(), likeActivity.class);
				getActivity().startActivity(intent2);
				break;
			case R.id.menu_fenlei:
				Intent intent3 = new Intent(getContext(), ScrollGridActivity.class);
				getActivity().startActivity(intent3);
				break;
			default:
				break;
			}
		}
	};
	
	public class GetRecommend {
		
	    public  MediaType JSON=MediaType.parse("application/json; charset=utf-8");
	    private final Gson gson = new Gson();
		private List<Recommend> list;
		public GetRecommend() {
			// TODO Auto-generated constructor stub
			
			
		}
		
		public List<Recommend> getRecommend(){
			 list = new ArrayList<Recommend>();
			//����
	    	    Recommend demoRecommend=new Recommend();
	    	   demoRecommend.setGoods_id(7777);
	    	   Drawable drawable =getResources().getDrawable(R.drawable.ic_launcher);
	    	   Bitmap bitmap =((BitmapDrawable)drawable).getBitmap();
	    	   File file = new File(Environment.getExternalStorageDirectory(),
	                   "launcher.png");
	    	   if(file.exists())
	    		   file.delete();
	           FileOutputStream out = null;
	           try{
	               // ��ָ���ļ������
	               out = new FileOutputStream(file);
	               // ��λͼ�����ָ���ļ�
	               bitmap.compress(Bitmap.CompressFormat.PNG, 100,
	                       out);
	               out.close();
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	           System.out.print(Environment.getExternalStorageDirectory());
	           demoRecommend.setImage_path("file://"+Environment.getExternalStorageDirectory()+"/launcher.png");
	    	   list.add(demoRecommend);
			HttpUtil1.postAsync("showrecommend.php", null,  new HttpUtil1.DataCallBack() {
				
				@Override
				public void requestSuccess(String result) throws Exception {
					// TODO Auto-generated method stub
					
					System.out.println(result);
					
		    	    JsonParser parser = new JsonParser();
		    	    JsonArray Jarray = parser.parse(result).getAsJsonArray();
		    	  
		    	   
		    	    for (JsonElement obj : Jarray){
		    	          Recommend recommend =  gson.fromJson(obj, Recommend.class);
		    	          list.add(recommend);
		    	    }
		    	    myAdapter.notifyDataSetChanged();
				}
				
				@Override
				public void requestFailure(Request request, IOException e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			
			return list;
			
			
		}
		

	}
	
	
	//�Զ��������� ,����չʾ
    class MyAdapter extends BaseAdapter{ 
        //�����Ķ��� 
        private Context context; 
        //ͼƬ���� 
        
        MyAdapter(Context context){ 
            this.context = context; 
        } 
        @Override
		public int getCount() { 
            return reclist.size(); 
        } 
 
        @Override
		public Object getItem(int item) { 
            return item; 
        } 
 
        @Override
		public long getItemId(int id) { 
            return id; 
        } 
         
        //����View���� 
        @Override
		public View getView(int position, View convertView, ViewGroup parent) { 
             ImageView imageView; 
             if (convertView == null) { 
                 imageView = new ImageView(context); 
                 imageView.setLayoutParams(new GridView.LayoutParams(520, 520));//����ImageView���󲼾� 
                 imageView.setAdjustViewBounds(false);//���ñ߽����
                 imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//���ÿ̶ȵ�����                 
                 //imageView.setPadding(10, 5, 10, 5);//���ü�� 
             }  
             else { 
                 imageView = (ImageView) convertView; 
             } 
             ImageLoader.getInstance().displayImage(reclist.get(position).getImage_path(), imageView); 
             return imageView; 
        } 
    } 
    
  //VierPager�������ӿ�
  	private class myPageChangeListener implements OnPageChangeListener {

  		private int oldPosition = 0;

  		@Override
  		public void onPageScrollStateChanged(int arg0) {
  			
  		}

  		@Override
  		public void onPageScrolled(int arg0, float arg1, int arg2) {
  			
  		}

  		@Override
  		public void onPageSelected(int position) {
  			currentItem = position;
  			//Banner banner = adList.get(position);
  			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
  			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
  			oldPosition = position;
  			
  			
  		}
	
  	}
  	
  	
  
  	
   //VierPager������  bannar
  	private class myBannerAdapter extends PagerAdapter {

  		@Override
  		public int getCount() {
  			return adList.size();
  		}

  		@Override
  		public Object instantiateItem(ViewGroup container, final int position) {
  			ImageView iv = imageViews.get(position);
  			((ViewPager) container).addView(iv);
  			//final Banner adDomain = adList.get(position);
  			//�����������������ͼƬ�ĵ���¼�
  			iv.setOnClickListener(new OnClickListener() {

  				@Override
  				public void onClick(View v) {
  					Toast.makeText(getContext(), "������"+position, Toast.LENGTH_SHORT).show();
  				}
  			});
  			return iv;
  		}

  		@Override
  		public void destroyItem(View arg0, int arg1, Object arg2) {
  			((ViewPager) arg0).removeView((View) arg2);
  		}

  		@Override
  		public boolean isViewFromObject(View arg0, Object arg1) {
  			return arg0 == arg1;
  		}

  		@Override
  		public void restoreState(Parcelable arg0, ClassLoader arg1) {

  		}

  		@Override
  		public Parcelable saveState() {
  			return null;
  		}

  		@Override
  		public void startUpdate(View arg0) {

  		}

  		@Override
  		public void finishUpdate(View arg0) {

  		}
  	}

}


