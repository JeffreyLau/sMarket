package org.splay.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.splay.R;
import org.splay.factory.PriorityPoolFactory;
import org.splay.task.Priority;
import org.splay.task.PriorityRunnable;
import org.splay.utils.ViewUIUtils;
import org.xutils.x;

/**
 * Created by jeffrey on 16-2-13.
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";
    private boolean injected = false;
    public Context mContext;
    private FrameView mFrameView;

    public FrameView getFrameView() {
        return mFrameView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mContext = getActivity();
        if (null == mFrameView)
            mFrameView = new FrameView(mContext);
        return mFrameView;
        //injected = true;
        //return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }

        Log.i(TAG,"onViewCreated");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
        //if (null != mFrameViewHolder)
        //     mFrameViewHolder.removeCommonView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    /**
     * 因为每个子Fragment都不知道要如何显示
     * 所以每个子Fragment必须实现该方法
     *
     * @return
     */
    public abstract View onInitSuccessView();

    /**
     * 用于子类异步加载数据
     * 放在哪里加载?
     * 放在当前页面被选中的时候加载
     *
     * @return
     */
    public abstract BaseLoadState onLoading();




    /**
     * 每一个子fragment都用ContentViewGroup来描述
     * 并且每个子Fragment都用下面４种View状态
     *
     * @mLoadingView :加載數據顯示的視圖
     * @mErrorView:加载数据失败显示的视图
     * @mEmptyView:空视图
     * @mSuccessView:加载数据成功显示的视图
     */
    public class FrameView extends FrameLayout {

        private View mLoadingView;
        private View mErrorView;
        private View mEmptyView;
        private View mSuccessView;
        private BaseLoadState mState = BaseLoadState.NONE;

        public FrameView(Context context) {
            super(context);
            initCommonView(context);
            addCommonView();
            refreshUI();
        }

        /**
         * @param context
         */
        private void initCommonView(Context context) {
            // ① 加载页面
            this.mLoadingView = View.inflate(context, R.layout.pager_loading, null);
            // ② 错误页面
            this.mErrorView = View.inflate(context, R.layout.pager_error, null);
            this.mErrorView.findViewById(R.id.error_btn_retry).
                    setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i(TAG, "error_btn_retry");
                        }
                    });
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
                //setPadding(int left, int top, int right, int bottom)
                //this.setPadding(0,52,0,0);
                this.addView(mSuccessView);
            }
        }

        private void refreshSuccessView() {
            if (null != this.mSuccessView) {
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
            //每次加载数据前都处于加载状态,如果加载成功过的就无需再次加载数据
            if (mState != BaseLoadState.SUCCESS &&
                    mState != BaseLoadState.LOADING) {
                Log.i(TAG, "startLoading");
                mState = BaseLoadState.LOADING;
                refreshUI();
                PriorityPoolFactory.getMainPool().
                        execute(new PriorityRunnable(Priority.BG_TOP,
                                new OnLoadingTask()));
            }
        }

        private class OnLoadingTask implements Runnable {
            @Override
            public void run() {
                //异步加载数据返回一个BaseLoadState的值
                BaseLoadState oldState = mState;
                BaseLoadState newState = onLoading();
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
