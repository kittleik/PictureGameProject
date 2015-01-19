package ntust.picturegameproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameLoseScreenActivity extends Activity {
	
	private final static String fileName = "score.txt";
	
	Button homepageBtn;
	TextView scoreText;
	String value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_lose_screen);
		
		homepageBtn = (Button) this.findViewById(R.id.homepageBtn);
		
		Intent data = this.getIntent();
		value = data.getExtras().getString("survivalTime");
		if(value != null) {
			Toast.makeText(getApplicationContext(), "OK !", Toast.LENGTH_SHORT).show();
			scoreText = (TextView) this.findViewById(R.id.scoreText);
			scoreText.setText(value);
			if(Integer.parseInt(value) > Integer.parseInt(readScoreFromFile(fileName)))
				writeIntoFile(value);
		}
		
		homepageBtn.setOnClickListener(new Button.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent data = new Intent(GameLoseScreenActivity.this, HomeScreenActivity.class);
				
				startActivity(data);
			}});
		
	}

	private void writeIntoFile(String value) {
		try{
			FileOutputStream writer = openFileOutput(fileName, Context.MODE_PRIVATE);
			writer.write(value.getBytes());
			writer.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
	}
	
	private String readScoreFromFile(String fileName) {
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
			  return result;
		  else
			  return "0";
	}

}
