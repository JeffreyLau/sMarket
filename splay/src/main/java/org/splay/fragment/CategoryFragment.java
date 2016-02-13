package org.splay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.splay.utils.UIUtils;

/**
 * Created by jeffrey on 16-2-13.
 */
public class CategoryFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText(this.getClass().getSimpleName());
    }
}
