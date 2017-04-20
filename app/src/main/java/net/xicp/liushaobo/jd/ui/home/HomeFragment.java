package net.xicp.liushaobo.jd.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.rd.Orientation;
import com.rd.PageIndicatorView;
import com.rd.animation.AnimationType;

import net.xicp.liushaobo.jd.R;
import net.xicp.liushaobo.jd.ui.home.model.FloorList;
import net.xicp.liushaobo.jd.ui.home.model.HomePage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class HomeFragment extends Fragment implements HomeView {

    private View view;

    private HomePresenter presenter;
    private HomeAdapter adapter = new HomeAdapter();
    GenericDraweeHierarchy hierarchy;

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

        ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
        pager.setAdapter(adapter);

        PageIndicatorView indicatorView = (PageIndicatorView) view.findViewById(R.id.pageindicatorview);
        indicatorView.setViewPager(pager);
        indicatorView.setAnimationType(AnimationType.WORM);
        indicatorView.setOrientation(Orientation.HORIZONTAL);
        indicatorView.setRadius(5);
        presenter = new HomePresenterImpl(this);
        presenter.fetch(getContext());
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(getResources());
        hierarchy = builder
                .setFadeDuration(300)
                .build();
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

    private View createPageView(String imgUrl) {
        View view = new View(getContext());
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        simpleDraweeView.setImageURI(Uri.parse(imgUrl),hierarchy);
        view.setBackgroundColor(getResources().getColor(R.color.red));
        return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void onPullDownToRefresh() {

    }

    @Override
    public void onPullUpToRefresh() {

    }

    @Override
    public void getHomeData(HomePage homePage) {
        List<View> pageList = new ArrayList<>();
        /*for(int i=0;i<homePage.floorList.bannerContentFloor.content.size();i++){
            String img = "https://m.360buyimg.com/mobilecms/s720x351_jfs/t4936/90/1261251670/94339/a0a0d32b/58eee702N5669681d.jpg!q70.jpg.webp";
            homePage.floorList.bannerContentFloor.content.get(i);
            pageList.add(createPageView(img));

        }*/
        adapter.setData(createPageList());

    }
}
