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
        //        TextView te = new TextView(mContext);
        //        te.setText("科目四");
        //        te.setGravity(Gravity.CENTER);
        //        Button button = new Button(mContext);
        //        button.setText("点击啊啊啊啊");
        //        button.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                SpUtil.getInstance().save(Contact.SUBJECT, "4");
        //                Intent intent = new Intent(mContext, IntroduceActivity.class);
        //                startActivity(intent);
        //            }
        //        });
        //
        //        return button;
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
