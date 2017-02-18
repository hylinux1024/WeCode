package net.angrycode.wehub.mvp.presenter;

import net.angrycode.wehub.BuildConfig;
import net.angrycode.wehub.R;
import net.angrycode.wehub.api.LangApi;
import net.angrycode.wehub.api.TrendingApi;
import net.angrycode.wehub.bean.AppUpdate;
import net.angrycode.wehub.bean.Langs;
import net.angrycode.wehub.mvp.view.MainView;
import net.angrycode.wehub.utils.LogUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lancelot on 2016/12/17.
 */

public class MainPresenter extends Presenter<MainView> {
    public MainPresenter(MainView view) {
        super(view);
    }

    public void getLangs() {
        Observable<Langs> observable = LangApi.getLangs(getActivity());
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(langs -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (langs != null) {
                                view.onGetLangsFinished(langs);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, view.getActivity().getString(R.string.error_request_data));

                            }
                        }, error -> {
                            LogUtils.e(error);
                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, error.getMessage());
                        }, () -> view.onRequestFinished())
        );

    }

    public void checkAppUpdate() {
        Observable<AppUpdate> observable = TrendingApi.checkAppUpdate(BuildConfig.VERSION_NAME);
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(appUpdate -> {
                                    if (!isViewAttached()) {
                                        return;
                                    }
                                    if (appUpdate.getCode() > 0) {
                                        view.onCheckAppUpdateFinish(appUpdate);
                                    }
                                }, throwable -> {
                                    LogUtils.e(throwable);
                                    view.onRequestError(ErrorCode.ERROR_SAVE_DATA, view.getActivity().getString(R.string.error_request_data));
                                },
                                () -> view.onRequestFinished())
        );
    }
}
