package net.angrycode.wehub.ui.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.angrycode.wehub.R;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.mvp.presenter.FavorPresenter;
import net.angrycode.wehub.mvp.view.FavorView;
import net.angrycode.wehub.ui.adapter.TrendsAdapter;
import net.angrycode.wehub.ui.component.CustomTabActivityHelper;
import net.angrycode.wehub.ui.component.DividerItemDecoration;
import net.angrycode.wehub.ui.component.WebViewFallback;
import net.angrycode.wehub.utils.ToastUtils;
import net.angrycode.wehub.utils.Utils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lancelot on 2016/12/9.
 */

public class FavoritesActivity extends BaseActivity implements FavorView {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    FavorPresenter mPresenter;
    TrendsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        mPresenter = new FavorPresenter(this);
        mPresenter.getFavorList();
    }

    private void init() {
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
        builder.setToolbarColor(ContextCompat.getColor(getActivity(), R.color.primary));
        CustomTabsIntent customTabsIntent = builder.build();
        CustomTabActivityHelper.openCustomTab(
                getActivity(), customTabsIntent, Uri.parse(url), new WebViewFallback());
    }

    private void onItemLongClick(View itemView, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.more_opt_favor, (dialog, which) -> {
            TrendingRepo repo = mAdapter.getItem(position);
            switch (which) {
                case 0:
                    mPresenter.deleteFavor(repo);
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
    protected int getLayoutResource() {
        return R.layout.activity_favorites;
    }

    @Override
    public void onGetFavorListFinish(List<TrendingRepo> repoList) {
        mAdapter.setData(repoList);
    }

    @Override
    public void onDelFavorFinish(TrendingRepo repo) {
        mAdapter.remove(repo);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRequestLoading() {
        showLoading();
    }

    @Override
    public void onRequestFinished() {
        dismissLoading();
    }

    @Override
    public void onRequestError(int code, String message) {
        ToastUtils.show(this, message);
    }
}
