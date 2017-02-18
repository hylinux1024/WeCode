package net.angrycode.wehub.api;

import net.angrycode.wehub.bean.Repos;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 受欢迎库的趋势列表
 * Created by lancelot on 2016/11/13.
 */

public class ReposApi extends BaseApi {
    private interface ReposService {
        @GET("/search/repositories")
        Observable<Repos> getRepositories(@Query("q") String query);
    }

    protected static final ReposService service = getRetrofit().create(ReposService.class);

    public static Observable<Repos> getRepositories(String query) {
        return service.getRepositories(query);
    }
}
