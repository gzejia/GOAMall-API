package com.nuon.goamall.api.v1;

import com.nuon.goamall.exception.ForbiddenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @GetMapping("/test")
    public String test() {
        throw new ForbiddenException(10000);
    }
}
