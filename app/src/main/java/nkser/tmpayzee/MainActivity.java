package nkser.tmpayzee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import nkser.tmpayzee.data.VocabularyDBhelper;

public class MainActivity extends Activity {

	private VocabularyDBhelper dBhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
			Set intent
		 */
		ImageView image_start = (ImageView) findViewById(R.id.main_start);
		image_start.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,GameActivity.class);
				startActivity(intent);
			}
		});

		ImageView image_resume = (ImageView) findViewById(R.id.main_resume);
		image_resume.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});

		ImageView image_setting = (ImageView) findViewById(R.id.main_setting);
		image_setting.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
				startActivity(intent);
			}
		});

		ImageView image_rank = (ImageView) findViewById(R.id.main_rank);
		ImageView image_quit = (ImageView) findViewById(R.id.main_quit);
		image_quit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				finish();
			}
		});


		dBhelper = new VocabularyDBhelper(getBaseContext());
	}

}
