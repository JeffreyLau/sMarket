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
public class MenuAdapter extends BaseListAdapter<MenuViewHolder> {
    private Context context;
    private String[] itemTitle;
    private int[] itemIcon;

    public MenuAdapter(Context context) {
        this.context = context;
        itemTitle = context.
                getResources().getStringArray(R.array.drawer_items);
        itemIcon = new int[]{
                R.mipmap.ic_home,
                R.mipmap.ic_setting,
                R.mipmap.ic_theme,
                R.mipmap.ic_scans,
                R.mipmap.ic_feedback,
                R.mipmap.ic_updates,
                R.mipmap.ic_about,
                R.mipmap.ic_exit};
    }

    @Override
    public BaseListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itmview = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(itmview);
    }

    @Override
    public void onBindViewHolder(BaseListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);//必须继承实现点击事件
        MenuViewHolder mMenuViewHolder = (MenuViewHolder) holder;
        mMenuViewHolder.setData(itemIcon[position], itemTitle[position]);
    }

    @Override
    public int getItemCount() {
        return itemTitle.length;
    }
}
