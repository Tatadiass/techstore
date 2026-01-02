package br.com.t2_fat.techstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // retorna o nome da view: templates/home.html
        return "home";
    }
}
