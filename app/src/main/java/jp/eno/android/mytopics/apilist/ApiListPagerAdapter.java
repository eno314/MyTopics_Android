package jp.eno.android.mytopics.apilist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.eno.android.mytopics.entry.EntryFragment;
import jp.eno.android.mytopicslibrary.model.MyApi;

/**
 * Created by eno314 on 2014/11/17.
 */
public class ApiListPagerAdapter extends FragmentPagerAdapter {

    private List<MyApi> mMyApiList;

    public ApiListPagerAdapter(FragmentManager fm) {
        super(fm);
        mMyApiList = new ArrayList<MyApi>();
    }

    @Override
    public Fragment getItem(int position) {
        return new EntryFragment();
    }

    @Override
    public int getCount() {
        return mMyApiList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMyApiList.get(position).name;
    }

    public void setSettingApiList(List<MyApi> apiList) {
        mMyApiList = apiList;
    }
}
