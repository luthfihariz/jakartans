package com.jakartans.activities;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.jakartans.R;

public class TrayekFullActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trayek_full);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.update_status, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return super.onOptionsItemSelected(item);
	}*/

}
