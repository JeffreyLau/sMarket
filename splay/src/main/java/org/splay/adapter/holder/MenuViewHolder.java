package org.splay.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.splay.R;
import org.splay.base.BaseViewHolder;
import org.splay.bean.MenuContentInfo;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by jeffrey on 16-2-16.
 */
public class MenuViewHolder extends BaseViewHolder {


    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.iv_icon)
    ImageView iv_icon;

    public MenuViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(MenuContentInfo info) {
        iv_icon.setImageResource(info.icon);
        tv_title.setText(info.title);
    }

}
