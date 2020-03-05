package com.nuon.goamall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Banner {

    @Id
    private Long id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private String title;
    private String img;

    @OneToMany(fetch = FetchType.LAZY) // 默认开启懒加载
    @JoinColumn(name = "bannnerId")
    private List<BannerItem> items;
}
