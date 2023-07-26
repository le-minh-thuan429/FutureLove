package com.example.futurelove.service.api;

public interface QueryValueCallback {
    void onQueryValueReceived(String queryValue);
    void onApiCallFailed(Throwable t);
}

