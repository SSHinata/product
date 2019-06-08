package com.product.util.page;

public class BasePage {
    private int pageNum = 1;   // 第几页
    private int pageSize;    // 每页记录数
    private int pages;        // 总页数
    private int size;        // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
