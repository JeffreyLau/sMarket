package org.splay.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.splay.R;
import org.splay.utils.ViewUIUtils;

/**
 * Created by jeffrey on 16-2-13.
 */
public abstract class BaseFragment extends Fragment {

    private boolean injected = false;
    public Context mContext;
    private FrameViewHolder mFrameViewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        mFrameViewHolder = new FrameViewHolder(mContext);
        return mFrameViewHolder;
        //injected = true;
        //return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*if (!injected) {
            x.view().inject(this, this.getView());
        }*/
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mFrameViewHolder)
            mFrameViewHolder.removeCommonView();
    }

    public void startLoading() {
        if (null != mFrameViewHolder)
            mFrameViewHolder.startLoading();
    }

    /**
     * 因为每个子Fragment都不知道要如何显示
     * 所以每个子Fragment必须实现该方法
     * @return
     */
    public abstract View onInitSuccessView();

    /**
     * 用于子类异步加载数据
     * 放在哪里加载?
     * 放在当前页面被选中的时候加载
     * @return
     */
    public abstract BaseLoadState onAsyncLoading();

    /**
     * 每一个子fragment都用ContentViewGroup来描述
     * 并且每个子Fragment都用下面４种View状态
     * @mLoadingView :加載數據顯示的視圖
     * @mErrorView:加载数据失败显示的视图
     * @mEmptyView:空视图
     * @mSuccessView:加载数据成功显示的视图
     */
    private class FrameViewHolder extends FrameLayout {

        private View mLoadingView;
        private View mErrorView;
        private View mEmptyView;
        private View mSuccessView;
        private BaseLoadState mState = BaseLoadState.NONE;

        public FrameViewHolder(Context context) {
            super(context);
            initCommonView(context);
            addCommonView();
            refreshUI();
        }

        /**
         *
         * @param context
         */
        private void initCommonView(Context context) {
            // ① 加载页面
            this.mLoadingView = View.inflate(context, R.layout.pager_loading, null);
            // ② 错误页面
            this.mErrorView = View.inflate(context, R.layout.pager_error, null);
            // ③ 空页面
            this.mEmptyView = View.inflate(context, R.layout.pager_empty, null);
        }

        private void addCommonView() {
            this.addView(mLoadingView);
            this.addView(mErrorView);
            this.addView(mEmptyView);
        }

        private void removeCommonView() {
            this.removeView(mLoadingView);
            this.removeView(mErrorView);
            this.removeView(mEmptyView);
            if (null != mSuccessView)
                this.removeView(mSuccessView);
        }

        private void addSuccessView() {
            if (null == mSuccessView &&
                    mState == BaseLoadState.SUCCESS) {
                //加載成功界面
                // 创建成功视图
                this.mSuccessView = onInitSuccessView();
                //將view添加到本佈局
                this.addView(mSuccessView);
            }
        }

        private void refreshSuccessView() {
            if (null !=this.mSuccessView) {
                // 控制success视图显示隐藏
                this.mSuccessView.setVisibility((mState
                        == BaseLoadState.SUCCESS) ? View.VISIBLE : View.GONE);
            }
        }

        private void refreshCommonView() {
            this.mLoadingView.setVisibility((mState == BaseLoadState.LOADING)
                    || (mState == BaseLoadState.NONE) ? View.VISIBLE : View.GONE);
            // 控制emptyView视图显示隐藏
            this.mEmptyView.setVisibility((mState == BaseLoadState.EMPTY)
                    ? View.VISIBLE : View.GONE);
            // 控制errorView视图显示隐藏
            this.mErrorView.setVisibility((mState == BaseLoadState.ERROR)
                    ? View.VISIBLE : View.GONE);
        }

        private void refreshUI() {
            refreshCommonView();
            addSuccessView();
            refreshSuccessView();
        }

        /**
         * 用于暴露给各fragment加载数据
         * 其他子类fragment可以调用该方法加载数据
         */
        public void startLoading() {
            new Thread(new OnLoadingTask()).start();
        }

        private class OnLoadingTask implements Runnable {
            @Override
            public void run() {
                //异步加载数据返回一个BaseLoadState的值
                BaseLoadState oldState = mState;
                BaseLoadState newState = onAsyncLoading();
                //结果处理
                mState = newState;
                ViewUIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        //刷新UI
                        refreshUI();
                    }
                });
            }
        }
    }
}
