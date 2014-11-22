package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.model.ApiList;
import jp.eno.android.mytopicslibrary.request.ApiListRequest;
import jp.eno.android.mytopicslibrary.volley.VolleyQueue;

/**
 * Created by eno314 on 2014/11/17.
 */
public class AddSettingApiActivity extends Activity {

    private static final String MESSAGE_TEXT_EMPTY = "URLを入力後に決定ボタンを押してください";
    private static final String MESSAGE_FAILED_REQUEST = "APIリクエストに失敗しました。通信状態や入力値を確認してください";

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_setting_api);

        // 最初からソフトウェアキーボードを表示させる
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        mEditText = (EditText) findViewById(R.id.add_setting_api_edit_text);
        // カーソルの位置を最後尾にする
        mEditText.setSelection(mEditText.getText().length());

        findViewById(R.id.add_setting_api_button_positive)
                .setOnClickListener(createPositiveButtonCLickListener());

        findViewById(R.id.add_setting_api_button_negative)
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
        final String editedText = mEditText.getText().toString();

        if (TextUtils.isEmpty(editedText)) {
            showMessage(MESSAGE_TEXT_EMPTY);
            return;
        }

        VolleyQueue.getQueue(getApplicationContext()).add(buildRequest(editedText));
    }

    private ApiListRequest buildRequest(String url) {
        return new ApiListRequest.Builder(url)
                .setListener(new Response.Listener<ApiList>() {
                    @Override
                    public void onResponse(ApiList response) {
                        showMessage(response.name);
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showMessage(MESSAGE_FAILED_REQUEST);
                    }
                })
                .build();
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
