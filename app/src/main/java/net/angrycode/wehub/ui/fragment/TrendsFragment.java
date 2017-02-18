package net.angrycode.wehub.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.angrycode.wehub.R;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.mvp.presenter.TrendsPresenter;
import net.angrycode.wehub.mvp.view.TrendingView;
import net.angrycode.wehub.ui.adapter.TrendsAdapter;
import net.angrycode.wehub.ui.component.CustomTabActivityHelper;
import net.angrycode.wehub.ui.component.DividerItemDecoration;
import net.angrycode.wehub.ui.component.WebViewFallback;
import net.angrycode.wehub.utils.ToastUtils;
import net.angrycode.wehub.utils.Utils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lancelot on 2016/12/3.
 */

public class TrendsFragment extends BaseFragment implements TrendingView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    TrendsPresenter mPresenter;
    TrendsAdapter mAdapter;

    String mLanguage = "all";

//    String mQuery;

    public static TrendsFragment newInstance(String language) {
        TrendsFragment fragment = new TrendsFragment();
        fragment.mLanguage = language;
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_repos;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mQuery = mLanguage+"&language:" + mLanguage;
        mPresenter = new TrendsPresenter(this);
        mPresenter.getTrending(mLanguage, true);
        mRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.getTrending(mLanguage, false);
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, R.color.divider));
        mAdapter = new TrendsAdapter();
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this::onItemClick);
        mAdapter.setOnItemLongClickListener(this::onItemLongClick);
    }

    private void onItemClick(View itemView, int position) {
        String url = mAdapter.getItem(position).getUrl();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(getContext(), R.color.primary));
        CustomTabsIntent customTabsIntent = builder.build();
        CustomTabActivityHelper.openCustomTab(
                getActivity(), customTabsIntent, Uri.parse(url), new WebViewFallback());
    }

    private void onItemLongClick(View itemView, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.more_opt, (dialog, which) -> {
            TrendingRepo repo = mAdapter.getItem(position);
            switch (which) {
                case 0:
                    mPresenter.addFavor(repo);
                    break;
                case 1:
                    Utils.copyText(getActivity(), repo.getUrl());
                    break;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onRequestLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onRequestFinished() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestError(int code, String message) {
        ToastUtils.show(getContext(), message);
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetTrendingsFinish(List<TrendingRepo> repos) {
        mAdapter.setData(repos);
    }

    @Override
    public void onAddFavorFinish(TrendingRepo repo) {
        ToastUtils.show(getActivity(), R.string.add_favor_finish);
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }
}
