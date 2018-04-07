package RequestRepsonseObjects;


import java.util.List;

public class searchResponse extends ResponseObject{
    public int page;
    public List<result> results;
    public int total_pages;
    public int total_results;

    public int status_code;
    public String status_message;

}
