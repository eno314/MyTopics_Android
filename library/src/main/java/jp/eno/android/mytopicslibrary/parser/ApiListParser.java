package jp.eno.android.mytopicslibrary.parser;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import jp.eno.android.mytopicslibrary.model.ApiList;
import jp.eno.android.mytopicslibrary.model.MyApi;

/**
 * Created by eno314 on 2014/11/16.
 */
public class ApiListParser extends Parser<ApiList> {

    private static final String ELEMENT_ENTRIES = "entries";
    private static final String ELEMENT_LIST_NAME = "listName";
    private static final String ELEMENT_API_LIST = "apiList";
    private static final String ELEMENT_NAME = "name";
    private static final String ELEMENT_URL = "url";

    @Override
    ApiList parseInternal(byte[] body) throws ParseException {
        if (body == null || body.length == 0) {
            throw new ParseException("body is empty...");
        }

        ApiList apiList;

        try {
            apiList = parseRoot(new String(body, HTTP.UTF_8));
        } catch (JSONException e) {
            throw new ParseException(e);
        } catch (UnsupportedEncodingException e) {
            throw new ParseException(e);
        }

        return apiList;
    }

    @Override
    boolean isValid(ApiList result) {
        if (result == null) {
            return false;
        }

        return result.isValid();
    }

    private ApiList parseRoot(String body) throws JSONException {

        final JSONObject rootObject = new JSONObject(body);
        final JSONObject entries = rootObject.getJSONObject(ELEMENT_ENTRIES);

        final ApiList apiList = new ApiList();

        apiList.name = entries.getString(ELEMENT_LIST_NAME);
        apiList.list = parseApiList(entries.getJSONArray(ELEMENT_API_LIST));

        return apiList;
    }

    private ArrayList<MyApi> parseApiList(JSONArray responseApiList) throws JSONException {
        final ArrayList<MyApi> myApiList = new ArrayList<MyApi>();

        for (int i = 0; i < responseApiList.length(); i++) {
            final JSONObject responseApiInfo = responseApiList.getJSONObject(i);

            final MyApi myApi = new MyApi();
            myApi.name = responseApiInfo.getString(ELEMENT_NAME);
            myApi.url = responseApiInfo.getString(ELEMENT_URL);

            myApiList.add(myApi);
        }

        return myApiList;
    }
}
