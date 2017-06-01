package nkser.tmpayzee;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import nkser.tmpayzee.GlobalValues;
import nkser.tmpayzee.R;

//import com.example.adam_yang.tmpayzee.R;

///**
// * A {@link PreferenceActivity} that presents a set of application settings. On
// * handset devices, settings are presented as a single list. On tablets,
// * settings are split by category, with category headers shown to the left of
// * the list of settings.
// * <p>
// * See <a href="http://developer.android.com/design/patterns/settings.html">
// * Android Design: Settings</a> for design guidelines and the <a
// * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
// * API Guide</a> for more information on developing a Settings UI.
// */
//public class SettingsActivity extends AppCompatPreferenceActivity {
//	/**
//	 * A preference value change listener that updates the preference's summary
//	 * to reflect its new value.
//	 */
//	private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
//		@Override
//		public boolean onPreferenceChange(Preference preference, Object value) {
//			String stringValue = value.toString();
//
//			if (preference instanceof ListPreference) {
//				// For list preferences, look up the correct display value in
//				// the preference's 'entries' list.
//				ListPreference listPreference = (ListPreference) preference;
//				int index = listPreference.findIndexOfValue(stringValue);
//
//				// Set the summary to reflect the new value.
//				preference.setSummary(
//						index >= 0
//								? listPreference.getEntries()[index]
//								: null);
//
//			} else if (preference instanceof RingtonePreference) {
//				// For ringtone preferences, look up the correct display value
//				// using RingtoneManager.
//				if (TextUtils.isEmpty(stringValue)) {
//					// Empty values correspond to 'silent' (no ringtone).
//					preference.setSummary(R.string.pref_ringtone_silent);
//
//				} else {
//					Ringtone ringtone = RingtoneManager.getRingtone(
//							preference.getContext(), Uri.parse(stringValue));
//
//					if (ringtone == null) {
//						// Clear the summary if there was a lookup error.
//						preference.setSummary(null);
//					} else {
//						// Set the summary to reflect the new ringtone display
//						// name.
//						String name = ringtone.getTitle(preference.getContext());
//						preference.setSummary(name);
//					}
//				}
//
//			} else {
//				// For all other preferences, set the summary to the value's
//				// simple string representation.
//				preference.setSummary(stringValue);
//			}
//			return true;
//		}
//	};
//
//	/**
//	 * Helper method to determine if the device has an extra-large screen. For
//	 * example, 10" tablets are extra-large.
//	 */
//	private static boolean isXLargeTablet(Context context) {
//		return (context.getResources().getConfiguration().screenLayout
//				& Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
//	}
//
//	/**
//	 * Binds a preference's summary to its value. More specifically, when the
//	 * preference's value is changed, its summary (line of text below the
//	 * preference title) is updated to reflect the value. The summary is also
//	 * immediately updated upon calling this method. The exact display format is
//	 * dependent on the type of preference.
//	 *
//	 * @see #sBindPreferenceSummaryToValueListener
//	 */
//	private static void bindPreferenceSummaryToValue(Preference preference) {
//		// Set the listener to watch for value changes.
//		preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
//
//		// Trigger the listener immediately with the preference's
//		// current value.
//		sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
//				PreferenceManager
//						.getDefaultSharedPreferences(preference.getContext())
//						.getString(preference.getKey(), ""));
//	}
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setupActionBar();
//	}
//
//	/**
//	 * Set up the {@link android.app.ActionBar}, if the API is available.
//	 */
//	private void setupActionBar() {
//		ActionBar actionBar = getSupportActionBar();
//		if (actionBar != null) {
//			// Show the Up button in the action bar.
//			actionBar.setDisplayHomeAsUpEnabled(true);
//		}
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean onIsMultiPane() {
//		return isXLargeTablet(this);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//	public void onBuildHeaders(List<Header> target) {
//		loadHeadersFromResource(R.xml.pref_headers, target);
//	}
//
//	/**
//	 * This method stops fragment injection in malicious applications.
//	 * Make sure to deny any unknown fragments here.
//	 */
//	protected boolean isValidFragment(String fragmentName) {
//		return PreferenceFragment.class.getName().equals(fragmentName)
//				|| GeneralPreferenceFragment.class.getName().equals(fragmentName)
//				|| DataSyncPreferenceFragment.class.getName().equals(fragmentName)
//				|| NotificationPreferenceFragment.class.getName().equals(fragmentName);
//	}
//
//	/**
//	 * This fragment shows general preferences only. It is used when the
//	 * activity is showing a two-pane settings UI.
//	 */
//	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//	public static class GeneralPreferenceFragment extends PreferenceFragment {
//		@Override
//		public void onCreate(Bundle savedInstanceState) {
//			super.onCreate(savedInstanceState);
//			addPreferencesFromResource(R.xml.pref_general);
//			setHasOptionsMenu(true);
//
//			// Bind the summaries of EditText/List/Dialog/Ringtone preferences
//			// to their values. When their values change, their summaries are
//			// updated to reflect the new value, per the Android Design
//			// guidelines.
//			bindPreferenceSummaryToValue(findPreference("example_text"));
//			bindPreferenceSummaryToValue(findPreference("example_list"));
//		}
//
//		@Override
//		public boolean onOptionsItemSelected(MenuItem item) {
//			int id = item.getItemId();
//			if (id == android.R.id.home) {
//				startActivity(new Intent(getActivity(), SettingsActivity.class));
//				return true;
//			}
//			return super.onOptionsItemSelected(item);
//		}
//	}
//
//	/**
//	 * This fragment shows notification preferences only. It is used when the
//	 * activity is showing a two-pane settings UI.
//	 */
//	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//	public static class NotificationPreferenceFragment extends PreferenceFragment {
//		@Override
//		public void onCreate(Bundle savedInstanceState) {
//			super.onCreate(savedInstanceState);
//			addPreferencesFromResource(R.xml.pref_notification);
//			setHasOptionsMenu(true);
//
//			// Bind the summaries of EditText/List/Dialog/Ringtone preferences
//			// to their values. When their values change, their summaries are
//			// updated to reflect the new value, per the Android Design
//			// guidelines.
//			bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
//		}
//
//		@Override
//		public boolean onOptionsItemSelected(MenuItem item) {
//			int id = item.getItemId();
//			if (id == android.R.id.home) {
//				startActivity(new Intent(getActivity(), SettingsActivity.class));
//				return true;
//			}
//			return super.onOptionsItemSelected(item);
//		}
//	}
//
//	/**
//	 * This fragment shows data and sync preferences only. It is used when the
//	 * activity is showing a two-pane settings UI.
//	 */
//	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//	public static class DataSyncPreferenceFragment extends PreferenceFragment {
//		@Override
//		public void onCreate(Bundle savedInstanceState) {
//			super.onCreate(savedInstanceState);
//			addPreferencesFromResource(R.xml.pref_data_sync);
//			setHasOptionsMenu(true);
//
//			// Bind the summaries of EditText/List/Dialog/Ringtone preferences
//			// to their values. When their values change, their summaries are
//			// updated to reflect the new value, per the Android Design
//			// guidelines.
//			bindPreferenceSummaryToValue(findPreference("sync_frequency"));
//		}
//
//		@Override
//		public boolean onOptionsItemSelected(MenuItem item) {
//			int id = item.getItemId();
//			if (id == android.R.id.home) {
//				startActivity(new Intent(getActivity(), SettingsActivity.class));
//				return true;
//			}
//			return super.onOptionsItemSelected(item);
//		}
//	}
//}
public class SettingsActivity extends Activity {

	//    private float down= AudioManager.ADJUST_LOWER;
//    private float up=AudioManager.ADJUST_RAISE;
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
		//RadioButton gre=(RadioButton) findViewById(R.id.choosegre);
		RadioButton cet4=(RadioButton) findViewById(R.id.choosecet4);
		RadioButton cet6=(RadioButton) findViewById(R.id.choosecet6);

		preferences=getSharedPreferences("game_info",MODE_PRIVATE);
		editor=preferences.edit();

//		GlobalValues.player=new MediaPlayer();
//		GlobalValues.player=MediaPlayer.create(SettingsActivity.this,R.raw.bgm);//创建媒体播放器
//		GlobalValues.player.setLooping(true);
//		GlobalValues.player.setVolume(volume,volume);
//		GlobalValues.player.start();


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

//        gre.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

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
