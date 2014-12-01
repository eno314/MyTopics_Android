package jp.eno.android.mytopics.entry;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.model.Entry;
import jp.eno.android.mytopicslibrary.request.EntryRequest;
import jp.eno.android.mytopicslibrary.volley.ImageLoaderManager;
import jp.eno.android.mytopicslibrary.volley.VolleyQueue;

/**
 * Created by eno314 on 2014/11/16.
 */
public class EntryFragment extends Fragment {

    private static final String ARGUMENT_API_URL = "ARGUMENT_API_URL";

    private EntryListAdapter mAdapter;
    private EntryRequest mRequest;

    public EntryFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView)
                inflater.inflate(R.layout.fragment_entry, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new EntryListAdapter(createListener(), getImageLoader());
        recyclerView.setAdapter(mAdapter);

        return recyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (needRefresh()) {
            refresh();
        }
    }

    private String getApiUrl() {
        return getArguments().getString(ARGUMENT_API_URL);
    }

    /**
     * コンテキストの取得
     */
    private Context getContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * リクエスト用のキューの取得
     */
    private RequestQueue getRequestQueue() {
        return VolleyQueue.getQueue(getContext());
    }

    /**
     * ImageLoaderの取得
     */
    private ImageLoader getImageLoader() {
        return ImageLoaderManager.getLoader(getContext());
    }

    /**
     * 更新が必要かどうか
     */
    private boolean needRefresh() {
        if (mRequest == null) {
            return true;
        }

        if (mAdapter.getItemCount() == 0) {
            return true;
        }

        final Cache.Entry entry = getRequestQueue().getCache().get(mRequest.getCacheKey());
        return entry == null || entry.refreshNeeded();

    }

    /**
     * 表示の更新処理
     */
    private void refresh() {
        cancelRequest();

        mRequest = buildRequest();
        getRequestQueue().add(mRequest);
    }

    /**
     * リクエストのキャンセル
     */
    private void cancelRequest() {
        if (mRequest == null) {
            return;
        }

        mRequest.cancel();
    }

    private EntryRequest buildRequest() {
        return new EntryRequest.Builder(getApiUrl())
                .setListener(new Response.Listener<List<Entry>>() {
                    @Override
                    public void onResponse(List<Entry> response) {
                        mAdapter.setEntryList(response);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAAAA", error.toString());
                    }
                })
                .build();
    }

    /**
     * リスナー生成
     */
    private EntryCellView.OnClickListener createListener() {
        return new EntryCellView.OnClickListener() {
            @Override
            public void onClick(String linkUrl) {
                final Uri uri = Uri.parse(linkUrl);
                final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };
    }

    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static EntryFragment newInstance(String apiUrl) {
        EntryFragment fragment = new EntryFragment();

        Bundle args = new Bundle();
        args.putString(ARGUMENT_API_URL, apiUrl);
        fragment.setArguments(args);

        return fragment;
    }
}
