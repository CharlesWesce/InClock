package com.example.clockin.common.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.clockin.R;
import com.example.clockin.module.clock.fragment.ClockFragment;
import com.example.clockin.wedget.timepicker.PickerView;
import com.example.clockin.wedget.timepicker.PickerView.onSelectListener;

public class TimePickerDialog extends Activity {
	private static final int TIME_PICKER_DIALOG_CODE = 100;

	PickerView pv_hour;
	PickerView pv_minute;

	private Button btn_confirm;
	private String strOnTime = "09:00";

	private String strHour = "09";
	private String strMinute = "00";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_time_picker);
		
		initView();
		initData();
		initListener();
	}

	private void initView() {
		// 重新设置窗口铺满屏幕
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		getWindow().setAttributes(lp);

		pv_hour = (PickerView) findViewById(R.id.pv_hour);
		pv_minute = (PickerView) findViewById(R.id.pv_minute);
		btn_confirm = (Button) findViewById(R.id.btn_confirm);
	}

	private void initData( ) {
		List<String> data = new ArrayList<String>();
		List<String> seconds = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			data.add(i < 10 ? "0" + i : "" + i);
		}
		for (int i = 0; i < 60; i++) {
			seconds.add(i < 10 ? "0" + i : "" + i);
		}
		
		pv_hour.setData(data);
		pv_minute.setData(seconds);
		
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			strOnTime = bundle.getString("on_time");
			String s[] = strOnTime.split(":") ;
			strHour = s[0];
			strMinute = s[1];
			pv_hour.setSelected(s[0]);
			pv_minute.setSelected(s[1]);
		} else {
			pv_hour.setSelected(9);
			pv_minute.setSelected(0);
		}
	}
	
	private void initListener(){
		pv_hour.setOnSelectListener(new onSelectListener() {

			@Override
			public void onSelect(String text) {
				strHour = text;
				// Toast.makeText(TimePickerDialog.this, "选择了 " + text +" 分",Toast.LENGTH_SHORT).show();
			}
		});
		
		pv_minute.setOnSelectListener(new onSelectListener() {

			@Override
			public void onSelect(String text) {
				strMinute = text;
				// Toast.makeText(TimePickerDialog.this, "选择了 " + text +" 秒",Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void onClickConfirm(View v) {
		Intent intent = new Intent(TimePickerDialog.this, ClockFragment.class);
		String on_time = strHour + ":" + strMinute;
		intent.putExtra("on_time", on_time);
		setResult(TIME_PICKER_DIALOG_CODE, intent);
		finish();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN&& isOutOfBounds(TimePickerDialog.this, event)) {
			this.finish();
			return true;
		}
		return super.onTouchEvent(event);
	}

	private boolean isOutOfBounds(Activity context, MotionEvent event) {
		final int x = (int) event.getX();
		final int y = (int) event.getY();
		final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
		final View decorView = context.getWindow().getDecorView();
		return (x < -slop) || (y < -slop)|| (x > (decorView.getWidth() + slop))|| (y > (decorView.getHeight() + slop));
	}
}
