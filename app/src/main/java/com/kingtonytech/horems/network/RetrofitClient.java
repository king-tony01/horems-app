package com.kingtonytech.horems.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .cookieJar(new AppCookieJar())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://horems-api.onrender.com/api/") // use your IP or 10.0.2.2 for emulator
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}