package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import jp.eno.android.mytopics.R;

/**
 * Created by eno314 on 2014/11/23.
 */
public class ConfirmAddSettingApiDialog extends DialogFragment {

    private static final String ARGUMENT_API_NAME = "ARGUMENT_API_NAME";

    private boolean mIsClickPositiveButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mIsClickPositiveButton = false;

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String message = createMessage(getArguments().getString(ARGUMENT_API_NAME));
        final String positiveButtonText = getString(R.string.add_common_button_positive);
        final String negativeButtonText = getString(R.string.add_common_button_negative);

        builder.setTitle(getString(R.string.add_setting_api_dialog_title))
                .setMessage(message)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mIsClickPositiveButton = true;
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing...
                    }
                });
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        final Activity parentActivity = getActivity();

        if (parentActivity instanceof Listener) {
            ((Listener) parentActivity).onDismissDialog(mIsClickPositiveButton);
        }
    }

    private String createMessage(String apiName) {
        final String messageFormat = getString(R.string.add_setting_api_dialog_message_format);
        return String.format(messageFormat, apiName);
    }

    /**
     * ConfirmAddSettingApiDialogインスタンス生成メソッド
     *
     * @param name 設定APIの名前
     * @return ConfirmAddSettingApiDialogのインスタンス
     */
    public static ConfirmAddSettingApiDialog newInstance(String name) {
        final ConfirmAddSettingApiDialog dialog = new ConfirmAddSettingApiDialog();

        Bundle args = new Bundle();
        args.putString(ARGUMENT_API_NAME, name);
        dialog.setArguments(args);

        return dialog;
    }

    /**
     * ダイアログのリスナー
     */
    public interface Listener {

        /**
         * ダイアログのボタンをおした時のリスナー
         *
         * @param isClickPositiveButton ポジティブボタンを押したかどうかのフラグ
         */
        void onDismissDialog(boolean isClickPositiveButton);
    }
}
