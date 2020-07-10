package com.nuon.goamall.model;

import com.nuon.goamall.util.ListAndJson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Sku extends BaseEntity {

    @Id
    private Long id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    //    @Convert(converter = MapAndJson.class)
    //    private Map<String, Object> specs;
    @Convert(converter = ListAndJson.class)
    private List<Object> specs;
    private String code;
    private Long stock;
    private Integer categoryId;
    private Integer rootCategoryId;
}
