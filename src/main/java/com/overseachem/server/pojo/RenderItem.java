package com.overseachem.server.pojo;

public class RenderItem {
    private String alias;
    private String title;
    private String intro;
    private String url;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RenderItem(String alias, String title, String intro, String url) {
        this.alias = alias;
        this.title = title;
        this.intro = intro;
        this.url = url;
    }
}
