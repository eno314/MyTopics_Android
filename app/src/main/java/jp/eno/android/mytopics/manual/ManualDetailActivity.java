package jp.eno.android.mytopics.manual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopics.entry.EntryFragment;
import jp.eno.android.mytopicslibrary.layout.SlidingTabLayout;

/**
 * Created by eno314 on 2014/12/02.
 */
public class ManualDetailActivity extends FragmentActivity {

    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_API_URL = "EXTRA_API_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_detail);

        final Intent intent = getIntent();
        final String apiUrl = intent.getStringExtra(EXTRA_API_URL);

        setTitle(intent.getStringExtra(EXTRA_NAME));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.manual_detail_container, EntryFragment.newInstance(apiUrl))
                .commit();
    }

    public static void start(Activity activity, EntryApiDB entryApi) {
        final Intent intent = new Intent(activity.getApplicationContext(), ManualDetailActivity.class);
        intent.putExtra(EXTRA_NAME, entryApi.name);
        intent.putExtra(EXTRA_API_URL, entryApi.url);
        activity.startActivity(intent);
    }
}
