package com.breakyourass.net;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class MainActivity extends SherlockListActivity implements OnItemClickListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	private AnimationListener mAnimationListener;
	private Context mContext;
	
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	ActionBar mSupportActionBar;
	
    String[] countryArray = {"Dressing", "Duas", "Kids Section", "Pillars","Prayers","Ramadan","Sunna","Verses"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSupportActionBar = getSupportActionBar();
		//mSupportActionBar.setBackgroundDrawable(new ColorDrawable(0xff123456));
		mSupportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008000")));
		
		@SuppressWarnings("rawtypes")
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
			      R.layout.item_simple, countryArray);
			      
	    ListView listView = (ListView) findViewById(android.R.id.list);
	    listView.setAdapter(adapter);
	    
	    listView.setOnItemClickListener(this);

		mContext = this;
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
	
		mViewFlipper.setAutoStart(true);
		mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
		        android.R.anim.fade_in));
		mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, 
				android.R.anim.fade_out));
		mViewFlipper.setFlipInterval(4000);
		mViewFlipper.startFlipping();
		
//		actionBar.setBackgroundDrawable(drawable.ab_background_textured_example);
		
		mViewFlipper.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});

		//animation listener
		mAnimationListener = new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
				//animation started event
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				//TODO animation stopped event
			}
		};

	}

	// so that we know something was triggered
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
	
	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showNext();
					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showPrevious();
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch(position){
		      
		case 0:
			
			Intent dressingIntent = new Intent(MainActivity.this, Dressing.class);
    		startActivity(dressingIntent);
    		
		break;	
			
		case 1:
			
			Intent duasIntent = new Intent(MainActivity.this, Duas.class);
    		startActivity(duasIntent);
    		
		break;
		
		case 2:
			
			Intent kidsIntent = new Intent(MainActivity.this,KidsSection.class);
    		startActivity(kidsIntent);
    		
		break;
			
		case 3:
			
			Intent pillarsIntent = new Intent(MainActivity.this, Pillars.class);
    		startActivity(pillarsIntent);
    		
		break;
		
		case 4:
			
			Intent prayersIntent = new Intent(MainActivity.this, Praying.class);
    		startActivity(prayersIntent);
    		
		break;
		
		case 5:
			
			Intent ramadanIntent = new Intent(MainActivity.this, Ramadan.class);
    		startActivity(ramadanIntent);
    		
		break;
		
		case 6:
			
			Intent sunnaIntent = new Intent(MainActivity.this, Sunna.class);
    		startActivity(sunnaIntent);	
    		
		break;
		 
		case 7:
			
			Intent versesIntent = new Intent(MainActivity.this, SplashActivity.class);
    		startActivity(versesIntent);
    		
    	break;
		
		
		}
		
	}


}
