package net.angrycode.wehub.mvp.presenter;

import android.app.Activity;
import android.support.annotation.StringRes;

import net.angrycode.wehub.R;
import net.angrycode.wehub.mvp.view.BaseView;

import rx.subscriptions.CompositeSubscription;

public abstract class Presenter<T extends BaseView> {
    T view;

    interface ErrorCode {
        int ERROR_REQUEST_DATA = 400;
        int ERROR_OBSERVER_DATA = 404;
        int ERROR_SAVE_DATA = 405;
        int ERROR_GET_DATA = 406;
        int ERROR_DEL_DATA = 407;
    }

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    public Presenter(T view) {
        this.view = view;
    }

    public Activity getActivity() {
        return view.getActivity();
    }

    public void resume() {
    }

    public void pause() {

    }

    public String getString(@StringRes int res) {
        return view.getActivity().getString(res);
    }

    public String getRequestErrorMsg() {
        return view.getActivity().getString(R.string.error_request_data);
    }

    public boolean isViewAttached() {
        if (compositeSubscription.isUnsubscribed()) {
            return false;
        }
        if (view == null || view.getActivity() == null || view.getActivity().isFinishing()) {
            return false;
        }
        return true;
    }

    public void destroy() {
        compositeSubscription.unsubscribe();
        view = null;
    }
}