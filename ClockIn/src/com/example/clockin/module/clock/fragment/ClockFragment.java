package com.example.clockin.module.clock.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.clockin.R;
import com.example.clockin.common.activity.CommonAlertDialog;
import com.example.clockin.common.activity.TimePickerDialog;
import com.example.clockin.dao.ClockDao;
import com.example.clockin.module.clock.adapter.ClockAdapter;
import com.example.clockin.module.clock.entity.ClockModel;
import com.example.clockin.utils.ACache;
import com.example.clockin.utils.DeviceInfoUtil;
import com.example.clockin.utils.PreferencesUtils;
import com.example.clockin.utils.SingleLayoutListView;
import com.example.clockin.utils.StringUtils;
import com.example.clockin.utils.TimeUtils;

public class ClockFragment extends Fragment implements OnClickListener {
	private static final String ACACHE = "today";

	private Button btn_clock;
	private TextView txt_clock;
	private TextView txt_top_clock;
	private TextView txt_total;
	private TextView tv_OnTime;
	private ImageView iv_delete;
	private ImageView iv_set;
	private Button btn_month;

	private SingleLayoutListView mListView;
	private ListView monthListView;
	private ClockAdapter mAdapter;
	private List<ClockModel> mList;
	private ClockDao mDao;
	private PopupWindow popWindow;
	private String[] months;
	private String curMonth = "";
	
	private String strOnTime = "09:00" ;
	
