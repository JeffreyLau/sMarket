package org.splay.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.splay.adapter.HomeAdapter;
import org.splay.base.BaseFragment;
import org.splay.base.BaseListAdapter;
import org.splay.base.BaseLoadState;
import org.xutils.x;

/**
 * Created by jeffrey on 16-2-13.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private HomeAdapter mHomeAdapter;
    public View onInitSuccessView() {
        mRecyclerView = new RecyclerView(mContext);
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mHomeAdapter = new HomeAdapter(mContext);
        mHomeAdapter.setOnItemClickListener(new BaseListAdapter.OnItemClickListener() {
            //public static Toast makeText(Context context, CharSequence text, @Duration int duration)
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(mContext, "条目:" + position + "被点击",
                //        Toast.LENGTH_SHORT).show();
                Log.i(TAG,"条目:" + position + "被点击");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //Toast.makeText(mContext, "条目:" + position + "被长按",
                //        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "条目:" + position + "被长按");
            }
        });
        mRecyclerView.setAdapter(mHomeAdapter);
        return mRecyclerView;
    }

    @Override
    public BaseLoadState onLoading() {
        x.http().postSync();
        return BaseLoadState.SUCCESS;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //startLoading();
    }
}
