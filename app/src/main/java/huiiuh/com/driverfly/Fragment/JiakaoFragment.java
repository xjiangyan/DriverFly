package huiiuh.com.driverfly.Fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import huiiuh.com.driverfly.Activity.CarTypeActivity;
import huiiuh.com.driverfly.Base.BaseFragment;
import huiiuh.com.driverfly.Contact;
import huiiuh.com.driverfly.Pager.Subject_Four;
import huiiuh.com.driverfly.Pager.Subject_One;
import huiiuh.com.driverfly.Pager.Subject_Three;
import huiiuh.com.driverfly.Pager.Subject_Two;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class JiakaoFragment extends BaseFragment {

    public ViewPager mViewpager_jiakao;
    private TabLayout mTablayout;
    private ArrayList<Fragment> mFragments;
    private String[] mTitles;
    private View mView;
    private TextView mTv_changecartype;
    private TextView mTitleName;


    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.fragment_jiakao, null);
        findView();
        mViewpager_jiakao.setAdapter(new MyAdapter(getFragmentManager()));
        mTablayout.setupWithViewPager(mViewpager_jiakao);
        mViewpager_jiakao.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                SpUtil.getInstance().save(Contact.SUBJECT, position + 1 + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return mView;
    }

    private void findView() {

        mViewpager_jiakao = (ViewPager) mView.findViewById(R.id.viewpager_jiakao);
        mTv_changecartype = (TextView) mView.findViewById(R.id.tv_changecartype);
        mTv_changecartype.setVisibility(View.VISIBLE);
        mTitleName = (TextView) mView.findViewById(R.id.titleName);
        mTitleName.setText("DRIVERFLY");
        mTitleName.setVisibility(View.VISIBLE);
        String c1 = SpUtil.getInstance().getString(Contact.CARTYPE, "c1");
        String cartype = c1;
        switch (c1) {
            case "c1":
                cartype = "C1/C2/C3";
                break;
            case "a2":
                cartype = "A2/B2";

                break;
            case "b1":
                cartype = "A1/A3/B1";

                break;
            case "d":
                cartype = "D/E/F";

                break;
        }
        mTv_changecartype.setText("车辆类型\n" + cartype);
        mTv_changecartype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CarTypeActivity.class);
                startActivity(intent);
            }
        });
        mTablayout = (TabLayout) mView.findViewById(R.id.tablayout);
        mFragments = new ArrayList<>();
        mFragments.add(new Subject_One());
        mFragments.add(new Subject_Two());
        mFragments.add(new Subject_Three());
        mFragments.add(new Subject_Four());
        mTitles = new String[]{"科目一", "科目二", "科目三", "科目四", "公交"};
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
