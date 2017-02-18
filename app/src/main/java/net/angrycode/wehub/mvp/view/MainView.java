package net.angrycode.wehub.mvp.view;

import net.angrycode.wehub.bean.AppUpdate;
import net.angrycode.wehub.bean.Langs;

/**
 * Created by lancelot on 2016/12/17.
 */

public interface MainView extends BaseView {

    void onGetLangsFinished(Langs langs);

    void onCheckAppUpdateFinish(AppUpdate appUpdate);
}
