package com.nuon.goamall.vo;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PagingDozer<T, K> extends Paging {

    @SuppressWarnings("unchecked")
    public PagingDozer(Page<T> pageT, Class<K> kClass) {
        this.initPageParam(pageT);

        List<T> tList = pageT.getContent();
        List<K> voList = new ArrayList<>();

        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        tList.forEach(t -> {
            K vo = mapper.map(t, kClass);
            voList.add(vo);
        });
        this.setItems(voList);
    }
}
