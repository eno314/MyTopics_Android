package jp.eno.android.mytopics.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopics.manual.AddManualActivity;
import jp.eno.android.mytopics.setting.AddSettingApiActivity;
import jp.eno.android.mytopicslibrary.layout.SlidingTabLayout;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SlidingTabLayout tabLayout = (SlidingTabLayout) findViewById(R.id.main_tab);
        tabLayout.setCustomTabView(R.layout.layout_main_tab, R.id.main_tab_text);
        tabLayout.setViewPager(createViewPager());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_setting_api) {
            AddSettingApiActivity.start(this);
            return true;
        }

        if (id == R.id.action_add_manual) {
            AddManualActivity.start(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ViewPager createViewPager() {
        final ViewPager pager = (ViewPager) findViewById(R.id.main_view_pager);
        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        return pager;
    }
}
