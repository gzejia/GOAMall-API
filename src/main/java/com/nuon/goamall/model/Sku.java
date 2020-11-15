package com.nuon.goamall.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nuon.goamall.util.GenericAndJson;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online = 1")
public class Sku extends BaseEntity {

    @Id
    private Long id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    private String code;
    private Long stock;
    private Integer categoryId;
    private Integer rootCategoryId;

    // 序列化方案1
    //    @Convert(converter = MapAndJson.class)
    //    private Map<String, Object> specs;

    // 序列化方案2
    //    @Convert(converter = ListAndJson.class)
    //    private List<Object> specs;

    // 序列化方案3
    private String specs;

    public List<Spec> getSpecs() {
        if (null == this.specs) {
            return Collections.emptyList();
        }
        return GenericAndJson.json2Object(this.specs, new TypeReference<List<Spec>>() {
        });
    }

    public void setSpecs(List<Spec> list) {
        if (null == specs) {
            return;
        }
        this.specs = GenericAndJson.object2Json(list);
    }
}
