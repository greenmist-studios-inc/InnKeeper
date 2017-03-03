package com.greenmist.innkeeper.rest.hs;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.greenmist.innkeeper.android.BuildConfig;
import com.greenmist.innkeeper.android.R;
import com.greenmist.innkeeper.android.utils.Constants;
import com.greenmist.innkeeper.android.utils.DateUtils;
import com.greenmist.innkeeper.android.dao.model.HSCard;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by geoff.powell on 2/5/17.
 */
public class HSWebClient {

    private static HSWebClient instance;

    private HSWebAPI hsWebAPI;

    public static void init(Context context) {
        if (instance == null) {
            instance = new HSWebClient();
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new HSRequestInterceptor(context.getString(R.string.hs_api_key)))
                .connectTimeout(Constants.API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.API_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.API_READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder()
            .setDateFormat(DateUtils.DEFAULT_DATE_FORMAT)
            .create());

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl(context.getString(R.string.hs_api_url))
                .build();

        instance.hsWebAPI = retrofit.create(HSWebAPI.class);
    }

    public static HSWebClient getInstance() {
        return instance;
    }

    public Observable<List<HSCard>> getCard(String idOrName) {
        return hsWebAPI.getCard(idOrName, 0, "enUS");
    }

    public Observable<List<HSCard>> getCard(String idOrName, boolean collectible) {
        return hsWebAPI.getCard(idOrName, collectible ? 1 : 0, "enUS");
    }

    public Observable<List<HSCard>> getCard(String idOrName, boolean collectible, String locale) {
        return hsWebAPI.getCard(idOrName, collectible ? 1 : 0, locale);
    }
}
