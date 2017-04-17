package net.xicp.liushaobo.jd.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rd.Orientation;
import com.rd.PageIndicatorView;
import com.rd.animation.AnimationType;

import net.xicp.liushaobo.jd.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class HomeFragment extends Fragment {

    private View view;

    public static HomeFragment instance() {
        HomeFragment view = new HomeFragment();
		return view;
	}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        initViews();
        return view;
    }

    @SuppressWarnings("ConstantConditions")
    private void initViews() {
        HomeAdapter adapter = new HomeAdapter();
        adapter.setData(createPageList());

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
        pager.setAdapter(adapter);

        PageIndicatorView indicatorView = (PageIndicatorView) view.findViewById(R.id.pageindicatorview);
        indicatorView.setViewPager(pager);
        indicatorView.setAnimationType(AnimationType.WORM);
        indicatorView.setOrientation(Orientation.VERTICAL);
    }

    @NonNull
    private List<View> createPageList() {
        List<View> pageList = new ArrayList<>();
        pageList.add(createPageView(R.color.red));
        pageList.add(createPageView(android.R.color.holo_blue_bright));
        pageList.add(createPageView(android.R.color.darker_gray));
        pageList.add(createPageView(android.R.color.holo_green_dark));

        return pageList;
    }

    @NonNull
    private View createPageView(int color) {
        View view = new View(getContext());
        view.setBackgroundColor(getResources().getColor(color));

        return view;
    }
}
