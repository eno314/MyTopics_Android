package jp.eno.android.mytopics.manual;

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
 * Created by eno314 on 2014/11/30.
 */
public class ManualCursorAdapter extends CursorAdapter {

    private final ManualCellListener mListener;

    public ManualCursorAdapter(Context context, ManualCellListener listener) {
        super(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListener = listener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_manual_cell, null);

        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.mTextView = (TextView) view.findViewById(R.id.manual_cell_text);
        viewHolder.mButton = (Button) view.findViewById(R.id.manual_cell_delete_button);

        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final EntryApiDB entryApi = new EntryApiDB(cursor);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickCell(entryApi);
            }
        });

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mTextView.setText(entryApi.name);
        viewHolder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickDeleteButton(entryApi);
            }
        });
    }

    private static class ViewHolder {
        private TextView mTextView;
        private Button mButton;
    }
}
