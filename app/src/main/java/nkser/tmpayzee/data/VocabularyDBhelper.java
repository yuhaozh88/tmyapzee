package nkser.tmpayzee.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Adam_Yang on 2017/5/7.
 */

public class VocabularyDBhelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static String DATABASE_PATH = "/data/data/nkser.tmpayzee/databases/";
	public static final String DATABASE_NAME = "vocabulary.db";
	private Context myContext;

	private SQLiteDatabase myDataBase;

	public VocabularyDBhelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		myContext = context;
	}

	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();

		if(dbExist){
			//do nothing - database already exist
			Log.e("hehe", "caonimadashabi");
		}else{
			//By calling this method and empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();

			try {
				copyDataBase();
				Log.e("axiba","copied");
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase(){

		SQLiteDatabase checkDB = null;
		File f =null;

		try{
			String myPath = DATABASE_PATH + DATABASE_NAME;
			f = new File(myPath);
			if (!f.exists())
				return false;
			//checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(Exception e){
			//database does't exist yet.
		}

		return true;
//		if(checkDB != null){
//			checkDB.close();
//		}
//
//		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open("vocabulary.db");
		//InputStream myInput = myContext.getResources().openRawResource(R.raw.vocabulary);

		// Path to the just created empty db
		String outFileName = DATABASE_PATH + DATABASE_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {
		//Open the database
		String myPath = DATABASE_PATH + DATABASE_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

	}


	public SQLiteDatabase getMyDataBase() {
		return myDataBase;
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
