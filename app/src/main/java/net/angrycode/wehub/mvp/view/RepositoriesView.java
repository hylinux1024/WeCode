package net.angrycode.wehub.mvp.view;

import net.angrycode.wehub.bean.Repos;
import net.angrycode.wehub.bean.TrendingRepo;

/**
 * Created by lancelot on 2016/11/13.
 */

public interface RepositoriesView extends BaseView {

    void onGetRepositories(Repos repos);

    void onAddFavorFinish(TrendingRepo repo);
}
