package com.nuon.goamall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class BannerItem {

    @Id
    private Long id;
    private String img;
    private String keyword;
    private short type;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private Long bannerId;
    private String name;
}
