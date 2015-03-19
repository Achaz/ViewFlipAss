package com.breakyourass.net;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.ConnectionListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.breakyourass.net.ui.activities.RegistrationActivity;
import com.breakyourass.net.ui.fragments.RoomsFragment;
import com.breakyourass.net.ui.fragments.UsersFragment;
import com.quickblox.module.chat.QBChatService;
import com.quickblox.module.users.model.QBUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class Verses extends SherlockFragmentActivity implements ActionBar.TabListener{

	ActionBar mSupportActionBar;
	
	private static final int AUTHENTICATION_REQUEST = 1;
    private static final int POSITION_USER = 0;
    private static final int POSITION_ROOM = 1;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private Action lastAction;
    private ConnectionListener connectionListener;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verses);
		
		mSupportActionBar = getSupportActionBar();
		//mSupportActionBar.setBackgroundDrawable(new ColorDrawable(0xff123456));
		mSupportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008000")));
		mSupportActionBar.setDisplayHomeAsUpEnabled(true);
		mSupportActionBar.setTitle("Verses");
		
		mSupportActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		List<Fragment> tabs = new ArrayList<Fragment>();
        tabs.add(UsersFragment.getInstance());
        tabs.add(RoomsFragment.getInstance());

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), tabs);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            	mSupportActionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
        	mSupportActionBar.addTab(mSupportActionBar.newTab().setText(sectionsPagerAdapter.getPageTitle(i)).setTabListener(
                    this));
        }
		
	
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
    		
    		Intent intent = new Intent(Verses.this, Dressing.class);
    		startActivity(intent);
    		
    		break;
    	
    	case R.id.duas:
    		
    		Intent newIntent = new Intent(Verses.this, Duas.class);
    		startActivity(newIntent);
    		
    		break;
    		
    	case R.id.kids:
    		
    		Intent kidsIntent = new Intent(Verses.this, KidsSection.class);
    		startActivity(kidsIntent);
    		
    		break;
    		
    	case R.id.pillars:
    		
    		Intent pillarsIntent = new Intent(Verses.this, Pillars.class);
    		startActivity(pillarsIntent);
    		
    		break;
    		
    	case R.id.prayers:
    		
    		Intent prayersIntent = new Intent(Verses.this, Praying.class);
    		startActivity(prayersIntent);
    
    		break;
    		
    	case R.id.ramadan:
    		
    		Intent ramadanIntent = new Intent(Verses.this, Ramadan.class);
    		startActivity(ramadanIntent);
    		
    		break;
    		
    	case R.id.sunna:
    		
    		Intent sunnaIntent = new Intent(Verses.this, Sunna.class);
    		startActivity(sunnaIntent);
    		
    		break;
    		
    	case R.id.verses:
    		
    		Intent versesIntent = new Intent(Verses.this, Verses.class);
    		startActivity(versesIntent);
    		
    		break;
    		
    	case R.id.home:
    		
    		Intent homeIntent = new Intent(Verses.this, MainActivity.class);
    		startActivity(homeIntent);
    		
    		break;
  

    	}
 
    	return true;
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		int position = tab.getPosition();
        QBUser qbUser = ((App) getApplication()).getQbUser();
        if (qbUser != null) {
            viewPager.setCurrentItem(position);
        } else if (position == POSITION_ROOM) {
            lastAction = Action.ROOM_LIST;
            showAuthenticateDialog();
        }
		
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
        if (resultCode == RESULT_OK) {
        	
            switch (lastAction) {
            
                case CHAT:
                	
                    ((UsersFragment) sectionsPagerAdapter.getItem(POSITION_USER)).startChat();
                    break;
                    
                case ROOM_LIST:
                	
                    viewPager.setCurrentItem(POSITION_ROOM);
                    break;
            }
            
            connectionListener = new ChatConnectionListener();
            QBChatService.getInstance().addConnectionListener(connectionListener);
            ((RoomsFragment) sectionsPagerAdapter.getItem(POSITION_ROOM)).loadRooms();
            
        } else {
        	
            showUsersFragment();
            
        }
    }
	
	public static enum Action {CHAT, ROOM_LIST}

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public SectionsPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case POSITION_USER:
                    return "Users";
                case POSITION_ROOM:
                    return "Rooms";
            }
            return null;
        }
    }

    private void showUsersFragment() {
        getSupportActionBar().selectTab(getSupportActionBar().getTabAt(POSITION_USER));
        viewPager.setCurrentItem(POSITION_USER);
    }

    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    public void showAuthenticateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Authorize first");
        builder.setItems(new String[]{"Login", "Register"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(Verses.this, LoginActivity.class);
                        startActivityForResult(intent, AUTHENTICATION_REQUEST);
                        break;
                    case 1:
                        intent = new Intent(Verses.this, RegistrationActivity.class);
                        startActivityForResult(intent, AUTHENTICATION_REQUEST);
                        break;
                }
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showUsersFragment();
            }
        });
        builder.show();
    }
    
    
    private void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Verses.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
    
    private class ChatConnectionListener implements ConnectionListener {

        @Override
        public void connectionClosed() {
            showToast("connectionClosed");
        }

        @Override
        public void connectionClosedOnError(Exception e) {
            showToast("connectionClosed on error" + e.getLocalizedMessage());
        }

        @Override
        public void reconnectingIn(int i) {

        }

        @Override
        public void reconnectionSuccessful() {

        }

        @Override
        public void reconnectionFailed(Exception e) {

        }
    }

}
