package huiiuh.com.driverfly.Util;

import android.content.Context;
import android.content.SharedPreferences;

import huiiuh.com.driverfly.MyApplication;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

public class SpUtil {

    private static SpUtil instance = new SpUtil();
    private static SharedPreferences mSp;

    private SpUtil() {

    }

    // 单例
    public static SpUtil getInstance() {

        //        context.getSharedPreferences()
        if (mSp == null) {
            //在MyApplication里获取全局上下文对象
            mSp = MyApplication.getGlobalApplication().getSharedPreferences("config", Context.MODE_PRIVATE);
        }

        return instance;
    }

    // 保存
    public void save(String key, Object value) {

        if (value instanceof String) {
            mSp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            mSp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            mSp.edit().putInt(key, (Integer) value).commit();
        }
    }

    // 获取数据的方法
    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    // 获取boolean数据
    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    // 获取int类型数据
    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }
}
