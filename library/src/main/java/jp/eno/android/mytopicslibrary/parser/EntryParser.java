package jp.eno.android.mytopicslibrary.parser;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import jp.eno.android.mytopicslibrary.model.Entry;

/**
 * Created by eno314 on 2014/11/16.
 */
public class EntryParser extends Parser<List<Entry>> {

    private static final String ELEMENT_ENTRIES = "entries";
    private static final String ELEMENT_TITLE = "title";
    private static final String ELEMENT_URL = "url";
    private static final String ELEMENT_IMAGE = "image";

    @Override
    List<Entry> parseInternal(byte[] body) throws ParseException {
        if (body == null || body.length == 0) {
            throw new ParseException("body is empty...");
        }

        List<Entry> entryList;

        try {
            entryList = parseRoot(new String(body, HTTP.UTF_8));
        } catch (JSONException e) {
            throw new ParseException(e);
        } catch (UnsupportedEncodingException e) {
            throw new ParseException(e);
        }

        return entryList;
    }

    @Override
    boolean isValid(List<Entry> result) {
        if (result == null || result.size() == 0) {
            return false;
        }

        for (Entry entry : result) {
            if (!entry.isValid()) {
                return false;
            }
        }

        return true;
    }

    private List<Entry> parseRoot(String body) throws JSONException {
        final List<Entry> entryList = new ArrayList<Entry>();

        final JSONObject rootObject = new JSONObject(body);
        final JSONArray entries = rootObject.getJSONArray(ELEMENT_ENTRIES);

        for (int i = 0; i < entries.length(); i++) {
            final JSONObject responseEntry = entries.getJSONObject(i);
            final Entry entry = new Entry();
            entry.title = responseEntry.getString(ELEMENT_TITLE);
            entry.url = responseEntry.getString(ELEMENT_URL);

            if (responseEntry.has(ELEMENT_IMAGE)) {
                entry.imageUrl = responseEntry.getString(ELEMENT_IMAGE);
            }

            entryList.add(entry);
        }

        return entryList;
    }
}
