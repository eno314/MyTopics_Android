package jp.eno.android.mytopics;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import jp.eno.android.mytopics.setting.AddSettingApiActivity;
import jp.eno.android.mytopics.setting.SettingListFragment;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.main_list, new SettingListFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();

        if (id == R.id.action_add_setting_api) {
            AddSettingApiActivity.start(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
