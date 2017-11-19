package huiiuh.com.driverfly.adapter;

import android.content.Context;
import android.content.Intent;
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
class MyGridViewVideosAdapter_SubjectThree extends BaseAdapter {
    private final Context context;
    private int[] images = new int[]{R.drawable.p6, R.drawable.p7, R.drawable.p10, R.drawable.p12};
    private String[] titles = new String[]{"起步＆夜间", "遇车场景", "路周场景", "其他场景"};
    private String[] contents = new String[]{"考察上车起步及夜间驾驶能力", "考察跟车，超车，会车的技能",
            "通过人行道，公交站，学校和路口", "包括变更车道，掉头，靠边停车等"};
    private String[] urls = new String[]{"daocheruku.html", "cefangtingche.html", "zhijiaozhuanwan.html", "potingqibu.html"};

    public MyGridViewVideosAdapter_SubjectThree(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
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
            convertView = View.inflate(context, R.layout.driving_videos_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_driving_videos);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_driving_videos_title);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_driving_videos_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv.setImageResource(images[i]);
        viewHolder.tv_title.setText(titles[i]);
        viewHolder.tv_content.setText(contents[i]);
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, URlActivity.class);
                intent.putExtra(Constants.TITLENAME, titles[i]);
                intent.putExtra(Constants.URL_PATH, urls[i]);
                if (i == 2) {

                    intent.putExtra(Constants.URL_PATH2, "quxianxingshi.html");
                } else if (i == 3) {
                    intent.putExtra(Constants.URL_PATH2, "qibu.html");

                }
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        private ImageView iv;
        private TextView tv_title;
        private TextView tv_content;
    }
}
