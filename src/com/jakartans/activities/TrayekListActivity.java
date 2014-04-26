package com.jakartans.activities;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jakartans.R;
import com.jakartans.adapter.TrayekListAdapter;
import com.jakartans.beans.Trayek;
import com.luthfihariz.utilities.AlertDialogManager;
import com.luthfihariz.utilities.Api;

public class TrayekListActivity extends SherlockActivity {

	AlertDialogManager alertDialogManager;
	SeekBar seekBarJamming;
	SeekBar seekBarFluent;
	SeekBar seekBarAvailibility;
	EditText etNameTrayek;
	EditText etUpdateInfo;
	View view;
	LayoutInflater layoutInflater;
	
	private String from, to;
	private ListView trayekList;
	private ProgressBar progressBar;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trayek_list);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.ic_jakartans_with_text);
		actionBar.setDisplayShowTitleEnabled(false);
		
		from = getIntent().getStringExtra("from");
		to = getIntent().getStringExtra("to");

		trayekList = (ListView) findViewById(R.id.lv_trayek);
		progressBar = (ProgressBar) findViewById(R.id.trayekProgress);

		SearchTask search = new SearchTask();
		search.execute(from, to);
	}

	private class SearchTask extends AsyncTask<String, Void, JSONObject> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected JSONObject doInBackground(String... params) {
			try {
				return Api.searchTrayek(params[0], params[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			progressBar.setVisibility(View.GONE);
			if (result != null) {
				try {
					ArrayList<Trayek> trayeks = new ArrayList<Trayek>();
					JSONArray response = result.getJSONArray("result");
					for (int i = 0; i < response.length(); i++) {
						JSONObject singleResponse = response.getJSONObject(i);
						Trayek trayek = new Trayek();
						trayek.setHackJackId(singleResponse.getString("hackjackId"));
						trayek.setJenisAngkutan(singleResponse.getString("jenisAngkutan"));
						trayek.setJenisTrayek(singleResponse.getString("jenisTrayek"));
						trayek.setNoTrayek(singleResponse.getString("noTrayek"));
						trayek.setNamaTrayek(singleResponse.getString("namaTrayek"));
						trayek.setTerminal(singleResponse.getString("terminal"));
						trayek.setRuteBerangkat(singleResponse.getString("ruteBerangkat"));
						trayek.setRuteKembali(singleResponse.getString("ruteKembali"));

						JSONObject avgRate = singleResponse.getJSONObject("averageRate");
						if (avgRate != null) {
							trayek.setOverallRate(avgRate.getInt("overall"));
							trayek.setAvgTrafficRate(avgRate.getInt("trafficRate"));
							trayek.setAvgBusRate(avgRate.getInt("bussRate"));
							trayek.setAvgBusAvailRate(avgRate.getInt("bussAvailRate"));
						}
						trayeks.add(trayek);						
					}
					
					TrayekListAdapter adapter = new TrayekListAdapter(TrayekListActivity.this, trayeks);
					trayekList.setAdapter(adapter);
					trayekList.setOnItemClickListener(adapter);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getSupportMenuInflater().inflate(R.menu.menu_profil_and_update_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_info:

			break;
		case R.id.item_update_info:
			final ViewHolder viewHolder;

			if (view == null) {
				viewHolder = new ViewHolder();
				view = layoutInflater.inflate(R.layout.dialog_update_info, null);

				viewHolder.etNameTrayek = (EditText) view.findViewById(R.id.et_name_trayek);
				viewHolder.seekBarAvailibility = (SeekBar) view.findViewById(R.id.seek_availability);
				viewHolder.seekBarFluent = (SeekBar) view.findViewById(R.id.seek_bus);
				viewHolder.seekBarJamming = (SeekBar) view.findViewById(R.id.seek_jamming);
				viewHolder.etUpdateInfo = (EditText) view.findViewById(R.id.et_update_info);

				// alertDialogManager.showDialog(SearchActivity.this,
				// "Perbaruhi Status",view,);

				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}

			break;

		default:
			break;
		}
		return true;
	}*/

	public class ViewHolder {
		SeekBar seekBarJamming;
		SeekBar seekBarFluent;
		SeekBar seekBarAvailibility;
		EditText etNameTrayek;
		EditText etUpdateInfo;
	}
}
