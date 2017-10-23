package huiiuh.com.driverfly;

import android.app.Application;
import android.content.Context;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局上下文对象
        mContext = this;
    }

    public static Context getGlobalApplication() {
        return mContext;
    }


}
