package org.splay.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import org.splay.base.BaseFragment;
import org.splay.base.BaseLoadState;

/**
 * Created by jeffrey on 16-2-13.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HotFragment";
    @Override
    public View onInitSuccessView() {
        TextView textView = new TextView(mContext);
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

    @Override
    public BaseLoadState onAsyncLoading() {
        SystemClock.sleep(3000);
        return BaseLoadState.SUCCESS;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startLoading();
    }
}
