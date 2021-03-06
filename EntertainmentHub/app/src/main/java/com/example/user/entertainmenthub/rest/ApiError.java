package com.example.user.entertainmenthub.rest;

/**
 * Created by user on 10-11-2017.
 */

import retrofit.RetrofitError;
import retrofit.client.Response;


public class ApiError {

    private String message;

    private int statusCode;

    private String reason;

    private boolean isNetworkError = false;


    public ApiError(int statusCode, String reason, Exception exception) {
        this.statusCode = statusCode;
        this.reason = reason;
        this.message = exception.getMessage();
    }


    public ApiError(RetrofitError error) {
        // no network connection, timeout, other IOException
        if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
            isNetworkError = true;
        }
        message = error.getMessage();
        Response response = error.getResponse();
        if (response != null) {
            this.statusCode = response.getStatus();
            this.reason = response.getReason();
        }
    }


    public int getStatusCode() {
        return statusCode;
    }

    public String getReason() {
        return reason;
    }

    public boolean isNetworkError() {
        return isNetworkError;
    }
}
