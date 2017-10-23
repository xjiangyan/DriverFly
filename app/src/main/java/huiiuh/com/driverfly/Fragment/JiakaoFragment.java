package huiiuh.com.driverfly.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import huiiuh.com.driverfly.Base.BaseFragment;
import huiiuh.com.driverfly.Pager.Subject_Four;
import huiiuh.com.driverfly.Pager.Subject_One;
import huiiuh.com.driverfly.Pager.Subject_Three;
import huiiuh.com.driverfly.Pager.Subject_Two;
import huiiuh.com.driverfly.R;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class JiakaoFragment extends BaseFragment {

    private ViewPager mViewpager_jiakao;
    private TabLayout mTablayout;
    private ArrayList<Fragment> mFragments;
    private String[] mTitles;
    private View mView;

    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.fragment_jiakao, null);
        findView();
        mViewpager_jiakao.setAdapter(new MyAdapter(getFragmentManager()));
        mTablayout.setupWithViewPager(mViewpager_jiakao);

        return mView;
    }

    private void findView() {
        mViewpager_jiakao = (ViewPager) mView.findViewById(R.id.viewpager_jiakao);
        mTablayout = (TabLayout) mView.findViewById(R.id.tablayout);
        mFragments = new ArrayList<>();
        mFragments.add(new Subject_One());
        mFragments.add(new Subject_Two());
        mFragments.add(new Subject_Three());
        mFragments.add(new Subject_Four());
        mTitles = new String[]{"科目一", "科目二", "科目三", "科目四","公交"};
    }

    @Override
    public void initData() {

        super.initData();


    }

    @Override
    protected void loadData() {

    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
