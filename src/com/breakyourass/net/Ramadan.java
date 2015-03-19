package com.breakyourass.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Ramadan extends SherlockActivity{

	ActionBar mSupportActionBar;
	ProgressDialog pDialog;
	List<HashMap<String,String>> weatherDataCollection = new ArrayList<HashMap<String,String>>();
	ListView lv;
	
	static final String KEY_DESCRIPTION = "description";
	static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
	static final String url = "http://109.123.112.186/ChurchApp/php/view_all_events.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		mSupportActionBar = getSupportActionBar();
		//mSupportActionBar.setBackgroundDrawable(new ColorDrawable(0xff123456));
		mSupportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008000")));
		mSupportActionBar.setDisplayHomeAsUpEnabled(true);
		mSupportActionBar.setTitle("Ramadan");
		
		new DownLoadList().execute();
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
    		
    		Intent dressingIntent = new Intent(Ramadan.this, Dressing.class);
    		startActivity(dressingIntent);
    		
    		break;
    	
    	case R.id.duas:
    		
    		Intent duasIntent = new Intent(Ramadan.this, Duas.class);
    		startActivity(duasIntent);
    		
    		break;
    		
    	case R.id.kids:
    		
    		Intent kidsIntent = new Intent(Ramadan.this, KidsSection.class);
    		startActivity(kidsIntent);
    		
    		break;
    		
    	case R.id.pillars:
    		
    		Intent pillarsIntent = new Intent(Ramadan.this, Pillars.class);
    		startActivity(pillarsIntent);
    		
    		break;
    		
    	case R.id.prayers:
    		
    		Intent prayersIntent = new Intent(Ramadan.this, Praying.class);
    		startActivity(prayersIntent);
    		
    		break;
    		
    	case R.id.ramadan:
    		
    		Intent ramadanIntent = new Intent(Ramadan.this, Ramadan.class);
    		startActivity(ramadanIntent);
    		 		
    		break;
    		
    	case R.id.sunna:
    		
    		Intent sunnaIntent = new Intent(Ramadan.this, Sunna.class);
    		startActivity(sunnaIntent);
    		
    		break;
    		
    	case R.id.verses:
    		
    		Intent versesIntent = new Intent(Ramadan.this, SplashActivity.class);
    		startActivity(versesIntent);
    		
    		break;
    		
    	case R.id.home:
    		
    		Intent homeIntent = new Intent(Ramadan.this, MainActivity.class);
    		startActivity(homeIntent);
    		
    		break;
  

    	}
 
    	return true;
	}
    
	class DownLoadList extends AsyncTask<String, String, String>{

		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Ramadan.this);
			pDialog.setMessage("Loading Sunna Please Wait ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try{
				
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = docBuilder.parse (url);

				// normalize text representation
				doc.getDocumentElement ().normalize ();  
              
                NodeList weatherList = doc.getElementsByTagName("events");
                
                HashMap<String,String> map = null;
                
                for (int i = 0; i < weatherList.getLength(); i++) {
				 
	                	Node firstWeatherNode = weatherList.item(i);
	                	map = new HashMap<String,String>();
                	
                	 if(firstWeatherNode.getNodeType() == Node.ELEMENT_NODE){
                	
	                	Element firstWeatherElement = (Element)firstWeatherNode;
	                    //-------
	                    NodeList idList = firstWeatherElement.getElementsByTagName(KEY_ID);
	                    Element firstIdElement = (Element)idList.item(0);
	                    NodeList textIdList = firstIdElement.getChildNodes();
	                    //--id
	                    map.put(KEY_ID, ((Node)textIdList.item(0)).getNodeValue().trim());
	                    
	                    NodeList titlelist = firstWeatherElement.getElementsByTagName(KEY_TITLE);
	                    Element  firstTitleElement = (Element)titlelist.item(0);
	                    NodeList textTitleList = firstTitleElement.getChildNodes();
	                    
	                    map.put(KEY_TITLE, ((Node)textTitleList.item(0)).getNodeValue().trim());
	                    
	                    NodeList desclist = firstWeatherElement.getElementsByTagName(KEY_DESCRIPTION);
	                    Element  firstDescElement = (Element)desclist.item(0);
	                    NodeList textDescList = firstDescElement.getChildNodes();
	                    
	                    map.put(KEY_DESCRIPTION, ((Node)textDescList.item(0)).getNodeValue().trim());
                    
                    
	                    weatherDataCollection.add(map);
                    
                    
                	
                	 }	
                }
			}catch (IOException localIOException){
				
		        Log.e("Error", localIOException.getMessage());
		        
		    }catch (Exception localException){
		    	
		        Log.e("Error", "Loading exception");
		        
		    }
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			pDialog.dismiss();
			lv = (ListView)findViewById(android.R.id.list);
			
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					SimpleAdapter adapter = new SimpleAdapter(Ramadan.this, weatherDataCollection, R.layout.list_item, new String[] { "title", "description" }, new int[] { R.id.title, R.id.description });
					lv.setAdapter(adapter);
					
				}
			});
		}
		
		
		
	}

}
