package com.nuon.goamall.api.v1;

import com.nuon.goamall.exception.NoFoundException;
import com.nuon.goamall.model.Spu;
import com.nuon.goamall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {

    @Autowired
    SpuService spuService;

    @GetMapping("/id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id) {
        Spu spu = spuService.getSpu(id);

        if (null == spu) {
            throw new NoFoundException(30003);
        }
        return spu;
    }

    @GetMapping("/latest")
    public List<Spu> getLatestSpuList(){
        return spuService.getLatestPaginationSpu();
    }
}
