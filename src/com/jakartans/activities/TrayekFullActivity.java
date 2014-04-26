package com.jakartans.activities;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.jakartans.R;
import com.jakartans.adapter.TrayekFullAdapter;
import com.jakartans.beans.Status;
import com.jakartans.beans.User;
import com.luthfihariz.utilities.Api;
import com.luthfihariz.utilities.Helper;

public class TrayekFullActivity extends SherlockActivity {

	private TextView tvJenisTrayek;
	private TextView tvNamaTrayek;
	private TextView tvRuteBrgkt;
	private TextView tvRuteKembali;
	private TextView tvJam;
	private TextView tvBus;
	private TextView tvAvail;
	private ImageView ivJenisTrayek;
	private ImageView ivJam;
	private ImageView ivBus;
	private ImageView ivAvail;
	private ListView statusList;
	private String hackjackId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_trayek_full);
		setSupportProgressBarIndeterminateVisibility(true);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.ic_jakartans_with_text);
		actionBar.setDisplayShowTitleEnabled(false);

		tvJenisTrayek = (TextView) findViewById(R.id.tv_username);
		tvNamaTrayek = (TextView) findViewById(R.id.tv_status);
		ivJenisTrayek = (ImageView) findViewById(R.id.iv_trayek);
		ivJam = (ImageView) findViewById(R.id.iv_jam);
		ivBus = (ImageView) findViewById(R.id.iv_bus);
		ivAvail = (ImageView) findViewById(R.id.iv_avail);
		tvJam = (TextView) findViewById(R.id.tv_name_jam);
		tvBus = (TextView) findViewById(R.id.tv_name_bus);
		tvAvail = (TextView) findViewById(R.id.tv_name_avail);
		statusList = (ListView) findViewById(R.id.lv_status);

		tvJenisTrayek.setText(getIntent().getStringExtra("jenisTrayek") + " "
				+ getIntent().getStringExtra("noTrayek"));
		tvNamaTrayek.setText(getIntent().getStringExtra("namaTrayek"));
		/*
		 * tvRuteBrgkt.setText("Rute Berangkat :"+getIntent().getStringExtra(
		 * "ruteBrgkt"));
		 * tvRuteBrgkt.setText("Rute Kembali :"+getIntent().getStringExtra
		 * ("ruteKembali"));
		 */
		
		LoadStatusTask loadTask = new LoadStatusTask();
		loadTask.execute(hackjackId = getIntent().getStringExtra("hackjackId"));

		int trafficRate = getIntent().getIntExtra("trafficRate", 0);
		int busRate = getIntent().getIntExtra("busRate", 0);
		int busAvailRate = getIntent().getIntExtra("busAvailRate", 0);

		switch (trafficRate) {
		case 1:
			tvJam.setText("Cenderung Padat");
			ivJam.setImageResource(R.drawable.ic_road_conditions_red);
			break;
		case 2:
			tvJam.setText("Cenderung Ramai");
			ivJam.setImageResource(R.drawable.ic_road_conditions_yellow);
			break;
		case 3:
			tvJam.setText("Cenderung Lancar");
			ivJam.setImageResource(R.drawable.ic_road_conditions_green);
			break;
		case 0:
			tvJam.setVisibility(View.GONE);
			ivJam.setVisibility(View.GONE);
			break;
		}

		switch (busRate) {
		case 1:
			tvBus.setText("Banyak Penumpang");
			ivBus.setImageResource(R.drawable.ic_passenger_density_red);
			break;
		case 2:
			tvBus.setText("Sedang");
			ivBus.setImageResource(R.drawable.ic_passenger_density_yellow);
			break;
		case 3:
			tvBus.setText("Sedikit Penumpang");
			ivBus.setImageResource(R.drawable.ic_passenger_density_green);
			break;
		default:
			tvBus.setVisibility(View.GONE);
			ivBus.setVisibility(View.GONE);
			break;
		}

		switch (busAvailRate) {
		case 1:
			tvAvail.setText("Cenderung Banyak");
			ivAvail.setImageResource(R.drawable.ic_vehicle_availability_red);
			break;
		case 2:
			tvAvail.setText("Sedang");
			ivAvail.setImageResource(R.drawable.ic_vehicle_availability_yellow);
			break;
		case 3:
			tvAvail.setText("Cenderung Sedikit");
			ivAvail.setImageResource(R.drawable.ic_vehicle_availability_green);
			break;
		default:
			tvAvail.setVisibility(View.GONE);
			ivAvail.setVisibility(View.GONE);
			break;
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_map:
			showMap();
			break;
		default:
			break;
		}
		return true;
	}

	private void showMap() {
		Intent intent = new Intent(this, TrayekMapActivity.class);
		intent.putExtra("hackjakId", hackjackId);
		startActivity(intent);
	}

	private class LoadStatusTask extends AsyncTask<String, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(String... params) {
			try {
				return Api.getSingleRoute(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			setSupportProgressBarIndeterminateVisibility(false);
			if (result != null) {
				try {
					ArrayList<com.jakartans.beans.Status> statuses = new ArrayList<com.jakartans.beans.Status>();
					JSONArray status = result.getJSONObject("result").getJSONArray("status");
					for (int i = 0; i < status.length(); i++) {
						JSONObject singleStatus = status.getJSONObject(i);
						com.jakartans.beans.Status statusObj = new com.jakartans.beans.Status();
						statusObj.setTimeStampDate(singleStatus.getString("published_date"));
						statusObj.setStatus(singleStatus.getString("status"));
						User user = new User();
						user.setUsername(singleStatus.getString("username"));
						statusObj.setUser(user);
						statuses.add(statusObj);			
					}
					TrayekFullAdapter adapter = new TrayekFullAdapter(TrayekFullActivity.this, statuses);
					statusList.setAdapter(adapter);
				} catch (JSONException e) {
					Helper.log("err : "+e.getMessage());
				}
			}
		}

	}
}
