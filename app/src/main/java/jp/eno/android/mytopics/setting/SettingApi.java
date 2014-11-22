package jp.eno.android.mytopics.setting;

/**
 * Created by eno314 on 2014/11/16.
 */
public class SettingApi {
    /**
     * リストに表示するAPI名
     */
    public String name;

    /**
     * APIのurl
     */
    public String url;

    public SettingApi(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
