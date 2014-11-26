package jp.eno.android.mytopicslibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ぼくトピ用のSQLiteOpenHelper
 * Created by eno314 on 2014/11/23.
 */
public class MyTopicsOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DB_NAME = "my_topics";

    private static final String SQL_SETTING_API_CREATE =
            "CREATE TABLE IF NOT EXISTS " + SettingApiColumns.TABLE_NAME + " (" +
                    SettingApiColumns._ID + " INTEGER PRIMARY KEY," +
                    SettingApiColumns.COLUMN_URL + " TEXT NOT NULL UNIQUE," +
                    SettingApiColumns.COLUMN_NAME + " TEXT NOT NULL);";

    private static final String SQL_ENTRY_API_CREATE =
            "CREATE TABLE IF NOT EXISTS " + EntryApiColumns.TABLE_NAME + " (" +
                    EntryApiColumns._ID + " INTEGER PRIMARY KEY," +
                    EntryApiColumns.COLUMN_URL + " TEXT NOT NULL, " +
                    EntryApiColumns.COLUMN_NAME + " TEXT NOT NULL, " +
                    EntryApiColumns.COLUMN_SETTING_API_ID + " INTEGER NOT NULL);";

    private static final String SQL_SETTING_API_DROP =
            "DROP TABLE IF EXISTS " + SettingApiColumns.TABLE_NAME + ";";

    private static final String SQL_ENTRY_API_DROP =
            "DROP TABLE IF EXISTS " + EntryApiColumns.TABLE_NAME + ";";

    /**
     * コンストラクタでDB名とDBバージョンを設定する
     */
    public MyTopicsOpenHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_SETTING_API_CREATE);
        db.execSQL(SQL_ENTRY_API_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_SETTING_API_DROP);
        db.execSQL(SQL_ENTRY_API_CREATE);
        onCreate(db);
    }
}
