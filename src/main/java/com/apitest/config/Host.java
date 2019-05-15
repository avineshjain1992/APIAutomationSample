package com.apitest.config;

public enum Host {


    RAP_HOST("https://localhost");

    String uri;

    Host(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }
}