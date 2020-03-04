package com.nuon.goamall.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String img;

    private String title;

    @OneToMany(mappedBy = "banner", fetch = FetchType.LAZY) // 默认开启懒加载
    private List<BannerItem> items;
}
