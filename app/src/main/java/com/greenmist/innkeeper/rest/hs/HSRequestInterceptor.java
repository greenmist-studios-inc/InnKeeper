package com.greenmist.innkeeper.rest.hs;

import com.greenmist.innkeeper.rest.Headers;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by geoff.powell on 2/7/17.
 */
public class HSRequestInterceptor implements Interceptor {

    private String apiKey;

    public HSRequestInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .addHeader("Accept", Headers.APPLICATION_JSON)
                .addHeader("Content-type", Headers.APPLICATION_JSON)
                .addHeader(HSHeaders.MASHAPE_KEY.getKey(), apiKey);

        return chain.proceed(requestBuilder.build());
    }
}
