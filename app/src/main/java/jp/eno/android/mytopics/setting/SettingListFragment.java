package jp.eno.android.mytopics.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopics.apilist.ApiListActivity;
import jp.eno.android.mytopicslibrary.model.ApiList;
import jp.eno.android.mytopicslibrary.request.ApiListRequest;
import jp.eno.android.mytopicslibrary.volley.VolleyQueue;

/**
 * 自分が入力した設定API一覧を表示するFragment
 * Created by eno314 on 2014/11/16.
 */
public class SettingListFragment extends Fragment {

    private SettingListAdapter mAdapter;

    public SettingListFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView)
                inflater.inflate(R.layout.fragment_setting_list, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        mAdapter = new SettingListAdapter(createListener());
        recyclerView.setAdapter(mAdapter);

        return recyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // TODO DBからデータ取得
        List<SettingApi> settingApiList = new ArrayList<SettingApi>();
        settingApiList.add(new SettingApi("ねこ", "http://api-nyantarou.ddo.jp/api/config/setting/apilist1.json"));
        settingApiList.add(new SettingApi("ねこねこ", "http://api-nyantarou.ddo.jp/api/config/setting/apilist1.json"));

        mAdapter.setSettingApiList(settingApiList);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * リクエスト用のキューの取得
     */
    private RequestQueue getRequestQueue() {
        return VolleyQueue.getQueue(getActivity().getApplicationContext());
    }

    private ApiListRequest buildRequest(String url) {
        return new ApiListRequest.Builder(url)
                .setListener(new Response.Listener<ApiList>() {
                    @Override
                    public void onResponse(ApiList response) {
                        ApiListActivity.start(getActivity(), response);
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
                .build();
    }

    private SettingListAdapter.OnClickListener createListener() {
        return new SettingListAdapter.OnClickListener() {
            @Override
            public void onClickCell(SettingApi settingApi) {
                getRequestQueue().add(buildRequest(settingApi.url));
            }
        };
    }
}
