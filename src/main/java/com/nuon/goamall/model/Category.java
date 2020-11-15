package com.nuon.goamall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {

    @Id
    private Long id;
    private String name;
    private String description;
    private Boolean isRoot;
    private Long parentId;
    private String img;
    private Integer index;
    private Integer online;
    private Integer level;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="theme_spu", joinColumns = @JoinColumn(name="theme_id")
            , inverseJoinColumns = @JoinColumn(name="spu_id"))
    private List<Spu> spuList;
}
