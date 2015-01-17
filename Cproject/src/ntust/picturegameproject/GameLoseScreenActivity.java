package ntust.picturegameproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameLoseScreenActivity extends Activity {
	
	
	Button homepageBtn;
	TextView scoreText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_lose_screen);
		
		homepageBtn = (Button) this.findViewById(R.id.homepageBtn);
		
		Intent data = this.getIntent();
		String value = data.getExtras().getString("survivalTime");
		if(value != null) {
			Toast.makeText(getApplicationContext(), "OK !", Toast.LENGTH_SHORT).show();
			scoreText = (TextView) this.findViewById(R.id.scoreText);
			scoreText.setText(value);
		}
		
		homepageBtn.setOnClickListener(new Button.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent data = new Intent(GameLoseScreenActivity.this, HomeScreenActivity.class);
				
				startActivity(data);
			}});
		
	}

	

}
