package com.jy.wanandroid;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FoodDbBean {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String pic;
    private String des;
    @Generated(hash = 1560625216)
    public FoodDbBean(Long id, String title, String pic, String des) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.des = des;
    }
    @Generated(hash = 649472808)
    public FoodDbBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
}
