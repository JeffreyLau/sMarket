package org.splay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.splay.R;

/**
 * Created by jeffrey on 16-2-16.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itmview = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new HomeViewHolder(itmview,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        holder.setData(position);
        /*if (mOnItemClickListener != null) {
            *//**
             * 这里加了判断，holder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             *//*
            if (!holder.itemView.hasOnClickListeners()) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickListener.onItemLongClick(v, pos);
                        return true;
                    }
                });
            }
        }*/
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
