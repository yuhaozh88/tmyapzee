package nkser.tmpayzee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created by Adam_Yang on 2017/5/31.
 */

public class PauseDialog extends Activity {

	GlobalValues globalV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pausedialog);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_BACK) {
			globalV.IsPaused=false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
