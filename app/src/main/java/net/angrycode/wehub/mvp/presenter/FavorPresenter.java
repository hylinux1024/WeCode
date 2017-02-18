package net.angrycode.wehub.mvp.presenter;

import net.angrycode.wehub.R;
import net.angrycode.wehub.WeCodeApp;
import net.angrycode.wehub.bean.DaoSession;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.bean.TrendingRepoDao;
import net.angrycode.wehub.mvp.view.FavorView;
import net.angrycode.wehub.utils.LogUtils;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by lancelot on 2016/12/18.
 */

public class FavorPresenter extends Presenter<FavorView> {
    RxDao<TrendingRepo, Long> mRxDao;
    private RxQuery<TrendingRepo> mRxQuery;

    public FavorPresenter(FavorView view) {
        super(view);
        DaoSession session = WeCodeApp.get().getDaoSession();
        mRxDao = session.getTrendingRepoDao().rx();
    }

    public void deleteFavor(TrendingRepo repo) {
        mRxDao.delete(repo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            view.onDelFavorFinish(repo);
                        },
                        throwable -> {
                            LogUtils.e(throwable);
                            view.onRequestError(ErrorCode.ERROR_DEL_DATA, throwable.getMessage());
                        },
                        () -> view.onRequestFinished());
    }

    public void getFavorList() {
        if (mRxQuery == null) {
            DaoSession session = WeCodeApp.get().getDaoSession();
            mRxQuery = session.getTrendingRepoDao().queryBuilder().orderAsc(TrendingRepoDao.Properties.Date).rx();
        }
        mRxQuery.list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoList -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (repoList != null) {
                                view.onGetFavorListFinish(repoList);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_GET_DATA, getActivity().getResources().getString(R.string.error_request_data));
                            }
                        },
                        throwable -> {
                            LogUtils.e(throwable);
                            view.onRequestError(ErrorCode.ERROR_GET_DATA, throwable.getMessage());
                        },
                        () -> view.onRequestFinished());
    }
}
