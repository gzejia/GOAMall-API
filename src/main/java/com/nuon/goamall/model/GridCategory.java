package com.nuon.goamall.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Where(clause = "delete_time is null")
public class GridCategory extends BaseEntity{

    @Id
    private Long id;
    private String title;
    private String img;
    private String name;
    private Long categoryId;
    private Long rootCategoryId;
}
