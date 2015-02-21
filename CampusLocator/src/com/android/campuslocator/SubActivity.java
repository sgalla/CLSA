package com.android.campuslocator;

import java.util.ArrayList;

import com.android.campuslocator.R;
import com.android.campuslocator.TXTAdapter;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class SubActivity extends Activity{
	private TXTAdapter adapter;
	private EditText inputSearch;

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
	    			if (!adapter.equals(null))
	    				adapter.sortAlphabetically();
	    			break;
	    		case R.id.radioNumerically:
	    			if (!adapter.equals(null))
	    				adapter.sortNumerically();
	    			break;
	    		}
	    	}
	    });
	   
	    // create object adapter from TXTAdapter class
	    
	    // get id of the listview we are using (only one)
	    ListView listview = (ListView)findViewById(R.id.listView1);
	    
	    
	    //get search
	    inputSearch = (EditText) findViewById(R.id.inputSearch);
	    
	    // -1 value is dummy, constructor of TXTAdapter required it
	    adapter = new TXTAdapter(this, -1);
	    listview.setAdapter(adapter);
	    
	    // on item click what do we want to do?? 
	    /* in theory we want to send the lat and long coordinates to the 
	     * map methods to display the location on the map.
	     */
	    listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 
				// TODO Auto-generated method stub
				// set up an intent to pass data when item from row is clicked
				
				Intent intent = new Intent(SubActivity.this, MainActivity.class);
				
				//intent.putExtra("latitude", AssetsReader.getLatitude());
				//intent.putExtra("longitude", AssetsReader.getLongitude());
				//setResult(RESULT_OK, intent);
				startActivityForResult(intent, 0);

			}
			
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    });
	    
	    
	}
}
