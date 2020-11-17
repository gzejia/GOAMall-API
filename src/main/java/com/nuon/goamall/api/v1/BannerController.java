package com.nuon.goamall.api.v1;

import com.nuon.goamall.exception.NotFoundException;
import com.nuon.goamall.model.Banner;
import com.nuon.goamall.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/banner")
@Validated
public class BannerController {

    @Autowired
    BannerService bannerService;

    /**
     * NotNull：不能为null，但可以为empty
     * NotEmpty：不能为null，而且长度必须大于0
     * NotBlank：只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
     */
    @PostMapping("/name/{name}")
    public Banner getByName(@PathVariable @NotBlank String name) {
        Banner banner = bannerService.getByName(name);

        if (null == banner) {
            throw new NotFoundException(30005);
        }
        return banner;
    }
}
