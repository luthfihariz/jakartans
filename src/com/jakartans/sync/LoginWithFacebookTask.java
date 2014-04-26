package com.jakartans.sync;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.luthfihariz.utilities.Api;
import com.luthfihariz.utilities.OnAsyncTaskCompleted;

public class LoginWithFacebookTask extends AsyncTask<String, Void, JSONObject> {

	private OnAsyncTaskCompleted completeListener;

	public LoginWithFacebookTask(OnAsyncTaskCompleted completeListener) {
		this.completeListener = completeListener;
	}

	@Override
	protected JSONObject doInBackground(String... params) {
		try {
			return Api.loginWithFacebook(params[0], params[1], params[2], params[3], params[4], params[5],
					params[6], params[7]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		if (result != null) {
			try {
				boolean status = result.getBoolean("status");
				if(status){
					int userId = result.getJSONObject("user").getInt("id");
					completeListener.onCompleted(status, userId);
				}				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
