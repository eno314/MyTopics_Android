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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.model.Entry;
import jp.eno.android.mytopicslibrary.request.EntryRequest;
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
        final String url = mEditUrlText.getText().toString();
        final String name = mEditNameText.getText().toString();

        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(name)) {
            final String message = getResources().getString(R.string.add_manual_message_empty);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        getQueue().add(buildRequest(url));
    }

    private RequestQueue getQueue() {
        return VolleyQueue.getQueue(getApplicationContext());
    }

    private EntryRequest buildRequest(String url) {
        return new EntryRequest.Builder(url)
                .setListener(new Response.Listener<List<Entry>>() {
                    @Override
                    public void onResponse(List<Entry> response) {
                        Toast.makeText(getApplicationContext(), "リクエスト成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        final String message = getResources()
                                .getString(R.string.add_common_message_request_failed);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }).build();
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
