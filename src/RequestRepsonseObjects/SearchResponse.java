package RequestRepsonseObjects;


import java.util.List;

public class SearchResponse extends ResponseObject{
    public int page;
    public List<Result> results;
    public int total_pages;
    public int total_results;

    public int status_code;
    public String status_message;

}
