package nkser.tmpayzee;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import android.content.SharedPreferences;

import static android.R.attr.width;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Adam_Yang on 2017/5/13.
 */
public class functions extends Application{

/*
	private static final float wallSize = 23;
	private static final float gridSize = 72;
	private static final int gridSizeW = 72;
	private static final int mazeMapSizeInDp = 300;
	SharedPreferences sharedPref;

	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}

	void refreshWords(Context context, GameActivity act, TextView[] textViewArr ){
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

			textViewArr[i].setText(styledText, TextView.BufferType.SPANNABLE);
		}

	}

	void refreshLocation(GameActivity act){
		ImageView mazeMap = (ImageView) act.findViewById(R.id.mazeMap);
		ImageView plane = (ImageView) act.findViewById(R.id.planeImg);

		//return value in px
		float mapCoorX = mazeMap.getLeft();
		float mapCoorY = mazeMap.getTop();

		//-5 ----experiment result
		sharedPref = context.getSharedPreferences("game_info", MODE_PRIVATE);
		float unitW = sharedPref.getFloat("unitW", -1);
		float unitH = sharedPref.getFloat("unitH", -1);

		if (unitW == -1 || unitH == -1) {
			unitW = ((mazeMap.getBottom() - mazeMap.getTop()) / (11 * wallSize + 10 * gridSize) * (wallSize + gridSize));
			unitH = ((mazeMap.getBottom() - mazeMap.getTop()) / (11 * wallSize + 10 * gridSize) * (wallSize + gridSize)) - 5;
			SharedPreferences.Editor edit = sharedPref.edit();
			edit.putFloat("unitW",unitW);
			edit.putFloat("unitH",unitH);
		}

//		float unitH = ((mazeMap.getBottom()-mazeMap.getTop())/(11*wallSize+10*gridSize)*(wallSize+gridSize));
		//X\Y bias = 40
		float x = 40+mapCoorX + (act.coordinate_x-1)*unitW;
		float y = 40+mapCoorY + (globalV.coordinate_y-1)*unitH;
//		Log.e("X ", .toString(x));

		Log.e("x ", Float.toString(x));
		Log.e("Y ", Float.toString(y));
		plane.setTranslationX(x);
		plane.setTranslationY(y);

		Log.e("coor_x_y",Integer.toString(globalV.coordinate_x)+" "+Integer.toString(globalV.coordinate_y));
	//	plane.setForeground(R.drawable.plane);
	}
*/
}
