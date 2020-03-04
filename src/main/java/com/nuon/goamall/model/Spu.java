package com.nuon.goamall.model;

import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.util.List;

@Entity
public class Spu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    @ManyToMany(mappedBy = "spuList")
    private List<Them> themList;
}
