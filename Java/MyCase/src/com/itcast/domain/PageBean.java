package com.itcast.domain;

import java.util.List;

/**
 * @Author: xue
 * @Date: 2019/4/15 17:23
 * @Version: 1.0
 * @File: PageBean
 * @Description: 封装每一页显示的内容和显示范围的JavaBean类
 */
public class PageBean<T> {
    private int totalCount; //总记录数 从数据库获取
    private int totalPage; //总页码 从数据库获取
    private List<T> list; //每页的数据对象
    private int currentPage; //当前页码 从浏览器获取
    private int rows; //每页显示数 从浏览器获取

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
