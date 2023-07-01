package com.example.employees.controller;

import com.example.employees.dto.RegisterDto;
import com.example.employees.mappers.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/main/login")
    public String getLogin() {
        return "main/login";
    }

    @PostMapping("/main/login")
    public String checkLogin(@ModelAttribute RegisterDto registerDto) {
//        System.out.println(loginMapper.getEmpInfo(registerDto));
        RegisterDto r = loginMapper.getEmpInfo(registerDto);
        if(r != null) {
            if(r.getKorEmpAuth().equals("Y")) {
                System.out.println("관리자 페이지로 이동");
                return "redirect:/admin";
            } else {
                System.out.println("사용자 페이지로 이동");
                return "redirect:/home";
            }

        } else {
            System.out.println("가입된 사원 정보가 없습니다.");
            return "redirect:/main/register";
        }
    }
}
