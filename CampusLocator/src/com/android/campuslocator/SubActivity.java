package com.android.campuslocator;

import java.util.ArrayList;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class SubActivity extends Activity{
	
	// create object adapter from TXTAdapter class
	// needs to be declared global to entire class 
    TXTAdapter adapter;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sub_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
		case R.id.search_action:
			//CODE HERE FOR SEARCH ON CLICK OF SEARCH ICON

			return true;
		case R.id.help:
			//CODE HERE FOR HELP TOOLTIP WHEN HELP ICON CLICKED
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    setContentView(R.layout.sub_activity);
	    
	    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
	    radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
	    {
	    	public void onCheckedChanged(RadioGroup group, int checkedId)
	    	{
	    		switch(checkedId)
	    		{
	    		case R.id.radioAlphabetically:
	    			//CALL METHOD TO SORT LIST ALPHABETICALLY BY NAME
	    		case R.id.radioNumerically:
	    			//CALL METHOD TO SORT LIST NUMERICALLY
	    			//
	    		}
	    	}
	    });
	    
	    // get id of the listview we are using (only one)
	    ListView listview = (ListView)findViewById(R.id.listView1);
	    
	    // -1 value is dummy, constructor of TXTAdapter required it
	    adapter = new TXTAdapter(this, -1);
	    listview.setAdapter(adapter);
	    
	    // on item click what do we want to do?? 
	    /* in theory we want to send the lat and long coordinates to the 
	     * map methods to display the location on the map.
	     */
	    listview.setOnItemClickListener(new OnItemClickListener() {
	    AssetsReader AR = new AssetsReader();
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 
				// TODO Auto-generated method stub
				// set up an intent to pass data when item from row is clicked
				
				Intent intent = new Intent(SubActivity.this, MainActivity.class);
				
				Toast.makeText(SubActivity.this, adapter.getItem(arg2).getLatitude(), Toast.LENGTH_LONG).show();
				Toast.makeText(SubActivity.this, adapter.getItem(arg2).getLongitude(), Toast.LENGTH_LONG).show();
				
				intent.putExtra("latitude", adapter.getItem(arg2).getLatitude());
				intent.putExtra("longitude", adapter.getItem(arg2).getLongitude());
				//intent.putExtra("latitude", AR.getLatitude());
				//intent.putExtra("longitude", AR.getLongitude());

				startActivity(intent);

			}
			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    });
	    	
	    	
	    	
	    };
	    
	    
	}
