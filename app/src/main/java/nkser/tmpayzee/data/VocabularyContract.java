package nkser.tmpayzee.data;

/**
 * Created by Adam_Yang on 2017/5/7.
 */

public class VocabularyContract {

	public static class VocabularyEntry{
		public static final String TABLE_NAME = "vocabulary_table";
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_VOCABULARY = "vocabulary";
		public static final String COLUMN_LEVEL = "level";
		public static final String COLUMN_BELONGING = "belonging";

	}

	public static class LexiconEntry{
		public static final String TABLE_NAME = "lexicon";
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_NUM = "num";
	}

}
