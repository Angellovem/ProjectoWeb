package edu.javeriana.wiki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/description")
    public String description() {
        return "Description";
    }

    @RequestMapping("/presentation")
    public String presentation() {
        return "Presentation";
    }
}
