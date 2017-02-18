package net.angrycode.wehub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import net.angrycode.wehub.R;
import net.angrycode.wehub.bean.Repos;
import net.angrycode.wehub.bean.Repository;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.mvp.presenter.ReposPresenter;
import net.angrycode.wehub.mvp.view.RepositoriesView;
import net.angrycode.wehub.ui.activity.SearchActivity;
import net.angrycode.wehub.ui.adapter.ReposAdapter;
import net.angrycode.wehub.ui.component.DividerItemDecoration;
import net.angrycode.wehub.utils.Keyboard;
import net.angrycode.wehub.utils.ToastUtils;
import net.angrycode.wehub.utils.Utils;
import net.angrycode.wehub.utils.WebUtils;

import butterknife.BindView;

/**
 * Created by lancelot on 2016/11/15.
 */

public class ReposFragment extends BaseFragment implements RepositoriesView {
    ReposPresenter mPresenter;
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    ReposAdapter mAdapter;

//    String mLanguage;

    String mQuery;

    public static ReposFragment newInstance(String query) {
        ReposFragment fragment = new ReposFragment();
        fragment.mQuery = query;
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
        mPresenter = new ReposPresenter(this);
        search(mQuery);
        mRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.getRepositories(mQuery);
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, R.color.divider));
        mAdapter = new ReposAdapter();
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((itemView, position) -> {
            String url = mAdapter.getItem(position).getHtml_url();
            WebUtils.openUrl(getActivity(), url);
        });
        mAdapter.setOnItemLongClickListener(this::onItemLongClick);
    }

    private void onItemLongClick(View itemView, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.more_opt, (dialog, which) -> {
            Repository repository = mAdapter.getItem(position);
            switch (which) {
                case 0:
                    mPresenter.addFavor(repository.convert2TrendingRepo());
                    break;
                case 1:
                    Utils.copyText(getActivity(), repository.getHtml_url());
                    break;
            }
            if (getActivity() instanceof SearchActivity) {
                ((SearchActivity) getActivity()).clearFocus();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void search(String query) {
        mPresenter.getRepositories(query);
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onGetRepositories(Repos repos) {
        Log.d("main", repos.toString());
        mAdapter.setData(repos.getItems());
        Keyboard.hide(getActivity().getCurrentFocus());
    }

    @Override
    public void onAddFavorFinish(TrendingRepo repo) {
        ToastUtils.show(getActivity(), R.string.add_favor_finish);
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
}
