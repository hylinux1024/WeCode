package net.angrycode.wehub.mvp.view;

import android.app.Activity;

public interface BaseView {
    Activity getActivity();

    /**
     * 请求中
     */
    void onRequestLoading();

    /**
     * 请求结束
     */
    void onRequestFinished();

    /**
     * 请求出错
     * @param code
     * @param message
     */
    void onRequestError(int code, String message);

}