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

public class TrayekFullAdapter extends BaseAdapter {

	private ArrayList<Status> statusTrayek;
	private LayoutInflater inflater;
	private ViewHolder holder;
	
	public TrayekFullAdapter(Context context, ArrayList<Status> statusTrayek) {
		this.statusTrayek = statusTrayek;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return statusTrayek.size();
	}

	@Override
	public Object getItem(int position) {
		return statusTrayek.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Status status = (Status)getItem(position);
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.row_trayek_full, null);
			holder.username = (TextView) view.findViewById(R.id.tv_username);
			holder.status = (TextView) view.findViewById(R.id.tv_status);
			holder.place = (TextView) view.findViewById(R.id.tv_place);
			holder.time = (TextView) view.findViewById(R.id.tv_time);
			view.setTag(holder);
		
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		holder.username.setText(status.getUser().getUsername()); // username
		holder.status.setText(status.getStatus());
		holder.place.setText(" at Jakarta"); // place
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
