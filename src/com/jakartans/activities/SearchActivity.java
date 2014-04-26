package com.jakartans.activities;

import java.io.IOException;

import org.json.JSONObject;

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
import android.widget.SeekBar;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jakartans.R;
import com.luthfihariz.utilities.AlertDialogManager;
import com.luthfihariz.utilities.Api;
import com.luthfihariz.utilities.Helper;

public class SearchActivity extends SherlockActivity {

	AlertDialogManager alertDialogManager;
	LayoutInflater layoutInflater;
	View view;

	private AutoCompleteTextView fromEdit, toEdit;
	private Button searchBtn;
	private ArrayAdapter<String> adapter;

	SeekBar seekBarJamming;
	SeekBar seekBarFluent;
	SeekBar seekBarAvailibility;
	EditText etNameTrayek;
	EditText etUpdateInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

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

	private void goToTrayekList(String from, String to){
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

			break;
		case R.id.item_update_info:		

			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.dialog_update_info, null);

			etNameTrayek = (EditText) view.findViewById(R.id.et_name_trayek);
			seekBarAvailibility = (SeekBar) view.findViewById(R.id.seek_availability);
			seekBarFluent = (SeekBar) view.findViewById(R.id.seek_fluent);
			seekBarJamming = (SeekBar) view.findViewById(R.id.seek_jamming);
			etUpdateInfo = (EditText) view.findViewById(R.id.et_update_info);
			alertDialogManager = new AlertDialogManager();
			alertDialogManager.showDialog(SearchActivity.this, "Perbaruhi Status", view,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Helper.toastShort(SearchActivity.this, "crot!");
						}
					});
			break;
		default:
			break;
		}
		return true;
	}

}
