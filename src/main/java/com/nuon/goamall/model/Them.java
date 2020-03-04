package com.nuon.goamall.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Them {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subTitle;

    @ManyToMany
    @JoinTable(name = "them_spu", joinColumns = @JoinColumn(name = "them_id"),
            inverseJoinColumns = @JoinColumn(name = "spu_id"))
    private List<Spu> spuList;
}
