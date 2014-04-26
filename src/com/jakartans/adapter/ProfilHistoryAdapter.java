package com.jakartans.adapter;

import java.util.ArrayList;

import com.jakartans.R;
import com.jakartans.beans.Status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilHistoryAdapter extends BaseAdapter {

	private ArrayList<Status> statuse;
	private ViewHolder holder;
	private LayoutInflater inflater;
	
	
	public ProfilHistoryAdapter(Context context, ArrayList<Status> statuse) {
		this.statuse = statuse;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return statuse.size();
	}

	@Override
	public Object getItem(int position) {
		return statuse.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Status status = (Status) getItem(position);
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.row_trayek_full, null);
			holder.username = (TextView) view.findViewById(R.id.tv_username);
			holder.status = (TextView) view.findViewById(R.id.tv_status);
			holder.place = (TextView) view.findViewById(R.id.tv_place);
			holder.time = (TextView) view.findViewById(R.id.tv_time);
			holder.iconType = (ImageView) view.findViewById(R.id.iv_trayek);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.iconType.setVisibility(View.GONE);
		holder.place.setVisibility(View.GONE);
		holder.username.setText("For "+status.getUser().getUsername());
		holder.status.setText(status.getStatus());
		// holder.place.setText(null); // place
		holder.time.setText(status.getTimeStampDate());
		return view;
	}
	
	static class ViewHolder {
		TextView status;
		TextView username;
		TextView place;
		TextView time;
		ImageView iconType;
	}

}
