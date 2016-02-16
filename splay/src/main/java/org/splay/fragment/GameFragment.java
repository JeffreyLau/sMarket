package org.splay.fragment;

import android.view.View;
import android.widget.TextView;

import org.splay.base.BaseFragment;
import org.splay.base.BaseLoadState;

/**
 * Created by jeffrey on 16-2-13.
 */

public class GameFragment extends BaseFragment {
    private static final String TAG = "GameFragment";

    @Override
    public View onInitSuccessView() {
        TextView textView = new TextView(mContext);
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

    @Override
    public BaseLoadState onAsyncLoading() {
        return BaseLoadState.SUCCESS;
    }
}
