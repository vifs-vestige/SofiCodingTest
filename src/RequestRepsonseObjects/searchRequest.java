package RequestRepsonseObjects;



public class searchRequest extends RequestObject{
    private String ApiKey;
    private String Query;
    private Integer Page;

    private String url = "https://api.themoviedb.org/3/search/company?";

    public searchRequest(String api_key, String query){
        this(api_key, query, null);
    }

    public searchRequest(String api_key, String query, Integer page){
        ApiKey = api_key;
        Query = query;
        Page = page;
    }

    public String GetURL(){
        String temp;
        if(Page == null) {
            temp = url +
                    "api_key=" + ApiKey +
                    "&query=" + Query;
        }
        else{
            temp = url +
                    "api_key=" + ApiKey +
                    "&query=" + Query +
                    "&page=" + Page;
        }


        temp.replaceAll("/s", "%20");


        return temp;

    }
}
