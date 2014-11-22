package jp.eno.android.mytopicslibrary.request;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.HttpStatus;

import java.util.List;

import jp.eno.android.mytopicslibrary.model.Entry;
import jp.eno.android.mytopicslibrary.parser.EntryParser;
import jp.eno.android.mytopicslibrary.parser.ParseException;
import jp.eno.android.mytopicslibrary.volley.VolleyCache;

/**
 * Created by eno314 on 2014/11/16.
 */
public class EntryRequest extends Request<List<Entry>> {

    /**
     * リクエスト成功時のリスナー
     */
    private final Response.Listener<List<Entry>> mListener;

    /**
     * キャッシュの時間
     */
    private final long mCacheDurationMs;

    private EntryRequest(Builder builder) {
        super(Method.GET, builder.mUrl, builder.mErrorListener);

        mListener = builder.mListener;
        mCacheDurationMs = builder.mCacheDurationMs;
    }

    @Override
    protected Response<List<Entry>> parseNetworkResponse(NetworkResponse response) {
        if (response.statusCode != HttpStatus.SC_OK) {
            return Response.error(new VolleyError("Unexpected status code " + response.statusCode));
        }

        Response<List<Entry>> apiListResponse;

        try {
            final EntryParser parser = new EntryParser();
            final List<Entry> entryList = parser.parse(response.data);
            final Cache.Entry cache = VolleyCache.createEntry(response, mCacheDurationMs, true);

            apiListResponse = Response.success(entryList, cache);
        } catch (ParseException e) {
            apiListResponse = Response.error(new ParseError(e));
        }

        return apiListResponse;
    }

    @Override
    protected void deliverResponse(List<Entry> response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    /**
     * リクエスト生成用のクラス
     */
    public static class Builder {
        /**
         * リクエストURL
         */
        private final String mUrl;

        /**
         * 通信成功時のリスナー
         */
        private Response.Listener<List<Entry>> mListener;

        /**
         * 通信エラー時のリスナー
         */
        private Response.ErrorListener mErrorListener;

        /**
         * キャッシュ時間（デフォルト１０分）
         */
        private long mCacheDurationMs = 10 * 60 * 1000;

        public Builder(String url) {
            mUrl = url;
        }

        public Builder setListener(Response.Listener<List<Entry>> listener) {
            mListener = listener;
            return this;
        }

        public Builder setErrorListener(Response.ErrorListener errorListener) {
            mErrorListener = errorListener;
            return this;
        }

        public Builder setCacheDurationMs(long cacheDurationMs) {
            mCacheDurationMs = cacheDurationMs;
            return this;
        }

        public EntryRequest build() {
            return new EntryRequest(this);
        }
    }
}
