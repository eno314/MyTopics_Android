package jp.eno.android.mytopics.manual;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.provider.EntryApiProvider;

/**
 * 手入力したAPIを表示するフラグメント
 * Created by eno314 on 2014/11/23.
 */
public class ManualFragment extends Fragment
        implements ManualCellListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 1;

    private ManualCursorAdapter mAdapter;

    public ManualFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mAdapter = new ManualCursorAdapter(getContext(), this);;
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
                inflater.inflate(R.layout.fragment_manual, container, false);
        listView.setAdapter(mAdapter);

        return listView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getContext(), EntryApiProvider.getContentUri(), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onClickCell(EntryApiDB entryApi) {

    }

    @Override
    public void onClickDeleteButton(EntryApiDB entryApi) {

    }

    private Context getContext() {
        return getActivity().getApplicationContext();
    }
}
