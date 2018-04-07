import RequestRepsonseObjects.RequestObject;
import RequestRepsonseObjects.ResponseObject;
import RequestRepsonseObjects.searchRequest;
import RequestRepsonseObjects.searchResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.response.Response;

import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.get;

public class TestHelper {
    private String ApiKey;

    private static TestHelper Instance = new TestHelper();

    public static TestHelper GetHelper(){
        if(Instance == null)
            Instance = new TestHelper();
        return Instance;
    }

    private TestHelper(){
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            else
                throw new Error("Could not find config.properties file");
            ApiKey = prop.getProperty("ApiKey");
        }
        catch (Exception e){
            throw new Error("Could not find config.properties file");
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

    public ResponseObject GetResponse(RequestObject request, Class classType){
        if(!ResponseObject.class.isAssignableFrom(classType)){
            throw new Error("The class passed to Helper.GetResponse does not extend from RestAssured.Response");
        }
        Response response = get(request.GetURL());
        Object temp = response.as(classType);
        ((ResponseObject)temp).response = response;
        return (ResponseObject)temp;

    }


}

