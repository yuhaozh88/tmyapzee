package nkser.tmpayzee;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

/**
 * Created by Adam_Yang on 2017/5/14.
 */

public class GlobalValues extends Application {
	public final int mazeStart[][]={{9,1},{9,1},{4,1},{6,1}};
	public final int mazeDestination[][]={{1,10},{6,10},{2,10},{4,10}};

	private static final float wallSize = 23;
	private static final float gridSize = 72;


	SharedPreferences sharedPref;


	public boolean IsPaused;
	public int[][][] maze;

	public int coordinate_x;
	public int coordinate_y;
	public boolean resume_or_not;
	public long timer;
	public final long timeRestrict = 100;


	public static MediaPlayer player;
	protected static boolean situation=false;
	public static float volume;

	public GlobalValues() {
		maze = new int[11][11][4];
	}

	void refreshWords(Context context, GameActivity act){
		//get Random Nums
		int[] index = new int[4];
		int intRd = 0; //存放随机数
		int count = 0; //记录生成的随机数个数
		int flag = 0; //是否已经生成过标志
		while(count<4) {
			Random rdm = new Random(System.currentTimeMillis());
			intRd = Math.abs(rdm.nextInt()) % 32 + 1;
			for (int i = 0; i < count; i++) {
				if (index[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if(flag==0){
				index[count] = intRd;
				count++;
			}
		}
		for (int i=0;i<4;i++) {

			String tempWord = act.words.get(index[i]);
			act.currWordsArr[i] = tempWord;
			act.strPos[i]=0;
			SpannableString styledText = new SpannableString(tempWord);
			styledText.setSpan(new TextAppearanceSpan(context, R.style.UnTypedCharacter), 0, styledText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			act.textViewArr[i].setText(styledText, TextView.BufferType.SPANNABLE);
		}

	}

	void refreshLocation(GameActivity act){
		ImageView mazeMap = (ImageView) act.findViewById(R.id.mazeMap);
		ImageView plane = (ImageView) act.findViewById(R.id.planeImg);

		//return value in px
		float mapCoorX = mazeMap.getLeft();
		float mapCoorY = mazeMap.getTop();

		//-5 ----experiment result
		sharedPref = getSharedPreferences("game_info", MODE_PRIVATE);
		float unitW = sharedPref.getFloat("unitW", -1);
		float unitH = sharedPref.getFloat("unitH", -1);

		if (Math.abs(unitW+1) <= 0.000001 || Math.abs(unitH+1)<= 0.000001
				|| Math.abs(unitW) < 0.000001 || Math.abs(unitH)< 0.000001) {

			unitW = ((mazeMap.getBottom() - mazeMap.getTop()) / (11 * wallSize + 10 * gridSize) * (wallSize + gridSize));
			unitH = ((mazeMap.getBottom() - mazeMap.getTop()) / (11 * wallSize + 10 * gridSize) * (wallSize + gridSize));
				SharedPreferences.Editor edit = sharedPref.edit();
				edit.putFloat("unitW",unitW);
				edit.putFloat("unitH",unitH);
				edit.apply();
				Log.e("unit refreashed ","fuck");
		}

		Log.e("unitW ",Float.toString(unitW));
		Log.e("unitH ",Float.toString(unitH));
		//X\Y bias = 40
		float x = 40+mapCoorX + (coordinate_x-1)*unitW;
		float y = 40+mapCoorY + (coordinate_y-1)*unitH;

		Log.e("x ", Float.toString(x));
		Log.e("Y ", Float.toString(y));
		plane.setTranslationX(x);
		plane.setTranslationY(y);

		Log.e("coor_x_y",Integer.toString(coordinate_x)+" "+Integer.toString(coordinate_y));
	}


}
