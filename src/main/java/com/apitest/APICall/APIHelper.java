package com.apitest.APICall;

import com.apitest.utils.PropertyUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class APIHelper {

    static Response response;
    PropertyUtils prp;
    String body;

    public  APIHelper(String endpoint){
        RestAssured.baseURI = endpoint;
        RestAssured.useRelaxedHTTPSValidation();


    }

    public Response postCall(String path, String body, Map<String, String> headers, Map<String, String> params) {

        RequestSpecification rs = RestAssured.given();
        rs.log().all();
        rs.headers(headers);
        rs.queryParams(params);
        rs.body(body);
        response = rs.post(path);
        response.prettyPrint();
         return response;
    }


    public String createBodyForUse(String user, String email){
        prp = new PropertyUtils("./src/main/resources/testdata.properties");
        body = prp.getProperty("createUser");
        body = body.replace("#username#", user);
        body = body.replace("#email#", email);
        System.out.println(body);
      return body;
    }


    public Response getCall(String path, Map<String, String> headers,Map<String, String> params ){

        RequestSpecification rs = RestAssured.given();
        rs.log().all();

        rs.headers(headers);
        rs.queryParams(params);
        System.out.println();rs.request();
        response = rs.get(path);

        response.prettyPrint();
        return response;
    }

    public Response putCall(String path, String body, Map<String, String> headers, Map<String, String> params) {

        RequestSpecification rs = RestAssured.given();
        rs.log().all();
        rs.headers(headers);
        rs.queryParams(params);
        rs.body(body);
        response = rs.put(path);
        response.prettyPrint();
        return response;
    }

    public Response deleteCall(String path, Map<String, String> headers,Map<String, String> params ){

        RequestSpecification rs = RestAssured.given();
        rs.headers(headers);
        rs.params(params);
        response = rs.delete(path);
        response.prettyPrint();
        return response;
    }


}
