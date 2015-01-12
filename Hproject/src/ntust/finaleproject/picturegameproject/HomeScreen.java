package ntust.finaleproject.picturegameproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends Activity {
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent game = new Intent(getApplicationContext(), GameScreen.class);
				startActivity(game);
			}
		});
	}
}
