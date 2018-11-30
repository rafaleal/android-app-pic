package com.android.utp.praticasintegrativascomplementares.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static final String BASE_URL = "http://services.koruthos.com.br/";
    private static NetworkManager sInstance = new NetworkManager();

    private final Retrofit mRetrofit;
    private final RestService mRestService;

    private NetworkManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mRestService = mRetrofit.create(RestService.class);
    }

    public static NetworkManager getsInstance() {
        if (sInstance == null) sInstance = new NetworkManager();
        return sInstance;
    }

    public static RestService service() {
        return getsInstance().mRestService;
    }

}
