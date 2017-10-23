package huiiuh.com.driverfly.Pager;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import huiiuh.com.driverfly.Base.BaseFragment;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Bus_Service extends BaseFragment {
    @Override
    public View initView() {
        TextView te=new TextView(mContext);
        te.setText("公交服务");
        te.setGravity(Gravity.CENTER);
        return te;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void loadData() {

    }
}
