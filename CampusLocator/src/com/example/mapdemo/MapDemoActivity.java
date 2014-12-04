package com.example.mapdemo;

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
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapDemoActivity extends FragmentActivity{
		//GooglePlayServicesClient.ConnectionCallbacks,
		//GooglePlayServicesClient.OnConnectionFailedListener,
		//LocationListener{

	private SupportMapFragment mapFragment;
	private GoogleMap map;
	private LocationClient mLocationClient;
	public Location loc;
	static final LatLng Hamburg = new LatLng(53.558, 9.927);
	static final LatLng Kiel = new LatLng(53.551, 9.993);
	Marker location;
	/*
	 * Define a request code to send to Google Play services This code is
	 * returned in Activity.onActivityResult
	 */
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_demo_activity);
	    //mLocationClient = new LocationClient(this, this, this);
		mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
		map = mapFragment.getMap();
		
		LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long)100, 0.2f, new LocationListener() {
				
				@Override
				public void onLocationChanged(Location arg0) {
					//location = map.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude()))
					//		.title("Hamburg"));
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
				Toast.makeText(this, "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();
				//Marker location = map.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude()))
				//		.title("Hamburg"));
				//Marker kiel = map.addMarker(new MarkerOptions().position(Kiel)
				//		.title("kiel").snippet("kiel is cool").icon(BitmapDescriptorFactory
				//				.fromResource(R.drawable.ic_launcher)));
				//map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 15));

				//map.setMyLocationEnabled(true);
			} else {
				Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
		}

	}

	
//	// * Called when the Activity becomes visible.
///*	 
//	@Override
//	protected void onStart() {
//		System.out.println("on start");
//		super.onStart();
//		// Connect the client.
//		if (isGooglePlayServicesAvailable()) {
//			mLocationClient.connect();
//		}
//
//	}
//
//	
//	// * Called when the Activity is no longer visible.
//	 
//	@Override
//	protected void onStop() {
//		System.out.println("on stop");
//		// Disconnecting the client invalidates it.
//		mLocationClient.disconnect();
//		super.onStop();
//	}
//
//	
//	// * Handle results returned to the FragmentActivity by Google Play services
//	 
//	/*@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		System.out.println("on activity result");
//		// Decide what to do based on the original request code
//		switch (requestCode) {
//
//		case CONNECTION_FAILURE_RESOLUTION_REQUEST:
//			
//			// * If the result code is Activity.RESULT_OK, try to connect again
//			 
//			switch (resultCode) {
//			case Activity.RESULT_OK:
//				mLocationClient.connect();
//				break;
//			}
//
//		}
//	}*/
//
//	private boolean isGooglePlayServicesAvailable() {
//		System.out.println("is google play services available");
//		// Check that Google Play services is available
//		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
//		// If Google Play services is available
//		if (ConnectionResult.SUCCESS == resultCode) {
//			// In debug mode, log the status
//			Log.d("Location Updates", "Google Play services is available.");
//			return true;
//		} else {
//			// Get the error dialog from Google Play services
//			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this,
//					CONNECTION_FAILURE_RESOLUTION_REQUEST);
//
//			// If Google Play services can provide an error dialog
//			if (errorDialog != null) {
//				// Create a new DialogFragment for the error dialog
//				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
//				errorFragment.setDialog(errorDialog);
//				errorFragment.show(getSupportFragmentManager(), "Location Updates");
//			}
//
//			return false;
//		}
//	}
//
//	
////	 * Called by Location Services when the request to connect the client
////	 * finishes successfully. At this point, you can request the current
////	 * location or start periodic updates
//	 
//	@Override
//	public void onConnected(Bundle dataBundle) {
//		System.out.println("on connected");
//		// Display the connection status
//		//if (location != null) {
//			//while (mLocationClient.getLastLocation().equals(null));
//		    //loc = mLocationClient.getLastLocation();
//			Toast.makeText(this, "GPS location was found!", Toast.LENGTH_SHORT).show();
//		
//			//	LatLng zimmerman = new LatLng(35.085, -106.621);
//		//	Marker zimmermanMarker = map.addMarker(new MarkerOptions().position(zimmerman)
//		//			.title("Hamburg"));
//		//	CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(zimmerman, 15);
//		//	map.animateCamera(cameraUpdate);
//		//} else {
//		//	Toast.makeText(this, "Current location was null, enable GPS on emulator!", Toast.LENGTH_SHORT).show();
//		//}
//	}
//
//	
//	// * Called by Location Services if the connection to the location client
//	 //* drops because of an error.
//	 
//	@Override
//	public void onDisconnected() {
//		System.out.println("on disconnected");
//		// Display the connection status
//		Toast.makeText(this, "Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
//	}
//
//	
////	 * Called by Location Services if the attempt to Location Services fails.
//	 
//	@Override
//	public void onConnectionFailed(ConnectionResult connectionResult) {
//	//	 * Google Play services can resolve some errors it detects. If the error
//	//	 * has a resolution, try sending an Intent to start a Google Play
//	//	 * services activity that can resolve error.
//		 
//		System.out.println("on connection failed");
//		if (connectionResult.hasResolution()) {
//			try {
//				// Start an Activity that tries to resolve the error
//				connectionResult.startResolutionForResult(this,
//						CONNECTION_FAILURE_RESOLUTION_REQUEST);
//				
//			//	 * Thrown if Google Play services canceled the original
//			//	 * PendingIntent
//				 
//			} catch (IntentSender.SendIntentException e) {
//				// Log the error
//				e.printStackTrace();
//			}
//		} else {
//			Toast.makeText(getApplicationContext(),
//					"Sorry. Location services not available to you", Toast.LENGTH_LONG).show();
//		}
//	}
//
//	// Define a DialogFragment that displays the error dialog
//	public static class ErrorDialogFragment extends DialogFragment {
//
//		// Global field to contain the error dialog
//		private Dialog mDialog;
//
//		// Default constructor. Sets the dialog field to null
//		public ErrorDialogFragment() {
//			super();
//			mDialog = null;
//		}
//
//		// Set the dialog to display
//		public void setDialog(Dialog dialog) {
//			mDialog = dialog;
//		}
//
//		// Return a Dialog to the DialogFragment.
//		@Override
//		public Dialog onCreateDialog(Bundle savedInstanceState) {
//			return mDialog;
//		}
//	}

}