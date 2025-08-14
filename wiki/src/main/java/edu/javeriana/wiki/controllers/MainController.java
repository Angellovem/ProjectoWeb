package edu.javeriana.wiki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "MainPage";
    }

    @GetMapping("/MainPage")
    public String mainPage() {
        return "MainPage";
    }

    @GetMapping("/description")
    public String description() {
        return "Description";
    }
}
