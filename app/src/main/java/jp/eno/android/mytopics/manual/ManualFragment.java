package jp.eno.android.mytopics.manual;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.eno.android.mytopics.R;

/**
 * 手入力したAPIを表示するフラグメント
 * Created by eno314 on 2014/11/23.
 */
public class ManualFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manual, container, false);
    }
}
