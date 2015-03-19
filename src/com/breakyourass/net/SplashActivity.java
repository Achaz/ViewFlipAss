package com.breakyourass.net;

import com.quickblox.core.QBCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends Activity implements QBCallback{

	private static final String APP_ID = "11692";
    private static final String AUTH_KEY = "AqWKzq5t9QXpN8E";
    private static final String AUTH_SECRET = "nSa9W5g3NuBWnFx";

    private ProgressBar progressBar;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);
        QBAuth.createSession(this);
	}

	@Override
	public void onComplete(Result result) {
		// TODO Auto-generated method stub
		progressBar.setVisibility(View.GONE);

        if (result.isSuccess()) {
            Intent intent = new Intent(this, Verses.class);
            startActivity(intent);
            finish();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Error(s) occurred. Look into DDMS log for details, " +
                    "please. Errors: " + result.getErrors()).create().show();
        }
		
	}

	@Override
	public void onComplete(Result arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
