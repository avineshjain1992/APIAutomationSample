package com.apitest.utils;


import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    private Properties props = new Properties();

    public PropertyUtils(String path) {

        loadPropertyFile(path);
    }

    public void loadPropertyFile(String propertyFileName) {
        try {
            props.load(new FileInputStream(propertyFileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Assert.fail("Unable to load file!", e);
            // e.printStackTrace();
        }
    }

    public String getProperty(String propertyKey) {
        String propertyValue = props.getProperty(propertyKey.trim());

        if (propertyValue == null || propertyValue.trim().length() == 0) {
            // Log error message
        }

        return propertyValue;
    }

    public void setProperty(String propertyKey, String value) {
        props.setProperty(propertyKey, value);
    }
}
