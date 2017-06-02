package nkser.tmpayzee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nkser.tmpayzee.data.VocabularyDBhelper;

import java.io.IOException;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Bundle;

import static android.R.attr.level;
import static java.util.logging.Logger.GLOBAL_LOGGER_NAME;
import static java.util.logging.Logger.global;
import static nkser.tmpayzee.R.id.pauseIcon;

public class GameActivity extends Activity {
	private SharedPreferences pref;
	private String timeStr = "";
	private Handler mHandler = new Handler();
	private TextView tv_timer;
	private GlobalValues globalV;

	public int[] strPos;
	public TextView textViewLeft;
	public TextView textViewRight;
	public TextView textViewUp;
	public TextView textViewDown;
	public TextView[] textViewArr;
	public String[] currWordsArr =null;

	public void countTimer(){
		mHandler.postDelayed(TimerRunnable, 1000);
	}

	private Runnable TimerRunnable = new Runnable() {

		@Override
		public void run() {
				globalV.timer -= 1;
				Long temptime = globalV.timer;
				timeStr = Long.toString(temptime / 600);
				temptime = temptime % 600;
				timeStr += Long.toString(temptime / 60);
				timeStr += " : ";
				temptime = temptime % 60;
				timeStr += Long.toString(temptime / 10);
				temptime = temptime % 10;
				timeStr += Long.toString(temptime);
				tv_timer.setText(timeStr);
				if (globalV.timer == 0) {

					final GameOverDialog tempdialog= new GameOverDialog(GameActivity.this);
					tempdialog.setOwnerActivity(GameActivity.this);
					globalV.IsPaused = true;
					tempdialog.show();
					tempdialog.setCancelable(false);
				}

		if (!globalV.IsPaused){
			countTimer();
		}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		globalV = (GlobalValues) getApplication();

		pref = getSharedPreferences("game_info",MODE_PRIVATE);
		globalV.lexicon = pref.getString("lexicon","cet6");
		globalV.coordinate_x = pref.getInt("coor_x",globalV.start_x);
		globalV.coordinate_y = pref.getInt("coor_y",globalV.start_y);
		globalV.level = pref.getInt("level",1);

		Log.e("level ", Integer.toString(globalV.level));

		boolean startOrNot = getIntent().getBooleanExtra("start_or_resume",true);
		if (startOrNot){
			globalV.coordinate_x =globalV.start_x;
			globalV.coordinate_y =globalV.start_y;
			globalV.level=1;
			Log.e("coor_x_y",Integer.toString(globalV.coordinate_x)+" "+Integer.toString(globalV.coordinate_y));
		}

		ImageView mazeImg = (ImageView) findViewById(R.id.mazeMap);
		mazeImg.setImageResource(globalV.img[globalV.level-1]);

		VocabularyDBhelper dBhelper= new VocabularyDBhelper(this);
		try {
			dBhelper.createDataBase();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		globalV.refreshWordsLib();
		new KeyboardUtil(globalV,this, this).showKeyboard();

		textViewLeft = (TextView) findViewById(R.id.TextViewLeft);
		textViewRight = (TextView) findViewById(R.id.TextViewRight);
		textViewUp = (TextView) findViewById(R.id.TextViewUp);
		textViewDown = (TextView) findViewById(R.id.TextViewDown);

		textViewArr = new TextView[4];
		textViewArr[0]=textViewUp;
		textViewArr[1]=textViewDown;
		textViewArr[2]=textViewLeft;
		textViewArr[3]=textViewRight;
		strPos = new int[4];
		currWordsArr = new String[4];

		globalV.refreshWords(this,this);
		globalV.refreshLocation(this);

		globalV.IsPaused =false;
		globalV.timer = globalV.timeRestrict;
		tv_timer = (TextView) findViewById(R.id.tv_timer);

		countTimer();

		ImageView pauseIcon = (ImageView)findViewById(R.id.pauseIcon);
		class templistener implements View.OnClickListener
		{
			GameActivity parentContext;
			templistener(GameActivity ga){
				parentContext = ga;
			}
			@Override
			public void onClick(View view) {
				final GameDialog tempdialog= new GameDialog(parentContext);
				tempdialog.setOwnerActivity(parentContext);
				globalV.IsPaused = true;
				tempdialog.show();
				tempdialog.setCancelable(false);
			}
		}
		pauseIcon.setOnClickListener(new templistener(this));
		Log.e("level ", Long.toString(globalV.level));
	}

	@Override
	protected  void onDestroy() {



		pref = getSharedPreferences("game_info", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();

		editor.putString("lexicon",globalV.lexicon);
		editor.putInt("coor_x",globalV.coordinate_x);
		editor.putInt("coor_y",globalV.coordinate_y);
		editor.putInt("level",globalV.level);
		editor.apply();

		super.onDestroy();
	}

	@Override
	protected  void onPause(){
		super.onPause();
		globalV.IsPaused= true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}