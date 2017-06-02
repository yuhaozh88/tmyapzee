package nkser.tmpayzee;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class KeyboardUtil {
	private Context ctx;
	private GameActivity act;
	private KeyboardView keyboardView;
	private Keyboard k1;// 字母键盘
	private GlobalValues globalV;

	public KeyboardUtil(GlobalValues glv,GameActivity act, Context ctx) {
		this.act = act;
		this.ctx = ctx;
		k1 = new Keyboard(ctx, R.xml.qwerty);
		keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
		keyboardView.setKeyboard(k1);
		keyboardView.setEnabled(true);
		keyboardView.setPreviewEnabled(false);
		keyboardView.setOnKeyboardActionListener(listener);
		globalV = glv;
	}

	private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onPress(int primaryCode) {
		}

		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			TextView[] tvArr = act.textViewArr;

			for (int i=0;i<4;i++){
				String tempStr=act.currWordsArr[i];
				if (tempStr.charAt(act.strPos[i]) == (char)primaryCode || tempStr.charAt(act.strPos[i]) == (char)(primaryCode-22)) {
					SpannableString styledText1 = new SpannableString(tempStr);
					act.strPos[i]++;
					styledText1.setSpan(new TextAppearanceSpan(act, R.style.TypedCharacter), 0, act.strPos[i], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					styledText1.setSpan(new TextAppearanceSpan(act, R.style.UnTypedCharacter), act.strPos[i], styledText1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					tvArr[i].setText(styledText1, TextView.BufferType.SPANNABLE);

					GlobalValues globalV = (GlobalValues) act.getApplication();
					if (act.strPos[i] == tempStr.length()){

						globalV.refreshWords(ctx, act);

						Log.e("maze", Integer.toString(globalV.maze[globalV.coordinate_x][globalV.coordinate_y][0])+
								Integer.toString(globalV.maze[globalV.coordinate_x][globalV.coordinate_y][1])+
								Integer.toString(globalV.maze[globalV.coordinate_x][globalV.coordinate_y][2])+
								Integer.toString(globalV.maze[globalV.coordinate_x][globalV.coordinate_y][3]));
						if (globalV.maze[globalV.coordinate_x][globalV.coordinate_y][i]==1) {
							switch (i) {
								case 0 :
									globalV.coordinate_y--;
									Log.e("coord ",Integer.toString(globalV.coordinate_x) + " "+Integer.toString(globalV.coordinate_y));
									break;
								case 1:
									globalV.coordinate_y++;
									Log.e("coord ",Integer.toString(globalV.coordinate_x) + " "+Integer.toString(globalV.coordinate_y));
									break;
								case 2:
									globalV.coordinate_x--;
									Log.e("coord ",Integer.toString(globalV.coordinate_x) + " "+Integer.toString(globalV.coordinate_y));
									break;
								case 3:
									globalV.coordinate_x++;
									Log.e("coord ",Integer.toString(globalV.coordinate_x) + " "+Integer.toString(globalV.coordinate_y));
									break;
							}

							if (globalV.coordinate_x == globalV.mazeDestination[globalV.level-1][1]
									&& globalV.coordinate_y == globalV.mazeDestination[globalV.level-1][0]){

								final FinishDialog tempdialog= new FinishDialog(act);
								tempdialog.setOwnerActivity(act);
								globalV.IsPaused = true;
								tempdialog.show();
								tempdialog.setCancelable(false);
							}
							globalV.refreshLocation(act);
						}

						break;

					}

				}
			}
		}
	};

	public void showKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.GONE || visibility == View.INVISIBLE) {
			keyboardView.setVisibility(View.VISIBLE);
		}
	}

}