package com.pue.android.myfirstbanner;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new MyFragment()).commit();
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class MyFragment extends Fragment {

		public MyFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public static class AdFragment extends Fragment {
		
		private AdView mAdView;
		
		public AdFragment() {
			
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			return inflater.inflate(R.layout.fragment_ad,container,false);
			//R.layout.fragment_ad es la vista donde se va a cargar el anuncio
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			mAdView = (AdView)getView().findViewById(R.id.adView);
			
			AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();
			//AdRequest.DEVICE_ID_EMULATOR importante para hacer pruebas con el emulador
			mAdView.loadAd(adRequest);
		}
		
		@Override
		public void onPause() {
			if (mAdView != null){
				mAdView.pause();
			}
			super.onPause();
		}
		
		@Override
		public void onResume() {
			super.onResume();
			if (mAdView != null) {
				mAdView.resume();
			}
		}
		
		@Override
		public void onDestroy() {
			if (mAdView != null) {
				mAdView.destroy();
				//libreacion de memoria
			}
			super.onDestroy();
		}
		
	} 
}








