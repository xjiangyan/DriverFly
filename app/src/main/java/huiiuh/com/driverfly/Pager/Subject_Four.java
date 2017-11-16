package huiiuh.com.driverfly.Pager;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import huiiuh.com.driverfly.Activity.IntroduceActivity;
import huiiuh.com.driverfly.Base.BaseFragment;
import huiiuh.com.driverfly.Contact;
import huiiuh.com.driverfly.Util.SpUtil;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Subject_Four extends BaseFragment {
    @Override
    public View initView() {
        TextView te = new TextView(mContext);
        te.setText("科目四");
        te.setGravity(Gravity.CENTER);
        Button button = new Button(mContext);
        button.setText("点击啊啊啊啊");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtil.getInstance().save(Contact.SUBJECT, "4");
                Intent intent = new Intent(mContext, IntroduceActivity.class);
                startActivity(intent);
            }
        });

        return button;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void loadData() {

    }
}
