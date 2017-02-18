package net.angrycode.wehub.mvp.view;

import net.angrycode.wehub.bean.TrendingRepo;

import java.util.List;

/**
 * Created by lancelot on 2016/12/18.
 */

public interface FavorView extends BaseView {

    void onGetFavorListFinish(List<TrendingRepo> repoList);

    void onDelFavorFinish(TrendingRepo repo);
}
