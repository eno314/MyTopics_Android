package jp.eno.android.mytopics.manual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.volley.VolleyQueue;

/**
 * Created by eno314 on 2014/11/29.
 */
public class AddManualActivity extends FragmentActivity {

    /**
     * APIのURLの入力用EditText
     */
    private EditText mEditUrlText;

    /**
     * API名入力用のEditText
     */
    private EditText mEditNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manual);

        // 最初からソフトウェアキーボードを表示させる
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        mEditUrlText = (EditText) findViewById(R.id.add_manual_edit_text_url);
        mEditUrlText.setSelection(mEditUrlText.getText().length());

        mEditNameText = (EditText) findViewById(R.id.add_manual_edit_text_name);

        findViewById(R.id.add_manual_button_positive)
                .setOnClickListener(createPositiveButtonCLickListener());

        findViewById(R.id.add_manual_button_negative)
                .setOnClickListener(createNegativeButtonClickListener());
    }

    private View.OnClickListener createPositiveButtonCLickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPositiveButton();
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

    /**
     * 決定ボタンをおした時の処理
     */
    private void onClickPositiveButton() {
        Log.d("BBBBB", "url : " + mEditUrlText.getText().toString());
        Log.d("BBBBB", "name : " + mEditNameText.getText().toString());
    }

    /**
     * ApiListActivityに遷移するためのメソッド
     *
     * @param activity 起動元のアクティビティ
     */
    public static void start(Activity activity) {
        final Intent intent = new Intent(activity, AddManualActivity.class);
        activity.startActivity(intent);
    }
}
