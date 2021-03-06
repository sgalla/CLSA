package com.android.campuslocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import android.view.animation.AnimationUtils;

public class LoadingScreen extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1900;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.loading_activity);
        Toast.makeText(this, "Welcome to the CLSA app!", 
        		Toast.LENGTH_SHORT).show();
        
        AnimationUtils.loadAnimation(this, R.anim.anim_translate); //load animation method
        
        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
        	
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(LoadingScreen.this, MenuMain.class);
                LoadingScreen.this.startActivity(mainIntent);
                LoadingScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}