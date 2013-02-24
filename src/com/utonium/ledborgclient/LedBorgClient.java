/**
 * 
 */
package com.utonium.ledborgclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;


/**
 * @author Ross Taylor
 *
 */
public class LedBorgClient {

	private class NetworkTask extends AsyncTask<String, Void, HttpResponse> {

		private Activity _mainactivity;

		public NetworkTask(Activity activity) {
			this._mainactivity = activity;
		}

		@Override
		protected HttpResponse doInBackground(String... params) {
			String link = params[0];
			HttpGet request = new HttpGet(link);
			AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
			try {
				return client.execute(request);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				client.close();
			}
		}

		@Override
		protected void onPostExecute(HttpResponse result) {
			if (result != null) {
				if(result.containsHeader("Colour-LedBorg")) {
					currentColour = new Colour(result.getFirstHeader("Colour-LedBorg").getValue());
					LedButton ledCurrent = (LedButton) this._mainactivity.findViewById(R.id.LedButtonCurrent);
					ledCurrent.setColour(currentColour);
					ledCurrent.postInvalidate();

					((MainActivity) this._mainactivity).setBusyMode(false);
				}
			}
		}

	}

	public Colour currentColour;
	private String _server_host = "";
	private int _server_port = 9999;

	public LedBorgClient(String server_host, int server_port) {
		_server_host = server_host;
		_server_port = server_port;
	}

	public String generateUrl(String action, Colour colour) {
		String url = "http://" + _server_host + ":" + Integer.toString(_server_port) + "/";

		if(action.equals("getColour")) {
			url += "?GetColour";
		} else if(action.equals("setColour")) {
			url += "?SetColour";

			if(colour != null) {
				url += "&ledborg-colour=" + colour.getLedBorgColour();
			}
		}

		return(url);
	}


	public void getColourFromServer() {
		String url = generateUrl("getColour", null);

		new NetworkTask(MainActivity._activity).execute(url);
	}


	public void setColourOnServer(Colour colour) {
		String url = generateUrl("setColour", colour);

		new NetworkTask(MainActivity._activity).execute(url);
	}


}



