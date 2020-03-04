package com.nuon.goamall.api.v1;

import com.nuon.goamall.core.UnifyResponse;
import com.nuon.goamall.dto.PersonDTO;
import com.nuon.goamall.model.Banner;
import com.nuon.goamall.service.BannerService;
import org.hibernate.validator.constraints.Length;
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
    public UnifyResponse getByName(@PathVariable @NotBlank String name) {
        Banner banner = bannerService.getByName(name);
        return null;
    }

    @GetMapping("/test")
    public String test(@RequestParam @Length(min = 6) String name, @RequestBody @Validated PersonDTO person) {
        return "123";
    }
}
