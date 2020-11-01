package com.example.paging.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class ResponseState {

    @NonNull
    public final Status status;

    public String errorDescription = "Something went wrong";


    public ResponseState(@NonNull Status status, @Nullable Throwable error) {
        this.status = status;
        if (error != null) {
            parseThrowable(error);
        }
    }

    public static ResponseState loading() {
        return new ResponseState(Status.LOADING, null);
    }

    public static ResponseState success() {
        return new ResponseState(Status.SUCCESS, null);
    }

    public static ResponseState error(Throwable throwable) {
        return new ResponseState(Status.ERROR, throwable);
    }

    private void parseThrowable(Throwable throwable) {
        if (throwable instanceof SocketTimeoutException) {
            this.errorDescription = "Oooops! We couldn’t capture your request in time. Please try again.";
        } else if (throwable instanceof MalformedURLException) {
            this.errorDescription = "Oops! We hit an error. Try again later.";
        } else if (throwable instanceof IOException) {
            this.errorDescription = "Oh! You are not connected to a wifi or cellular data network. Please connect and try again";
        } else if (throwable instanceof HttpException) {

            if (((HttpException) throwable).code() == 500) {
                this.errorDescription = "Internal Server Error";
            } else {
                try {
                    this.errorDescription = ((HttpException) throwable).response().errorBody().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            this.errorDescription=throwable.getLocalizedMessage();
        }
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public enum Status {LOADING ,SUCCESS, ERROR}
}
