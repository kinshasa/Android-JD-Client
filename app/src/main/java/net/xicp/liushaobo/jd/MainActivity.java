package net.xicp.liushaobo.jd;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import net.xicp.liushaobo.jd.ui.activity.BaseActivity;
import net.xicp.liushaobo.jd.ui.fragment.HomeFragment;
import net.xicp.liushaobo.jd.ui.fragment.CartFragment;
import net.xicp.liushaobo.jd.ui.fragment.CategoryFragment;
import net.xicp.liushaobo.jd.ui.fragment.MineFragment;
import net.xicp.liushaobo.jd.ui.fragment.NewsFragment;
import net.xicp.liushaobo.jd.ui.viewpager.APSTSViewPager;

/**
 * Created by lshaobocsu@gmail.com on 2017.3.27.
 */

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    public AdvancedPagerSlidingTabStrip mAPSTS;
    public APSTSViewPager mVP;

    private static final int VIEW_HOME = 0;
    private static final int VIEW_CATEGORY = 1;
    private static final int VIEW_NEWS = 2;
    private static final int VIEW_CART = 3;
    private static final int VIEW_MINE = 4;

    private static final int VIEW_SIZE = 5;

    private HomeFragment mHomeFragment = null;
    private CategoryFragment mCategoryFragment = null;
    private NewsFragment mNewsFragment = null;
    private CartFragment mCartFragment = null;
    private MineFragment mMineFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        init();
    }

    private void findViews() {
        mAPSTS = (AdvancedPagerSlidingTabStrip) findViewById(R.id.tabs);
        mVP = (APSTSViewPager) findViewById(R.id.vp_main);
    }

    private void init() {
        mVP.setOffscreenPageLimit(VIEW_SIZE);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        mVP.setAdapter(new FragmentAdapter(getSupportFragmentManager()));

        adapter.notifyDataSetChanged();
        mAPSTS.setViewPager(mVP);
        mAPSTS.setOnPageChangeListener(this);
        mVP.setCurrentItem(VIEW_HOME);
        mAPSTS.showDot(VIEW_HOME, "99+");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FragmentAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_HOME:
                        if (null == mHomeFragment)
                            mHomeFragment = HomeFragment.instance();
                        return mHomeFragment;

                    case VIEW_CATEGORY:
                        if (null == mCategoryFragment)
                            mCategoryFragment = CategoryFragment.instance();
                        return mCategoryFragment;

                    case VIEW_NEWS:
                        if (null == mNewsFragment)
                            mNewsFragment = NewsFragment.instance();
                        return mNewsFragment;

                    case VIEW_CART:
                        if (null == mCartFragment)
                            mCartFragment = CartFragment.instance();
                        return mCartFragment;

                    case VIEW_MINE:
                    default:
                        if (null == mMineFragment)
                            mMineFragment = mMineFragment.instance();
                        return mMineFragment;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return VIEW_SIZE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0 && position < VIEW_SIZE) {
                switch (position) {
                    case VIEW_HOME:
                        return "京东";
                    case VIEW_CATEGORY:
                        return "分类";
                    case VIEW_NEWS:
                        return "发现";
                    case VIEW_CART:
                        return "购物车";
                    case VIEW_MINE:
                        return "我的";
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public Integer getPageIcon(int index) {
            if (index >= 0 && index < VIEW_SIZE) {
                switch (index) {
                    case VIEW_HOME:
                        return R.drawable.ic_home_black_24dp;
                    case VIEW_CATEGORY:
                        return R.drawable.ic_category_black_24dp;
                    case VIEW_NEWS:
                        return R.drawable.ic_find_in_page_black_24dp;
                    case VIEW_CART:
                        return R.drawable.ic_shopping_cart_black_24dp;
                    case VIEW_MINE:
                        return R.drawable.ic_person_outline_black_24dp;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Integer getPageSelectIcon(int index) {
            if (index >= 0 && index < VIEW_SIZE) {
                switch (index) {
                    case VIEW_HOME:
                        return R.drawable.ic_home_black_24dp_selected;
                    case VIEW_CATEGORY:
                        return R.drawable.ic_category_black_24dp_selected;
                    case VIEW_NEWS:
                        return R.drawable.ic_find_in_page_black_24dp_selected;
                    case VIEW_CART:
                        return R.drawable.ic_shopping_cart_black_24dp_selected;
                    case VIEW_MINE:
                        return R.drawable.ic_person_outline_black_24dp_selected;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Rect getPageIconBounds(int position) {
            return null;
        }
    }
}
