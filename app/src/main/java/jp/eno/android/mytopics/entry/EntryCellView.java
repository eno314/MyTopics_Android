package jp.eno.android.mytopics.entry;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import jp.eno.android.mytopics.R;
import jp.eno.android.mytopicslibrary.model.Entry;

/**
 * Created by eno314 on 2014/11/16.
 */
public class EntryCellView extends LinearLayout {

    private View mCardView;
    private NetworkImageView mImageView;
    private TextView mTextView;

    public EntryCellView(Context context) {
        super(context);
    }

    public EntryCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EntryCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mCardView = findViewById(R.id.entry_card);
        mImageView = (NetworkImageView) findViewById(R.id.entry_cell_image);
        mTextView = (TextView) findViewById(R.id.entry_cell_text);
    }

    public void setEntry(final Entry entry, final OnClickListener listener, ImageLoader loader) {
        if (TextUtils.isEmpty(entry.imageUrl)) {
            mImageView.setVisibility(View.GONE);
        } else {
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setImageUrl(entry.imageUrl, loader);
        }

        mTextView.setText(entry.title);

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(entry.url);
            }
        });
    }

    public interface OnClickListener {
        public void onClick(String linkUrl);
    }
}
