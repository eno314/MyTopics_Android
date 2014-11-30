package jp.eno.android.mytopics.manual;

import android.database.Cursor;
import android.text.TextUtils;

import jp.eno.android.mytopicslibrary.database.EntryApiColumns;

/**
 * Created by eno314 on 2014/11/30.
 */
public class EntryApiDB {

    public long id;

    public String name;

    public String url;

    public long settingApiId;

    public EntryApiDB(Cursor cursor) {
        int index = cursor.getColumnIndex(EntryApiColumns._ID);
        if (index >= 0) {
            id = cursor.getLong(index);
        }

        index = cursor.getColumnIndex(EntryApiColumns.COLUMN_NAME);
        if (index >= 0) {
            name = cursor.getString(index);
        }

        index = cursor.getColumnIndex(EntryApiColumns.COLUMN_URL);
        if (index >= 0) {
            url = cursor.getString(index);
        }

        index = cursor.getColumnIndex(EntryApiColumns.COLUMN_SETTING_API_ID);
        if (index >= 0) {
            settingApiId = cursor.getLong(index);
        }
    }

    public boolean isValid() {
        if (id < 0) {
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            return false;
        }

        if (TextUtils.isEmpty(url)) {
            return false;
        }

        return true;
    }
}
