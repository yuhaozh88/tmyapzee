package nkser.tmpayzee;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		//volume=0.5f;//设置默认打开的音量
		GlobalValues.player.start();

		Button back=(Button) findViewById(R.id.backbutton);
		Button volumeDown=(Button)findViewById(R.id.volumedown);
		Button volumeUp=(Button)findViewById(R.id.volumeup);
		RadioButton toefl=(RadioButton) findViewById(R.id.choosetoefl);
		RadioButton gaokao=(RadioButton) findViewById(R.id.choosegaokao);
		RadioButton cet4=(RadioButton) findViewById(R.id.choosecet4);
		RadioButton cet6=(RadioButton) findViewById(R.id.choosecet6);

		preferences=getSharedPreferences("game_info",MODE_PRIVATE);
		editor=preferences.edit();


		back.setOnClickListener(new View.OnClickListener(){//返回键的功能
			@Override
			public void onClick(View v) {
				finish();//目前的back键是退出程序
			}
		});

		volumeDown.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (GlobalValues.volume>0.0f){
					GlobalValues.volume-=0.1f;
					GlobalValues.player.setVolume(GlobalValues.volume,GlobalValues.volume);
					Toast.makeText(SettingsActivity.this,String.valueOf((int)(10*GlobalValues.volume)),Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(SettingsActivity.this,"MIN", Toast.LENGTH_SHORT).show();
				}
			}
		});

		volumeUp.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (GlobalValues.volume<1.0f){
					GlobalValues.volume+=0.1f;
					GlobalValues.player.setVolume(GlobalValues.volume,GlobalValues.volume);
					Toast.makeText(SettingsActivity.this,String.valueOf((int)(10*GlobalValues.volume)),Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(SettingsActivity.this,"MAX",Toast.LENGTH_SHORT).show();
				}
			}
		});

		toefl.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				editor.putString("lexicon","toefl");
			}
		});

		gaokao.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				editor.putString("lexicon","gaokao");
			}
		});

		cet4.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				editor.putString("lexicon","cet4");
			}
		});

		cet6.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				editor.putString("lexicon","cet6");
			}
		});
	}
}
