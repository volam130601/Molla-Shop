package com.molla.controller.admin;

import com.molla.util.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminController")
@RequestMapping("/admin")
public class HomeController {
    @Autowired
    AuthenticationUser authenticationUser;

    public void common(Model model) {
        authenticationUser.filterUser(model);
    }

    @GetMapping("")
    public String viewAdminPage(Model model) {
        common(model);
        return "admin/index";
    }

}
