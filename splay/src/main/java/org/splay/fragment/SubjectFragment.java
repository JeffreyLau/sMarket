package org.splay.fragment;

import android.view.View;

import org.splay.base.BaseFragment;
import org.splay.base.BaseLoadState;

/**
 * Created by jeffrey on 16-2-13.
 */
public class SubjectFragment extends BaseFragment {
    private static final String TAG = "SubjectFragment";

    @Override
    public View onInitSuccessView() {
        return null;
    }

    @Override
    public BaseLoadState onAsyncLoading() {
        return BaseLoadState.SUCCESS;
    }
}
