package jp.eno.android.mytopics.setting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopics.apilist.ApiListActivity;

/**
 * Created by eno314 on 2014/11/17.
 */
public class SettingListAdapter extends RecyclerView.Adapter<SettingListAdapter.ViewHolder> {

    private List<SettingApi> mSettingApiList;
    private final OnClickListener mListener;

    public SettingListAdapter(OnClickListener listener) {
        mListener = listener;
        mSettingApiList = new ArrayList<SettingApi>();
    }

    public void setSettingApiList(List<SettingApi> settingApiList) {
        mSettingApiList = settingApiList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.layout_setting_list_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SettingApi settingApi = mSettingApiList.get(position);
        holder.mTextView.setText(settingApi.name);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickCell(settingApi);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSettingApiList.size();
    }

    public interface OnClickListener {
        public void onClickCell(SettingApi settingApi);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view.findViewById(R.id.setting_list_card);
            mTextView = (TextView) view.findViewById(R.id.setting_list_cell_text);
        }
    }
}
