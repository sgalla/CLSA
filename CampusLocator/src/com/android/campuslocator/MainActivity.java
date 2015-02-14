package com.android.campuslocator;

import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MainActivity extends FragmentActivity{

	private SupportMapFragment mapFragment;
	private GoogleMap map;
	public Location loc;
	Marker location;
	String latitude;
	String longitude;
	double lat, longi;
	Intent intent = getIntent();
	
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
		case R.id.help:
			Intent i2 = new Intent(this, HelpPage.class);
			startActivity(i2);
			return true;	
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == 0 && data != null)
		{
			latitude = data.getStringExtra("latitude");
			longitude = data.getStringExtra("longitude");

			Toast.makeText(this, "UNM Main Campus", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
		map = mapFragment.getMap();
		map.getUiSettings().setZoomControlsEnabled(true);
		
		while (map.equals(null))
			map = mapFragment.getMap();
		    
		LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0.2f, new LocationListener() {
				
				@Override
				public void onLocationChanged(Location arg0) {
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arg0.getLatitude(), arg0.getLongitude()), 15));

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
				Toast.makeText(this, "UNM Main Campus", Toast.LENGTH_LONG).show();
			} 
			else {
				Toast.makeText(this, "Map failed! Please restart your device"
						+ "or see our Help page", Toast.LENGTH_LONG).show();
			}
		} 
		else {
			Toast.makeText(this, "Map failed! Please restart your device"
						+ "or see our Help page", Toast.LENGTH_LONG).show();
		}

		
	}
	public static double convertStringToDouble (String arg) {
		double aDouble = Double.parseDouble(arg);
		return aDouble;
	}
	
}