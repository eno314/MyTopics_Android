package jp.eno.android.mytopics.setting;

import android.database.Cursor;
import android.text.TextUtils;

import jp.eno.android.mytopicslibrary.database.SettingApiColumns;

/**
 * Created by eno314 on 2014/11/16.
 */
public class SettingApi {

    /**
     * DBのID
     */
    public long id;
    /**
     * リストに表示するAPI名
     */
    public String name;

    /**
     * APIのurl
     */
    public String url;

    public SettingApi(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public SettingApi(Cursor cursor) {
        int index = cursor.getColumnIndex(SettingApiColumns._ID);
        if (index >= 0) {
            id = cursor.getLong(index);
        }

        index = cursor.getColumnIndex(SettingApiColumns.COLUMN_NAME);
        if (index >= 0) {
            name = cursor.getString(index);
        }

        index = cursor.getColumnIndex(SettingApiColumns.COLUMN_URL);
        if (index >= 0) {
            url = cursor.getString(index);
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
