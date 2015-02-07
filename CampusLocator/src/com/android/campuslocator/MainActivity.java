package com.android.campuslocator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.*;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;

public class MainActivity extends FragmentActivity{

	private SupportMapFragment mapFragment;
	private GoogleMap map;
	public Location loc;
	Marker location;
	String latitude;
	String longitude;

	
	//MapView mapView = (MapView) findViewById(R.id.mapView);
	//mapView.setBuiltInZoomControls(true);
	
	/*
	 * Define a request code to send to Google Play services This code is
	 * returned in Activity.onActivityResult
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
		case R.id.menu_button:
			Intent i = new Intent(this, SubActivity.class);
			startActivityForResult(i, 100);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
		map = mapFragment.getMap();
		
		// bundle and intent used to change camera location if user has selected coordinates from list
		Bundle intent = getIntent().getExtras();
		//if (intent != null && intent.getString("latitude") != null && intent.getString("longitude") != null) {
		if (intent != null) {
			double lat, longi;
			
			latitude = intent.getString("latitude");
			longitude = intent.getString("longitude");
			
			lat = convertStringToDouble(latitude);
			longi = convertStringToDouble(longitude);
			
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, longi), 10);
			map.animateCamera(cameraUpdate);
			
			LatLng buildingSelected = new LatLng(lat, longi);
			map.addMarker(new MarkerOptions()
					.position(buildingSelected)
					.title("the title")
					.snippet("here is a snippet"));
			
		}

		
		while (map.equals(null))
			map = mapFragment.getMap();
		
		LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long)100, 0.2f, new LocationListener() {
				
				@Override
				public void onLocationChanged(Location arg0) {
					//map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arg0.getLatitude(), arg0.getLongitude()), 15));

				}

				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onStatusChanged(String provider, int status,
						Bundle extras) {
					// TODO Auto-generated method stub
					
				}
			});
		
		
		map.setMyLocationEnabled(true);
		if (mapFragment != null) {
			map = mapFragment.getMap();
			if (map != null) {
				Toast.makeText(this, "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();
			} 
			else {
				Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
			}
		} 
		else {
			Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
		}

		
	}
	public static double convertStringToDouble (String arg) {
		double aDouble = Double.parseDouble(arg);
		return aDouble;
	}
	
}