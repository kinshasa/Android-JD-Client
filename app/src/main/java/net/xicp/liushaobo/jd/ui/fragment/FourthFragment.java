package net.xicp.liushaobo.jd.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.xicp.liushaobo.jd.R;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class FourthFragment extends Fragment {

    public static FourthFragment instance() {
        FourthFragment view = new FourthFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        return view;
    }
}