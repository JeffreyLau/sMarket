package org.splay.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.splay.R;
import org.splay.base.BaseViewHolder;
import org.splay.bean.HomeAppInfo;
import org.splay.conf.Constants;
import org.splay.utils.StringUtils;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jeffrey on 16-2-16.
 */
public class HomeViewHolder extends BaseViewHolder {

    @ViewInject(R.id.iv_appinfo_icon)
    private ImageView iv_appinfo_icon;

    @ViewInject(R.id.tv_appinfo_title)
    private TextView tv_appinfo_title;

    @ViewInject(R.id.rb_appinfo_stars)
    private RatingBar rb_appinfo_stars;

    @ViewInject(R.id.tv_appinfo_size)
    private TextView tv_appinfo_size;

    @ViewInject(R.id.tv_appinfo_des)
    private TextView tv_appinfo_des;


    public HomeViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(HomeAppInfo appInfo) {
        //加载图标
        x.image().bind(iv_appinfo_icon, Constants.URL.IMAGEURL + appInfo.iconUrl);
        tv_appinfo_title.setText(appInfo.name);
        rb_appinfo_stars.setRating(appInfo.stars);
        tv_appinfo_size.setText(StringUtils.formatFileSize(appInfo.size));
        tv_appinfo_des.setText(appInfo.des);
    }
}
