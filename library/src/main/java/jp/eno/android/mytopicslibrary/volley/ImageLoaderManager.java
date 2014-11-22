package jp.eno.android.mytopicslibrary.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by eno314 on 2014/11/17.
 */
public class ImageLoaderManager {
    /**
     * ImageLoaderのインスタンスをアプリ内で１つしか生成させないようにstaticで持つ
     */
    private static ImageLoader sImageLoader;

    /**
     * キューの取得
     *
     * @param context
     * @return ImageLoaderのリクエストキュー
     */
    public static ImageLoader getLoader(Context context) {
        if (sImageLoader == null) {
            RequestQueue queue = Volley.newRequestQueue(context);
            sImageLoader = new ImageLoader(queue, new BitmapLruCache());
        }

        return sImageLoader;
    }
}
