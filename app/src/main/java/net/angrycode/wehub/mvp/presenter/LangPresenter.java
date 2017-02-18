package net.angrycode.wehub.mvp.presenter;

import net.angrycode.wehub.R;
import net.angrycode.wehub.api.LangApi;
import net.angrycode.wehub.bean.Langs;
import net.angrycode.wehub.event.LangEditedEvent;
import net.angrycode.wehub.mvp.view.LangView;
import net.angrycode.wehub.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lancelot on 2016/12/17.
 */

public class LangPresenter extends Presenter<LangView> {
    public LangPresenter(LangView view) {
        super(view);
    }

    public void getLangs() {
        view.onRequestLoading();
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

    public void setLangs(final Langs langs) {
//        view.onRequestLoading();
        Observable<Langs> observable = LangApi.setLangs(getActivity(), langs);
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(result -> {
                            EventBus.getDefault().post(new LangEditedEvent(langs));
                            if (!isViewAttached()) {
                                return;
                            }
                            if (result != null) {
                                view.onGetLangsFinished(result);
                            } else {
//                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, view.getActivity().getString(R.string.error_request_data));
                                LogUtils.log("Set langs error");
                            }
                        }, error -> {
                            LogUtils.e(error);
//                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, error.getMessage());
                        }, () -> view.onRequestFinished())
        );

    }
}
