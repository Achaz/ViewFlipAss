package com.breakyourass.net;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class Praying extends SherlockActivity{

	ActionBar mSupportActionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prayers);
		mSupportActionBar = getSupportActionBar();
		//mSupportActionBar.setBackgroundDrawable(new ColorDrawable(0xff123456));
		mSupportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008000")));
		mSupportActionBar.setDisplayHomeAsUpEnabled(true);
		mSupportActionBar.setTitle("Prayers");
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
    		
    		Intent dressingIntent = new Intent(Praying.this, Dressing.class);
    		startActivity(dressingIntent);
    		
    		break;
    	
    	case R.id.duas:
    		
    		Intent duasIntent = new Intent(Praying.this, Duas.class);
    		startActivity(duasIntent);
    		
    		break;
    		
    	case R.id.kids:
    		
    		Intent kidsIntent = new Intent(Praying.this, KidsSection.class);
    		startActivity(kidsIntent);
    		
    		break;
    		
    	case R.id.pillars:
    		
    		Intent pillarsIntent = new Intent(Praying.this, Pillars.class);
    		startActivity(pillarsIntent);
    		
    		break;
    		
    	case R.id.prayers:
    		
    		Intent prayersIntent = new Intent(Praying.this, Praying.class);
    		startActivity(prayersIntent);
    		
    		break;
    		
    	case R.id.ramadan:
    		
    		Intent ramadanIntent = new Intent(Praying.this, Ramadan.class);
    		startActivity(ramadanIntent);
    		
    		break;
    		
    	case R.id.sunna:
    		
    		Intent sunnaIntent = new Intent(Praying.this, Sunna.class);
    		startActivity(sunnaIntent);
    		
    		break;
    		
    	case R.id.verses:
    		
    		Intent versesIntent = new Intent(Praying.this, SplashActivity.class);
    		startActivity(versesIntent);
    		
    		break;
    		
    	case R.id.home:
    		
    		Intent homeIntent = new Intent(Praying.this, MainActivity.class);
    		startActivity(homeIntent);
    		
    		break;
  

    	}
 
    	return true;
	}
	

}
