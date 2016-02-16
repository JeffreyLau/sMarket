package org.splay.base;

import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;

import org.splay.fragment.AppFragment;
import org.splay.fragment.CategoryFragment;
import org.splay.fragment.GameFragment;
import org.splay.fragment.HomeFragment;
import org.splay.fragment.HotFragment;
import org.splay.fragment.RecommendFragment;
import org.splay.fragment.SubjectFragment;

/**
 * Created by jeffrey on 16-2-13.
 */
public class BaseFragmentManager {

    private static BaseFragmentManager sInstance;
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_APP = 1;
    public static final int FRAGMENT_GAME = 2;
    public static final int FRAGMENT_SUBJECT = 3;
    public static final int FRAGMENT_RECOMMEND = 4;
    public static final int FRAGMENT_CATEGORY = 5;
    public static final int FRAGMENT_HOT = 6;

    private static SparseArrayCompat<BaseFragment> cacheFragment =
            new SparseArrayCompat<BaseFragment>();

    public static final BaseFragmentManager getDefault() {
        if (sInstance == null) {
            sInstance = new BaseFragmentManager();
        }
        return sInstance;
    }

    public static Fragment getFragment(int position) {
        BaseFragment mBaseFragment = null;
        BaseFragment mFragment = cacheFragment.get(position);
        if (null != mFragment) {
            mBaseFragment = mFragment;
            return mBaseFragment;
        }
        switch (position) {
            case FRAGMENT_HOME:
                mBaseFragment = new HomeFragment();
                break;
            case FRAGMENT_APP:
                mBaseFragment = new AppFragment();
                break;
            case FRAGMENT_GAME:
                mBaseFragment = new GameFragment();
                break;
            case FRAGMENT_SUBJECT:
                mBaseFragment = new SubjectFragment();
                break;
            case FRAGMENT_RECOMMEND:
                mBaseFragment = new RecommendFragment();
                break;
            case FRAGMENT_CATEGORY:
                mBaseFragment = new CategoryFragment();
                break;
            case FRAGMENT_HOT:
                mBaseFragment = new HotFragment();
                break;
            default:
                break;
        }
        cacheFragment.put(position, mBaseFragment);
        return mBaseFragment;
    }
}
