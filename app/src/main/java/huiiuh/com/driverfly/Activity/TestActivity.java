package huiiuh.com.driverfly.Activity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import huiiuh.com.driverfly.Bean.DataBean;
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

    public ViewPager mTest_viewpager;
    private List<Fragment> mFragments;
    private LinearLayout mLine_explain;
    public MyPagerAdapter mMyPagerAdapter;
    private List<DataBean.ResultBeanX.ResultBean.ListBean> mList;
    private PopupWindow mPopupWindow;


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

        getData();
        String type = SpUtil.getInstance().getString("type", "c1");
        mFragments = new ArrayList<>();
        for (int i = 0; i < 1311; i++) {

            mFragments.add(new TestPager(mList, i));
        }
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mTest_viewpager.setAdapter(mMyPagerAdapter);
        mTest_viewpager.setOffscreenPageLimit(10);

        int currentItem = SpUtil.getInstance().getInt("CurrentItem", 0);

        mTest_viewpager.setCurrentItem(currentItem);

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


    private void getData() {
        //读取assets下的资源文件
        try {
            InputStream is = getAssets().open("question_c1_subjectOne.txt");
            int lenght = 0;

            lenght = is.available();

            byte[] buffer = new byte[lenght];

            is.read(buffer);

            String result = new String(buffer, "utf8");
            processData(result);
            Log.d("TestActivity", "读取到的" + result);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TestActivity", "读取出错" + e.getMessage().toString());
        }
    }

    private void processData(String result) {
        Gson gson = new Gson();
        DataBean dataBean = gson.fromJson(result, DataBean.class);
        mList = dataBean.getResult().getResult().getList();
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
            case R.id.titleFunction:
                showTextSizeChangeBar();
                break;

        }
    }

    private void showTextSizeChangeBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String sizes[] = new String[]{"0.5", "0.75", "1.0", "1.2", "1.5"};

        builder.setSingleChoiceItems(sizes, 2, new DialogInterface.OnClickListener() {
            int size = 0;

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        size = 0;
                        break;
                    case 1:
                        size = 1;
                        break;
                    case 2:
                        size = 2;
                        break;
                    case 3:
                        size = 3;
                        break;
                    case 4:
                        size = 4;
                        break;
                }
                SpUtil.getInstance().save("textsize", sizes[size]);
                mMyPagerAdapter.notifyDataSetChanged();
            }
        }).show();
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int currentItem = SpUtil.getInstance().getInt("CurrentItem", 0);
        if (currentItem < mTest_viewpager.getCurrentItem()) {
            currentItem = mTest_viewpager.getCurrentItem();
            SpUtil.getInstance().save("CurrentItem", currentItem);
        }
        Log.d("TestActivity", "保存的item位置是" + currentItem);
    }

    @Override
    public Resources getResources() {
        String textsize = SpUtil.getInstance().getString("textsize", "1.0");
        float v = Float.parseFloat(textsize);
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale = v; //1 设置正常字体大小的倍数
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;

    }
}
