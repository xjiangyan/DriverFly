package huiiuh.com.driverfly.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import huiiuh.com.driverfly.Constants;
import huiiuh.com.driverfly.Model.bean.DataBean;
import huiiuh.com.driverfly.Pager.TestPager;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;

import static android.widget.Toast.makeText;

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
    public List<DataBean.ResultBeanX.ResultBean.ListBean> mList;

    private String mTotal;
    private int num;

    private boolean isLastPage = false;
    private boolean isDragPage = false;
    private boolean canJumpPage = true;

    private RelativeLayout mRela_extraarea;
    private LinearLayout mLine_timer;
    private int mTimers = 2700000;
    private TextView mTv_timer;
    public ImageView mIv_timestart;
    private TextView mTv_finishtest;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTimers = mTimers - 1000;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            String time = simpleDateFormat.format(new Date(mTimers));
            if (mTimers < 10000) {
                mTv_timer.setTextColor(Color.RED);
            }
            mTv_timer.setText(time);
            mHandler.removeMessages(0);
            mHandler.sendEmptyMessageDelayed(1, 1000);
            if (mTimers <= 0) {

                mHandler.removeCallbacksAndMessages(null);
                finishTestAndShowScore();
            }
        }
    };


    /**
     * 结束答题并且显示成绩
     */
    private void finishTestAndShowScore() {
        Intent intent = new Intent(getApplication(), ResultActivity.class);
        startActivity(intent);
        finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        findview();
        init();


    }

    /**
     * 生成0-i的随机数
     *
     * @return
     */
    private int random_num(int i) {
        ArrayList<Integer> list = new ArrayList<>();
        int r = (int) (Math.random() * i);

        for (int v : list) {
            if (v == r) {
                return random_num(i);
            }
        }
        list.add(r);
        Log.d("TestActivity", "随机数" + r);
        return r;
    }

    private void init() {


        if (SpUtil.getInstance().getBoolean(Constants.ISDATI, true)) {
            mBtnDati.setChecked(true);

        } else {
            mBtnBeiti.setChecked(true);

        }
        String cartype = SpUtil.getInstance().getString(Constants.CARTYPE, "c1");
        String subject = SpUtil.getInstance().getString(Constants.SUBJECT, "1");
        String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");

        getData(cartype, subject);

        mFragments = new ArrayList<>();

        if (testtype.equals("0")) {

            for (int i = 0; i < mList.size(); i++) {

                mFragments.add(new TestPager(mList, i));
            }
        } else if (testtype.equals("1")) {
            ArrayList<Integer> randnums = new ArrayList<>();

            for (int j = 0; j < mList.size(); j++) {
                randnums.add(random_num(mList.size()));
            }

            for (int i = 0; i < mList.size(); i++) {
                mFragments.add(new TestPager(mList, randnums.get(i)));

            }
        } else if (testtype.equals("2")) {
            ArrayList<Integer> randnums = new ArrayList<>();

            for (int j = 0; j < 100; j++) {

                randnums.add(random_num(100));
            }

            for (int i = 0; i < 100; i++) {

                mFragments.add(new TestPager(mList, randnums.get(i)));

            }

            mTitleName.setText("全真模拟");
            mTitleName.setVisibility(View.VISIBLE);

            //初始化计时器
            mRela_extraarea.setVisibility(View.VISIBLE);
            mTitlebarRg.setVisibility(View.INVISIBLE);
            mHandler.sendEmptyMessageDelayed(0, 1000);
        }


        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mTest_viewpager.setAdapter(mMyPagerAdapter);
        //判断viewpager是否是最后一页，是的话向左滑动进入下一页
        mTest_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isLastPage && isDragPage && positionOffsetPixels == 0) {   //当前页是最后一页，并且是拖动状态，并且像素偏移量为0
                    if (canJumpPage) {

                        canJumpPage = false;
                        JumpToNext();
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                isLastPage = position == mList.size() - 1;
            }

            /*
             在手指操作屏幕的时候发生变化
             @param state   有三个值：0（END）,1(PRESS) , 2(UP) 。
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                isDragPage = state == 1;
            }
        });
        if (SpUtil.getInstance().getString(Constants.TESTTYPE, "0").equals("0")) {
            int currentItem = SpUtil.getInstance().getInt(Constants.CURRENTITEM, 0);

            //            mTest_viewpager.setCurrentItem(currentItem);
            mTest_viewpager.setCurrentItem(currentItem);
        }


        mTitlebarRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.btn_dati:
                        makeText(getApplicationContext(), "答题", Toast.LENGTH_SHORT).show();
                        SpUtil.getInstance().save(Constants.ISDATI, true);
                        mMyPagerAdapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_beiti:
                        makeText(getApplicationContext(), "背题", Toast.LENGTH_SHORT).show();
                        SpUtil.getInstance().save(Constants.ISDATI, false);
                        mMyPagerAdapter.notifyDataSetChanged();

                        break;
                }
            }
        });
    }

    private void JumpToNext() {
        Intent intent = new Intent(TestActivity.this, ResultActivity.class);
        startActivity(intent);
        TestActivity.this.overridePendingTransition(R.anim.next_in, R.anim.next_out);
        finish();
        makeText(this, "已经是最后一页了！", Toast.LENGTH_SHORT).show();
    }


    private void getData(String cartype, String subject) {


        String url = cartype + "_" + subject + "_" + "0.txt";
        //        String url = "c1_1_0.txt";
        // webview.loadUrl("file:///android_asset/article/detail/baomingxuzhi.html");

        //读取assets下的资源文件

        try {
            InputStream is = getAssets().open(url);
            //            InputStream is = getAssets().open("file:///android_asset/"+url);
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
        mTotal = dataBean.getResult().getResult().getTotal();
        mList = dataBean.getResult().getResult().getList();
        if (SpUtil.getInstance().getString(Constants.TESTTYPE, "0").equals("2")) {
            SpUtil.getInstance().save(Constants.LISTNUM, 100 + "");

        } else {
            SpUtil.getInstance().save(Constants.LISTNUM, mList.size() + "");

        }
    }

    private void findview() {

        mTest_viewpager = (ViewPager) findViewById(R.id.test_viewpager);
        mLine_explain = (LinearLayout) findViewById(R.id.line_explain);
        mRela_extraarea = (RelativeLayout) findViewById(R.id.rela_extraarea);
        mLine_timer = (LinearLayout) findViewById(R.id.line_timer);
        mTv_timer = (TextView) findViewById(R.id.tv_timer);
        mIv_timestart = (ImageView) findViewById(R.id.iv_timestart);
        mTv_finishtest = (TextView) findViewById(R.id.tv_finishtest);
        mTv_finishtest.setOnClickListener(this);
        mLine_timer.setOnClickListener(this);
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
                String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");

                if (testtype.equals("2")) {
                    showGoOutMessage();
                } else {

                    finish();
                }
                break;
            case R.id.titleFunction:
                showTextSizeChangeBar();
                break;
            case R.id.line_timer:
                mIv_timestart.setEnabled(!mIv_timestart.isEnabled());
                StartOrStopTimer();

                break;
            case R.id.tv_finishtest:
                showFinishMessage();
                if (mIv_timestart.isEnabled()) {
                    mIv_timestart.setEnabled(false);

                    StartOrStopTimer();
                }

                break;
        }
    }


    public void StartOrStopTimer() {
        if (!mIv_timestart.isEnabled()) {

            mHandler.removeCallbacksAndMessages(null);
        } else {
            if (mTimers > 0)
                mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 提交答卷的提示框
     */
    private void showFinishMessage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定提交试卷结束答题？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplication(), ResultActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mIv_timestart.setEnabled(true);
                StartOrStopTimer();

            }
        });

        builder.show();
    }


    private void showTextSizeChangeBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String sizestext[] = new String[]{"0.5特小字体", "0.75小字体", "1.0普通字体", "1.2大字体", "1.5特大字体"};
        final String sizes[] = new String[]{"0.5", "0.75", "1.0", "1.2", "1.5"};
        String textsize = SpUtil.getInstance().getString(Constants.TEXTSIZE, sizes[2]);
        int checkedtextsize = 2;
        switch (textsize) {
            case "0.5":
                checkedtextsize = 0;
                break;
            case "0.75":
                checkedtextsize = 1;
                break;
            case "1.0":
                checkedtextsize = 2;
                break;
            case "1.25":
                checkedtextsize = 3;
                break;
            case "1.5":
                checkedtextsize = 4;
                break;

        }
        builder.setSingleChoiceItems(sizes, checkedtextsize, new DialogInterface.OnClickListener() {
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
                SpUtil.getInstance().save(Constants.TEXTSIZE, sizes[size]);
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
            //刷新页面
            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            showGoOutMessage();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    private void showGoOutMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要退出答题吗？");
        builder.setTitle("提示");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        int currentItem = SpUtil.getInstance().getInt(Constants.CURRENTITEM, 0);
        if (currentItem < mTest_viewpager.getCurrentItem()) {
            currentItem = mTest_viewpager.getCurrentItem();
            SpUtil.getInstance().save(Constants.CURRENTITEM, currentItem);
        }
        Log.d("TestActivity", "保存的item位置是" + currentItem);
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        canJumpPage = true;
    }

    @Override
    public Resources getResources() {
        String textsize = SpUtil.getInstance().getString(Constants.TEXTSIZE, "1.0");
        float v = Float.parseFloat(textsize);
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale = v; //1 设置正常字体大小的倍数sp为单位的
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;

    }
}
