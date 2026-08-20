package com.example.myaplication.api;

import android.content.Context;

import com.example.myaplication.session.SesssoinManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor  implements Interceptor {
    private final SesssoinManager sessionManager;

    public AuthInterceptor(Context context) {
        sessionManager = new SesssoinManager(context);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request original = chain.request();

        String token = sessionManager.getToken();

        if (token == null || token.isEmpty()) {
            return chain.proceed(original);
        }

        Request request = original.newBuilder()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .build();

        return chain.proceed(request);
    }
}
