package run.demo01.mvpshop.network;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient INSTANCE;
    private static final String BASE_URL = "http://edu-lance.github.io/";
    private Retrofit retrofit;
    private List<Cookie> cookies = new ArrayList<>();

    public static RetrofitClient getInstance(){
        if (INSTANCE == null)
        {
            synchronized (RetrofitClient.class){
                if (INSTANCE == null)
                {
                    INSTANCE = new RetrofitClient();
                }
            }
        }
        return INSTANCE;
    }

    public <T> T getService(Class<T> clazz){
        return getRetrofit().create(clazz);
    }

    private Retrofit getRetrofit() {
        if (retrofit == null)
        {
            synchronized (RetrofitClient.class)
            {
                if (retrofit == null)
                {
                    retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                            .callFactory(new OkHttpClient.Builder().cookieJar(new CookieJar() {
                                @Override
                                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                                    cookies.clear();
                                    cookies.addAll(cookies);
                                }

                                @Override
                                public List<Cookie> loadForRequest(HttpUrl url) {
                                    if (cookies == null)
                                        cookies = new ArrayList<>();
                                    return cookies;
                                }
                            }).build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
