package ntust.finaleproject.picturegameproject;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;


public class GameScreen extends Activity {
	ImageView imageview, b1,b2,b3,b4,b5,b6,b7,b8,b9;
	Button removebtn;
	ArrayList<ImageView> grid = new ArrayList<ImageView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		imageview = (ImageView) findViewById(R.id.imageview);
		b1 = (ImageView) findViewById(R.id.black1);
		b2 = (ImageView) findViewById(R.id.black2);
		b3 = (ImageView) findViewById(R.id.black3);
		b4 = (ImageView) findViewById(R.id.black4);
		b5 = (ImageView) findViewById(R.id.black5);
		b6 = (ImageView) findViewById(R.id.black6);
		b7 = (ImageView) findViewById(R.id.black7);
		b8 = (ImageView) findViewById(R.id.black8);
		b9 = (ImageView) findViewById(R.id.black9);
		
		removebtn = (Button) findViewById(R.id.removebtn);
		
		
	}
	private void showPiece(){
		
	}
	
}
