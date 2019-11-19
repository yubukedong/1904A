package com.example.daytwo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RecentBean {
    @Id(autoincrement = true)
    private Long id;
    private String thumbnail;
    private String title;
    @Generated(hash = 1756309479)
    public RecentBean(Long id, String thumbnail, String title) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.title = title;
    }
    @Generated(hash = 1697461393)
    public RecentBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
