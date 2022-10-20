package com.molla.controller.web;

import com.molla.util.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "WebController")
public class HomeController {
    @Autowired
    AuthenticationUser authenticationUser;

    @GetMapping("/shop")
    public String viewShopPage(Model model) {
        authenticationUser.filterUser(model);
        return "web/shop";
    }
}
