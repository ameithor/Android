package com.pue.android.myfirstinterstitial;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new GameFragment()).commit();
		}
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
	public static class GameFragment extends Fragment {
		
		private InterstitialAd mInterstitialAd;
		private CountDownTimer mTimer;
		private Button mRetryButton;
		
		
		public GameFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			//initButton();
			StartGame();
			InitAd();
			
			initTimer();
			
			
		
		}

		private void initTimer() {
			// TODO Auto-generated method stub
			final TextView txtCounter = (TextView)getView().findViewById(R.id.timer);
			
			mTimer = new CountDownTimer(4000,1000) {
				
				
				@Override
				public void onTick(long millisUntilFinished) {
					// TODO Auto-generated method stub
					txtCounter.setText(""+millisUntilFinished/1000);
				}
				
				@Override
				public void onFinish() {
					// TODO Auto-generated method stub
					
					displayAd();
					
					
				}
			};
		
			mTimer.start();
			
		}

		private void initButton() {
			// TODO Auto-generated method stub
			mRetryButton = (Button) getView().findViewById(R.id.retry_button);
			mRetryButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					displayAd();
				}


			});
		}

		
		private void displayAd() {
			// TODO Auto-generated method stub
			if(mInterstitialAd!=null && mInterstitialAd.isLoaded()){
				mInterstitialAd.show();
				Log.i("displayAd()","if(mInterstitialAd!=null && mInterstitialAd.isLoaded()){");
				
			}
			else{
				Toast.makeText(getActivity(), "Anuncio no cargado", Toast.LENGTH_LONG);
				//importante utilizar getActivity para el contexto porque
				//dentro dle fragment no funcionaria correctamente si ponemos this
				//ya que this hace referencia al fragment y el paramentro necesita una Activity
				Log.i("displayAd()","!!!!if(mInterstitialAd!=null && mInterstitialAd.isLoaded()){");
				
			}
			
		}
		
		private void InitAd() {
			// TODO Auto-generated method stub
			mInterstitialAd = new InterstitialAd(getActivity());
			mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
		}
		
		private void StartGame(){
			AdRequest adRequest = new AdRequest.Builder()
			.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
			.addTestDevice("123456789").build();
			mInterstitialAd.loadAd(adRequest);
		}
		
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StartGame();
	
	}	
	}
	
	
}
