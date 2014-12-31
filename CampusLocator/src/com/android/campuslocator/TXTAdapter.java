package com.android.campuslocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TXTAdapter extends ArrayAdapter<AssetsReader> {
	Context ctx;

	public TXTAdapter(Context context, int resource) {
		super(context, resource);

		this.ctx = context;
		
		// load the data
		loadArrayFromFile();
		
		
	}
	
	public View getView (final int pos, View convertView, final ViewGroup parent) {
		
		TextView mView = (TextView) convertView;
		
		if(null == mView) {
			mView = new TextView(parent.getContext());
			mView.setTextSize(28);
		}
		
		mView.setText(getItem(pos).getTitle());
		
		return mView;
	}
	
	
	
	private void loadArrayFromFile() {
		
		
		try {
		// get stream and buffer reader for file
			InputStream IN = ctx.getAssets().open("abqbuildings.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(IN));
			String line;
		
		// read each line
			while ((line = reader.readLine()) != null) {
			
			// split to separate the attributes in text file
				String[] RowData = line.split(",");
			
			// create objects for rowdata
				AssetsReader current = new AssetsReader();
				current.setTitle(RowData[0]); // grab the title from file
				current.setBuildingNum(RowData[1]); // grab the building num
				current.setLatitude(RowData[2]); // grab lat
				current.setLongitude(RowData[3]); // grab long
			
			// add object to array list
				this.add(current);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
