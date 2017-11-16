package huiiuh.com.driverfly.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import huiiuh.com.driverfly.Activity.IntroduceActivity;
import huiiuh.com.driverfly.Activity.TestActivity;
import huiiuh.com.driverfly.Contact;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.SpUtil;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MasonryHolder> {
    private Context mContext;
    private int[] images;
    private String[] texts;
    private int type = 0;

    public MyRecycleViewAdapter(Context context) {
        this.mContext = context;
        images = new int[]{R.drawable.shunxulianxi, R.drawable.suijilainxi, R.drawable.monikaoshi};
        texts = new String[]{"顺序练习", "随机练习", "模拟考试"};
    }

    @Override
    public MyRecycleViewAdapter.MasonryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View testtype = View.inflate(mContext, R.layout.shunxulianxi, null);
        return new MasonryHolder(testtype);
    }

    @Override
    public void onBindViewHolder(MyRecycleViewAdapter.MasonryHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public class MasonryHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private RelativeLayout mRelativeLayout;

        public MasonryHolder(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView.findViewById(R.id.te_testtype_name);
            this.mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rela_background);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getLayoutPosition() == 2) {

                        Intent intent = new Intent(mContext, IntroduceActivity.class);
                        mContext.startActivity(intent);
                        SpUtil.getInstance().save(Contact.TESTTYPE, "2");
//                        SpUtil.getInstance().save(Contact.SUBJECT, "1");

                    } else {

                        Toast.makeText(mContext, "点击了" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, TestActivity.class);
                        SpUtil.getInstance().save(Contact.TESTTYPE, getLayoutPosition() + "");
//                        SpUtil.getInstance().save(Contact.SUBJECT, "1");
                        mContext.startActivity(intent);
                    }
                }
            });
        }

        public void setData(int position) {
            mRelativeLayout.setBackgroundResource(images[position]);
            tv.setText(texts[position]);


        }
    }

}
