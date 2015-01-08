package com.android.campuslocator;

/*
 * class AssetReader used for abqbuildings.txt. needs to be modified to support all
 * text files we will be using (easy task). all objects set as string type.
 * perhaps change this rather than converting later on??
 */

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

public class AssetsReader {
	
	public String title;
	public String latitude;
	public String longitude;
	public String buildingNum;

	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	
}
