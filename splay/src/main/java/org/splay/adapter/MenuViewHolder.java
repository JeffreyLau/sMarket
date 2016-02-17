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
public class MenuViewHolder extends BaseListViewHolder {


    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.iv_icon)
    ImageView iv_icon;

    public MenuViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(int icon, String title) {
        iv_icon.setImageResource(icon);
        tv_title.setText(title);

    }

}
