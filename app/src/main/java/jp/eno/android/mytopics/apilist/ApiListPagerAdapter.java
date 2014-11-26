package jp.eno.android.mytopics.apilist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.eno.android.mytopics.entry.EntryFragment;
import jp.eno.android.mytopicslibrary.model.EntryApi;

/**
 * Created by eno314 on 2014/11/17.
 */
public class ApiListPagerAdapter extends FragmentPagerAdapter {

    private List<EntryApi> mEntryApiList;

    public ApiListPagerAdapter(FragmentManager fm) {
        super(fm);
        mEntryApiList = new ArrayList<EntryApi>();
    }

    @Override
    public Fragment getItem(int position) {
        return new EntryFragment();
    }

    @Override
    public int getCount() {
        return mEntryApiList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mEntryApiList.get(position).name;
    }

    public void setSettingApiList(List<EntryApi> apiList) {
        mEntryApiList = apiList;
    }
}
