package com.jakartans.adapter;

import java.util.ArrayList;

import com.jakartans.R;
import com.jakartans.beans.Trayek;
import com.luthfihariz.utilities.Helper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrayekListAdapter extends BaseAdapter implements OnItemClickListener{

	private ArrayList<Trayek> trayeks;
	private ViewHolder holder;
	private LayoutInflater inflater;
	
	public TrayekListAdapter(Context context, ArrayList<Trayek> trayeks) {
		this.trayeks = trayeks;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {		
		return trayeks.size();
	}

	@Override
	public Object getItem(int position) {		
		return trayeks.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {		
		Trayek trayek = (Trayek) getItem(position);
		if(view == null){
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.row_trayek, null);
			holder.jenisTrayek = (TextView) view.findViewById(R.id.tv_name_trayek);
			holder.noTrayek = (TextView) view.findViewById(R.id.tv_no_trayek);
			holder.ruteTrayek = (TextView) view.findViewById(R.id.tv_rute_trayek);
			holder.overallRating = (TextView) view.findViewById(R.id.tv_overall_rating);
			holder.iconType = (ImageView) view.findViewById(R.id.iv_trayek);
			holder.iconTrafficRate = (ImageView) view.findViewById(R.id.iv_fluent);
			holder.iconBusRate = (ImageView) view.findViewById(R.id.iv_jamming);
			holder.iconBusAvailRate = (ImageView) view.findViewById(R.id.iv_availability);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		holder.jenisTrayek.setText(trayek.getJenisTrayek());
		holder.noTrayek.setText(trayek.getNoTrayek());
		holder.ruteTrayek.setText(trayek.getNamaTrayek());
		holder.overallRating.setText(""+trayek.getOverallRate());
		
		return view;
	}	
	
	static class ViewHolder{
		TextView jenisTrayek;
		TextView noTrayek;
		TextView ruteTrayek;
		TextView overallRating;
		ImageView iconType;
		ImageView iconTrafficRate;
		ImageView iconBusRate;
		ImageView iconBusAvailRate;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Helper.log("click");
	}
}
