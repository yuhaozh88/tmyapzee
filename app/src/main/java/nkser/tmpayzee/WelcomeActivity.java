package nkser.tmpayzee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		}, 2000);
	//	mHandler.sendEmptyMessageAtTime(GOTO_MAIN_ACTIVITY, 3000);//3秒跳转
	}

	private static final int GOTO_MAIN_ACTIVITY = 0;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case GOTO_MAIN_ACTIVITY:
					Intent intent = new Intent();
					intent.setClass(WelcomeActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
			}
		}
	};
}
