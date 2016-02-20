package org.splay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.splay.R;
import org.splay.adapter.holder.MenuViewHolder;
import org.splay.base.BaseAdapter;
import org.splay.base.BaseViewHolder;
import org.splay.bean.MenuContentInfo;

import java.util.List;

/**
 * Created by jeffrey on 16-2-16.
 */
public class MenuAdapter extends BaseAdapter<MenuContentInfo> {

    private List<MenuContentInfo> infos;
    private Context context;

    public MenuAdapter(Context context, List<MenuContentInfo> listViews) {
        super(context, listViews);
        infos = listViews;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itmview = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(itmview);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);//必须继承实现点击事件
        MenuViewHolder mMenuViewHolder = (MenuViewHolder) holder;
        MenuContentInfo info = infos.get(position);
        mMenuViewHolder.setData(info);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
