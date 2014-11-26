package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.database.MyTopicsOpenHelper;
import jp.eno.android.mytopicslibrary.database.SettingApiColumns;

/**
 * 自分が入力した設定API一覧を表示するFragment
 * Created by eno314 on 2014/11/16.
 */
public class SettingListFragment extends Fragment implements SettingListCellListener {

    private SettingListCursorAdapter mAdapter;

    public SettingListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        final Context context = activity.getApplicationContext();
        mAdapter = new SettingListCursorAdapter(context, read(context), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final ListView listView = (ListView)
                inflater.inflate(R.layout.fragment_setting_list, container, false);
        listView.setAdapter(mAdapter);

        return listView;
    }

    private Cursor read(Context context) {
        MyTopicsOpenHelper helper = new MyTopicsOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                SettingApiColumns._ID,
                SettingApiColumns.COLUMN_NAME,
                SettingApiColumns.COLUMN_URL
        };

        return db.query(SettingApiColumns.TABLE_NAME, projection, null, null, null, null, null);
    }

    @Override
    public void onClickCell(SettingApi settingApi) {
        Log.d("AAAAA", "onClickCell : " + settingApi.name);
    }

    @Override
    public void onClickDeleteButton(SettingApi settingApi) {
        Log.d("AAAAA", "onClickDeleteButton : " + settingApi.name);
    }
}
