package com.lzg.manager.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/page")
public class ViewController {

    @RequestMapping("**")
    public String page(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
