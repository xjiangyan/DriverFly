package huiiuh.com.driverfly.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.Util.MyGridView;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyRecycleViewAdapter_SubjectTwo extends RecyclerView.Adapter {
    private final Context context;
    private int currentItem = 0;


    public MyRecycleViewAdapter_SubjectTwo(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View Driving_videos = View.inflate(context, R.layout.driving_videos, null);
            Log.d("MyRecycleViewAdapter_Su", "实例化了" + 0);

            return new Driving_Videos_ViewHolder(Driving_videos);
        } else if (viewType == 1) {
            Log.d("MyRecycleViewAdapter_Su", "实例化了" + 1);
            View Driving_skills = View.inflate(context, R.layout.driving_skills_subjecttwo, null);
            return new Driving_Skills_ViewHolder(Driving_skills);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //position的值与默认的getItemViewType(position)不同
        if (getItemViewType(position) == 0) {
            Driving_Videos_ViewHolder driving_videos_viewHolder = (Driving_Videos_ViewHolder) holder;
            driving_videos_viewHolder.setData();
            Log.d("MyRecycleViewAdapter_Su", "绑定了" + 0);
        } else if (getItemViewType(position) == 1) {
            Driving_Skills_ViewHolder driving_skills_viewholder = (Driving_Skills_ViewHolder) holder;
            driving_skills_viewholder.setData();
            Log.d("MyRecycleViewAdapter_Su", "绑定了" + 1);

        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * 实现多item布局，用不同的itemType去加载不同的布局。
     * 主要思路就是先定义好标识itemType的常量，然后重写getItemViewType()方法，
     * 根据不同的位置（position）返回不同的Type，接着在onCreateViewHolder()中
     * 根据参数viewType去判断该item项应该 inflate 哪个布局文件，并返回相应的ViewHolder实例
     * (这里ViewHolder是根据不同的item布局预先自定义好的不同的ViewHolder)
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //position的值与默认的getItemViewType(position)不同

        switch (position) {
            case 0:
                currentItem = 0;
                break;
            case 1:
                currentItem = 1;

                break;
        }
        return currentItem;
    }

    private class Driving_Videos_ViewHolder extends RecyclerView.ViewHolder {
        private MyGridView gv;

        public Driving_Videos_ViewHolder(View itemView) {
            super(itemView);
            gv = (MyGridView) itemView.findViewById(R.id.gv_Driving_Videos);

        }

        public void setData() {
            gv.setAdapter(new MyGridViewVideosAdapter_SubjectTwo(context));

        }
    }

    private class Driving_Skills_ViewHolder extends RecyclerView.ViewHolder {
        private MyGridView gv;

        public Driving_Skills_ViewHolder(View itemView) {
            super(itemView);
            gv = (MyGridView) itemView.findViewById(R.id.gv_Driving_skills);

        }

        public void setData() {
            gv.setAdapter(new MyGridViewAdapter_SubjectTwo(context));
        }
    }
}
