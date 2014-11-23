package jp.eno.android.mytopicslibrary.model;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by eno314 on 2014/11/16.
 */
public class ApiList implements RequestResponseModel {

    /**
     * APIリストの名前
     */
    public String name;

    /**
     * 自作APIのリスト
     */
    public ArrayList<EntryApi> list;

    @Override
    public boolean isValid() {
        if (TextUtils.isEmpty(name)) {
            return false;
        }

       // リストが0件は無効
        if (list == null || list.size() == 0) {
            return false;
        }

        for (EntryApi api : list) {
            // 1件でも無効なデータがあればエラー扱い
            if (!api.isValid()) {
                return false;
            }
        }

        return true;
    }
}
