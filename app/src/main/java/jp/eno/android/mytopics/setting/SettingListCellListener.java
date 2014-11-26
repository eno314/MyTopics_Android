package jp.eno.android.mytopics.setting;

/**
 * Created by eno314 on 2014/11/27.
 */
public interface SettingListCellListener {
    /**
     * セルが押された時のコールバック
     */
    void onClickCell(SettingApi settingApi);

    /**
     * 削除ボタンが押された時のコールバック
     */
    void onClickDeleteButton(SettingApi settingApi);
}
