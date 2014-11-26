package jp.eno.android.mytopicslibrary.database;

import android.provider.BaseColumns;

/**
 * entry_apiテーブルのカラム定義
 * Created by eno314 on 2014/11/23.
 */
public class EntryApiColumns implements BaseColumns {

    public static final String TABLE_NAME = "entry_api";

    public static final String COLUMN_URL = "url";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_SETTING_API_ID = "setting_api_id";
}
