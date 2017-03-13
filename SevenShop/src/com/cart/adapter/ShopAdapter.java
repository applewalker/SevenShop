package com.cart.adapter;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.HashMap;
import java.util.List;

import com.cart.bean.CarList;
import com.cart.bean.carBean;
import com.google.gson.Gson;
import com.http.util.HttpUtil1;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shop.entity.Response;
import com.squareup.okhttp.Request;
import com.Seven.Shop.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ShopAdapter extends BaseAdapter {

	private Handler mHandler;
	private int resourceId; // ��������ͼ��ԴID
	private Context context; // �����Ķ���
	private List<carBean> list; // ���ݼ���List
	private LayoutInflater inflater; // ���������
	private static HashMap<Integer, Boolean> isSelected;
	

	@SuppressLint("UseSparseArrays")
	public ShopAdapter(Context context, List<carBean> list, Handler mHandler, int resourceId) {
		this.list = list;
		this.context = context;
		this.mHandler = mHandler;
		this.resourceId = resourceId;
		inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();
		initDate();
	}

	// ��ʼ��isSelected������
	private void initDate() {
		for (int i = 0; i < list.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		ShopAdapter.isSelected = isSelected;
	}

	public List<carBean> getList(){
		return list;	
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		carBean bean = list.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(resourceId, null);
			holder = new ViewHolder();
			holder.shop_photo = (ImageView) convertView.findViewById(R.id.shop_photo);
			holder.shop_name = (TextView) convertView.findViewById(R.id.shop_name);
			holder.shop_description = (TextView) convertView.findViewById(R.id.shop_description);
			holder.shop_price = (TextView) convertView.findViewById(R.id.shop_price);
			holder.shop_number = (TextView) convertView.findViewById(R.id.shop_number);
			holder.shop_check = (CheckBox) convertView.findViewById(R.id.shop_check);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(bean.getImagepath(), holder.shop_photo);
		holder.shop_name.setText(bean.getGoods_name());
		holder.shop_description.setText(bean.getGoodsdescribe());
		holder.shop_price.setText("��" + bean.getPrice());
		holder.shop_number.setTag(position);
		holder.shop_number.setText(String.valueOf(bean.getNumber()));
		holder.shop_number.setOnClickListener(new ShopNumberClickListener());
		holder.shop_check.setTag(position);
		holder.shop_check.setChecked(getIsSelected().get(position));
		holder.shop_check.setOnCheckedChangeListener(new CheckBoxChangedListener());
		return convertView;
	}

	private final class ViewHolder {
		public ImageView shop_photo; // ��ƷͼƬ
		public TextView shop_name; // ��Ʒ����
		public TextView shop_description; // ��Ʒ����
		public TextView shop_price; // ��Ʒ�۸�
		public TextView shop_number; // ��Ʒ����
		public CheckBox shop_check; // ��Ʒѡ��ť
	}

	// ����TextView���������
	private final class ShopNumberClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// ��ȡ��Ʒ������
			String str = ((TextView) v).getText().toString();
			int shopNum = Integer.valueOf(str);
			showDialog(shopNum, (TextView) v);
		}
	}

	private int number = 0; // ��¼�Ի����е�����
	private EditText editText; // �Ի����������༭��

	/**
	 * �����Ի��������Ʒ������
	 * 
	 * @param shopNum
	 *            ��Ʒԭ��������
	 * @param textNum
	 *            Item����ʾ��Ʒ�����Ŀؼ�
	 */
	private void showDialog(int shopNum, final TextView textNum) {
		View view = inflater.inflate(R.layout.number_update, null);
		Button btnSub = (Button) view.findViewById(R.id.numSub);
		Button btnAdd = (Button) view.findViewById(R.id.numAdd);
		editText = (EditText) view.findViewById(R.id.edt);
		editText.setText(String.valueOf(shopNum));
		btnSub.setOnClickListener(new ButtonClickListener());
		btnAdd.setOnClickListener(new ButtonClickListener());
		number = shopNum;
		new AlertDialog.Builder(context).setView(view).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// ���û����ĵ���Ʒ�������µ�������
				int position = (Integer) textNum.getTag();
				carBean carBean = new carBean();
				carBean.setGoodscar_id(list.get(position).getGoodscar_id());
				carBean.setNumber(number);
				carBean.setState(3);
				
				HttpUtil1.postAsync("updatecar.php", carBean, new HttpUtil1.DataCallBack() {
					
					@Override
					public void requestSuccess(String result) throws Exception {
						// TODO Auto-generated method stub
						
						System.out.println(result);
						Gson gson = new Gson();
						Response r = gson.fromJson(result, Response.class);
						int position = (Integer) textNum.getTag();
						if (r.getSuccess() == 0){
							list.get(position).setNumber(number);
							handler.sendMessage(handler.obtainMessage(1, textNum));
						}else {
							//ʧ��
						}
						
					}
					
					@Override
					public void requestFailure(Request request, IOException e) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		}).setNegativeButton("ȡ��", null).create().show();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) { // ������Ʒ����
				((TextView) msg.obj).setText(String.valueOf(number));
				// ������Ʒ������֪ͨActivity������Ҫ���ѵ��ܽ��
				mHandler.sendMessage(mHandler.obtainMessage(10, getTotalPrice()));
			} else if (msg.what == 2) {// ���ĶԻ����е�����
				editText.setText(String.valueOf(number));
			}
		}
	};

	// Button���������
	private final class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.numSub) {
				if (number > 1) {
					number--;
					handler.sendEmptyMessage(2);
				}
			} else if (v.getId() == R.id.numAdd) {
				number++;
				handler.sendEmptyMessage(2);
			}
		}
	}

	// CheckBoxѡ��ı������
	private final class CheckBoxChangedListener implements OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton cb, boolean flag) {
			int position = (Integer) cb.getTag();
			getIsSelected().put(position, flag);
			carBean bean = list.get(position);
			bean.setChoosed(flag);
			mHandler.sendMessage(mHandler.obtainMessage(10, getTotalPrice()));
			// ������е���Ʒȫ����ѡ�У���ȫѡ��ťҲĬ�ϱ�ѡ��
			mHandler.sendMessage(mHandler.obtainMessage(11, isAllSelected()));
		}
	}

	/**
	 * ����ѡ����Ʒ�Ľ��
	 * 
	 * @return ������Ҫ���ѵ��ܽ��
	 */
	private float getTotalPrice() {
		carBean bean = null;
		float totalPrice = 0;
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			if (bean.isChoosed()) {
				totalPrice += bean.getNumber() * bean.getPrice();
			}
		}
		return totalPrice;
	}

	/**
	 * �ж��Ƿ��ﳵ�����е���Ʒȫ����ѡ��
	 * 
	 * @return true������Ŀȫ����ѡ�� false������Ŀû�б�ѡ��
	 */
	private boolean isAllSelected() {
		boolean flag = true;
		for (int i = 0; i < list.size(); i++) {
			if (!getIsSelected().get(i)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
