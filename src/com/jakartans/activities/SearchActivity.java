package com.jakartans.activities;

import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jakartans.R;
import com.luthfihariz.utilities.AlertDialogManager;

public class SearchActivity extends SherlockActivity {

	AlertDialogManager alertDialogManager;
	LayoutInflater layoutInflater;
	View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.search, menu);
		return super.onCreateOptionsMenu(menu);
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
				
				viewHolder.etNameTrayek = (EditText)view.findViewById(R.id.et_name_trayek);
				viewHolder.seekBarAvailibility = (SeekBar)view.findViewById(R.id.seek_availability);
				viewHolder.seekBarFluent = (SeekBar)view.findViewById(R.id.seek_fluent);
				viewHolder.seekBarJamming = (SeekBar)view.findViewById(R.id.seek_jamming);
				viewHolder.etUpdateInfo = (EditText)view.findViewById(R.id.et_update_info);
				
				//alertDialogManager.showDialog(SearchActivity.this, "Perbaruhi Status",view,);
				
				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder)view.getTag();
			}
			
			break;

		default:
			break;
		}
		return true;
	}
	
	public class ViewHolder {
		SeekBar seekBarJamming;
		SeekBar seekBarFluent;
		SeekBar seekBarAvailibility;
		EditText etNameTrayek;
		EditText etUpdateInfo;
	}

}
