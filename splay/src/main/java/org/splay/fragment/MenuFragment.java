package org.splay.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.splay.R;
import org.splay.adapter.MenuAdapter;
import org.splay.base.BaseAdapter;
import org.splay.base.BaseDrawerFragment;
import org.splay.bean.MenuContentInfo;
import org.splay.widget.DividerItem;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffrey on 16-2-16.
 */
@ContentView(R.layout.fragment_menu)
public class MenuFragment extends BaseDrawerFragment {

    private static final String TAG = "MenuFragment";
    @ViewInject(R.id.item_header)
    private LinearLayout item_header;

    @ViewInject(R.id.rv_list)
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private MenuAdapter mMenuAdapter;

    private String[] itemTitle;
    private int[] itemIcon;
    private List<MenuContentInfo> infos;

    private void initData() {
        itemTitle = getActivity().
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
        infos = new ArrayList<MenuContentInfo>();
        for (int i = 0; i < itemIcon.length; i++) {
            MenuContentInfo info = new MenuContentInfo();
            info.icon = itemIcon[i];
            info.title = itemTitle[i];
            infos.add(info);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMenuAdapter = new MenuAdapter(getActivity(), infos);
        mMenuAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            //public static Toast makeText(Context context, CharSequence text, @Duration int duration)
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(mContext, "条目:" + position + "被点击",
                //        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "条目:" + position + "被点击");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //Toast.makeText(mContext, "条目:" + position + "被长按",
                //        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "条目:" + position + "被长按");
            }
        });
        //设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置adapter
        mRecyclerView.setAdapter(mMenuAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割線
        mRecyclerView.addItemDecoration(new DividerItem(getActivity(),
                DividerItem.VERTICAL_LIST));

        //mRecyclerView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    @Event(R.id.item_header)
    private void headerOnClick(View view) {
        Log.i(TAG, "条目:" + "header " + "被长按");
    }

}
