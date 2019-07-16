package br.com.thiagosousa.ordersapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class SwaggerController {

    @ApiIgnore
    @GetMapping("/")
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}
