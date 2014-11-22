package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import jp.eno.android.mytopics.R;

/**
 * Created by eno314 on 2014/11/17.
 */
public class AddSettingApiActivity extends Activity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_setting_api);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        mEditText = (EditText) findViewById(R.id.add_setting_api_edit_text);
        // カーソルの位置を最後尾にする
        mEditText.setSelection(mEditText.getText().length());

        findViewById(R.id.add_setting_api_button_positive)
                .setOnClickListener(createPositiveButtonCLickListener());

        findViewById(R.id.add_setting_api_button_negative)
                .setOnClickListener(createNegativeButtonClickListener());
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

    private View.OnClickListener createPositiveButtonCLickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AAAAA", mEditText.getText().toString());
            }
        };
    }

    private View.OnClickListener createNegativeButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }
}
