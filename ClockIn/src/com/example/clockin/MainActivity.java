package com.example.clockin;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {

	// TabSpec 标签
	private final String TAB_HOME = "tab_home";
	private final String TAB_CONTECT = "tab_contect";
	private final String TAB_SETTING = "tab_setting";
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec(TAB_HOME).setIndicator("打卡")
				.setContent(R.id.fragment_home));
		tabHost.addTab(tabHost.newTabSpec(TAB_CONTECT).setIndicator("通讯录")
				.setContent(R.id.fragment_cardcase));
		tabHost.addTab(tabHost.newTabSpec(TAB_SETTING).setIndicator("设置")
				.setContent(R.id.fragment_more));

		((RadioButton) findViewById(R.id.rb_home))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.rb_cont))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.rb_setting))
				.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(CompoundButton v, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			switch (v.getId()) {
			case R.id.rb_home:
				tabHost.setCurrentTabByTag(TAB_HOME);
				break;
			case R.id.rb_cont:
				tabHost.setCurrentTabByTag(TAB_CONTECT);
				break;
			case R.id.rb_setting:
				tabHost.setCurrentTabByTag(TAB_SETTING);
				break;
			default:
				break;
			}
		}
	}
	
	
/*	@Override
	public boolean dispatchTouchEvent(MotionEvent event)
	{
		// TODO Auto-generated method stub
		for ( MyOntouchListener listener : touchListeners )
		{
			listener.onTouchEvent( event );
		}
		return super.onTouchEvent( event );
	}

	private ArrayList<MyOntouchListener> touchListeners = new ArrayList<MainActivity.MyOntouchListener>();


	public void registerListener(MyOntouchListener listener)
	{
		touchListeners.add( listener );
	}


	public void unRegisterListener(MyOntouchListener listener)
	{
		touchListeners.remove( listener );
	}

	public interface MyOntouchListener
	{
		public void onTouchEvent(MotionEvent event);
	}*/
	

	private boolean exitApp = false;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (exitApp) {
				finish();
				//android.os.Process.killProcess(android.os.Process.myPid());
			} else {
				exitApp = true;
				Toast.makeText(this, "再按一次返回鍵退出", Toast.LENGTH_SHORT).show();
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						exitApp = false;
					}
				}, 2000);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
