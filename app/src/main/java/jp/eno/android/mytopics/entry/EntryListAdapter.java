package jp.eno.android.mytopics.entry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.model.Entry;

/**
 * Created by eno314 on 2014/11/16.
 */
public class EntryListAdapter extends RecyclerView.Adapter<EntryListAdapter.ViewHolder> {

    private List<Entry> mEntryList;
    private EntryCellView.OnClickListener mListener;
    private ImageLoader mImageLoader;

    public EntryListAdapter(EntryCellView.OnClickListener listener, ImageLoader imageLoader) {
        mListener = listener;
        mImageLoader = imageLoader;
        mEntryList = new ArrayList<Entry>();
    }

    @Override
    public EntryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final EntryCellView entryCellView = (EntryCellView)
                inflater.inflate(R.layout.layout_entry_cell, parent, false);
        return new ViewHolder(entryCellView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Entry entry = mEntryList.get(position);
        holder.entryCellView.setEntry(entry, mListener, mImageLoader);
    }

    @Override
    public int getItemCount() {
        return mEntryList.size();
    }

    public void setEntryList(List<Entry> entryList) {
        mEntryList = entryList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final EntryCellView entryCellView;

        public ViewHolder(EntryCellView view) {
            super(view);
            entryCellView = view;
        }
    }
}
