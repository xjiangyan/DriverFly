package huiiuh.com.driverfly.Activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import huiiuh.com.driverfly.Pager.TestPager;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.titleBack)
    ImageView mTitleBack;
    @Bind(R.id.btn_dati)
    RadioButton mBtnDati;
    @Bind(R.id.btn_beiti)
    RadioButton mBtnBeiti;
    @Bind(R.id.titlebar_rg)
    RadioGroup mTitlebarRg;
    @Bind(R.id.titleName)
    TextView mTitleName;
    @Bind(R.id.titleFunction)
    ImageView mTitleFunction;
    @Bind(R.id.titleBar)
    RelativeLayout mTitleBar;

    private ViewPager mTest_viewpager;
    private List<Fragment> mFragments;
    private LinearLayout mLine_explain;
    private MyPagerAdapter mMyPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        findview();
        init();
    }

    private void init() {
        if (SpUtil.getInstance().getBoolean("isdati", true)) {
            mBtnDati.setChecked(true);

        } else {
            mBtnBeiti.setChecked(true);

        }


        String type = SpUtil.getInstance().getString("type", "c1");
        mFragments = new ArrayList<>();
        for (int i = 0; i < 1311; i++) {

            mFragments.add(new TestPager(type, 1, 1, "sort", i));
        }
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mTest_viewpager.setAdapter(mMyPagerAdapter);
        //        mTest_viewpager.setCurrentItem(SpUtil.getInstance().getInt("testedquestionnum", 0));
        //        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        //
        //
        //        //替换页面
        //        transaction.replace(R.id.frame_testcontain, new TestPager("c1", 1, 1, "normal",1)).commit();
        mTitlebarRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.btn_dati:
                        Toast.makeText(getApplicationContext(), "答题", Toast.LENGTH_SHORT).show();
                        SpUtil.getInstance().save("isdati", true);
                        mMyPagerAdapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_beiti:
                        Toast.makeText(getApplicationContext(), "背题", Toast.LENGTH_SHORT).show();
                        SpUtil.getInstance().save("isdati", false);
                        mMyPagerAdapter.notifyDataSetChanged();

                        break;
                }
            }
        });
    }

    private void findview() {

        mTest_viewpager = (ViewPager) findViewById(R.id.test_viewpager);
        mLine_explain = (LinearLayout) findViewById(R.id.line_explain);


        mTitleBack.setOnClickListener(this);
        mTitleFunction.setOnClickListener(this);
        mTitleName.setVisibility(View.INVISIBLE);
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleFunction.setVisibility(View.VISIBLE);
        mTitlebarRg.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;


        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        private int mChildCount = 0;



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        public MyPagerAdapter(FragmentManager fm) {
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
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {

            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }
    }
}
