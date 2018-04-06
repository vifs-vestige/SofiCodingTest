import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class test {

    @Test
    public void test(){

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                new Jackson2ObjectMapperFactory() {
                    public ObjectMapper create(Class aClass, String s) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        return objectMapper;
                    }
                }
        ));



        searchResponse temp = get("https://api.themoviedb.org/3/search/company?api_key=3b41ccc422d320281eb66cac655d7703&query=disney&page=1\n").as(searchResponse.class);
    }
}
