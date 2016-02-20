package org.splay.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffrey on 16-2-20.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> listData = new ArrayList<T>();
    public Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private BaseViewHolder mBaseViewHolder;

    public BaseAdapter(Context context, List<T> listData) {
        this.listData = listData;
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener
                                               onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        if (null != mBaseViewHolder)
            mBaseViewHolder.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    @Override
    public abstract BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        mBaseViewHolder = holder;
        if (null != mBaseViewHolder &&
                null != this.mOnItemClickListener) {
            if (null == mBaseViewHolder.mOnItemClickListener) {
                mBaseViewHolder.mOnItemClickListener = this.mOnItemClickListener;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (null != listData) {
            return listData.size();
        }
        return 0;
    }
}
