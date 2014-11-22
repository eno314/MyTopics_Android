package jp.eno.android.mytopicslibrary.model;

/**
 * APIリクエストのレスポンス専用モデル
 * Created by eno314 on 2014/11/16.
 */
public interface RequestResponseModel {

    /**
     * 有効なモデルかどうか
     *
     * @return true : 有効 / false : 無効
     */
    public boolean isValid();
}
