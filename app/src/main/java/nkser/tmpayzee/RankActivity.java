package nkser.tmpayzee;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.appsee.Appsee;

/**
 * Created by dell on 2017/5/16.
 */

public class RankActivity extends Activity {
	private Typeface typeface;
	private char[][] place;
	public  SharedPreferences rank;;
	//public static ArrayList<Integer> score_rank=new ArrayList<>();
	private SharedPreferences.Editor editor;
	protected  static int score;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		typeface=Typeface.createFromAsset(getAssets(),"fonts/cool.ttf");
		place=new char[3][];
		GlobalValues.player.start();
		Appsee.start("b60e4460e4a948c39987941614027495");


		rank=getSharedPreferences("Score", Context.MODE_PRIVATE);
		editor=rank.edit();

		int first_score;
		int second_score;
		int third_score;
		if (rank==null){
			editor.putInt("firstplace",0);
			editor.putInt("secondplace",0);
			editor.putInt("thirdplace",0);
			editor.apply();
		}

		//get score from rank and refresh them
		first_score=rank.getInt("firstplace",0);
		second_score=rank.getInt("secondplace",0);
		third_score=rank.getInt("thirdplace",0);


		if (score>=first_score){
			first_score=score;
			editor.putInt("firstplace",first_score);
			editor.apply();
		}
		else if (score<first_score&&score>=second_score){
			second_score=score;
			editor.putInt("secondplace",second_score);
			editor.apply();
		}
		else if (score<second_score&&score>=third_score){
			third_score=score;
			editor.putInt("thirdplace",third_score);
			editor.apply();
		}

		String s1=Integer.toString(first_score);
		String s2=Integer.toString(second_score);
		String s3=Integer.toString(third_score);
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

	@Override
	protected void onPause() {
		GlobalValues.player.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		GlobalValues.player.start();
		super.onResume();
	}

}