	private static final int  TIME_PICKER_DIALOG_CODE = 100 ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_clock, container,false);

		initView(contentView);
		initData();
		return contentView;
	}

	/***
	 * 初始化数据
	 */
	private void initData() {
		mList = new ArrayList<ClockModel>();
		mDao = new ClockDao(getActivity());
		mAdapter = new ClockAdapter(getActivity(), mList);
		mListView.setAdapter(mAdapter);

		strOnTime =PreferencesUtils.getString(getActivity(), "on_time",strOnTime);
		txt_total.setText(String.valueOf(calculateTotal()));
		curMonth = String.valueOf(new Date().getMonth() + 1);
		btn_month.setText(months[new Date().getMonth()]);
		getResult();
	}

	/***
	 * 初始化视图
	 * 
	 * @param v
	 */
	private void initView(View v) {
		btn_clock = (Button) v.findViewById(R.id.btn_clock);
		txt_clock = (TextView) v.findViewById(R.id.txt_clock);
		txt_top_clock = (TextView) v.findViewById(R.id.txt_top_clock);
		txt_total = (TextView) v.findViewById(R.id.txt_total);
		iv_delete = (ImageView) v.findViewById(R.id.iv_delete);
		iv_set = (ImageView) v.findViewById(R.id.iv_set);
		btn_month = (Button) v.findViewById(R.id.btn_month);
		tv_OnTime = (TextView)v.findViewById(R.id.tv_OnTime);
		mListView = (SingleLayoutListView) v.findViewById(R.id.mListView);
		

		btn_clock.setOnClickListener(this);
		txt_clock.setOnClickListener(this);
		txt_top_clock.setOnClickListener(this);
		iv_delete.setOnClickListener(this);
		iv_set.setOnClickListener(this);
		btn_month.setOnClickListener(this);

		btn_clock.setLongClickable(true);
		btn_clock.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				hideViewAnim();				
				return false;
			}
		});

		txt_clock.setLongClickable(true);
		txt_clock.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				hideViewAnim();
				return false;
			}
		});

		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDialog(position);
				return false;
			}
		});
		popWindow = initMonthPopWindow();
	}

	/***
	 * 隐藏Click 动画
	 */
	@SuppressLint("NewApi")
	public void hideViewAnim() {
		// need API12
		btn_clock.animate()
				//
				.alpha(0)
				//
				.y(DeviceInfoUtil.getDeviceHeight(getActivity()) / 2)
				.setDuration(1000)
				// need API 12
				.withStartAction(new Runnable() {
					@Override
					public void run() {
						// Log.e(TAG, "START");
					}
					// need API 16
				}).withEndAction(new Runnable() {

					@Override
					public void run() {
						// Log.e(TAG, "END");
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								btn_clock.setVisibility(View.GONE);
								txt_clock.setVisibility(View.GONE);
							}
						});
					}
				}).start();
	}

	/***
	 * 显示Click 动画
	 */
	@SuppressLint("NewApi")
	public void showViewAnim() {
		// need API12
		btn_clock.setVisibility(View.VISIBLE);
		txt_clock.setVisibility(View.VISIBLE);
		btn_clock.animate()
				.alpha(1)
				.y(DeviceInfoUtil.getDeviceHeight(getActivity()) / 2- btn_clock.getHeight()).setDuration(1000)
				// need API 12
				.withStartAction(new Runnable() {
					@Override
					public void run() {
						// Log.e(TAG, "START");
					}
				}).start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.txt_top_clock:
			showViewAnim();
			/*btn_clock.setVisibility(View.VISIBLE);
			ObjectAnimator oa=ObjectAnimator.ofFloat(btn_clock, "alpha", 0f, 1f);
			oa.setDuration(3000);
			oa.start();*/
			break;
		case R.id.btn_clock:
		case R.id.txt_clock:
			curMonth = String.valueOf(new Date().getMonth() + 1);
			btn_month.setText(months[new Date().getMonth()]);
			if (!TimeUtils.getCurDate().equals(getCacheStr(ACACHE+curMonth))) {
				setCacheStr(ACACHE+curMonth, TimeUtils.getCurDate());
				setInClock();
			} else {
				setOutClock();
			}

			break;
		case R.id.iv_delete:
			showWarning();
			break;
		case R.id.btn_month:
			popWindow.showAsDropDown(btn_month);
			break;
		case R.id.iv_set:
			Intent intent = new Intent(getActivity(), TimePickerDialog.class);
			intent.putExtra("on_time", strOnTime);
			startActivityForResult(intent, TIME_PICKER_DIALOG_CODE);
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case TIME_PICKER_DIALOG_CODE:
				 if(data!=null){
					 Bundle bundle = data.getExtras();
					 strOnTime = bundle.getString("on_time") ;
					 if(!StringUtils.isEmpty(strOnTime)){
						getResult();
					 }
				 }
			break;
		default:
			break;
		}
	}
	
	
	/***
	 * 创建月份选择popupwindow
	 * 
	 * @return
	 */
	public PopupWindow initMonthPopWindow() {
		LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
		View contentView = inflater.inflate(R.layout.pop_list, null, true);
		final PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);

		months = getResources().getStringArray(R.array.list_month);
		monthListView = (ListView) contentView.findViewById(R.id.mListView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.item_month, R.id.tv_month, months);
		monthListView.setAdapter(adapter);

		// 有了这句才可以点击返回（撤销）按钮dismiss()popwindow
		monthListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				btn_month.setText(months[position]);
				curMonth = String.valueOf(position + 1);
				getResult();
				popupWindow.dismiss();
			}
		});

		return popupWindow;
	}

	/***
	 * 弹出删除全部警告框
	 */
	private void showWarning() {
		if (mList.size() > 0) {
			CommonAlertDialog.Builder dlg = new CommonAlertDialog.Builder(
					getActivity());
			dlg.setCancleAble(true);
			dlg.setMessage("确定要删除此月打卡记录吗？")
			.setLeftButton("取消",new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							})
			.setRightButton("确定",new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									mDao.deleteByMonth(curMonth);
									getResult();
									setCacheStr(ACACHE+curMonth, "");
									dialog.dismiss();
								}
			}).create().show();
		}
	}

	/***
	 * 弹出删除单个提示
	 * 
	 * @param position
	 */
	private void showDialog(int position) {
		final AlertDialog dlg = new AlertDialog.Builder(getActivity()).create();
		dlg.show();
		dlg.setCanceledOnTouchOutside(true);
		dlg.setCancelable(true);
		Window window = dlg.getWindow();
		window.setContentView(R.layout.alert_dialog);
		TextView tv_content1 = (TextView) window.findViewById(R.id.tv_content1);
		tv_content1.setText(getString(R.string.delete));
		tv_content1.setTag(position);
		tv_content1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClockModel m = mList.get((int) v.getTag() - 1);
				mDao.deleteByDay(m);
				setCacheStr(ACACHE+curMonth, "");
				getResult();
				dlg.dismiss();
			}
		});
	}

	/***
	 * 计算总迟到时间
	 * 
	 * @return
	 */
	private int calculateTotal() {
		int result = 0;
		for (ClockModel model : mList) {
			result += model.getDelayTime();
		}
		return result;
	}

	/**
	 * 记录上班
	 */
	private void setInClock() {
		ClockModel clock = new ClockModel();
		clock.setInTime(TimeUtils.getCurrentTimeInLong());
		clock.setClockDate(TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy-MM-dd")));
		if (!mList.contains(clock)) {
			mDao.insert(clock);
		}
		getResult();
	}

	/***
	 * 记录下班
	 */
	private void setOutClock() {
		ClockModel clock = new ClockModel();
		clock.setOutTime(TimeUtils.getCurrentTimeInLong());
		clock.setClockDate(TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy-MM-dd")));
		mDao.update(clock);
		getResult();
	}

	/***
	 * 设置准时上班时间
	 * @param onTime
	 */
	private void  setOnTime (String onTime){
		PreferencesUtils.putString(getActivity(), "on_time", onTime);
		int i = 0 ;
		for (ClockModel model : mList) {
			String inTime = TimeUtils.getTime(model.getInTime(),new SimpleDateFormat("HH:mm"));
			int delay = TimeUtils.date2Compare(inTime, onTime);
			model.setDelayTime(delay) ;
			mList.get(i).setDelayTime(delay);
			i++;
		}
	}
	
	/************** 数据库操作 ***********************/
	public void getResult() {
		mList.clear();
		mList.addAll(mDao.getList(curMonth));
		setOnTime(strOnTime);
		mAdapter.notifyDataSetChanged();
		txt_total.setText("Delay: " + String.valueOf(calculateTotal()));
		tv_OnTime.setText("OnTime<"+strOnTime+">");
	}

	/************* 缓存 ***********************/
	public void setCacheStr(String key, String value) {
		ACache.get(getActivity()).put(key, value);
	}

	public String getCacheStr(String key) {
		return ACache.get(getActivity()).getAsString(key);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
