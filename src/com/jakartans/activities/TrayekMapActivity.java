package com.jakartans.activities;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.jakartans.R;

import android.os.Bundle;

public class TrayekMapActivity extends SherlockFragmentActivity{
				
	private GoogleMap googleMap;
	private SupportMapFragment supportMapFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trayek_map);
		
		supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.trayek_map);
		googleMap = supportMapFragment.getMap();
		
		googleMap.setMyLocationEnabled(true);

	}

}
