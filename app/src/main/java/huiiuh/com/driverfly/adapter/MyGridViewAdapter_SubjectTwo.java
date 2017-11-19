package huiiuh.com.driverfly.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import huiiuh.com.driverfly.Activity.URlActivity;
import huiiuh.com.driverfly.Constants;
import huiiuh.com.driverfly.R;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyGridViewAdapter_SubjectTwo extends BaseAdapter {

    private final Context context;
    private int[] images = new int[]{R.drawable.km2_anquandai, R.drawable.km2_dianhuokaiguan,
            R.drawable.km2_liheqi, R.drawable.km2_jiasu, R.drawable.km2_fangxiangpan,
            R.drawable.km2_zhidong, R.drawable.km2_zhuche, R.drawable.km2_zuoyi,
            R.drawable.km2_houshijing, R.drawable.km2_jingyan};
    private String[] texts = new String[]{"安全带", "点火开关", "离合器",
            "加速踏板", "方向盘", "制动踏板", "驻车制动", "座椅调整", "后视镜", "经验技巧"};
    private String[] Urls = new String[]{"2_anquandai.html", "2_dianhuokaiguan.html", "2_liheqi.html",
            "2_jiasutaban.html", "2_fangxiangpan.html", "2_zhidongtaban.html", "2_zhuchezhidong.html",
            "2_zuoyitiaozheng.html", "2_houshijingtiaozheng.html", "myjingyanjiqiao.html"};

    public MyGridViewAdapter_SubjectTwo(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.driving_skills_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_driving_skills);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_driving_skills);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv.setImageResource(images[i]);
        viewHolder.tv.setText(texts[i]);
        Log.d("Driving_Skills_ViewHold", "实例化了" + i);
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, URlActivity.class);
                intent.putExtra(Constants.URL_PATH, Urls[i]);
                intent.putExtra(Constants.TITLENAME,texts[i]);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        private TextView tv;
        private ImageView iv;
    }


}
