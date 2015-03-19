package com.breakyourass.net;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class Dressing extends SherlockFragmentActivity{

	ActionBar mSupportActionBar;
	
	ActionBar.Tab Tab1,Tab2;
	Fragment fragmentTab1 = new MensTab();
	Fragment fragmentTab2 = new WomensTab();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_container);
		mSupportActionBar = getSupportActionBar();
		//mSupportActionBar.setBackgroundDrawable(new ColorDrawable(0xff123456));
		mSupportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008000")));
		mSupportActionBar.setDisplayHomeAsUpEnabled(true);
		mSupportActionBar.setTitle("Dressing");
		
		mSupportActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab1 = mSupportActionBar.newTab().setText("Women");
		Tab2 = mSupportActionBar.newTab().setText("Men");
		
		// Set Tab Listeners
        Tab1.setTabListener(new TabListener(fragmentTab1));
        Tab2.setTabListener(new TabListener(fragmentTab2));
        
        mSupportActionBar.addTab(Tab1);
        mSupportActionBar.addTab(Tab2);
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.main, menu);

	    return super.onCreateOptionsMenu(menu);
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	super.onOptionsItemSelected(item);
    	
    	switch(item.getItemId()){
    	
    	case android.R.id.home:
            // app icon in action bar clicked; goto parent activity.
    		Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(backIntent);
            
            break;
           
    	     
    	case R.id.dressing:
    		
    		Intent dressingIntent = new Intent(Dressing.this, Dressing.class);
    		startActivity(dressingIntent);
    		
    		break;
    	
    	case R.id.duas:
    		
    		Intent duasIntent = new Intent(Dressing.this, Duas.class);
    		startActivity(duasIntent);
    		
    		break;
    		
    	case R.id.kids:
    		
    		Intent kidsIntent = new Intent(Dressing.this, KidsSection.class);
    		startActivity(kidsIntent);
    		
    		break;
    		
    	case R.id.pillars:
    		
    		Intent pillarsIntent = new Intent(Dressing.this,Pillars.class);
    		startActivity(pillarsIntent);
    		
    		break;
    		
    	case R.id.prayers:
    		
    		Intent prayersIntent = new Intent(Dressing.this,Praying.class);
    		startActivity(prayersIntent);
    		
    		break;
    		
    	case R.id.ramadan:
    		Intent ramadanIntent = new Intent(Dressing.this,Ramadan.class);
    		startActivity(ramadanIntent);
    		
    		break;
    		
    	case R.id.sunna:
    		
    		Intent sunnaIntent = new Intent(Dressing.this,Sunna.class);
    		startActivity(sunnaIntent);
    		
    		break;
    		
    	case R.id.verses:
    		
    		Intent versesIntent = new Intent(Dressing.this,SplashActivity.class);
    		startActivity(versesIntent);
    		
    		break;
    		
    	case R.id.home:
    		
    		Intent homeIntent = new Intent(Dressing.this,MainActivity.class);
    		startActivity(homeIntent);
    		
    		break;
  

    	}
 
    	return true;
	}
	

}
