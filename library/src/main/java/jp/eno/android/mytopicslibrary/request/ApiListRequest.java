package jp.eno.android.mytopicslibrary.request;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.HttpStatus;

import jp.eno.android.mytopicslibrary.model.ApiList;
import jp.eno.android.mytopicslibrary.parser.ApiListParser;
import jp.eno.android.mytopicslibrary.parser.ParseException;
import jp.eno.android.mytopicslibrary.volley.VolleyCache;

/**
 * Created by eno314 on 2014/11/16.
 */
public class ApiListRequest extends Request<ApiList> {

    /**
     * リクエスト成功時のリスナー
     */
    private final Response.Listener<ApiList> mListener;

    /**
     * キャッシュの時間
     */
    private final long mCacheDurationMs;

    private ApiListRequest(Builder builder) {
        super(Method.GET, builder.mUrl, builder.mErrorListener);

        mListener = builder.mListener;
        mCacheDurationMs = builder.mCacheDurationMs;
    }

    @Override
    protected Response<ApiList> parseNetworkResponse(NetworkResponse response) {
        if (response.statusCode != HttpStatus.SC_OK) {
            return Response.error(new VolleyError("Unexpected status code " + response.statusCode));
        }

        Response<ApiList> apiListResponse;

        try {
            final ApiListParser parser = new ApiListParser();
            final ApiList apiList = parser.parse(response.data);
            final Cache.Entry cache = VolleyCache.createEntry(response, mCacheDurationMs, true);

            apiListResponse = Response.success(apiList, cache);
        } catch (ParseException e) {
            apiListResponse = Response.error(new ParseError(e));
        }

        return apiListResponse;
    }

    @Override
    protected void deliverResponse(ApiList response) {
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
        private Response.Listener<ApiList> mListener;

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

        public Builder setListener(Response.Listener<ApiList> listener) {
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

        public ApiListRequest build() {
            return new ApiListRequest(this);
        }
    }
}
