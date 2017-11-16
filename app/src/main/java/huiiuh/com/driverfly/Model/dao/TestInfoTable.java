package huiiuh.com.driverfly.Model.dao;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestInfoTable {
    public static final String TAB_NAME = "TestInfo";
    public static final String COL_Type = "Type";
    public static final String COL_Subject = "Subject";
    public static final String COL_TYPE_SUBJECT_CURRENTITEM = "TYPE_SUBJECT_CURRENTITEM";
    //    public static final String COL_CurrentItem = "CurrentItem";
    public static final String COL_Item = "Item";
    public static final String COL_TrueOrFalse = "TrueOrFalse";


    public static final String CREATE_TAB = "create table " +
            TAB_NAME + " ( " +
            COL_Type + " text , " +
            COL_Subject + " text," +
            COL_TYPE_SUBJECT_CURRENTITEM + " text primary key, " +
            COL_Item + " text," +
            COL_TrueOrFalse + " text);";


}
