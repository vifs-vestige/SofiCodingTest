import RequestRepsonseObjects.searchRequest;
import RequestRepsonseObjects.searchResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class test {
    private String ApiKey;
    private TestHelper Helper;

    private searchResponse GetResponse(searchRequest request){
        return (searchResponse)Helper.GetResponse(request, searchResponse.class);
    }

    @BeforeClass
    public void RunBefore(){
        Helper = TestHelper.GetHelper();
        ApiKey = Helper.GetApiKey();
    }



    @Test
    public void NoResults(){
        searchRequest request = new searchRequest(ApiKey, "Better be no results");
        searchResponse response = GetResponse(request);
        Assert.assertEquals(response.results.size(), 0);
        Assert.assertEquals(response.total_results, 0);
        Assert.assertEquals(response.response.getStatusCode(), 200);
    }

    @Test
    public void OneResult(){
        searchRequest request = new searchRequest(ApiKey, "Pixar");
        searchResponse response = GetResponse(request);
        Assert.assertEquals(response.results.size(), 1);
        Assert.assertEquals(response.total_results, 1);
        Assert.assertEquals(response.results.get(0).name, "Pixar");
        Assert.assertEquals(response.response.getStatusCode(), 200);
    }

    @Test
    public void MoreThenOnePage(){
        searchRequest request = new searchRequest(ApiKey, "Disney");
        searchResponse response = GetResponse(request);
        Assert.assertNotEquals(response.total_pages, 1);
        Assert.assertEquals(response.response.getStatusCode(), 200);

    }

    @Test
    public void BadApiKey(){
        searchRequest request = new searchRequest("a", "Disney");
        searchResponse response = GetResponse(request);
        Assert.assertEquals(response.status_code, 7);
        Assert.assertEquals(response.status_message, "Invalid API key: You must be granted a valid key.");
        Assert.assertEquals(response.response.getStatusCode(), 401);
    }

    //TODO:
    //test where you pass the page as 0, the pages start at 0
    //this should return some status code and a message
    //make sure it fails correctly when passed bad page

    //TODO:
    //test where you pass page as 2 for Disney
    //knowing this has 2 pages, make sure it returns page two
    //make sure it properly returns not just first page

    //TODO:
    //api key is required
    //we wrote a test where we passed a bad api key
    //make sure it fails correctly when api key is left out of the URL

    //TODO:
    //query is also required
    //leave query out of the url params
    //make sure it fails correctly with no query

    //TODO:
    //pass something that returns a lot, like Disney
    //check every object in the list and make sure disney is contained in the search
    //this makes sure that the search return will properly return items where the query is the superset

    //TODO:
    //add another param at the end of the URL
    //doesn't really matter what param, just something not defined in the docs, like hank=bob
    //it's still valid in terms of syntax so it should still return the correct response
    //this makes sure if a param is sent it, it doesn't break trying to map it

    //TODO:
    //have query be blank so like blahblah&query=
    //the docs say this is required and should be at least length of 1
    //this is making sure it is required

    //TODO:
    //page can go from 1 to 1000
    //pass 10001
    //this should make sure it returns a correct error message and fail safely




}
