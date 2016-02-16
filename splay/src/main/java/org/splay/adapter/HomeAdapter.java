package org.splay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.splay.R;
import org.splay.base.BaseListAdapter;
import org.splay.base.BaseListViewHolder;

/**
 * Created by jeffrey on 16-2-16.
 */
public class HomeAdapter extends BaseListAdapter<HomeViewHolder> {
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itmview = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new HomeViewHolder(itmview);
    }

    @Override
    public void onBindViewHolder(BaseListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);//必须继承实现点击事件
        HomeViewHolder mHomeViewHolder = (HomeViewHolder) holder;
        mHomeViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 1000;
    }
}
