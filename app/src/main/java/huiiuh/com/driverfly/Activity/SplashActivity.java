package huiiuh.com.driverfly.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.DensityUtil;


public class SplashActivity extends AppCompatActivity {

    private ViewPager mViewpager_splash;
    private int[] mGuides;
    private LinearLayout mIndicator_line;
    private ImageView mGuide_start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findview();
        init();
    }

    private void init() {

        mGuide_start = (ImageView) findViewById(R.id.guide_start);
        mGuide_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        for (int i = 0; i < 4; i++) {
            ImageView mIndicator = new ImageView(getApplicationContext());
            mIndicator.setImageResource(R.drawable.indicator_selector);


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i != 0) {
                layoutParams.leftMargin = DensityUtil.dip2px(getApplicationContext(), 10);
            }


            mIndicator.setLayoutParams(layoutParams);
            mIndicator_line.addView(mIndicator);
            mIndicator_line.getChildAt(0).setEnabled(false);

        }

        mGuides = new int[]{R.drawable.splash1, R.drawable.splash2, R.drawable.splash3, R.drawable.splash4};
        mViewpager_splash.setAdapter(new MyViewPagerAdapter());
        mViewpager_splash.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 4; i++) {
                    if (i == position) {
                        mIndicator_line.getChildAt(i).setEnabled(false);
                    } else {
                        mIndicator_line.getChildAt(i).setEnabled(true);

                    }
                    if (position == 3) {
                        mGuide_start.setVisibility(View.VISIBLE);
                    } else {
                        mGuide_start.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void findview() {
        mViewpager_splash = (ViewPager) findViewById(R.id.viewpager_splash);
        mIndicator_line = (LinearLayout) findViewById(R.id.indicator_line);

    }

    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(mGuides[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return mGuides.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
