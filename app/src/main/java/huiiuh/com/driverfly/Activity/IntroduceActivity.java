package huiiuh.com.driverfly.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huiiuh.com.driverfly.Contact;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.CircleImageView;
import huiiuh.com.driverfly.Util.SpUtil;

public class IntroduceActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.titleBack)
    ImageView mTitleBack;
    @Bind(R.id.circleimageview)
    CircleImageView mCircleimageview;
    @Bind(R.id.kaoshikemu)
    TextView mKaoshikemu;
    @Bind(R.id.kaoshitiku)
    TextView mKaoshitiku;
    @Bind(R.id.kaoshibiaozhun)
    TextView mKaoshibiaozhun;
    @Bind(R.id.tv_guize)
    TextView mTvGuize;
    @Bind(R.id.tv_jifenguize)
    TextView mTvJifenguize;
    @Bind(R.id.btn_startTest)
    Button mBtnStartTest;
    @Bind(R.id.titleName)
    TextView mTitleName;
    private String mKemu;

    private String mTiku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleName.setVisibility(View.VISIBLE);
        mTitleName.setText("模拟考试");
        mTitleBack.setOnClickListener(this);
        mBtnStartTest.setOnClickListener(this);
        String subject = SpUtil.getInstance().getString(Contact.SUBJECT, "1");
        String cartype = SpUtil.getInstance().getString(Contact.CARTYPE, "c1");
        getMessage(subject, cartype);


        mKaoshikemu.setText("科目" + mKemu + "理论考试");
        mKaoshitiku.setText(mTiku);


    }

    private void getMessage(String subject, String cartype) {
        if (subject.equals("1")) {
            mKemu = "一";
        } else {
            mKemu = "四";
        }
        switch (cartype) {
            case "c1":
                mTiku = "小车C1/C2/C3";
                break;
            case "a2":
                mTiku = "货车A2/B2";

                break;
            case "b1":
                mTiku = "客车A1/A3/B1";

                break;
            case "d":
                mTiku = "摩托D/E/F";

                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startTest:
                Intent intent = new Intent(getApplication(), TestActivity.class);
                startActivity(intent);
                finish();
                SpUtil.getInstance().save(Contact.ISTEST, true);
                break;
            case R.id.titleBack:
                finish();
                break;
        }
    }
}
