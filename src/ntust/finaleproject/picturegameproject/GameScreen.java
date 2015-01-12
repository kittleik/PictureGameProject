package ntust.finaleproject.picturegameproject;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GameScreen extends Activity {
	private static final int TIME_INTERVAL = 5;
	private static final int MAX_NUM_OF_LIFE = 10;
	private static final int UPDATE_FREQUENCY = 50;
	
	private static int life = 10;
	
	Button playBtn, rightBtn, wrongBtn;
	TextView survivalTimeText;
	ImageView lifeImageView;
	Handler mainHandler;
	ImageView imageview;
	Button removebtn;
	ArrayList<ImageView> picGrid ;
	int[] visibleBool = {1,1,1,1,1,1,1,1,1};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		imageview = (ImageView) findViewById(R.id.imageview);
		picGrid = new ArrayList<ImageView>();
		picGrid.add((ImageView) findViewById(R.id.black1));
		picGrid.add((ImageView) findViewById(R.id.black2));
		picGrid.add((ImageView) findViewById(R.id.black3));
		picGrid.add((ImageView) findViewById(R.id.black4));
		picGrid.add((ImageView) findViewById(R.id.black5));
		picGrid.add((ImageView) findViewById(R.id.black6));
		picGrid.add((ImageView) findViewById(R.id.black7));
		picGrid.add((ImageView) findViewById(R.id.black8));
		picGrid.add((ImageView) findViewById(R.id.black9));
		
		life = 10;
		rightBtn = (Button) this.findViewById(R.id.rightBtn);
		wrongBtn = (Button) this.findViewById(R.id.wrongBtn);
		playBtn = (Button) this.findViewById(R.id.playBtn);
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
		rightBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(life < MAX_NUM_OF_LIFE){
					life++;
				}
				
			}});
		
		wrongBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(life > 0){
					life--;
				}
			}});
		
	}
	
	Runnable updateStopThread = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int time = Integer.parseInt(survivalTimeText.getText().toString());
			survivalTimeText.setText("" + (time + 1));
			if(time % TIME_INTERVAL - 4 == 0 && life > 0){
				life--;
				showPiece();
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
			}
			else
				mainHandler.postDelayed(updateLifeImage, UPDATE_FREQUENCY);
		}
		
	};
	private void showPiece(){
		int blacks = 0;
		int teller = 0;
		for (int i:visibleBool){
			blacks+=i;
		}
		int random = (int )(Math.random() * blacks + 1);
		for (int i = 0;i < picGrid.size(); i++){
			if (visibleBool[i]==1) teller++;
			if (teller == random){
				visibleBool[i] = 0;
				picGrid.get(i).setVisibility(View.INVISIBLE);
			}
		}
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mainHandler.removeCallbacks(updateStopThread);
		mainHandler.removeCallbacks(updateLifeImage);
	}
	
}
