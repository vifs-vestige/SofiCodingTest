import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class test {
    private String ApiKey;
    private TestHelper Helper;

    @BeforeClass
    public void RunBefore(){
        Helper = new TestHelper();
        ApiKey = Helper.GetApiKey();
    }


    @Test
    public void NoResults(){
        searchRequest request = new searchRequest(ApiKey, "Better be no results");
        searchResponse response = Helper.GetResponse(request);
        assert response.results == null;
        assert response.total_results == 0;
    }

    @Test
    public void OneResult(){
        searchRequest request = new searchRequest(ApiKey, "Pixar");
        searchResponse response = Helper.GetResponse(request);
        assert response.results.size() == 1;
        assert response.total_results == 1;
        assert response.results.get(0).name == "Pixar";

    }



}
