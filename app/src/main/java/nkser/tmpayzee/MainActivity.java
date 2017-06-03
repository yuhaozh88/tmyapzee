package nkser.tmpayzee;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.appsee.Appsee;

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

		Appsee.start("b60e4460e4a948c39987941614027495");

		if (! GlobalValues.situation){
			GlobalValues.volume=0.5f;
			GlobalValues.player=new MediaPlayer();
			GlobalValues.player=MediaPlayer.create(MainActivity.this,R.raw.bgm);//创建媒体播放器
			GlobalValues.player.setLooping(true);
			GlobalValues.player.setVolume(GlobalValues.volume,GlobalValues.volume);
			GlobalValues.situation=true;
		}

		GlobalValues.player.start();


		ImageView image_start = (ImageView) findViewById(R.id.main_start);
		image_start.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,GameActivity.class);
				intent.putExtra("start_or_resume", true);
				startActivity(intent);
			}
		});

		ImageView image_resume = (ImageView) findViewById(R.id.main_resume);
		image_resume.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, GameActivity.class);
				intent.putExtra("start_or_resume", false);
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
		image_rank.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this, RankActivity.class);
				GlobalValues.player.pause();
				startActivity(intent);
			}
		});

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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		GlobalValues.situation=false;
		GlobalValues.player.stop();
	}

	@Override
	protected void onResume() {
		GlobalValues.player.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		GlobalValues.player.pause();
		super.onPause();
	}
}