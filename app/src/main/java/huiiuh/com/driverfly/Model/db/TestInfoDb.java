package huiiuh.com.driverfly.Model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import huiiuh.com.driverfly.Model.dao.TestInfoTable;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestInfoDb extends SQLiteOpenHelper {
    public TestInfoDb(Context context) {
        super(context, "testInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TestInfoTable.CREATE_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
