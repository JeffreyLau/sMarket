package org.splay.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.xutils.x;

/**
 * Created by jeffrey on 16-2-20.
 */
public class BaseViewHolder<VH> extends
        RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener {
    public BaseAdapter.OnItemClickListener mOnItemClickListener;
    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        x.view().inject(this, itemView);
    }

    @Override
    public void onClick(View v) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onItemLongClick(v, getLayoutPosition());
        }
        return true;
    }
}
