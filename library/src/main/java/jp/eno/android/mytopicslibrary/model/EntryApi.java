package jp.eno.android.mytopicslibrary.model;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by eno314 on 2014/11/16.
 */
public class EntryApi implements RequestResponseModel, Serializable {

    /**
     * API名
     */
    public String name;

    /**
     * APIのリクエストURL
     */
    public String url;

    @Override
    public boolean isValid() {
        if (TextUtils.isEmpty(name)) {
            return false;
        }

        if (TextUtils.isEmpty(url)) {
            return false;
        }

        return true;
    }
}
