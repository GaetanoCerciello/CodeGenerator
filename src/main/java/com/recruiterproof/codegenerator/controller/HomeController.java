package com.recruiterproof.codegenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController serves the index.html frontend.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
