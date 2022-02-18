package stepDefinitions;

import java.util.Map;

public class CarTypes {


    private Integer page;
    private Integer pageSize;
    private Integer totalPageCount;
    private Map<String, String> wkda;

    public CarTypes(Integer page, Integer pageSize, Integer totalPageCount, Map<String, String> wkda) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPageCount = totalPageCount;
        this.wkda = wkda;
    }

    public CarTypes(Map<String, String> wkda) {
        this.wkda = wkda;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Map<String, String> getWkda() {
        return wkda;
    }

    public void setWkda(Map<String, String> wkda) {
        this.wkda = wkda;
    }


}
