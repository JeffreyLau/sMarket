package org.splay.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.xutils.x;

/**
 * Created by jeffrey on 16-2-16.
 */
public class BaseListViewHolder extends RecyclerView.ViewHolder {

    public BaseListViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
    }

}
