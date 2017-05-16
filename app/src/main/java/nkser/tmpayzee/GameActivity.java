package nkser.tmpayzee;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import nkser.tmpayzee.data.VocabularyDBhelper;

import java.io.IOException;
import java.util.ArrayList;

public class GameActivity extends Activity {
	private SharedPreferences pref;
	public int coordinate_x;
	public int coordinate_y;
	private int level;
	private boolean resume_or_not;
	private String lexicon;
	public int[] strPos;
	public TextView textViewLeft;
	public TextView textViewRight;
	public TextView textViewUp;
	public TextView textViewDown;
	public ArrayList<String> words=null;
	public TextView[] textViewArr;
	public String[] currWordsArr =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);


		GlobalValues globalV = (GlobalValues) getApplication();


		pref = getSharedPreferences("game_info",MODE_PRIVATE);
		resume_or_not = pref.getBoolean("resume_or_not",false);
		lexicon = pref.getString("lexicon","cet6");
		coordinate_x = pref.getInt("coor_x",1);
		coordinate_y = pref.getInt("coor_y",1);
		level = pref.getInt("level",3);

		if (!resume_or_not){
			coordinate_x=globalV.mazeStart[level][1];
			coordinate_y=globalV.mazeStart[level][0];
//			coordinate_x=1;
//			coordinate_y=1;
			level=1;
			Log.e("coor_x_y",Integer.toString(coordinate_x)+" "+Integer.toString(coordinate_y));
		}


		VocabularyDBhelper dBhelper= new VocabularyDBhelper(this);
		try {
			dBhelper.createDataBase();
//			dBhelper.openDataBase();
			//dBhelper.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

//		dBhelper.close();
		SQLiteDatabase db = dBhelper.getReadableDatabase();

		String[] cols = {"vocabulary"};
		String[] levels = {Integer.toString(level)};

		Cursor cursor = db.query(lexicon,cols,"level = ?",levels,null,null,null);

		words = new ArrayList<>(0);
		if (cursor.moveToFirst()){
			do {
				words.add(cursor.getString(cursor.getColumnIndex("vocabulary")));
			}
			while (cursor.moveToNext());
		}
		String[] mazeCols = {"location", "up","down","left","right"};
		cursor = db.query("maze",mazeCols,"level = ?",levels, null,null,null);

		int[][][] mazePtr = globalV.maze;
		if (cursor.moveToFirst()){
			do {
				int temp_location = cursor.getInt(cursor.getColumnIndex("location"));
				int temp_y = (temp_location/10)+1 - (temp_location/100);
				int temp_x = (temp_location-1)%10+1;
				mazePtr[temp_x][temp_y][0] = cursor.getInt(cursor.getColumnIndex("up"));
				mazePtr[temp_x][temp_y][1] = cursor.getInt(cursor.getColumnIndex("down"));
				mazePtr[temp_x][temp_y][2] = cursor.getInt(cursor.getColumnIndex("left"));
				mazePtr[temp_x][temp_y][3] = cursor.getInt(cursor.getColumnIndex("right"));
			}
			while (cursor.moveToNext());
		}

		new KeyboardUtil(this, this).showKeyboard();

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

		new functions().refreshWords(this,this,textViewArr);
		new functions().refreshLocation(this);

	}

	@Override
	protected  void onDestroy() {

		pref = getSharedPreferences("game_info", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();

		editor.putString("lexicon",lexicon);
		editor.putInt("coor_x",coordinate_x);
		editor.putInt("coor_y",coordinate_y);
		editor.putInt("level",level);
		editor.putBoolean("resume_or_not",resume_or_not);
		editor.apply();

		super.onDestroy();
	}

	@Override
	protected  void onPause(){
		super.onPause();
	}


}
