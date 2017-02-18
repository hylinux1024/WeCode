package net.angrycode.wehub.mvp.presenter;

import android.util.Log;

import net.angrycode.wehub.R;
import net.angrycode.wehub.WeCodeApp;
import net.angrycode.wehub.api.TrendingApi;
import net.angrycode.wehub.bean.DaoSession;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.mvp.view.TrendingView;
import net.angrycode.wehub.utils.LogUtils;

import org.greenrobot.greendao.rx.RxDao;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lancelot on 2016/12/3.
 */

public class TrendsPresenter extends Presenter<TrendingView> {
    RxDao<TrendingRepo, Long> mRxDao;

    public TrendsPresenter(TrendingView view) {
        super(view);
        DaoSession session = WeCodeApp.get().getDaoSession();
        mRxDao = session.getTrendingRepoDao().rx();
    }

    /**
     * @param lang
     */
    public void getTrending(String lang, boolean cache) {
        view.onRequestLoading();
        Observable<List<TrendingRepo>> observable = TrendingApi.getRepos(lang);
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(repositories -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (repositories != null && repositories.size() > 0) {
                                view.onGetTrendingsFinish(repositories);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, view.getActivity().getString(R.string.error_request_data));
                            }
                        }, error -> {
                            LogUtils.e(error);
                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, getRequestErrorMsg());
                        }, () -> view.onRequestFinished())
        );
    }

    public void addFavor(TrendingRepo repo) {
        if (repo == null) {
            return;
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
