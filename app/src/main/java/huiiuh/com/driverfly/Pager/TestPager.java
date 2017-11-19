package huiiuh.com.driverfly.Pager;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import huiiuh.com.driverfly.Activity.ResultActivity;
import huiiuh.com.driverfly.Activity.TestActivity;
import huiiuh.com.driverfly.Base.BaseFragment;
import huiiuh.com.driverfly.Constants;
import huiiuh.com.driverfly.Model.bean.DataBean;
import huiiuh.com.driverfly.Model.bean.TestInfoBean;
import huiiuh.com.driverfly.Model.dao.TestInfoDao;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;

import static huiiuh.com.driverfly.R.id.line_option1;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestPager extends BaseFragment implements View.OnClickListener {


    private final List<DataBean.ResultBeanX.ResultBean.ListBean> list;

    private int position;
    private String type;
    private int subject;
    private int pagenum;
    private String sort;
    private View mTest_pager;
    private LinearLayout mLine_explain;

    private ProgressBar mProgressBar;
    private String mSubject;
    private String mChapter;
    private String mQuestion;
    private String mPic;
    private String mOption1;
    private String mOption2;
    private String mOption3;
    private String mOption4;
    private String mAnswer;
    private String mExplain;
    private TextView mTest_question;
    private TextView mTest_daan;
    private TextView mTest_explain;
    private TextView mTest_option1;
    private TextView mTest_option2;
    private TextView mTest_option3;
    private TextView mTest_option4;
    private ImageView mTest_pic;
    private List<DataBean.ResultBeanX.ResultBean.ListBean> mList;
    private String answer;
    private RadioButton mCheckedbutton;

    private ImageView mIv_option1;
    private ImageView mIv_option2;
    private ImageView mIv_option3;
    private ImageView mIv_option4;
    private LinearLayout mLine_option1;
    private LinearLayout mLine_option2;
    private LinearLayout mLine_option3;
    private LinearLayout mLine_option4;
    private LinearLayout mLine_questionAll;
    private String check;
    private int trueOrFalse;
    private Button mBtn_mult_check;
    private String mQuestion_type;
    boolean line_option1_selected = false;
    boolean line_option2_selected = false;
    boolean line_option3_selected = false;
    boolean line_option4_selected = false;
    private ImageView mIv_questiontype;
    String multcheck = "";

    public TestPager(List<DataBean.ResultBeanX.ResultBean.ListBean> list, int position) {
        super();
        this.list = list;
        this.position = position;

    }


    @Override
    public View initView() {

        mTest_pager = View.inflate(mContext, R.layout.test_pager, null);
        findview();
        return mTest_pager;
    }


    private void findview() {
        mTest_question = (TextView) mTest_pager.findViewById(R.id.test_question);
        mTest_option1 = (TextView) this.mTest_pager.findViewById(R.id.test_Option1);
        mTest_option2 = (TextView) this.mTest_pager.findViewById(R.id.test_Option2);
        mTest_option3 = (TextView) this.mTest_pager.findViewById(R.id.test_Option3);
        mTest_option4 = (TextView) this.mTest_pager.findViewById(R.id.test_Option4);
        mIv_option1 = (ImageView) mTest_pager.findViewById(R.id.iv_option1);
        mIv_option2 = (ImageView) mTest_pager.findViewById(R.id.iv_option2);
        mIv_option3 = (ImageView) mTest_pager.findViewById(R.id.iv_option3);
        mIv_option4 = (ImageView) mTest_pager.findViewById(R.id.iv_option4);
        mTest_pic = (ImageView) this.mTest_pager.findViewById(R.id.test_pic);
        mTest_daan = (TextView) this.mTest_pager.findViewById(R.id.test_daan);
        mTest_explain = (TextView) this.mTest_pager.findViewById(R.id.test_explain);
        mLine_option1 = (LinearLayout) mTest_pager.findViewById(line_option1);
        mLine_option2 = (LinearLayout) mTest_pager.findViewById(R.id.line_option2);
        mLine_option3 = (LinearLayout) mTest_pager.findViewById(R.id.line_option3);
        mLine_option4 = (LinearLayout) mTest_pager.findViewById(R.id.line_option4);
        mLine_questionAll = (LinearLayout) mTest_pager.findViewById(R.id.line_questionAll);
        mBtn_mult_check = (Button) mTest_pager.findViewById(R.id.btn_mult_check);
        mIv_questiontype = (ImageView) mTest_pager.findViewById(R.id.iv_questiontype);

        mLine_option1.setOnClickListener(this);
        mLine_option2.setOnClickListener(this);
        mLine_option3.setOnClickListener(this);
        mLine_option4.setOnClickListener(this);
        mBtn_mult_check.setOnClickListener(new MultOnClick());

        mLine_explain = (LinearLayout) mTest_pager.findViewById(R.id.line_explain);
        mProgressBar = (ProgressBar) mTest_pager.findViewById(R.id.progressBar);


    }

    @Override
    public void initData() {
        super.initData();
        setData();


    }

    @Override
    protected void loadData() {

        //        setData();

    }

    private void setData() {
        TestInfoDao testInfoDao = new TestInfoDao(mContext);
        String cartype = SpUtil.getInstance().getString(Constants.CARTYPE, "c1");
        String subject = SpUtil.getInstance().getString(Constants.SUBJECT, "1");
        String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");


        processData(list);

        mProgressBar.setVisibility(View.INVISIBLE);
        mTest_question.setText(mQuestion);
        mTest_option1.setText(mOption1);
        mTest_option2.setText(mOption2);
        mTest_option3.setText(mOption3);
        mTest_option4.setText(mOption4);
        mTest_daan.setText("答案：" + mAnswer);
        mTest_explain.setText("   " + mExplain);


        //是否是答题模式
        if (SpUtil.getInstance().getBoolean(Constants.ISDATI, true)) {
            mLine_explain.setVisibility(View.INVISIBLE);

        } else {
            showResult();
            mLine_explain.setVisibility(View.VISIBLE);
            mLine_option1.setEnabled(false);
            mLine_option2.setEnabled(false);
            mLine_option3.setEnabled(false);
            mLine_option4.setEnabled(false);
        }

        if (testInfoDao.getItem(cartype, subject, cartype + "-" + subject + "-" + testtype + "-" + position) != null) {

            TestInfoBean c1 = testInfoDao.getItem(cartype, subject, cartype + "-" + subject + "-" + testtype + "-" + position);
            TestInfoBean c11 = testInfoDao.getTrueOrFalseByAll(cartype, subject, cartype + "-" + subject + "-" + testtype + "-" + position);

            String item = c1.getItem();
            String trueorfalse = c11.getTrueOrFalse();
            loadResult(item, trueorfalse);
            mLine_explain.setVisibility(View.VISIBLE);

        }
    }

    /**
     * 读取存储的数据
     *
     * @param useranswer
     * @param trueorfalse
     */
    private void loadResult(String useranswer, String trueorfalse) {
        if (trueorfalse.equals("0")) {
            loadUserAnswersByRight(useranswer);
        } else if (trueorfalse.equals("1")) {
            if (useranswer.length() > 1) {
                String[] useranswers = new String[useranswer.length()];
                for (int i = 0; i < useranswer.length(); i++) {
                    useranswers[i] = useranswer.substring(i, i + 1);
                    loadUserAnswersByWrong(useranswers[i]);
                }
            }
        }
        mLine_option1.setEnabled(false);
        mLine_option2.setEnabled(false);
        mLine_option3.setEnabled(false);
        mLine_option4.setEnabled(false);

        showResult();

    }

    private void loadUserAnswersByRight(String useranswer) {
        switch (useranswer) {
            case "A":
                mIv_option1.setBackgroundResource(R.drawable.ic_answer_currect);
                break;
            case "对":
                mIv_option1.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "B":
                mIv_option2.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "错":
                mIv_option2.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "C":
                mIv_option3.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "D":
                mIv_option4.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
        }
    }

    private void loadUserAnswersByWrong(String useranswer) {
        switch (useranswer) {
            case "A":
                mIv_option1.setBackgroundResource(R.drawable.ic_answer_wrong);
                break;
            case "对":
                mIv_option1.setBackgroundResource(R.drawable.ic_answer_wrong);

                break;
            case "B":
                mIv_option2.setBackgroundResource(R.drawable.ic_answer_wrong);

                break;
            case "错":
                mIv_option2.setBackgroundResource(R.drawable.ic_answer_wrong);

                break;
            case "C":
                mIv_option3.setBackgroundResource(R.drawable.ic_answer_wrong);

                break;
            case "D":
                mIv_option4.setBackgroundResource(R.drawable.ic_answer_wrong);

                break;
        }
    }


    private void processData(List<DataBean.ResultBeanX.ResultBean.ListBean> data) {

        mChapter = data.get(position).getChapter();
        mQuestion = data.get(position).getQuestion();
        mPic = data.get(position).getPic();


        if (data.get(position).getOption1().length() > 3) {
            mOption1 = splitData(data.get(position).getOption1());
            mOption2 = splitData(data.get(position).getOption2());
            mOption3 = splitData(data.get(position).getOption3());
            mOption4 = splitData(data.get(position).getOption4());
        } else if (data.get(position).getOption1().length() == 0) {

            mLine_option3.setVisibility(View.GONE);
            mLine_option4.setVisibility(View.GONE);
            mOption1 = "对";
            mOption2 = "错";
        }
        mAnswer = data.get(position).getAnswer();
        mExplain = data.get(position).getExplain();
        judgeQuestionType(mAnswer);

        if (data.get(position).getPic() != null && data.get(position).getPic().length() > 0) {
            mPic = data.get(position).getPic();
            Picasso.with(mContext).load(mPic).into(mTest_pic);
            Log.d("TestPager", "你他妈执行了？");
        }


    }

    /**
     * 判断题目类型
     *
     * @param answer
     */
    private void judgeQuestionType(String answer) {
        if (answer.length() > 1) {
            SpUtil.getInstance().save(Constants.QUESTION_TYPE, "1");
            mIv_questiontype.setImageResource(R.drawable.jiakao_practise_duoxuanti_day);

        } else {
            if (answer.equals("对") || answer.equals("错")) {
                mIv_questiontype.setImageResource(R.drawable.jiakao_practise_panduanti_day);
            } else {
                mIv_questiontype.setImageResource(R.drawable.jiakao_practise_danxuanti_day);

            }
            SpUtil.getInstance().save(Constants.QUESTION_TYPE, "0");

        }
        mQuestion_type = SpUtil.getInstance().getString(Constants.QUESTION_TYPE, "0");
        if (mQuestion_type.equals("1")) {
            mBtn_mult_check.setVisibility(View.VISIBLE);
        }
    }

    private String splitData(String data) {


        return data.substring(2, data.length());
    }


    @Override
    public void onClick(View view) {
        if (mQuestion_type.equals("0")) {
            //单选的情况
            switch (view.getId()) {
                case line_option1:
                    if (mAnswer.equals("A") || mAnswer.equals("对")) {
                        mIv_option1.setBackgroundResource(R.drawable.ic_answer_currect);
                        trueOrFalse = 0;
                        goToNextViewPager();
                    } else {
                        mIv_option1.setBackgroundResource(R.drawable.ic_answer_wrong);
                        trueOrFalse = 1;
                    }
                    check = "A";

                    break;
                case R.id.line_option2:
                    if (mAnswer.equals("B") || mAnswer.equals("错")) {
                        mIv_option2.setBackgroundResource(R.drawable.ic_answer_currect);
                        goToNextViewPager();
                        trueOrFalse = 0;

                    } else {
                        mIv_option2.setBackgroundResource(R.drawable.ic_answer_wrong);
                        trueOrFalse = 1;
                    }
                    check = "B";

                    break;
                case R.id.line_option3:
                    if (mAnswer.equals("C")) {
                        mIv_option3.setBackgroundResource(R.drawable.ic_answer_currect);
                        goToNextViewPager();
                        trueOrFalse = 0;

                    } else {
                        mIv_option3.setBackgroundResource(R.drawable.ic_answer_wrong);
                        trueOrFalse = 1;
                    }
                    check = "C";

                    break;
                case R.id.line_option4:
                    if (mAnswer.equals("D")) {
                        mIv_option4.setBackgroundResource(R.drawable.ic_answer_currect);
                        goToNextViewPager();
                        trueOrFalse = 0;

                    } else {
                        mIv_option4.setBackgroundResource(R.drawable.ic_answer_wrong);
                        trueOrFalse = 1;

                    }
                    check = "D";

                    break;

            }
            showResult();
            storageInfo();


            TestActivity testActivity = (TestActivity) mContext;

            if (!testActivity.mIv_timestart.isEnabled()) {

                testActivity.mIv_timestart.setEnabled(true);
                testActivity.StartOrStopTimer();
            }
            showIfFinishedTest();

            mLine_option1.setEnabled(false);
            mLine_option2.setEnabled(false);
            mLine_option3.setEnabled(false);
            mLine_option4.setEnabled(false);
        } else if (mQuestion_type.equals("1")) {
            //多选的情况

            switch (view.getId()) {
                case line_option1:
                    if (mLine_option1.isEnabled()) {

                        if (!line_option1_selected) {

                            mLine_option1.setBackgroundColor(Color.GRAY);
                        } else {
                            mLine_option1.setBackgroundColor(Color.TRANSPARENT);

                        }
                        line_option1_selected = !line_option1_selected;
                    }

                    break;
                case R.id.line_option2:
                    if (mLine_option2.isEnabled()) {

                        if (!line_option2_selected) {

                            mLine_option2.setBackgroundColor(Color.GRAY);
                        } else {
                            mLine_option2.setBackgroundColor(Color.TRANSPARENT);

                        }
                        line_option2_selected = !line_option2_selected;
                    }
                    break;
                case R.id.line_option3:
                    if (mLine_option3.isEnabled()) {

                        if (!line_option3_selected) {

                            mLine_option3.setBackgroundColor(Color.GRAY);
                        } else {
                            mLine_option3.setBackgroundColor(Color.TRANSPARENT);

                        }
                        line_option3_selected = !line_option3_selected;
                    }
                    break;
                case R.id.line_option4:
                    if (mLine_option4.isEnabled()) {

                        if (!line_option4_selected) {

                            mLine_option4.setBackgroundColor(Color.GRAY);
                        } else {
                            mLine_option4.setBackgroundColor(Color.TRANSPARENT);

                        }
                        line_option4_selected = !line_option4_selected;
                    }
                    break;
            }
        }
    }


    private void showIfFinishedTest() {
        if (SpUtil.getInstance().getString(Constants.TESTTYPE, "0").equals("2")) {
            String cartype = SpUtil.getInstance().getString(Constants.CARTYPE, "c1");
            String subject = SpUtil.getInstance().getString(Constants.SUBJECT, "1");
            String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");

            TestInfoDao testInfoDao = new TestInfoDao(mContext);
            int falsenum = testInfoDao.getTrueOrFalseByLitter(cartype, subject, cartype + "-" + subject + "-" + testtype, "1");
            if (falsenum > 10) {
                Intent intent = new Intent(mContext, ResultActivity.class);
                startActivity(intent);
                mContext.finish();
            }
        }
    }

    private void showResult() {
        mLine_explain.setVisibility(View.VISIBLE);
        if (mAnswer.length() < 2) {
            showSingleResult(mAnswer);

        } else if (mAnswer.length() > 1) {
            String[] answers = new String[mAnswer.length()];

            for (int i = 0; i < mAnswer.length(); i++) {
                answers[i] = mAnswer.substring(i, i + 1);
                Log.d("TestPager", "答案是" + answers[i]);
                showSingleResult(answers[i]);

            }
        }
    }

    private void showSingleResult(String answer) {
        switch (answer.toString()) {
            case "A":
                mIv_option1.setBackgroundResource(R.drawable.ic_answer_currect);
                break;
            case "对":
                mIv_option1.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "B":
                mIv_option2.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "错":
                mIv_option2.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "C":
                mIv_option3.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
            case "D":
                mIv_option4.setBackgroundResource(R.drawable.ic_answer_currect);

                break;
        }
    }

    private void storageInfo() {
        TestActivity testActivity = (TestActivity) mContext;

        String cartype = SpUtil.getInstance().getString(Constants.CARTYPE, "c1");
        String subject = SpUtil.getInstance().getString(Constants.SUBJECT, "1");
        String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");
        TestInfoDao testInfoDao = new TestInfoDao(mContext);
        TestInfoBean testInfoBean = new TestInfoBean();
        testInfoBean.setType(cartype);
        testInfoBean.setSubject(subject);
        testInfoBean.setType_subject_currentItem(cartype + "-" + subject + "-" + testtype + "-" + position);
        testInfoBean.setItem(check);
        testInfoBean.setTrueOrFalse(trueOrFalse + "");
        testInfoDao.addTestInfo(testInfoBean);
    }

    private void goToNextViewPager() {

        TestActivity testActivity = (TestActivity) mContext;
        //        testActivity.mTest_viewpager.setCurrentItem(position + 1);
        testActivity.mTest_viewpager.setCurrentItem(testActivity.mTest_viewpager.getCurrentItem() + 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        String cartype = SpUtil.getInstance().getString(Constants.CARTYPE, "c1");
        String subject = SpUtil.getInstance().getString(Constants.SUBJECT, "1");
        String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");


        TestInfoDao testInfoDao = new TestInfoDao(mContext);
        testInfoDao.deleteDataByTesttype(cartype, subject, testtype);
    }

    private class MultOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (line_option1_selected) {
                multcheck = multcheck + "A";
            }
            if (line_option2_selected) {
                multcheck = multcheck + "B";

            }
            if (line_option3_selected) {
                multcheck = multcheck + "C";

            }
            if (line_option4_selected) {
                multcheck = multcheck + "D";

            }
            if (multcheck.equals(answer)) {
                trueOrFalse = 0;
            } else {
                trueOrFalse = 1;
            }
            mLine_option1.setEnabled(false);
            mLine_option2.setEnabled(false);
            mLine_option3.setEnabled(false);
            mLine_option4.setEnabled(false);

            String cartype = SpUtil.getInstance().getString(Constants.CARTYPE, "c1");
            String subject = SpUtil.getInstance().getString(Constants.SUBJECT, "1");
            String testtype = SpUtil.getInstance().getString(Constants.TESTTYPE, "0");
            TestInfoDao testInfoDao = new TestInfoDao(mContext);
            TestInfoBean testInfoBean = new TestInfoBean();
            testInfoBean.setType(cartype);
            testInfoBean.setSubject(subject);
            testInfoBean.setType_subject_currentItem(cartype + "-" + subject + "-" + testtype + "-" + position);
            testInfoBean.setItem(multcheck);
            testInfoBean.setTrueOrFalse(trueOrFalse + "");
            testInfoDao.addTestInfo(testInfoBean);
            //            showResult();
            loadResult(multcheck, trueOrFalse + "");
        }
    }
}



