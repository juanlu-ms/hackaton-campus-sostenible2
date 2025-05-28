package com.antimadrugones.hackaton_campus_sostenible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Inicio");
        model.addAttribute("content", "index");
        return "layout";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("content", "dashboard");
        return "layout";
    }

    @GetMapping("/contenedores")
    public String contenedores(Model model) {
        model.addAttribute("pageTitle", "Contenedores");
        model.addAttribute("content", "contenedores");
        return "layout";
    }
}