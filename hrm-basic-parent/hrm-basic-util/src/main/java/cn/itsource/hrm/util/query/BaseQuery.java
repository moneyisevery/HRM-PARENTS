package cn.itsource.hrm.util.query;

/**
 * 基础查询对象
 */
public class BaseQuery {
    //关键字
    private String keyword;
    //有公共属性-分页
    private Integer pageNum = 1; //当前页
    private Integer pageSize = 10; //每页显示多少条

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getStart(){
        return (pageSize-1)*pageSize;
    }
}
