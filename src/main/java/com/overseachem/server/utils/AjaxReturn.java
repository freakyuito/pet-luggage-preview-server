package com.overseachem.server.utils;

public class AjaxReturn {

    private Boolean stat;
    private String info;

    public Boolean getStat() {
        return stat;
    }

    public void setStat(Boolean stat) {
        this.stat = stat;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public AjaxReturn(Boolean stat, String info) {
        this.stat = stat;
        this.info = info;
    }
}
