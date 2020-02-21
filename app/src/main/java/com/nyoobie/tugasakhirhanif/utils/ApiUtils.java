package com.nyoobie.tugasakhirhanif.utils;

import com.nyoobie.tugasakhirhanif.network.ApiService;
import com.nyoobie.tugasakhirhanif.services.RetrofitClient;

public class ApiUtils {
    private ApiUtils() {
    }

    public static final String API_URL = "";

    public static ApiService getApiService() {
        return RetrofitClient.getClient(API_URL).create(ApiService.class);
    }
}
