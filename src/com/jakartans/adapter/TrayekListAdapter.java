package com.jakartans.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrayekListAdapter extends BaseAdapter{

	Context mContext;
	LayoutInflater layoutInflater;
	
	public TrayekListAdapter(Context mContext, LayoutInflater layoutInflater) {
		super();
		this.mContext = mContext;
		this.layoutInflater = layoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		
		return null;
	}
	
	private class ViewHolder {
		
	}

}
