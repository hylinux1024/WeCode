package net.angrycode.wehub.api;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseApi {
    public static String API_SERVER = "https://api.github.com";
    private static final OkHttpClient sOkHttpClient = new OkHttpClient();
    private static Retrofit sRetrofit;

    static {
        sOkHttpClient.newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        String[] headers = {
                                "application/vnd.github.html+json",
                                "application/vnd.github.raw+json"
                        };

//                        String token = TokenStore.getInstance(context).getToken();
                        String token = null;
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", "Token " + token)
                                .method(original.method(), original.body());

                        if (original.header("Accept") == null) {
                            requestBuilder.addHeader("Accept", TextUtils.join(",", headers));
                        }

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();
    }

    protected static Retrofit getRetrofit() {
        if (sRetrofit == null) {
//            Context context = TrendingApplication.get();
            //设定30秒超时
//            mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
            //设置拦截器，以用于自定义Cookies的设置
//            mOkHttpClient.networkInterceptors()
//                    .add(new CookiesInterceptor(context));
            //设置缓存目录
//            File cacheDirectory = new File(context.getCacheDir()
//                    .getAbsolutePath(), "HttpCache");
//            Cache cache = new Cache(cacheDirectory, 20 * 1024 * 1024);
//            mOkHttpClient.setCache(cache);
            //构建Retrofit
            sRetrofit = new Retrofit.Builder()
                    //配置服务器路径
                    .baseUrl(getBaseUrl() + "/")
                    //设置日期解析格式，这样可以直接解析Date类型
//                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    //配置转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(sOkHttpClient)
                    .build();
        }
        return sRetrofit;
    }

    protected static String getBaseUrl() {
        return API_SERVER;
    }
}