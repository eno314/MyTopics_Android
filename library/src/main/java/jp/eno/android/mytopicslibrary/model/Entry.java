package jp.eno.android.mytopicslibrary.model;

import android.text.TextUtils;

/**
 * Created by eno314 on 2014/11/16.
 */
public class Entry implements RequestResponseModel {

    public String imageUrl;

    public String title;

    public String url;

    @Override
    public boolean isValid() {
        if (TextUtils.isEmpty(title)) {
            return false;
        }

        if (TextUtils.isEmpty(url)) {
            return false;
        }

        return true;
    }
}
