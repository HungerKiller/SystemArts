package com.system.arts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //1
public class IndexController {
    @RequestMapping(value = "/")//2
    public String index() {
        return "Hello Spring boot";//3
    }
}
