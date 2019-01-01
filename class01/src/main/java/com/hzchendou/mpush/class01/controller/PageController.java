package com.hzchendou.mpush.class01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 网页访问控制.
 *
 * @author hzchendou
 * @date 18-11-22
 * @since 1.0
 */
@Controller
public class PageController {

    /**
     * 访问首页
     *
     * @return
     */
    @GetMapping(value = "index")
    public String index() {
        return "/index";
    }
}
