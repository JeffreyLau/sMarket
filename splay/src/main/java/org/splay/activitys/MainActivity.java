package org.splay.activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import org.splay.R;
import org.splay.base.BaseActivity;
import org.splay.base.BaseFragment;
import org.splay.factory.FragmentFactory;
import org.splay.fragment.MenuFragment;
import org.splay.utils.LogUtils;
import org.splay.utils.ViewUIUtils;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.fab)
    private FloatingActionButton fab;
    @ViewInject(R.id.main_tabs)
    private PagerSlidingTabStrip main_tabs;
    @ViewInject(R.id.main_viewpager)
    private ViewPager main_viewpager;
    @ViewInject(R.id.drawer_main)
    private DrawerLayout drawer_main;

    private MenuFragment mMenuFragment;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbarAndDralerLayout();
        initMenuFragment();
        initFab();
        initViewPagerAndTab();
    }

    private void initToolbarAndDralerLayout() {
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("sPlay");

        //初始化drawerlayout
        drawer_main.setDrawerShadow(R.mipmap.drawer_shadow,
                GravityCompat.START);
        //drawer_main.setDrawerListener(this);

        //初始化ActionBarDrawerToggle
        //初始化开关，并和drawer关联
        //为了生成，工具栏左上角的动态图标，要使用下面的方法
        mToggle = new ActionBarDrawerToggle(this, drawer_main,
                toolbar, R.string.drawer_open, R.string.drawer_close);
        mToggle.syncState();//该方法会自动和toolBar关联

        drawer_main.setDrawerListener(mToggle);
    }

    private void initFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void initViewPagerAndTab() {
        //main_viewpager.setAdapter(new HomePagerAdapter());
        //main_viewpager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        main_viewpager.setAdapter(new MainFragmentStateAdapter(getSupportFragmentManager()));
        main_tabs.setViewPager(main_viewpager);
        main_tabs.setOnPageChangeListener(new TabOnPageChangeListener());
    }

    private void initMenuFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mMenuFragment = new MenuFragment();
        transaction.replace(R.id.menu_fragment, mMenuFragment);
        transaction.commit();
    }


    private String[] initViewPager() {
        return ViewUIUtils.getStringArr(R.array.main_titles);
    }

    private class HomePagerAdapter extends PagerAdapter {
        private String[] pagerTitle;

        private HomePagerAdapter() {
            pagerTitle = initViewPager();
        }

        @Override
        public int getCount() {
            if (null != pagerTitle)
                return pagerTitle.length;
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText(pagerTitle[position]);
            container.addView(textView);
            return textView;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pagerTitle[position];
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class MainFragmentAdapter extends FragmentPagerAdapter {

        private String[] pagerTitle;

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
            pagerTitle = initViewPager();
        }

        @Override
        public Fragment getItem(int position) {
            LogUtils.d("初始化:" + pagerTitle[position]);
            return FragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            if (null != pagerTitle)
                return pagerTitle.length;
            return 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pagerTitle[position];
        }
    }

    private class MainFragmentStateAdapter extends FragmentStatePagerAdapter {

        private String[] pagerTitle;

        public MainFragmentStateAdapter(FragmentManager fm) {
            super(fm);
            pagerTitle = initViewPager();
        }

        @Override
        public Fragment getItem(int position) {
            LogUtils.d("初始化:" + pagerTitle[position]);
            return FragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            if (null != pagerTitle)
                return pagerTitle.length;
            return 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pagerTitle[position];
        }
    }

    private class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //触发数据加载
            BaseFragment mBaseFragment =
                    FragmentFactory.getFragment(position);
            mBaseFragment.getFrameView().startLoading();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
