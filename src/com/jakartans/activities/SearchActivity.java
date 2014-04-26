package com.jakartans.activities;

import java.io.IOException;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jakartans.R;
import com.luthfihariz.utilities.AlertDialogManager;
import com.luthfihariz.utilities.Api;
import com.luthfihariz.utilities.Helper;
import com.luthfihariz.utilities.SessionManager;

public class SearchActivity extends SherlockActivity {

	AlertDialogManager alertDialogManager;
	LayoutInflater layoutInflater;
	View view;

	private AutoCompleteTextView fromEdit, toEdit;
	private Button searchBtn;
	private ArrayAdapter<String> adapter;

	SeekBar seekBarJamming;
	SeekBar seekBarBus;
	SeekBar seekBarAvailibility;
	AutoCompleteTextView etNameTrayek;
	EditText etUpdateInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.ic_jakartans_with_text);
		actionBar.setDisplayShowTitleEnabled(false);

		fromEdit = (AutoCompleteTextView) findViewById(R.id.et_from);
		toEdit = (AutoCompleteTextView) findViewById(R.id.et_destination);
		searchBtn = (Button) findViewById(R.id.btn_search);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				Helper.getAllRoutes(this));
		fromEdit.setAdapter(adapter);
		fromEdit.setThreshold(2);
		toEdit.setAdapter(adapter);
		toEdit.setThreshold(2);

		searchBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				goToTrayekList(fromEdit.getText().toString(), toEdit.getText().toString());
			}
		});
	}

	private void goToTrayekList(String from, String to) {
		Intent intent = new Intent(this, TrayekListActivity.class);
		intent.putExtra("from", from);
		intent.putExtra("to", to);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu_profil_and_update_info, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_info:
			toProfileActivity();
			break;
		case R.id.item_update_info:
			showUpdateInformation();
			break;
		default:
			break;
		}
		return true;
	}

	private void toProfileActivity() {
		Intent intent = new Intent(this, ProfilActivity.class);
		startActivity(intent);
	}

	private void showUpdateInformation() {
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.dialog_update_info, null);
		etNameTrayek = (AutoCompleteTextView) view.findViewById(R.id.et_name_trayek);
		seekBarAvailibility = (SeekBar) view.findViewById(R.id.seek_availability);
		seekBarBus = (SeekBar) view.findViewById(R.id.seek_bus);
		seekBarJamming = (SeekBar) view.findViewById(R.id.seek_jamming);
		etUpdateInfo = (EditText) view.findViewById(R.id.et_update_info);
		final TextView tvJam = (TextView) view.findViewById(R.id.tv_jamming_status);
		final TextView tvBus = (TextView) view.findViewById(R.id.tv_bus_status);
		final TextView tvAvail = (TextView) view.findViewById(R.id.tv_avail_status);
		final ImageView ivJam = (ImageView) view.findViewById(R.id.iv_jam_status);
		final ImageView ivBus = (ImageView) view.findViewById(R.id.iv_bus_status);
		final ImageView ivAvail = (ImageView) view.findViewById(R.id.iv_avail_status);

		seekBarJamming.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				switch (progress) {
				case 0:
					tvJam.setText("Cenderung Lancar");
					ivJam.setImageResource(R.drawable.ic_road_conditions_green);
					break;
				case 1:
					tvJam.setText("Cenderung Ramai");
					ivJam.setImageResource(R.drawable.ic_road_conditions_yellow);
					break;
				case 2:
					tvJam.setText("Cenderung Padat");
					ivJam.setImageResource(R.drawable.ic_road_conditions_red);
					break;
				default:
					break;
				}
			}
		});

		seekBarBus.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				switch (progress) {
				case 0:
					tvBus.setText("Sedikit Penumpang");
					ivBus.setImageResource(R.drawable.ic_passenger_density_green);
					break;
				case 1:
					tvBus.setText("Sedang");
					ivBus.setImageResource(R.drawable.ic_passenger_density_yellow);
					break;
				case 2:
					tvBus.setText("Banyak Penumpang");
					ivBus.setImageResource(R.drawable.ic_passenger_density_red);
					break;
				default:
					break;
				}
			}
		});

		seekBarAvailibility.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				switch (progress) {
				case 0:
					tvAvail.setText("Cenderung Banyak");
					ivAvail.setImageResource(R.drawable.ic_vehicle_availability_green);
					break;
				case 1:
					tvAvail.setText("Sedang");
					ivAvail.setImageResource(R.drawable.ic_vehicle_availability_yellow);
					break;
				case 2:
					tvAvail.setText("Cenderung Sedikit");
					ivAvail.setImageResource(R.drawable.ic_vehicle_availability_red);
					break;
				default:
					break;
				}
			}
		});

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				Helper.getNoRoutes(this));
		etNameTrayek.setAdapter(adapter);
		etNameTrayek.setThreshold(1);
		
		alertDialogManager = new AlertDialogManager();
		alertDialogManager.showDialog(SearchActivity.this, "Update Informasi", view,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						UpdateInfoTask updateInfo = new UpdateInfoTask();
						String noTrayek = etNameTrayek.getText().toString().split(":")[0];
						int trafficRate = (seekBarJamming.getProgress() - 3) * -1;
						int busRate = (seekBarBus.getProgress() - 3) * -1;
						int availRate = (seekBarAvailibility.getProgress() - 3) * -1;
						updateInfo.execute(String.valueOf(noTrayek), etUpdateInfo
								.getText().toString(), String.valueOf(trafficRate), String.valueOf(busRate),
								String.valueOf(availRate));

					}
				});
	}

	private class UpdateInfoTask extends AsyncTask<String, Void, JSONObject> {

		private ProgressDialog dialog; 
		
		@Override
		protected void onPreExecute() {
			dialog = Helper.buildProgressDialog(SearchActivity.this, "Please wait.", false);
			dialog.show();
		}
		
		@Override
		protected JSONObject doInBackground(String... params) {
			int userId = new SessionManager(SearchActivity.this).getMemberId();
			try {
				return Api.updateStatus(userId, params[0], params[1], params[2], params[3], params[4], 0.0,
						0.0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			dialog.dismiss();
			if (result != null) {}
		}

	}

}
