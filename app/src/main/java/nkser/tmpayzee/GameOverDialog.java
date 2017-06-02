package nkser.tmpayzee;

import android.app.Dialog;

/**
 * Created by Adam_Yang on 2017/6/1.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.level;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

/**
 * Created by Adam_Yang on 2017/5/31.
 */

public class GameOverDialog extends Dialog {
	Context context;
	GlobalValues globalV;
	SharedPreferences sharedPreferences;
	GameActivity act;

	private TextView title;

	private ImageView restartButton;
	private ImageView menuButton;

	private ClickListenerInterface clickListenerInterface;

	public GameOverDialog(Context context) {
		super(context);
		this.context = context;
//		setCustomDialog();
	}

	public interface ClickListenerInterface {
		void doRestart();
		void doMenu();
	}

	public void setClicklistener() {
		ClickListenerInterface inter = new ClickListenerInterface() {
			@Override
			public void doRestart() {
				globalV.coordinate_x =globalV.start_x;
				globalV.coordinate_y =globalV.start_y;
				globalV.level =1;
				globalV.IsPaused = false;
				globalV.timer=globalV.timeRestrict;
				globalV.refreshWordsLib();
				globalV.refreshLocation((GameActivity) getOwnerActivity());
				globalV.refreshWords(getOwnerActivity(),(GameActivity) getOwnerActivity());
				dismiss();
			}

			@Override
			public void doMenu() {
				Intent intent = new Intent(getOwnerActivity(),MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				getOwnerActivity().startActivity(intent);
			}
		};
		this.clickListenerInterface = inter;
	}

	private class clickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
				case R.id.over_menu_button:
					clickListenerInterface.doMenu();
					break;
				case R.id.over_restart_button:
					clickListenerInterface.doRestart();
					break;
			}
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setClicklistener();
		init();
	}

	public void init() {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_gameover, null);
		setContentView(view);

		globalV = (GlobalValues)getOwnerActivity().getApplication();
		sharedPreferences = getOwnerActivity().getSharedPreferences("game_info",MODE_PRIVATE);

		act = (GameActivity) getOwnerActivity();

		restartButton  = (ImageView) view.findViewById(R.id.over_restart_button);
		menuButton  = (ImageView) view.findViewById(R.id.over_menu_button);

		restartButton.setOnClickListener(new clickListener());
		menuButton.setOnClickListener(new clickListener());

		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		lp.width = (int) (d.widthPixels * 0.9); // 高度设置为屏幕的0.6
		dialogWindow.setAttributes(lp);
	}

}