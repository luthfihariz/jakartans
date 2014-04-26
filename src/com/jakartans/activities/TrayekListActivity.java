package com.jakartans.activities;

import com.actionbarsherlock.app.SherlockActivity;
import com.jakartans.R;
import com.jakartans.activities.SearchActivity.ViewHolder;
import com.luthfihariz.utilities.AlertDialogManager;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.os.Build;

public class TrayekListActivity extends SherlockActivity {

	AlertDialogManager alertDialogManager;
	SeekBar seekBarJamming;
	SeekBar seekBarFluent;
	SeekBar seekBarAvailibility;
	EditText etNameTrayek;
	EditText etUpdateInfo;
	View view;
	LayoutInflater layoutInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trayek_list);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.menu_profil_and_update_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_info:
			
			break;
		case R.id.item_update_info:
			
			view = layoutInflater.inflate(R.layout.dialog_update_info, null);
			
			etNameTrayek = (EditText)view.findViewById(R.id.et_name_trayek);
			seekBarAvailibility = (SeekBar)view.findViewById(R.id.seek_availability);
			seekBarFluent = (SeekBar)view.findViewById(R.id.seek_fluent);
			seekBarJamming = (SeekBar)view.findViewById(R.id.seek_jamming);
			etUpdateInfo = (EditText)view.findViewById(R.id.et_update_info);
			
			//alertDialogManager.showDialog(TrayekListActivity.this, "Perbaruhi Status",view,) {
				
			break;

		default:
			break;
		}
		return true;
	}
}
