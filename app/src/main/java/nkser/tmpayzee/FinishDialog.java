package nkser.tmpayzee;

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
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Adam_Yang on 2017/6/2.
 */

public class FinishDialog extends Dialog {

	Context context;
	GlobalValues globalV;
	SharedPreferences sharedPreferences;
	GameActivity act;

	private ImageView continueButton;
	private ImageView restartButton;
	private ImageView menuButton;

	private ClickListenerInterface clickListenerInterface;

	public FinishDialog(Context context) {
		super(context);
		this.context = context;
	}

	public interface ClickListenerInterface {
		void doContinue();

		void doRestart();

		void doMenu();
	}

	public void setClicklistener() {
		ClickListenerInterface inter = new ClickListenerInterface() {
			@Override
			public void doContinue() {
				globalV.level++;
				globalV.resume_or_not = true;
				act.countTimer();
				globalV.refreshLocation((GameActivity) getOwnerActivity());
				globalV.refreshWords(getOwnerActivity(), (GameActivity) getOwnerActivity());
				dismiss();
			}

			@Override
			public void doRestart() {
				globalV.resume_or_not = false;
				globalV.refreshLocation((GameActivity) getOwnerActivity());
				act.countTimer();
				globalV.refreshWords(getOwnerActivity(), (GameActivity) getOwnerActivity());
				dismiss();
			}

			@Override
			public void doMenu() {
				Intent intent = new Intent(getOwnerActivity(), MainActivity.class);
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
				case R.id.finish_menu_button:
					clickListenerInterface.doMenu();
					break;
				case R.id.finish_restart_button:
					clickListenerInterface.doRestart();
					break;
				case R.id.finish_continue_button:
					clickListenerInterface.doContinue();
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
		View view = inflater.inflate(R.layout.dialog_finish, null);
		setContentView(view);

		globalV = (GlobalValues) getOwnerActivity().getApplication();
		act = (GameActivity)getOwnerActivity();
		sharedPreferences = getOwnerActivity().getSharedPreferences("game_info", MODE_PRIVATE);

		continueButton = (ImageView) view.findViewById(R.id.finish_continue_button);
		restartButton = (ImageView) view.findViewById(R.id.finish_restart_button);
		menuButton = (ImageView) view.findViewById(R.id.finish_menu_button);


		globalV.coordinate_x = globalV.start_x;
		globalV.coordinate_y = globalV.start_y;
		globalV.IsPaused = false;
		globalV.timer = globalV.timeRestrict;

		if (globalV.level == 4){
			continueButton.setVisibility(View.INVISIBLE);
		}

		continueButton.setOnClickListener(new clickListener());
		restartButton.setOnClickListener(new clickListener());
		menuButton.setOnClickListener(new clickListener());

		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		lp.width = (int) (d.widthPixels*0.9); // 高度设置为屏幕的0.6
		dialogWindow.setAttributes(lp);
	}
}