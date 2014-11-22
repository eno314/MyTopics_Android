package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import jp.eno.android.mytopics.R;

/**
 * Created by eno314 on 2014/11/17.
 */
public class AddSettingApiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_setting_api);
    }

    /**
     * ApiListActivityに遷移するためのメソッド
     *
     * @param activity 起動元のアクティビティ
     */
    public static void start(Activity activity) {
        final Intent intent = new Intent(activity, AddSettingApiActivity.class);
        activity.startActivity(intent);
    }
}
