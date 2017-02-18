package net.angrycode.wehub.mvp.view;

import net.angrycode.wehub.bean.Langs;

/**
 * Created by lancelot on 2016/12/17.
 */

public interface LangView extends BaseView {
    void onGetLangsFinished(Langs langs);

    void onSetLangsFinished(Langs langs);
}
