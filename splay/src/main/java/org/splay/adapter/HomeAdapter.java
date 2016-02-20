package org.splay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.splay.R;
import org.splay.adapter.holder.HomeViewHolder;
import org.splay.base.BaseAdapter;
import org.splay.base.BaseViewHolder;
import org.splay.bean.HomeAppInfo;

import java.util.List;

/**
 * Created by jeffrey on 16-2-16.
 */
public class HomeAdapter extends BaseAdapter<HomeAppInfo> {

    private List<HomeAppInfo> mHomeAppInfos;
    private Context context;
    public HomeAdapter(Context context,
                       List<HomeAppInfo> listViews) {
        super(context, listViews);
        mHomeAppInfos = listViews;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itmview = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        return new HomeViewHolder(itmview);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);//必须继承实现点击事件
        HomeViewHolder mHomeViewHolder = (HomeViewHolder) holder;
        HomeAppInfo mAppinfo = mHomeAppInfos.get(position);
        mHomeViewHolder.setData(mAppinfo);
    }
}
