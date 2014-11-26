package jp.eno.android.mytopics.apilist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.model.ApiList;
import jp.eno.android.mytopicslibrary.model.EntryApi;

/**
 * Created by eno314 on 2014/11/16.
 */
public class ApiListActivity extends FragmentActivity {

    private static final String INTENT_DATA_KEY_NAME = "INTENT_DATA_KEY_NAME";
    private static final String INTENT_DATA_KEY_LIST = "INTENT_DATA_KEY_LIST";

    private ApiListPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_list);

        setTitle(getIntent().getStringExtra(INTENT_DATA_KEY_NAME));

        final ArrayList<EntryApi> entryApiList = (ArrayList<EntryApi>)
                getIntent().getSerializableExtra(INTENT_DATA_KEY_LIST);

        mAdapter = new ApiListPagerAdapter(getSupportFragmentManager());
        mAdapter.setSettingApiList(entryApiList);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.api_list_pager);
        viewPager.setAdapter(mAdapter);

        // タブの境界線設定
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.api_list_pager_title_strip);
        pagerTabStrip.setDrawFullUnderline(true);        // タブの下線を有効
        pagerTabStrip.setTabIndicatorColor(Color.BLUE);  // タブのライン色
    }

    /**
     * ApiListActivityに遷移するためのメソッド
     *
     * @param activity 起動元のアクティビティ
     * @param apiList  apiのリスト
     */
    public static void start(Activity activity, ApiList apiList) {
        final Intent intent = new Intent(activity, ApiListActivity.class);
        intent.putExtra(INTENT_DATA_KEY_LIST, apiList.list);
        intent.putExtra(INTENT_DATA_KEY_NAME, apiList.name);

        activity.startActivity(intent);
    }
}
