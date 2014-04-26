package com.jakartans.adapter;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakartans.R;
import com.jakartans.activities.TrayekFullActivity;
import com.jakartans.beans.Trayek;
import com.luthfihariz.utilities.Helper;

public class TrayekListAdapter extends BaseAdapter implements OnItemClickListener{

	private ArrayList<Trayek> trayeks;
	private ViewHolder holder;
	private LayoutInflater inflater;
	private Activity act;
	
	public TrayekListAdapter(Activity act, ArrayList<Trayek> trayeks) {
		this.trayeks = trayeks;
		this.act = act;
		this.inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			holder.jenisTrayek = (TextView) view.findViewById(R.id.tv_username);
			holder.noTrayek = (TextView) view.findViewById(R.id.tv_place);
			holder.ruteTrayek = (TextView) view.findViewById(R.id.tv_status);
			holder.overallRating = (TextView) view.findViewById(R.id.tv_point);
			holder.iconType = (ImageView) view.findViewById(R.id.iv_trayek);
			holder.iconTrafficRate = (ImageView) view.findViewById(R.id.iv_jam);
			holder.iconBusRate = (ImageView) view.findViewById(R.id.iv_avail);
			holder.iconBusAvailRate = (ImageView) view.findViewById(R.id.iv_bus);
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
		Trayek trayek = (Trayek) getItem(arg2);
		Intent intent = new Intent(act, TrayekFullActivity.class);
		intent.putExtra("jenisTrayek", trayek.getJenisTrayek());
		intent.putExtra("hackjackId", trayek.getHackJackId());
		intent.putExtra("noTrayek", trayek.getNoTrayek());
		intent.putExtra("namaTrayek", trayek.getNamaTrayek());
		intent.putExtra("trafficRate", trayek.getAvgTrafficRate());
		intent.putExtra("busRate", trayek.getAvgBusRate());
		intent.putExtra("busAvailRate", trayek.getAvgBusAvailRate());
		intent.putExtra("ruteBrgkt", trayek.getRuteBerangkat());
		intent.putExtra("ruteKembali", trayek.getRuteKembali());
		act.startActivity(intent);
	}
}
