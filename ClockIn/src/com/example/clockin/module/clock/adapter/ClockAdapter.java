package com.example.clockin.module.clock.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.clockin.R;
import com.example.clockin.module.clock.entity.ClockModel;
import com.example.clockin.utils.TimeUtils;

public class ClockAdapter extends BaseAdapter {

	public List<ClockModel> lists = new ArrayList<ClockModel>();
	private Context mContext;
	private LayoutInflater mInflater;
	private ClockModel mClock;

	public ClockAdapter(Context mContext, List<ClockModel> lists) {
		this.mContext = mContext;
		this.lists = lists;
		this.mInflater = LayoutInflater.from(mContext);
	}

	public void appendList(List<ClockModel> list) {
		if (!lists.containsAll(list) && list != null && list.size() > 0) {
			lists.addAll(list);
		}
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ClockModel data = lists.get(position);
		convertView = getConvertView(convertView, data);
		convertView.setLongClickable(true);
		return convertView;
	}

	/***
	 * 获取convertView
	 * @param convertView
	 * @param model
	 * @return
	 */
	private View getConvertView(View convertView, ClockModel model) {
		ViewHolder holder = null;
		if (convertView == null|| !(convertView.getTag() instanceof ViewHolder)) {
			convertView = createConvertView();
		}
		holder = (ViewHolder) convertView.getTag();
		holder.txt_in_time.setText("in->"+TimeUtils.getTime(model.getInTime(), new SimpleDateFormat("HH:mm:ss")));
		
		if(model.getOutTime()!=0)
			holder.txt_out_time.setText("out->"+TimeUtils.getTime(model.getOutTime(), new SimpleDateFormat("HH:mm:ss")));
		else
			holder.txt_out_time.setText("");
		
		holder.txt_date.setText(model.getClockDate());
		holder.txt_delay_time.setText(String.valueOf(model.getDelayTime()));
		return convertView;
	}
	
	/***
	 * 创建convertView
	 * @return
	 */
	private View createConvertView() {
		View convertView = mInflater.inflate(R.layout.item_clock, null);
		ViewHolder holder = new ViewHolder();
		holder.txt_in_time = (TextView) convertView.findViewById(R.id.txt_in_time);
		holder.txt_out_time = (TextView) convertView.findViewById(R.id.txt_out_time);
		holder.txt_delay_time = (TextView) convertView.findViewById(R.id.txt_delay_time);
		holder.txt_date = (TextView) convertView.findViewById(R.id.txt_date);
		convertView.setTag(holder);
		return convertView;
	}

	class ViewHolder {
		private TextView txt_in_time;
		private TextView txt_out_time;
		private TextView txt_delay_time;
		private TextView txt_date;
	}

}
