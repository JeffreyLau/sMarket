package org.splay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.splay.R;
import org.splay.base.BaseDrawerFragment;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffrey on 16-2-16.
 */

public class MenuFragment extends BaseDrawerFragment {

    private ListView mDrawerListView;
    private String[] itemTitle = getResources().getStringArray(R.array.drawer_items);
    private int[] itemIcon = {
            R.mipmap.ic_home_slide,
            R.mipmap.ic_setting,
            R.mipmap.ic_theme,
            R.mipmap.ic_scans,
            R.mipmap.ic_feedback,
            R.mipmap.ic_updates,
            R.mipmap.ic_about,
            R.mipmap.ic_exit};

    private List<ItemInfo> mItemInfos = new ArrayList<ItemInfo>();
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void selectItem(int position) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mDrawerListView = (ListView) inflater.inflate(R.layout.fragment_menu_listview, container, false);
        View headerView = inflater.inflate(R.layout.item_header, null);
        mDrawerListView.addHeaderView(headerView);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        for (int i = 0; i < itemTitle.length; i++) {
            ItemInfo item = new ItemInfo();
            item.icon = itemIcon[i];
            item.title = itemTitle[i];
            mItemInfos.add(item);
        }
        //selectItem(mCurrentSelectedPosition);
        DrawerListAdapter adapter = new DrawerListAdapter(mContext, mItemInfos);
        mDrawerListView.setAdapter(adapter);
        //mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

        return mDrawerListView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class DrawerListAdapter extends BaseAdapter {

        private ViewHolder mViewHolder;
        private List<ItemInfo> infos;
        private Context context;

        public DrawerListAdapter(Context context, List<ItemInfo> infos) {
            this.infos = infos;
            this.context = context;
        }

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;// 优化ListView
            ViewHolder viewHolder;
            if (convertView == null) {// 服用历史缓存View,减少view对象的创建
                view = View.inflate(context,
                        R.layout.item_drawer_menu, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {// 复用历史缓存View,减少View对象的创建来达到优化的效果
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();// 取出子view根据各不同ＩＤ直接取出无需遍历
            }
            ItemInfo info = infos.get(position);
            viewHolder.setData(info.title, info.icon);
            return view;
        }

        private class ViewHolder {

            @ViewInject(R.id.tv_title)
            TextView tv_title;
            @ViewInject(R.id.iv_icon)
            ImageView iv_icon;

            public ViewHolder(View view) {
                x.view().inject(this, view);
            }

            public void setData(String title, int id) {
                tv_title.setText(title);
                iv_icon.setImageResource(id);
            }
        }
    }

    public class ItemInfo {
        private int icon;
        private String title;

        public ItemInfo() {
        }

    }

}
