package ntust.picturegameproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class GameScreenActivity extends Activity {

	private static final int TIME_INTERVAL = 5;
	private static final int MAX_NUM_OF_LIFE = 10;
	private static final int UPDATE_FREQUENCY = 50;
	
	private static final int REQUEST_CODE = 10;
	
	private static int life = 10;
	private static int time = 0;
	
	Button playBtn, rightBtn, wrongBtn;
	TextView survivalTimeText;
	ImageView lifeImageView;
	Handler mainHandler;
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_game_screen);
		life = 10;
		playBtn = (Button) this.findViewById(R.id.playBtn);
		rightBtn = (Button) this.findViewById(R.id.rightBtn);
		wrongBtn = (Button) this.findViewById(R.id.wrongBtn);
		
		survivalTimeText = (TextView) this.findViewById(R.id.survivalTimeText);
		
		lifeImageView = (ImageView) this.findViewById(R.id.lifeImageView);
		
		mainHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
			}	
		};
		playBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				survivalTimeText.setText("0");
				playBtn.setVisibility(Button.INVISIBLE);
				mainHandler.postDelayed(updateStopThread, 1000);
				mainHandler.postDelayed(updateLifeImage, UPDATE_FREQUENCY);
			}});
		
		//================================================================================================================
		//================================================================================================================
		
		rightBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(life < MAX_NUM_OF_LIFE){
					life++;
				}
				
			}});
		
		wrongBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(life > 0){
					life--;
				}
			}});
		
		//================================================================================================================
		//================================================================================================================
		
	}
	
	Runnable updateStopThread = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			time = Integer.parseInt(survivalTimeText.getText().toString());
			survivalTimeText.setText("" + (time + 1));
			if(time % TIME_INTERVAL - 4 == 0 && life > 0){
				life--;
			}
			mainHandler.postDelayed(updateStopThread, 1000);
		}
	};
	
	Runnable updateLifeImage = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String uri = "@drawable/life" + life;
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());
			lifeImageView.setImageResource(imageResource);
			if(life == 0){
				Toast.makeText(getApplicationContext(), "Finished !", Toast.LENGTH_SHORT).show();
				finish();
				gameLose();
			}
			else
				mainHandler.postDelayed(updateLifeImage, UPDATE_FREQUENCY);
		}
		
	};
	
	private void gameLose() {
		Intent data = new Intent(GameScreenActivity.this, GameLoseScreenActivity.class);
		startActivity(data);
		data.putExtra("survivalTime", ""+(time+1));
		startActivityForResult(data, REQUEST_CODE);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mainHandler.removeCallbacks(updateStopThread);
		mainHandler.removeCallbacks(updateLifeImage);
	}	
}


