package jp.eno.android.mytopics.setting;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by eno314 on 2014/11/17.
 */
public class SettingApiListDao {

    private static final String SHARE_NAME = "SettingApiListDao";

    SharedPreferences mPreferences;

    public SettingApiListDao(Context context) {
        mPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
    }

    public void save(List<SettingApi> settingApiList) {
        // settingApiListをjson文字列に変換
        // json文字列として保存
    }

    public List<SettingApi> get() {
        // 保存しているjson文字列を取得
        // json文字列をリストに変換
        return null;
    }
}
