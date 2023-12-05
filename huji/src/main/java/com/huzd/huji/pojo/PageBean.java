package com.huzd.huji.pojo;

import java.util.List;

//分页查询的JavaBean 使用泛型
public class PageBean<T> {
//    总记录数
    private int totalCount;
//    当前页面数据
    private List<T>pageData;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
