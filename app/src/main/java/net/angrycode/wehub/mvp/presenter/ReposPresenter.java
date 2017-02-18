package net.angrycode.wehub.mvp.presenter;

import android.util.Log;

import net.angrycode.wehub.R;
import net.angrycode.wehub.WeCodeApp;
import net.angrycode.wehub.api.ReposApi;
import net.angrycode.wehub.bean.DaoSession;
import net.angrycode.wehub.bean.Repos;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.mvp.view.RepositoriesView;

import org.greenrobot.greendao.rx.RxDao;

import java.util.Date;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lancelot on 2016/11/13.
 */

public class ReposPresenter extends Presenter<RepositoriesView> {
    RxDao<TrendingRepo, Long> mRxDao;

    public ReposPresenter(RepositoriesView view) {
        super(view);
    }

    public void getRepositories(String query) {
        view.onRequestLoading();
        Observable<Repos> observable = ReposApi.getRepositories(query);
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(repositories -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (repositories != null && !repositories.isIncomplete_results()) {
                                view.onGetRepositories(repositories);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, getRequestErrorMsg());
                            }
                        }, error -> {
                            Log.d("repositories", error.getMessage());
                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, getRequestErrorMsg());
                        }, () -> view.onRequestFinished())
        );
    }

    public void addFavor(TrendingRepo repo) {
        if (repo == null) {
            return;
        }
        if (mRxDao == null) {
            DaoSession session = WeCodeApp.get().getDaoSession();
            mRxDao = session.getTrendingRepoDao().rx();
        }
        repo.setDate(new Date());
        mRxDao.insertOrReplace(repo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trendingRepo -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (trendingRepo.getId() > 0) {
                                view.onAddFavorFinish(trendingRepo);
                            }
                        }, throwable -> {
                            view.onRequestError(ErrorCode.ERROR_SAVE_DATA, throwable.getMessage());
                        },
                        () -> view.onRequestFinished());
    }

}
