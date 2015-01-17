package ntust.picturegameproject;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameLoseScreenActivity extends Activity {
	
	private final static String fileName = "score.txt";
	
	Button homepageBtn, addBtn, readBtn;
	TextView scoreText;
	String value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_lose_screen);
		
		homepageBtn = (Button) this.findViewById(R.id.homepageBtn);
		addBtn = (Button) this.findViewById(R.id.addBtn);
		readBtn = (Button) this.findViewById(R.id.readBtn);
		
		
		Intent data = this.getIntent();
		value = data.getExtras().getString("survivalTime");
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
		
		addBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//getDir(fileName, Context.MODE_PRIVATE);
				try{
					FileOutputStream writer = openFileOutput(fileName, Context.MODE_PRIVATE);
					writer.write(value.getBytes());
					writer.close();
				}catch (FileNotFoundException e) {
				    e.printStackTrace();
				  } catch (IOException e) {
				    e.printStackTrace();
				  }
				
			}});
		
		readBtn.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
				  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
 
			}});
		
	}

	

}
