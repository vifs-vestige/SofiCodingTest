package RequestRepsonseObjects;



public class SearchRequest extends RequestObject{
    private String ApiKey;
    private String Query;
    private Integer Page;

    private String Url = "https://api.themoviedb.org/3/search/company?";

    public SearchRequest(String api_key, String query){
        this(api_key, query, null);
    }

    public SearchRequest(String api_key, String query, Integer page){
        ApiKey = api_key;
        Query = query;
        Page = page;
    }

    public String GetURL(){
        String temp;
        if(Page == null) {
            temp = Url +
                    "api_key=" + ApiKey +
                    "&query=" + Query;
        }
        else{
            temp = Url +
                    "api_key=" + ApiKey +
                    "&query=" + Query +
                    "&page=" + Page;
        }


        temp.replaceAll("/s", "%20");


        return temp;

    }
}
