package jp.eno.android.mytopics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jp.eno.android.mytopics.setting.SettingListFragment;

/**
 * Created by eno314 on 2014/11/23.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private static final int POSITION_SETTING = 0;
    private static final int POSITION_HAND_MAKE = 1;
    private static final String TITLE_SETTING = "設定API";
    private static final String TITLE_HAND_MAKE = "手入力";

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SettingListFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return TITLE_SETTING;
        }

        return TITLE_HAND_MAKE;
    }
}
