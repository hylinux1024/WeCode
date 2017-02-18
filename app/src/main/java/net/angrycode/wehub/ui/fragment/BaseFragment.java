package net.angrycode.wehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by lancelot on 2016/11/15.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resLayoutId = getLayoutResource();
        if (resLayoutId != 0) {
            View view = inflater.inflate(resLayoutId, container, false);
            ButterKnife.bind(this, view);
            return view;
        }
        return null;
    }

    public abstract int getLayoutResource();
}
