package org.splay.fragment;

import android.support.v4.app.Fragment;

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

    public static final BaseFragmentManager getDefault() {
        if (sInstance == null) {
            sInstance = new BaseFragmentManager();
        }
        return sInstance;
    }

    public static Fragment getFragment(int position) {
        BaseFragment mBaseFragment = null;
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
        return mBaseFragment;
    }
}
