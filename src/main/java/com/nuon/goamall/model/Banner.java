package com.nuon.goamall.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online = 1")
public class Banner extends BaseEntity{

    @Id
    private Long id;
    private String name;
    private String description;
    private String title;
    private String img;

    @OneToMany(fetch = FetchType.LAZY) // 默认开启懒加载
    @JoinColumn(name = "bannerId")
    private List<BannerItem> items;
}
