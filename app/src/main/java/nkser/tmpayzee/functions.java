package nkser.tmpayzee;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static android.R.attr.width;

/**
 * Created by Adam_Yang on 2017/5/13.
 */

public class functions {

	private static final float wallSize = 23;
	private static final float gridSize = 72;
	private static final int gridSizeW = 72;
	private static final int mazeMapSizeInDp = 300;

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

		//calculate the coordinates
//		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.EXACTLY);
//		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.EXACTLY);
//		mazeMap.measure(w, h);
//		int height =mazeMap.getMeasuredHeight();
//		int width =mazeMap.getMeasuredWidth();

//		w=mazeMap.getMeasuredHeight();
//		h=mazeMap.getMeasuredWidth();

		//-5 ----experiment result

		float unitW = ((mazeMap.getBottom()-mazeMap.getTop()/(11*wallSize+10*gridSize)*(wallSize+gridSize))-5;
		float unitH = ((mazeMap.getBottom()-mazeMap.getTop()/(11*wallSize+10*gridSize)*(wallSize+gridSize))-5;
//		float unitH = ((mazeMap.getBottom()-mazeMap.getTop())/(11*wallSize+10*gridSize)*(wallSize+gridSize));
		//int unitH = (int)(mazeMapSizeInDp*density/(11*wallSize+10*gridSizeH) * (wallSize+gridSizeH));
		//X\Y bias = 40
		float x = 40+mapCoorX + (act.coordinate_x-1)*unitW;
		float y = 40+mapCoorY + (act.coordinate_y-1)*unitH;
//		Log.e("X ", .toString(x));

		Log.e("unitH ", Float.toString(unitH));
		Log.e("Y ", Float.toString(y));
		plane.setTranslationX(x);
		plane.setTranslationY(y);

	//	plane.setForeground(R.drawable.plane);
	}

}
