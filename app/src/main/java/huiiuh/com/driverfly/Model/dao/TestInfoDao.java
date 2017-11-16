package huiiuh.com.driverfly.Model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import huiiuh.com.driverfly.Model.bean.TestInfoBean;
import huiiuh.com.driverfly.Model.db.TestInfoDb;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestInfoDao {

    private final TestInfoDb mHelper;

    public TestInfoDao(Context context) {
        mHelper = new TestInfoDb(context);
    }

    public void addTestInfo(TestInfoBean testInfoBean) {
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(TestInfoTable.COL_Type, testInfoBean.getType());
        values.put(TestInfoTable.COL_Subject, testInfoBean.getSubject());
        values.put(TestInfoTable.COL_Item, testInfoBean.getItem());
        values.put(TestInfoTable.COL_TYPE_SUBJECT_CURRENTITEM, testInfoBean.getType_subject_currentItem());
        values.put(TestInfoTable.COL_TrueOrFalse, testInfoBean.getTrueOrFalse());

        readableDatabase.replace(TestInfoTable.TAB_NAME, null, values);
    }

    public TestInfoBean getTrueOrFalseByAll(String type, String subject, String col_type_subject_currentitem) {

        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        String sql = "select * from " + TestInfoTable.TAB_NAME +
                " where " + TestInfoTable.COL_Type + "= ? and " +
                TestInfoTable.COL_Subject + " =? and " +
                TestInfoTable.COL_TYPE_SUBJECT_CURRENTITEM + "=?;";
        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{type, subject, col_type_subject_currentitem});
        TestInfoBean mTestInfoBean = null;

        if (cursor.moveToNext()) {
            mTestInfoBean = new TestInfoBean();
            mTestInfoBean.setTrueOrFalse(cursor.getString(cursor.getColumnIndex(TestInfoTable.COL_TrueOrFalse)));

        }
        cursor.close();

        return mTestInfoBean;
    }

    public int getTrueOrFalseByLitter(String type, String subject, String col_type_subject_currentitem, String trueorfalse) {

        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        String sql = "select * from " + TestInfoTable.TAB_NAME +
                " where " + TestInfoTable.COL_Type + "= ? and " +
                TestInfoTable.COL_Subject + " =? and " +
                TestInfoTable.COL_TYPE_SUBJECT_CURRENTITEM + " like ? and TrueOrFalse=?;";
        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{type, subject, col_type_subject_currentitem + "%", trueorfalse});

        int count = cursor.getCount();

        cursor.close();
        //        select * from Testinfo  where TYPE_SUBJECT_CURRENTITEM like'c1-1-0%' and TrueOrFalse='0'
        return count;
    }

    public TestInfoBean getItem(String type, String subject, String col_type_subject_currentitem) {
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        String sql = "select * from " + TestInfoTable.TAB_NAME +
                " where " + TestInfoTable.COL_Type + "= ? and " +
                TestInfoTable.COL_Subject + " =? and " +
                TestInfoTable.COL_TYPE_SUBJECT_CURRENTITEM + "=?;";
        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{type, subject, col_type_subject_currentitem});
        TestInfoBean mTestInfoBean = null;

        if (cursor.moveToNext()) {
            mTestInfoBean = new TestInfoBean();
            mTestInfoBean.setItem(cursor.getString(cursor.getColumnIndex(TestInfoTable.COL_Item)));

        }
        cursor.close();

        return mTestInfoBean;
    }

    public void deleteDataByTesttype(String type, String subject, String testtype) {
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        //        String sql="delete  from Testinfo  where TYPE_SUBJECT_CURRENTITEM like'c1-1-1%'";
        if (!testtype.equals("0")) {

            String sql = "delete  from Testinfo  where TYPE_SUBJECT_CURRENTITEM like'" + type + "-" + subject + "-" + testtype + "%'";
            readableDatabase.execSQL(sql);
        }
    }
}
