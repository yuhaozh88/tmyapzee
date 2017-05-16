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
		/*
		sqLiteDatabase.close();
		try {
			InputStream myInput = myContext.getResources().openRawResource(R.raw.vocabulary);
			//Open your local db as the input stream

			// Path to the just created empty db
			String outFileName = DATABASE_PATH + DATABASE_NAME;

			//Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);

			//transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}

			//Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
		}
		catch (IOException e){
			Log.e("CAO","FILE NOT FOUND");
		}
		*/

		/*
		final String SQL_CREATE_VOCABULARY_TABLE =
				"CREATE TABLE " + VocabularyContract.VocabularyEntry.TABLE_NAME + " ("+
						VocabularyContract.VocabularyEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						VocabularyContract.VocabularyEntry.COLUMN_VOCABULARY + " VARCHAR(30) NOT NULL, " +
						VocabularyContract.VocabularyEntry.COLUMN_LEVEL + " INTEGER NOT NULL, " +
						" FOREIGN KEY (" + VocabularyContract.VocabularyEntry.COLUMN_BELONGING + ") REFERENCES "+
						VocabularyContract.LexiconEntry.TABLE_NAME + " (" + VocabularyContract.LexiconEntry.COLUMN_NAME + "); ";

		final String SQL_CREATE_LEXICON_TABLE =
				"CREATE TABLE " + VocabularyContract.LexiconEntry.TABLE_NAME + " ("+
						VocabularyContract.LexiconEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
						VocabularyContract.LexiconEntry.COLUMN_NAME + " VARCHAR(20) NOT NULL, " +
						VocabularyContract.LexiconEntry.COLUMN_NUM + " INTEGER NOT NULL); ";

		sqLiteDatabase.execSQL(SQL_CREATE_LEXICON_TABLE);
		sqLiteDatabase.execSQL(SQL_CREATE_VOCABULARY_TABLE);
		*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		/*
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VocabularyContract.LexiconEntry.TABLE_NAME);
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VocabularyContract.VocabularyEntry.TABLE_NAME);
		onCreate(sqLiteDatabase);
		*/
	}
}
