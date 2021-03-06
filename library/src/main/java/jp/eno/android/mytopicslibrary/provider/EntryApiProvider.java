package jp.eno.android.mytopicslibrary.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import jp.eno.android.mytopicslibrary.database.EntryApiColumns;
import jp.eno.android.mytopicslibrary.database.MyTopicsOpenHelper;

/**
 * Created by eno314 on 2014/11/30.
 */
public class EntryApiProvider extends ContentProvider {

    private static final String AUTHORITY = "jp.eno.android.mytopicslibrary.provider.entryapiprovider";

    private static final int MATCH_ALL = 0;

    private static final int INVALID_ID = -1;

    private MyTopicsOpenHelper mHelper;

    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に使用します
    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, EntryApiColumns.TABLE_NAME, MATCH_ALL);
    }

    @Override
    public boolean onCreate() {
        mHelper = new MyTopicsOpenHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mHelper.getReadableDatabase();
        final Cursor cursor = db.query(
                EntryApiColumns.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mHelper.getWritableDatabase();
        final long rowId = db.insert(EntryApiColumns.TABLE_NAME, null, values);

        final Uri insertedUri = ContentUris.withAppendedId(uri, rowId);

        if (rowId != INVALID_ID) {
            getContext().getContentResolver().notifyChange(insertedUri, null);
        }

        return insertedUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mHelper.getWritableDatabase();
        final int deletedCount = db.delete(EntryApiColumns.TABLE_NAME, selection, selectionArgs);

        if (deletedCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deletedCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mHelper.getWritableDatabase();
        final int updatedCount = db.update(EntryApiColumns.TABLE_NAME, values, selection, selectionArgs);

        if (updatedCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updatedCount;
    }

    public static Uri getContentUri() {
        return Uri.parse("content://" + AUTHORITY + "/" + EntryApiColumns.TABLE_NAME);
    }
}
