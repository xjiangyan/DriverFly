package huiiuh.com.driverfly.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import huiiuh.com.driverfly.Constants;
import huiiuh.com.driverfly.R;

import static huiiuh.com.driverfly.R.id.webview;

public class URlActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.titleBack)
    ImageView mTitleBack;
    @Bind(R.id.titleName)
    TextView mTitleName;
    @Bind(webview)
    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        ButterKnife.bind(this);
        init();

    }

    /**
     * 初始化webview以及标题
     */
    private void init() {

        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);

        // webview.loadUrl("file:///android_asset/article/detail/baomingxuzhi.html");
        Intent intent = getIntent();
        String url_path = intent.getStringExtra(Constants.URL_PATH);
        mWebview.loadUrl(Constants.BASEURL + url_path);
        mWebview.getSettings().setJavaScriptEnabled(true);
        //        mWebview.getSettings().setUseWideViewPort(true);
        //        mWebview.getSettings().setLoadWithOverviewMode(true);

        if (intent.getStringExtra(Constants.URL_PATH2) != null) {
            WebView webview2 = (WebView) findViewById(R.id.webview2);
            webview2.loadUrl(Constants.BASEURL + intent.getStringExtra(Constants.URL_PATH2));
            webview2.getSettings().setJavaScriptEnabled(true);
            //            webview2.getSettings().setLoadWithOverviewMode(true);
            //            webview2.getSettings().setUseWideViewPort(true);
        }
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleBack.setOnClickListener(this);
        String titlename = intent.getStringExtra(Constants.TITLENAME);
        mTitleName.setVisibility(View.VISIBLE);
        mTitleName.setText(titlename);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBack:
                finish();
                break;
            default:
                break;
        }
    }
}
