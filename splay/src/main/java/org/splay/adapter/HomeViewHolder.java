package org.splay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.splay.R;
import org.splay.base.BaseListViewHolder;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by jeffrey on 16-2-16.
 */
public class HomeViewHolder extends BaseListViewHolder {

    private String names[] = {"Cloud", "Movie", "Laptop",
            "Loop", "Menu", "Mood", "Palette", "Search",
            "Time", "Work"};
    @ViewInject(R.id.icon)
    private ImageView icon;
    @ViewInject(R.id.name)
    private TextView name;
    @ViewInject(R.id.subname)
    private TextView subName;

    public HomeViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(int position) {
        icon.setImageResource(R.mipmap.ic_launcher);
        name.setText(names[position % 10]);
        subName.setText("This is position " + position);
    }
}
