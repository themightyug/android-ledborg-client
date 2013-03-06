package com.utonium.ledborgclient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	public static Context _application_context;
	public static Activity _activity;

	public ProgressBar spinner;

	private LedBorgClient _client;
	private String _serverHost;
	private int _serverPort;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_application_context = getApplicationContext();
		_activity = this;

		spinner = (ProgressBar) findViewById(R.id.progressBar1);
		spinner.setIndeterminate(true);
		setBusyMode(false);


		EditText textServerHost = (EditText) findViewById(R.id.ServerHostText);
		textServerHost.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable e) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int count, int after) {
				_serverHost = s.toString();
			}
		});
		_serverHost = textServerHost.getEditableText().toString();

		EditText textServerPort = (EditText) findViewById(R.id.ServerPortText);
		textServerPort.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable e) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int count, int after) {
				_serverPort = Integer.parseInt(s.toString());
			}
		});
		if(textServerPort.getEditableText().toString() != "") {
			_serverPort = Integer.parseInt(textServerPort.getEditableText().toString());
		}

		Button btnGetColour = (Button) findViewById(R.id.ButtonGetColour);
		btnGetColour.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setBusyMode(true);
				_client = new LedBorgClient(_serverHost, _serverPort);        		
				_client.getColourFromServer();
			}
		});

		// loop over LED buttons to assign there onclicks
		RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.rl2);
		for(int i=0; i<rl2.getChildCount(); i++) {
			LedButton led = (LedButton) rl2.getChildAt(i);
			led.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setBusyMode(true);

					LedButton led = (LedButton) v;

					_client = new LedBorgClient(_serverHost, _serverPort);
					_client.setColourOnServer(led.getColour());
				}
			});
		}
	}


	public void setBusyMode(boolean isBusy) {
		if(isBusy) {
			setSpinner(View.VISIBLE);
		} else {
			setSpinner(View.INVISIBLE);
		}
	}


	// the spinner just displays when we're communicating with the server
	private void setSpinner(int vis) {
		spinner.setVisibility(vis);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
