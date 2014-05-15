package com.jakartans.activities;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jakartans.R;
import com.jakartans.beans.Status;
import com.jakartans.beans.User;
import com.luthfihariz.utilities.Api;
import com.luthfihariz.utilities.Helper;

public class TrayekMapActivity extends SherlockFragmentActivity {

	private GoogleMap googleMap;
	private SupportMapFragment supportMapFragment;
	private String hackjackId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_trayek_map);
		setSupportProgressBarIndeterminateVisibility(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.ic_jakartans_with_text);
		actionBar.setDisplayShowTitleEnabled(false);

		supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(
				R.id.trayek_map);
		googleMap = supportMapFragment.getMap();
		googleMap.setMyLocationEnabled(true);
		LatLng latLng = new LatLng(-6.222281, 106.847996);
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12),
				2000, null);

		Helper.log("after intent : "+getIntent().getStringExtra("hackjackId"));
		
		LoadStatusTask loadTask = new LoadStatusTask();		
		loadTask.execute("S.P21");
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
						addMarkerToMap(statuses);
					}
				} catch (JSONException e) {
					Helper.log("err : " + e.getMessage());
				}
			}
		}
	}

	private void addMarkerToMap(ArrayList<com.jakartans.beans.Status> statuses) {
		MarkerOptions options;
		Status status;
		LatLng loc;
		for (int i = 0; i < statuses.size(); i++) {			
			status = statuses.get(i);
			loc = new LatLng(-6.222281, 106.847996);
			options = new MarkerOptions();
			options.position(loc);
			Helper.log("add marker : "+status.getLatitude()+" , "+status.getLongitude());
			googleMap.addMarker(options);
		}

	}
}
