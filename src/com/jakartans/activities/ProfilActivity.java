package com.jakartans.activities;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Window;
import com.jakartans.R;
import com.jakartans.adapter.ProfilHistoryAdapter;
import com.jakartans.beans.Status;
import com.jakartans.beans.User;
import com.luthfihariz.utilities.Api;
import com.luthfihariz.utilities.SessionManager;

public class ProfilActivity extends SherlockActivity {

	private TextView pointView;
	private ImageView photoView;
	private ListView statusList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil);
		setSupportProgressBarIndeterminateVisibility(true);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.ic_jakartans_with_text);
		actionBar.setDisplayShowTitleEnabled(false);
		
		pointView = (TextView) findViewById(R.id.tv_point);
		statusList = (ListView) findViewById(R.id.lv_history_user);
		
		LoadStatus load = new LoadStatus();
		load.execute(new SessionManager(this).getMemberId());
	}

	private class LoadStatus extends AsyncTask<Integer, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Integer... params) {
			try {
				return Api.getUserProfile(params[0]);
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
					JSONObject user = result.getJSONObject("user");
					int points = user.getInt("points");
					pointView.setText("Points : " + points);
					ArrayList<com.jakartans.beans.Status> statuses = new ArrayList<com.jakartans.beans.Status>();
					JSONArray statusArr = user.getJSONArray("status");
					for (int i = 0; i < statusArr.length(); i++) {
						JSONObject statusJs = statusArr.getJSONObject(i);
						com.jakartans.beans.Status status = new com.jakartans.beans.Status();
						status.setStatus(statusJs.getString("status"));
						status.setTimeStampDate(statusJs.getString("published_date"));
						User userObj = new User();
						userObj.setUsername(statusJs.getString("trayek_id"));
						status.setUser(userObj);
						statuses.add(status);						
					}
					ProfilHistoryAdapter adapter = new ProfilHistoryAdapter(ProfilActivity.this, statuses);
					statusList.setAdapter(adapter);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * 
	 * // Inflate the menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.profil, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */

}
