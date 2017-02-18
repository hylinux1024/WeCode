package net.angrycode.wehub.api;

import android.content.Context;

import net.angrycode.wehub.bean.AppUpdate;
import net.angrycode.wehub.bean.GsonBuilder;
import net.angrycode.wehub.bean.TrendingRepo;
import net.angrycode.wehub.utils.JsonCacheUtils;

import java.util.List;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lancelot on 2016/12/1.
 */

public class TrendingApi extends BaseTrendApi {
    public TrendingApi() {
    }

    private interface TrendingService {
        @GET("trending/{lang}")
        Observable<List<TrendingRepo>> getTrendingRepos(@Path("lang") String lang);

        @GET("check_update/{version}")
        Observable<AppUpdate> checkAppUpdate(@Path("version") String version);
    }

    protected static final TrendingService service = getRetrofit().create(TrendingService.class);

    public static Observable<List<TrendingRepo>> getRepos(String lang) {
        Observable<List<TrendingRepo>> observable = service.getTrendingRepos(lang);
        return observable;
    }

    /**
     * 获取缓存数据
     *
     * @param lang
     * @returnxxxx
     */
    public static Observable<List<TrendingRepo>> getReposFromeCache(Context context, String lang) {
        Observable<List<TrendingRepo>> observable = Observable.create(subscriber -> {
            String json = JsonCacheUtils.readFile(context, lang);
            List<TrendingRepo> list = GsonBuilder.buildArray(json, TrendingRepo.class);
            subscriber.onNext(list);
            subscriber.onCompleted();
        });
        return observable;
    }

    /**
     * 检查更新
     * @param version
     * @return
     */
    public static Observable<AppUpdate> checkAppUpdate(String version) {
        Observable<AppUpdate> observable = service.checkAppUpdate(version);
        return observable;
    }
}
