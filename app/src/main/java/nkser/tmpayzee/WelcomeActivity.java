package nkser.tmpayzee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.appsee.Appsee;

public class WelcomeActivity extends Activity {

	GlobalValues globalV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Appsee.start("b60e4460e4a948c39987941614027495");
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		}, 1500);

		globalV = (GlobalValues) getApplication();
		globalV.whether = false;
	}

}
