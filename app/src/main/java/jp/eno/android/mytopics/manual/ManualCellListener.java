package jp.eno.android.mytopics.manual;

import jp.eno.android.mytopicslibrary.model.EntryApi;

/**
 * Created by eno314 on 2014/11/30.
 */
public interface ManualCellListener {
    /**
     * セルが押された時のコールバック
     */
    void onClickCell(EntryApiDB entryApi);

    /**
     * 削除ボタンが押された時のコールバック
     */
    void onClickDeleteButton(EntryApiDB entryApi);
}
