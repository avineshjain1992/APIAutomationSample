package com.apitest.config;

public enum Path {

    GETTOKEN("/get_token"),
    USER("/user");


    String uri;

    Path(String uri) {
            this.uri = uri;
        }

        @Override
        public String toString() {
            return uri;
        }
    }



