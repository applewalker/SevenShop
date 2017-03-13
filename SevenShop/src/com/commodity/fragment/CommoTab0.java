package com.commodity.fragment;



import java.io.IOException;

import com.Seven.Shop.R;
import com.commodity.CommodityActivity;
import com.commodity.bean.Commodity;
import com.commodity.bean.EncodingHandler;
import com.commodity.bean.ShareMsg;
import com.google.zxing.WriterException;
import com.loopj.android.image.SmartImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shop.entity.Goods;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CommoTab0 extends Fragment implements OnClickListener{
	private View view;
	private TextView pro_name,pro_price;
	private ImageView pro_pic,pro_qrcode;
	private TextView pro_express;     //快递运费
	private TextView pro_sales;       //销量
	private TextView pro_area;       //产地
	private TextView pro_intro;       //简介
	private Button share;
	private Goods good = new Goods();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		view = inflater.inflate(R.layout.pro_tab0, container, false);
		pro_name = (TextView) view.findViewById(R.id.pro_name);
		pro_price = (TextView) view.findViewById(R.id.pro_price);
	
		pro_name.setText(good.getGoods_name());
		pro_price.setText(String.valueOf(good.getMainprice()));
		
		pro_pic = (ImageView) view.findViewById(R.id.pro_pic);
		//二维码
		pro_qrcode = (ImageView) view.findViewById(R.id.pro_qrcode);
		
		share = (Button) view.findViewById(R.id.btn_share);
		pro_express = (TextView)view.findViewById(R.id.pro_express);
		pro_sales = (TextView)view.findViewById(R.id.pro_sales);
		pro_area = (TextView)view.findViewById(R.id.pro_area);
		pro_intro = (TextView)view.findViewById(R.id.pro_intro);	
		
		share.setOnClickListener(this);
		
		return view;
	}
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);  
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.btn_share:
			//分享		
			ShareMsg s = null;
			try {
				s = new ShareMsg(good.getGoods_name(),
						good.getGoods_name(),view.findViewById(R.id.pro_pic),view.findViewById(R.id.pro_qrcode));
				startActivity(Intent.createChooser(s.getIntent(), "分享"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getActivity().getApplicationContext(), "分享失败",
					     Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}			
			break;
		
		}
	}
	
	public void upData() throws WriterException{
		good = ((CommodityActivity)getActivity()).getGoods();
		pro_name.setText(good.getGoods_name());
		ImageLoader.getInstance().displayImage(good.getImage_path(), pro_pic);
		
		creatQR();
		
		pro_price.setText("价格 "+good.getMainprice());
		pro_express.setText("快递运费："+good.getFare());
		pro_sales.setText("月销量："+good.getSalesvolume());
		pro_area.setText("产地："+good.getPlace());
		pro_intro.setText("商品简介："+good.getIntroduction());
	}
	public void creatQR() throws WriterException{
		//以下代码生成二维码
		String contentString = "7shop,Here’s to the crazy ones "
				+ "The misfits The rebels "
				+ "The troublemakers "
				+ "The round pegs in the square holes "
				+ "The ones who see things differently "
				+ "They’re not fond of rules"
				+ ","+good.getGoods_id();
		if (!contentString.equals("")) {
			
			Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
			Bitmap qrcode = addLogo(qrCodeBitmap, BitmapFactory.decodeResource(getResources(), R.drawable.qrlogo) );
			pro_qrcode.setImageBitmap(qrcode);
			//pro_qrcode.setImageBitmap(qrCodeBitmap);
		}else {
			Toast.makeText(getActivity(), "Text can not be empty", Toast.LENGTH_SHORT).show();
		}
		//以上代码生成二维码
	}
	private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }
 
        if (logo == null) {
            return src;
        }
 
        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();
 
        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }
 
        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }
 
        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
 
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }
 
        return bitmap;
    }
}
