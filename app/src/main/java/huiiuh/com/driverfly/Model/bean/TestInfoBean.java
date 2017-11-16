package huiiuh.com.driverfly.Model.bean;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestInfoBean {

    //车辆类型 c1小车,a2货车,b1客车,d摩托
    private String type;
    //科目 1,2,3,4
    private String subject;
//    //当前viewpager位置
//    private String currentItem;
    //当前题目位置
    private String type_subject_currentItem;
    //选择的选项
    private String item;
    //选择答案的对错 0是对 1是错
    private String trueOrFalse;

    public TestInfoBean() {

    }

    public TestInfoBean(String type, String subject, String type_subject_currentItem, String item, String trueOrFalse) {
        this.type = type;
        this.subject = subject;
        this.type_subject_currentItem = type_subject_currentItem;
        this.item = item;
        this.trueOrFalse = trueOrFalse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType_subject_currentItem() {
        return type_subject_currentItem;
    }

    public void setType_subject_currentItem(String type_subject_currentItem) {
        this.type_subject_currentItem = type_subject_currentItem;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(String trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }
}
