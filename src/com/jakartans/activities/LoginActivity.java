package com.jakartans.activities;

import java.util.Arrays;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.jakartans.R;
import com.jakartans.sync.LoginWithFacebookTask;
import com.luthfihariz.utilities.Helper;
import com.luthfihariz.utilities.OnAsyncTaskCompleted;
import com.luthfihariz.utilities.SessionManager;

public class LoginActivity extends Activity {

	private LoginButton fbLoginButton;
	private UiLifecycleHelper uiHelper;
	private SessionManager session;

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		session = new SessionManager(this);
		//session.clearSession();
		if (session.isLoggedIn())
			gotoSearchActivity();

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		fbLoginButton = (LoginButton) findViewById(R.id.fb_login_button);
		fbLoginButton.setReadPermissions(Arrays.asList("basic_info", "email", "user_hometown",
				"user_birthday"));

	}

	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		if (state.isOpened()) {
			Helper.log("Logged in...");
			requestForUserProfile(session);
		}
	}

	private void requestForUserProfile(Session session) {
		Request meRequest = Request.newMeRequest(session, new Request.GraphUserCallback() {
			@Override
			public void onCompleted(GraphUser user, Response response) {
				if (response != null) {
					sendUserProfile(user);
				}
			}
		});
		meRequest.executeAsync();
	}

	private void sendUserProfile(final GraphUser user) {
		String city = "", country = "";
		try {
			String[] hometown = user.getInnerJSONObject().getJSONObject("hometown").getString("name")
					.split(",");
			city = hometown[0].trim();
			country = hometown[1].trim();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		LoginWithFacebookTask loginTask = new LoginWithFacebookTask(new OnAsyncTaskCompleted() {

			@Override
			public void onCompleted(boolean status, Object... objects) {
				if (status) {
					Helper.log("user registered. id :" + (Integer) objects[0]);
					session.createSession((Integer) objects[0], user.getUsername());
					gotoSearchActivity();
				}
			}
		});

		loginTask.execute(user.getUsername(), user.getProperty("email").toString(), user.getBirthday(), city,
				country, user.getProperty("gender").toString(), "4.0", "");
	}

	@Override
	protected void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	private void gotoSearchActivity() {		
		Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
		finish();
		startActivity(intent);
	}
}
