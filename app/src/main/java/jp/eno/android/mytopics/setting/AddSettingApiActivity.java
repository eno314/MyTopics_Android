package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.database.SettingApiColumns;
import jp.eno.android.mytopicslibrary.model.ApiList;
import jp.eno.android.mytopicslibrary.provider.SettingApiProvider;
import jp.eno.android.mytopicslibrary.request.ApiListRequest;
import jp.eno.android.mytopicslibrary.volley.VolleyQueue;

/**
 * Created by eno314 on 2014/11/17.
 */
public class AddSettingApiActivity extends FragmentActivity
        implements ConfirmAddSettingApiDialog.Listener {

    /**
     * APIのURLの入力用EditText
     */
    private EditText mEditText;

    /**
     * 入力したAPIからのレスポンスをパースしたものを置く場所
     */
    private ApiList mReceivedApiList;

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

    /**
     * ダイアログが消えた時のコールバック
     *
     * @param isClickPositiveButton ポジティブボタンを押したかどうかのフラグ
     */
    @Override
    public void onDismissDialog(boolean isClickPositiveButton) {
        if (mReceivedApiList == null) {
            showMessage("illegal statement");
            return;
        }

        if (isClickPositiveButton) {
            execInsert(mEditText.getText().toString(), mReceivedApiList);
        }

        mReceivedApiList = null;
    }

    private void execInsert(String url, ApiList apiList) {
        final long rowId = insertSettingApi(url, apiList.name);
        if (rowId < 0) {
            showMessage(getResources().getString(R.string.add_setting_api_message_failed_insert));
        } else {
            showMessage(getResources().getString(R.string.add_setting_api_message_success_insert));
        }
    }

    private long insertSettingApi(String url, String name) {
        final ContentValues values = new ContentValues();
        values.put(SettingApiColumns.COLUMN_URL, url);
        values.put(SettingApiColumns.COLUMN_NAME, name);

        final Uri uri = getContentResolver().insert(SettingApiProvider.getContentUri(), values);
        return ContentUris.parseId(uri);
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
            showMessage(getString(R.string.add_setting_api_message_text_empty));
            return;
        }

        VolleyQueue.getQueue(getApplicationContext()).add(buildRequest(editedText));
    }

    private ApiListRequest buildRequest(String url) {
        return new ApiListRequest.Builder(url)
                .setListener(new Response.Listener<ApiList>() {
                    @Override
                    public void onResponse(ApiList response) {
                        onReceiveResponse(response);
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showMessage(getString(R.string.add_common_message_request_failed));
                    }
                })
                .build();
    }

    private void onReceiveResponse(ApiList apiList) {
        mReceivedApiList = apiList;

        final ConfirmAddSettingApiDialog dialog = ConfirmAddSettingApiDialog.newInstance(apiList.name);
        dialog.show(getSupportFragmentManager(), null);
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
