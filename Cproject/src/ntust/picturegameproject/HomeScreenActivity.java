package ntust.picturegameproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreenActivity extends Activity {
	
	private final static String fileName = "score.txt";
	
	Button startBtn;
	TextView highestScoreText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		startBtn = (Button) this.findViewById(R.id.startBtn);
		highestScoreText = (TextView) this.findViewById(R.id.highestScoreText);
		
		updateHighestScore();
		
		startBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent data = new Intent(HomeScreenActivity.this, GameScreenActivity.class);
				startActivity(data);
				
				//startActivityForResult(data, REQUEST_CODE);
			}});
		
		
		
	}

	private void updateHighestScore() {
		String result = null;
		  try {
		    StringBuilder sb = new StringBuilder();
		    FileInputStream fin = openFileInput(fileName);
		    byte[] data = new byte[fin.available()];
		    while (fin.read(data) != -1) {
		      sb.append(new String(data));
		    }
		    fin.close();
		    result = sb.toString();
		  } catch (FileNotFoundException e) {
		    e.printStackTrace();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		  if(result != null)
			  highestScoreText.setText(result);
		  else
			  highestScoreText.setText("0");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
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
}
