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
import org.splay.base.BaseDrawerFragment;
import org.splay.base.BaseListAdapter;
import org.splay.widget.DividerItem;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMenuAdapter = new MenuAdapter(getActivity());
        mMenuAdapter.setOnItemClickListener(new ItemClickListener());
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

    private class ItemClickListener implements BaseListAdapter.OnItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            Log.i(TAG, "条目:" + position + "被点击");
        }

        @Override
        public void onItemLongClick(View view, int position) {
            Log.i(TAG, "条目:" + position + "被长按");
        }
    }

    @Event(R.id.item_header)
    private void headerOnClick(View view) {
        Log.i(TAG, "条目:" + "header " + "被长按");
    }

}
