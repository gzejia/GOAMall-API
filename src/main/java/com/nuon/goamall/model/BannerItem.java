package com.nuon.goamall.model;

import javax.persistence.*;

@Entity
public class BannerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String img;

    private String keyword;

    private Short type;

    @ManyToOne
    private Banner banner;
}
