package org.splay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.splay.R;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jeffrey on 16-2-16.
 */
public class HomeViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener,View.OnLongClickListener{

    private String names[] = {"Cloud", "Movie", "Laptop",
            "Loop", "Menu", "Mood", "Palette", "Search",
            "Time", "Work"};
    @ViewInject(R.id.icon)
    private ImageView icon;
    @ViewInject(R.id.name)
    private TextView name;
    @ViewInject(R.id.subname)
    private TextView subName;
    private HomeAdapter.OnItemClickListener onItemClickListener;
    /*public HomeViewHolder(View itemView) {
        super(itemView);
        x.view().inject(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }*/

    public HomeViewHolder(View itemView,
                          HomeAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        x.view().inject(this, itemView);
        this.onItemClickListener = onItemClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setData(int position) {
        icon.setImageResource(R.mipmap.ic_launcher);
        name.setText(names[position % 10]);
        subName.setText("This is position " + position);
    }

    @Override
    public void onClick(View v) {
        if(null != this.onItemClickListener)
            this.onItemClickListener.onItemClick(v,getLayoutPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        if(null != this.onItemClickListener) {
            this.onItemClickListener.onItemLongClick(v,getLayoutPosition());
        }
        return true;
    }
}
