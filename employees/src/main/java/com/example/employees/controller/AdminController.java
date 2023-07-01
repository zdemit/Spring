package com.example.employees.controller;

import com.example.employees.mappers.WebsiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private WebsiteMapper websiteMapper;

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("web", websiteMapper.getWebsite());
        return "admin/admin";
    }
}
