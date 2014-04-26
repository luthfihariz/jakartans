package com.jakartans.activities;

import com.actionbarsherlock.app.SherlockActivity;
import com.jakartans.R;

import android.os.Bundle;

public class TrayekMapActivity extends SherlockActivity{
			
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trayek_map);
		
//		googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.trayek_map))
//	        .getMap();
//		
//	    // Move the camera instantly to hamburg with a zoom of 15.
//	    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
//
//	    // Zoom in, animating the camera.
//	    googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

	}

}
