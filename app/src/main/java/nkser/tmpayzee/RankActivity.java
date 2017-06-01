package nkser.tmpayzee;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dell on 2017/5/16.
 */

public class RankActivity extends Activity {
	private Typeface typeface;
	private char[][] place;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		typeface=Typeface.createFromAsset(getAssets(),"fonts/cool.ttf");
		place=new char[3][];
		GlobalValues.player.start();
		String s1="First Place";
		String s2="Second Place";
		String s3="Third Place";
		place[0]=s1.toCharArray();
		place[1]=s2.toCharArray();
		place[2]=s3.toCharArray();

		TextView firstPlace=(TextView) findViewById(R.id.firstplace);
		TextView secondPlace=(TextView) findViewById(R.id.secondplace);
		TextView thirdPlace=(TextView) findViewById(R.id.thirdplace);
		Button backbutton=(Button) findViewById(R.id.backbutton);

		firstPlace.setText(place[0],0,s1.length());
		secondPlace.setText(place[1],0,s2.length());
		thirdPlace.setText(place[2],0,s3.length());
		firstPlace.setTypeface(typeface);
		secondPlace.setTypeface(typeface);
		thirdPlace.setTypeface(typeface);

		backbutton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}