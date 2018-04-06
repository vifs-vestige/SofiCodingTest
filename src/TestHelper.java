import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.get;

public class TestHelper {
    private String ApiKey;

    public TestHelper(){



        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            ApiKey = prop.getProperty("ApiKey");
        }
        catch (Exception e){

        }

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                new Jackson2ObjectMapperFactory() {
                    public ObjectMapper create(Class aClass, String s) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        return objectMapper;
                    }
                }
        ));
    }

    public String GetApiKey(){
        return ApiKey;
    }


    //would be better to make searchResponse inherit from an interface
    //then have another class that holds a response object and that interface
    //then you can cast the interface back to the class sense you know what it will be in the test
    //with that you would have access to the json response and the meta data
    public searchResponse GetResponse(searchRequest request){
        return get(request.GetURL()).as(searchResponse.class);
    }


    //was going to add other assert helpers for comparing ints
    //to much effort for coding challenge
    //could of done something with enum and switch for dealing with different arithmetic operators
    public void assertHelp(String expected, String outcome){
        try{
            assert expected.equals(outcome);
        }
        catch (AssertionError e){
            throw new AssertionError("\n\toutcome: " + outcome + "\n\texcepted: " + expected);
        }
    }
}
