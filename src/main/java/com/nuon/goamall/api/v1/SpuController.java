package com.nuon.goamall.api.v1;

import com.nuon.goamall.bo.PageCounter;
import com.nuon.goamall.exception.NotFoundException;
import com.nuon.goamall.model.Spu;
import com.nuon.goamall.service.SpuService;
import com.nuon.goamall.util.CommonUtil;
import com.nuon.goamall.vo.PagingDozer;
import com.nuon.goamall.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {

    @Autowired
    SpuService spuService;

    @GetMapping("/id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id) {
        Spu spu = this.spuService.getSpu(id);

        if (null == spu) {
            throw new NotFoundException(30003);
        }
        return spu;
    }

    @GetMapping("/id/{id}/simplify")
    public SpuSimplifyVO getSimplifySpu(@PathVariable @Positive Long id) {
        Spu spu = this.spuService.getSpu(id);
        SpuSimplifyVO vo = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, vo);
        return vo;
    }

    @GetMapping("/latest")
    public PagingDozer<Spu, SpuSimplifyVO> getLatestSpuList(@RequestParam(defaultValue = "0") Integer start,
                                                            @RequestParam(defaultValue = "10") Integer count) {
        PageCounter pageCounter = CommonUtil.convert2PageParam(start, count);
        Page<Spu> spuList = this.spuService.getLatestPaginationSpu(pageCounter.getPage(), pageCounter.getCount());
        return new PagingDozer<>(spuList, SpuSimplifyVO.class);
    }

    @GetMapping("/by/category/{id}")
    public PagingDozer<Spu, SpuSimplifyVO> getByCategoryId(@PathVariable @Positive(message = "{id.positive}") Long id,
                                                           @RequestParam(name = "is_root", defaultValue = "false") Boolean isRoot,
                                                           @RequestParam(defaultValue = "0") Integer start,
                                                           @RequestParam(defaultValue = "10") Integer count) {
        PageCounter pageCounter = CommonUtil.convert2PageParam(start, count);
        Page<Spu> spuList = this.spuService.getByCategory(id, isRoot, pageCounter.getPage(), pageCounter.getCount());
        return new PagingDozer<>(spuList, SpuSimplifyVO.class);
    }
}
