package huiiuh.com.driverfly.Pager;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import huiiuh.com.driverfly.Base.BaseFragment;
import huiiuh.com.driverfly.Bean.DataBean;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;


/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestPager extends BaseFragment {


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


    public TestPager(String type, int subject, int pagenum, String sort, int position) {
        super();
        this.type = type;
        this.subject = subject;
        this.pagenum = pagenum;
        this.sort = sort;
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
        mTest_option1 = (TextView) mTest_pager.findViewById(R.id.test_Option1);
        mTest_option2 = (TextView) mTest_pager.findViewById(R.id.test_Option2);
        mTest_option3 = (TextView) mTest_pager.findViewById(R.id.test_Option3);
        mTest_option4 = (TextView) mTest_pager.findViewById(R.id.test_Option4);
        mTest_pic = (ImageView) mTest_pager.findViewById(R.id.test_pic);
        mTest_daan = (TextView) mTest_pager.findViewById(R.id.test_daan);
        mTest_explain = (TextView) mTest_pager.findViewById(R.id.test_explain);
        mLine_explain = (LinearLayout) mTest_pager.findViewById(R.id.line_explain);
        mProgressBar = (ProgressBar) mTest_pager.findViewById(R.id.progressBar);
        mTest_question.setVisibility(View.VISIBLE);

        if (SpUtil.getInstance().getBoolean("isdati", true)) {
            mLine_explain.setVisibility(View.INVISIBLE);
        } else {
            mLine_explain.setVisibility(View.VISIBLE);
        }


        //        //读取assets下的资源文件
        //        try {
        //            InputStream is = mContext.getAssets().open("question_c1_subjectOne.txt");
        //            int lenght = 0;
        //
        //            lenght = is.available();
        //
        //            byte[] buffer = new byte[lenght];
        //
        //            is.read(buffer);
        //
        //            String result = new String(buffer, "utf8");
        //            processData(result);
        //            Log.d("TestActivity", "读取到的" + result);
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            Log.d("TestActivity", "读取出错" + e.getMessage().toString());
        //        }

        //   mTest_pager.setOnTouchListener(new MyTouchListener());
    }

    @Override
    public void initData() {
        super.initData();
        setData();

    }

    @Override
    protected void loadData() {

    }

    private void setData() {

                        //读取assets下的资源文件
                        try {
                            InputStream is = mContext.getAssets().open("question_c1_subjectOne.txt");
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


        //        mProgressBar.setVisibility(View.INVISIBLE);
        mTest_question.setText(mQuestion);
        mTest_option1.setText(mOption1);
        mTest_option2.setText(mOption2);
        mTest_option3.setText(mOption3);
        mTest_option4.setText(mOption4);
        mTest_daan.setText("答案：" + mAnswer);
        mTest_explain.setText("   " + mExplain);
    }


    private void processData(String data) {
        Gson gson = new Gson();
        DataBean dataBean = gson.fromJson(data, DataBean.class);
        mList = dataBean.getResult().getResult().getList();
        mChapter = mList.get(position).getChapter();
        mQuestion = mList.get(position).getQuestion();
        mPic = mList.get(position).getPic();


        if (mList.get(position).getOption1().length() > 3) {
            mOption1 = splitData(mList.get(position).getOption1());
            mOption2 = splitData(mList.get(position).getOption2());
            mOption3 = splitData(mList.get(position).getOption3());
            mOption4 = splitData(mList.get(position).getOption4());
        } else if (mList.get(position).getOption1().length() == 0) {
            mTest_option3.setVisibility(View.GONE);
            mTest_option4.setVisibility(View.GONE);
            mOption1 = "对";
            mOption2 = "错";
        }
        mAnswer = mList.get(position).getAnswer();
        mExplain = mList.get(position).getExplain();

        if (mList.get(position).getPic() != null) {
            mPic = mList.get(position).getPic();
            Picasso.with(mContext).load(mPic).into(mTest_pic);
            Log.d("TestPager", "你他妈执行了？");
        }

        //        Log.d("TestPager", "问题：" + mQuestion + mOption1 + mOption2 + mOption3 + mOption4 + mAnswer + mExplain);

    }

    private String splitData(String data) {


        return data.substring(2, data.length());
    }

    //    private class MyTouchListener implements View.OnTouchListener {
    //        float startX = 0;
    //        float juli;
    //
    //        @Override
    //        public boolean onTouch(View view, MotionEvent motionEvent) {
    //
    //
    //            switch (motionEvent.getAction()) {
    //                case MotionEvent.ACTION_DOWN:
    //                    startX = motionEvent.getX();
    //                    break;
    //                case MotionEvent.ACTION_UP:
    //                    break;
    //                case MotionEvent.ACTION_MOVE:
    //                    juli = motionEvent.getX() - startX;
    //                    if (juli > 0) {
    //                        mContext.finish();
    //                    }else if(juli<0){
    //
    //                    }
    //                    break;
    //            }
    //            return false;
    //        }
    //    }
}
