package huiiuh.com.driverfly.Pager;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import huiiuh.com.driverfly.Base.BaseFragment;
import huiiuh.com.driverfly.R;
import huiiuh.com.driverfly.adapter.MyRecycleViewAdapter_SubjectTwo;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Subject_Two extends BaseFragment {

    @Bind(R.id.rv_subjecttwo)
    RecyclerView mRvSubjecttwo;
    private View mSubject_two;


    @Override
    public View initView() {

        mSubject_two = View.inflate(mContext, R.layout.subject_two, null);

        return mSubject_two;
    }

    @Override
    public void initData() {
        super.initData();
         initReccleView();
    }

    private void initReccleView() {
        mRvSubjecttwo.setLayoutManager(new GridLayoutManager(mContext, 1));
        MyRecycleViewAdapter_SubjectTwo myRecycleViewAdapter_subjectTwo = new MyRecycleViewAdapter_SubjectTwo(mContext);
        mRvSubjecttwo.setAdapter(myRecycleViewAdapter_subjectTwo);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
                ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
