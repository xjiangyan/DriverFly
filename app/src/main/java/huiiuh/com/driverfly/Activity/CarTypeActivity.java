package huiiuh.com.driverfly.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import huiiuh.com.driverfly.Constants;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;

public class CarTypeActivity extends AppCompatActivity {

    private RadioGroup mRg_type;
    private Button mBtn_typecheck;
    private String type = "c1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        //        if (!SpUtil.getInstance().getBoolean(Constants.ISCHOOSETYPE, false)) {
        //            findview();
        //            init();
        //        }
        //        else {
        //            Intent intent = new Intent(getApplication(), MainActivity.class);
        //            startActivity(intent);
        //            finish();
        //        }
        findview();
        init();
    }

    private void init() {
        mRg_type.check(R.id.rb_xiaoche);
        mRg_type.setOnCheckedChangeListener(new MyCheckedChangeListener());
        mBtn_typecheck.setOnClickListener(new Myonclick());
    }

    private void findview() {
        ImageView titleBack = (ImageView) findViewById(R.id.titleBack);
        ImageView titleFunction = (ImageView) findViewById(R.id.titleFunction);
        TextView titleName = (TextView) findViewById(R.id.titleName);
        titleName.setVisibility(View.VISIBLE);
        titleName.setText("题库类型");
        titleBack.setVisibility(View.INVISIBLE);
        titleFunction.setVisibility(View.INVISIBLE);
        mRg_type = (RadioGroup) findViewById(R.id.rg_type);

        mBtn_typecheck = (Button) findViewById(R.id.btn_typecheck);
    }

    private class MyCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.rb_xiaoche:
                    type = "c1";
                    break;
                case R.id.rb_huoche:
                    type = "a2";
                    break;
                case R.id.rb_keche:
                    type = "b1";
                    break;
                case R.id.rb_motuo:
                    type = "d";
                    break;
            }
        }
    }

    private class Myonclick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            SpUtil.getInstance().save(Constants.CARTYPE, type);
            SpUtil.getInstance().save(Constants.ISCHOOSETYPE, true);
            Toast.makeText(CarTypeActivity.this, "已选择" + type + "类车型", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
