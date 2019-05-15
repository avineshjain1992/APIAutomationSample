package com.apitest.Segment;

import com.apitest.APICall.APIHelper;
import com.apitest.config.Host;
import com.apitest.config.Path;
import com.apitest.utils.ExcelDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest {

    APIHelper helper;
    String body;
    String token;
    List<String> users;

    @BeforeClass
    public void setup(){
        helper = new APIHelper(Host.RAP_HOST.toString());
        getTokenTest();
        users = new ArrayList<>();
    }

    public void getTokenTest(){
        Response rsp = helper.getCall(Path.GETTOKEN.toString(),new HashMap(),new HashMap());
        token = rsp.jsonPath().getString("token");
    }

    @Test( dataProvider = "sampleTestData", dataProviderClass = ExcelDataProvider.class , priority = 1)
    public void createUser(String user, String email, String status) {
        Map<String, String> header1 = new HashMap();
        header1.put("Authorization","Bearer "+token);
        header1.put("Content-Type","application/json");
        body = helper.createBodyForUse(user,email);
        Response rsp = helper.postCall(Path.USER.toString(),body,header1,new HashMap());
        Assert.assertEquals(rsp.getStatusCode()+"",status);

    }

    @Test(  priority = 2)
    public void getAllUser() throws InterruptedException {
        Map<String, String> header = new HashMap();
        header.put("Authorization","Bearer "+token);
        Response rsp = helper.getCall(Path.USER.toString(),header,new HashMap());

        Assert.assertEquals(rsp.getStatusCode(),200);
    }

    @Test( dataProvider = "sampleTestData", dataProviderClass = ExcelDataProvider.class  , priority = 3)
    public void getUserDetail(String user, String email, String status){


            Map<String, String> params = new HashMap();
            Map<String, String> header = new HashMap();
            header.put("Authorization", "Bearer " + token);
            header.put("Content-Type", "application/json");
            Response rsp = helper.getCall(Path.USER.toString() + "/" + user, header, params);
            Assert.assertEquals(rsp.getStatusCode(), 200);

    }


}
