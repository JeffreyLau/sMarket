package org.splay.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.splay.adapter.HomeAdapter;
import org.splay.base.BaseAdapter;
import org.splay.base.BaseFragment;
import org.splay.base.BaseLoadState;
import org.splay.bean.HomeAppInfo;
import org.splay.bean.HomeJsonInfo;
import org.splay.conf.Constants;
import org.splay.widget.DividerItem;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by jeffrey on 16-2-13.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private HomeAdapter mHomeAdapter;

    private List<HomeAppInfo> mHomeAppInfos;
    private List<String> mPictures;

    @Override
    public View onInitSuccessView() {
        mRecyclerView = new RecyclerView(mContext);
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mHomeAdapter = new HomeAdapter(mContext, mHomeAppInfos);
        mHomeAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
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
        mRecyclerView.setAdapter(mHomeAdapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割線
        mRecyclerView.addItemDecoration(new DividerItem(getActivity(),
                DividerItem.VERTICAL_LIST));
        return mRecyclerView;
    }

    @Override
    public BaseLoadState onLoading() {
        //同步访问
        RequestParams params = new RequestParams(Constants.URL.HOMEURL);
        params.addQueryStringParameter("index", "0");
        try {
            JSONObject mJSONObject = x.http().postSync(params, JSONObject.class);
            Gson gson = new Gson();
            HomeJsonInfo mHomeJsonInfo = gson.fromJson(mJSONObject.toString(),
                    HomeJsonInfo.class);
            if ((null == mHomeJsonInfo)
                    || (null == mHomeJsonInfo.list)
                    || (mHomeJsonInfo.list.size() == 0))
                return BaseLoadState.EMPTY;
            //Log.i(TAG,"mHomeJson:" + mHomeJson.toString());
            mHomeAppInfos = mHomeJsonInfo.list;
            mPictures = mHomeJsonInfo.picture;
            return BaseLoadState.SUCCESS;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return BaseLoadState.ERROR;
        }
    }
}
