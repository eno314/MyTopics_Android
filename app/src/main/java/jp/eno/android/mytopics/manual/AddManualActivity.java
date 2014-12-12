package jp.eno.android.mytopics.manual;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.database.EntryApiColumns;
import jp.eno.android.mytopicslibrary.model.Entry;
import jp.eno.android.mytopicslibrary.provider.EntryApiProvider;
import jp.eno.android.mytopicslibrary.request.EntryRequest;
import jp.eno.android.mytopicslibrary.volley.VolleyQueue;

/**
 * Created by eno314 on 2014/11/29.
 */
public class AddManualActivity extends FragmentActivity {

    /**
     * APIのURLの入力用EditText
     */
    @InjectView(R.id.add_manual_edit_text_url) EditText mEditUrlText;

    /**
     * API名入力用のEditText
     */
    @InjectView(R.id.add_manual_edit_text_name) EditText mEditNameText;

    /**
     * プログレスダイアログ
     */
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manual);

        ButterKnife.inject(this);

        // 最初からソフトウェアキーボードを表示させる
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        mEditUrlText.setSelection(mEditUrlText.getText().length());

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.add_manual_loading_title));
        mProgressDialog.setMessage(getString(R.string.add_manual_loading_message));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
    }

    /**
     * 決定ボタンをおした時の処理
     */
    @OnClick(R.id.add_manual_button_positive)
    void onClickPositiveButton() {
        final String url = mEditUrlText.getText().toString();
        final String name = mEditNameText.getText().toString();

        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(name)) {
            final String message = getString(R.string.add_manual_message_empty);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            return;
        }

        getQueue().add(buildRequest(url));
        mProgressDialog.show();
    }

    /**
     * キャンセルボタンクリック時の処理
     */
    @OnClick(R.id.add_manual_button_negative)
    void onClickNegativeButton() {
        finish();
    }

    private RequestQueue getQueue() {
        return VolleyQueue.getQueue(getApplicationContext());
    }

    private EntryRequest buildRequest(String url) {
        return new EntryRequest.Builder(url)
                .setListener(new Response.Listener<List<Entry>>() {
                    @Override
                    public void onResponse(List<Entry> response) {
                        mProgressDialog.dismiss();
                        registerApi();
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressDialog.dismiss();
                        final String message = getString(R.string.add_common_message_request_failed);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }).build();
    }

    private void registerApi() {
        final ContentValues values = new ContentValues();
        values.put(EntryApiColumns.COLUMN_URL, mEditUrlText.getText().toString());
        values.put(EntryApiColumns.COLUMN_NAME, mEditNameText.getText().toString());

        final Uri uri = getContentResolver().insert(EntryApiProvider.getContentUri(), values);

        if (ContentUris.parseId(uri) < 0) {
            final String message = getString(R.string.add_manual_message_failed_insert);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        } else {
            final String message = getString(R.string.add_manual_message_success_insert);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
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
