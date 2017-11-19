package huiiuh.com.driverfly.Pager;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import huiiuh.com.driverfly.Base.BaseFragment;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.adapter.MyRecycleViewAdapter;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Subject_Four extends BaseFragment {
    private RecyclerView mRecyview_one;

    @Override
    public View initView() {

        View subject_one = View.inflate(getContext(), R.layout.subject_one, null);
        mRecyview_one = (RecyclerView) subject_one.findViewById(R.id.recyview_one);

        return subject_one;
    }

    @Override
    public void initData() {
        //        super.initData();
        super.initData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
        mRecyview_one.setLayoutManager(gridLayoutManager);
        mRecyview_one.setAdapter(new MyRecycleViewAdapter(mContext));
    }

    @Override
    protected void loadData() {

    }
}
