package jp.eno.android.mytopicslibrary.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * VolleyのQueueを管理するクラス
 * Created by eno314 on 2014/11/16.
 */
public class VolleyQueue {

    /**
     * Volleyのリクエストキュー
     * アプリ内で１つしか生成させないようにstaticで持つ
     */
    private static RequestQueue sRequestQueue;

    /**
     * キューの取得
     *
     * @param context
     * @return Volleyのリクエストキュー
     */
    public static RequestQueue getQueue(Context context) {
        if (sRequestQueue == null) {
            sRequestQueue = Volley.newRequestQueue(context);
        }

        return sRequestQueue;
    }
}
