package jp.eno.android.mytopics.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jp.eno.android.mytopics.manual.ManualFragment;
import jp.eno.android.mytopics.setting.SettingListFragment;

/**
 * Created by eno314 on 2014/11/23.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private static final int POSITION_SETTING = 0;
    private static final String TITLE_SETTING = "設定API";
    private static final String TITLE_MANUAL = "手入力";

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == POSITION_SETTING) {
            return new SettingListFragment();
        }
        return new ManualFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == POSITION_SETTING) {
            return TITLE_SETTING;
        }
        return TITLE_MANUAL;
    }
}
