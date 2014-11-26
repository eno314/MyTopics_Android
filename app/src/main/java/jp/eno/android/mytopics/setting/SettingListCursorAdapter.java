package jp.eno.android.mytopics.setting;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import jp.eno.android.mytopics.R;

/**
 * Created by eno314 on 2014/11/26.
 */
public class SettingListCursorAdapter extends CursorAdapter {

    private static final int VIEW_TAG_SETTING_API = 0;
    private static final int VIEW_TAG_HOLDER = 1;

    private final SettingListCellListener mListener;

    public SettingListCursorAdapter(Context context, Cursor c, SettingListCellListener listener) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListener = listener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_setting_list_cell, null);

        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.mTextView = (TextView) view.findViewById(R.id.setting_list_cell_text);
        viewHolder.mButton = (Button) view.findViewById(R.id.setting_list_cell_delete_button);

        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final SettingApi settingApi = new SettingApi(cursor);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickCell(settingApi);
            }
        });

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mTextView.setText(settingApi.name);
        viewHolder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickDeleteButton(settingApi);
            }
        });
    }

    private static class ViewHolder {
        private TextView mTextView;
        private Button mButton;
    }
}
