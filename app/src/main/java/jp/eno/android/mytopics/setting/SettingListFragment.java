package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.database.SettingApiColumns;

/**
 * 自分が入力した設定API一覧を表示するFragment
 * Created by eno314 on 2014/11/16.
 */
public class SettingListFragment extends Fragment
        implements SettingListCellListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 0;

    private SettingListCursorAdapter mAdapter;

    public SettingListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mAdapter = new SettingListCursorAdapter(getContext(), this);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        getLoaderManager().destroyLoader(LOADER_ID);
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

    private Context getContext() {
        return getActivity().getApplicationContext();
    }

    private ContentResolver getResolver() {
        return getActivity().getContentResolver();
    }

    @Override
    public void onClickCell(SettingApi settingApi) {
        Log.d("AAAAA", "onClickCell : " + settingApi.name);
    }

    @Override
    public void onClickDeleteButton(SettingApi settingApi) {
        // 削除処理の実行
        final Uri uri = SettingApiProvider.getContentUri();
        final String selection = SettingApiColumns._ID + " = ?";
        final String[] args = {
                String.valueOf(settingApi.id)
        };

        final int deleteCount = getActivity().getContentResolver().delete(uri, selection, args);

        if (deleteCount == 0) {
            Toast.makeText(getActivity(), "削除に失敗しました", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getContext(), SettingApiProvider.getContentUri(), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);
    }
}
