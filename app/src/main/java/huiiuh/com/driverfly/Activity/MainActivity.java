package huiiuh.com.driverfly.Activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import huiiuh.com.driverfly.Fragment.DiscoverFragment;
import huiiuh.com.driverfly.Fragment.JiakaoFragment;
import huiiuh.com.driverfly.Fragment.UserFragment;
import huiiuh.com.driverfly.R;

public class MainActivity extends AppCompatActivity {

    private RadioButton mRb_jiakao;
    private RadioButton mRb_discover;
    private RadioButton mRb_user;
    private FrameLayout mMain_contain;
    private RadioGroup mRg_main;
    private int checkednum = 0;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        init();
    }

    private void init() {
        mRb_jiakao.setChecked(true);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.main_contain, mFragments.get(0)).commit();
        mRg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_jiakao:
                        Toast.makeText(MainActivity.this, "驾考", Toast.LENGTH_SHORT).show();
                        checkednum = 0;
                        break;
                    case R.id.rb_discover:
                        Toast.makeText(MainActivity.this, "发现", Toast.LENGTH_SHORT).show();
                        checkednum = 1;

                        break;
                    case R.id.rb_user:
                        Toast.makeText(MainActivity.this, "用户", Toast.LENGTH_SHORT).show();
                        checkednum = 2;
                        break;
                }
                ShowAndHindFragment(checkednum);
            }
        });
    }



    private void ShowAndHindFragment(int num) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        for (int i = 0; i < 3; i++) {
            if (mFragments.get(i) != null) {

                transaction.hide(mFragments.get(i));
                Log.d("MainActivity", "隐藏" + i);
            }
            if (i == num) {
                if (!mFragments.get(i).isAdded()) {
                    transaction.add(R.id.main_contain, mFragments.get(i)).show(mFragments.get(i));
                    Log.d("MainActivity", "添加" + i);

                } else {

                    transaction.show(mFragments.get(i));
                    Log.d("MainActivity", "显示" + i);

                }
            }

        }
        transaction.commit();

    }

    private void findview() {
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);
        mRb_jiakao = (RadioButton) findViewById(R.id.rb_jiakao);
        mRb_discover = (RadioButton) findViewById(R.id.rb_discover);
        mRb_user = (RadioButton) findViewById(R.id.rb_user);
        mMain_contain = (FrameLayout) findViewById(R.id.main_contain);
        mFragments = new ArrayList<>();
        mFragments.add(new JiakaoFragment());
        mFragments.add(new DiscoverFragment());
        mFragments.add(new UserFragment());
    }
}
