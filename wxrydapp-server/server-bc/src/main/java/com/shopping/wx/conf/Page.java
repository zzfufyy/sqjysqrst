package com.shopping.wx.conf;

public class Page {
    private Integer page;
    private Integer rows;

    public Page(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public static boolean validate(Page page) {
        return page != null && page.getPage() != null && page.getRows() != null;
    }
}
