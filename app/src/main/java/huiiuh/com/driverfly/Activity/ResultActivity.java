package huiiuh.com.driverfly.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huiiuh.com.driverfly.Constants;
import huiiuh.com.driverfly.Model.dao.TestInfoDao;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.titleBack)
    ImageView mTitleBack;
    @Bind(R.id.tv_rightnum)
    TextView mTvRightnum;
    @Bind(R.id.tv_wrongnum)
    TextView mTvWrongnum;
    @Bind(R.id.tv_undonum)
    TextView mTvUndonum;
    @Bind(R.id.btn_check)
    Button mBtnCheck;
    @Bind(R.id.line_result_closingwords)
    LinearLayout mLineResultClosingwords;
    @Bind(R.id.iv_resultimageview)
    ImageView mIvResultimageview;
    @Bind(R.id.tv_resultscore)
    TextView mTvResultscore;
    @Bind(R.id.line_result_imageandscore)
    LinearLayout mLineResultImageandscore;
    private int mRightnum;
    private int mFalsenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);


        initView();
        setData();
    }

    private void setData() {
        String listnum = SpUtil.getInstance().getString(Constants.LISTNUM, "0");
        String cartype = SpUtil.getInstance().getString(Constants.CARTYPE, "c1");
        String subject = SpUtil.getInstance().getString(Constants.SUBJECT, "1");
        String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");
        TestInfoDao testInfoDao = new TestInfoDao(getApplicationContext());
        mRightnum = testInfoDao.getTrueOrFalseByLitter(cartype, subject, cartype + "-" + subject + "-" + testtype, "0");
        mFalsenum = testInfoDao.getTrueOrFalseByLitter(cartype, subject, cartype + "-" + subject + "-" + testtype, "1");
        int i = Integer.parseInt(listnum) - mRightnum - mFalsenum;
        mTvRightnum.setText(mRightnum + "\n作对的题数");
        mTvWrongnum.setText(mFalsenum + "\n作错的题数");
        mTvUndonum.setText(i + "\n未做的题数");
        Log.d("ResultActivity", "作对的题数是" + mRightnum);
        Log.d("ResultActivity", "作错的题数是" + mFalsenum);
        Log.d("ResultActivity", "未做的题数是" + listnum);
        if (testtype.equals("2")) {

            showresultimageandscore();
        } else {
            mLineResultClosingwords.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示结果图片以及分数
     */
    private void showresultimageandscore() {
        mLineResultImageandscore.setVisibility(View.VISIBLE);
        if (mRightnum < 90) {
            mTvResultscore.setTextColor(Color.RED);
            mIvResultimageview.setImageResource(R.drawable.malushahou_bg_w);
        } else if (mRightnum >= 90) {
            if (mRightnum == 90) {
                mIvResultimageview.setImageResource(R.drawable.xingyuner_bg_w);
            } else if (mRightnum > 90 && mRightnum < 100) {
                mIvResultimageview.setImageResource(R.drawable.jkbiaobing_bg_w);
            } else if (mRightnum == 100) {
                mIvResultimageview.setImageResource(R.drawable.jkshenren_bg_w);
            }
            mTvResultscore.setTextColor(Color.GREEN);
        }


        mTvResultscore.setText(mRightnum + "分");
    }


    private void initView() {

        mTitleBack.setVisibility(View.VISIBLE);
        mTitleBack.setOnClickListener(this);
        mBtnCheck.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        finish();

    }
}
