package com.example.grpBoard.Controller;

import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.mappers.LoginMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/login")
    public String GetLogin(){
        return"employees/login";
    }

    @PostMapping("/login")
    public String CheckLogin(@ModelAttribute EmployeesDto employeesDto, HttpServletRequest req, RedirectAttributes ra) {
        EmployeesDto e = loginMapper.CheckLogin(employeesDto);
        System.out.println("세션값 e :" + e);
        String failMessage = "아이디 또는 비밀번호를 확인하세요.";

        if (e != null) {
            if (e.getGrpEmpAuth().equals("Y")) {
                System.out.println("관리자페이지로 이동");
                HttpSession hs = req.getSession();
                hs.setAttribute("admin", e);
                hs.setMaxInactiveInterval(60 * 30);

                return "redirect:/admin/main";

            } else {
                System.out.println("메인페이지로 이동");
                HttpSession hs = req.getSession();
                hs.setAttribute("user", e);
                hs.setMaxInactiveInterval(60 * 30);


                return "redirect:/main";
            }
        }else{
            System.out.println("아이디 또는 비밀번호를 확인하세요");
            ra.addFlashAttribute("loginFail" ,failMessage);
            return "redirect:/login";
        }
    }


    @PostMapping("/logout")
    public String getLogout(HttpSession hs) {
        hs.invalidate();
        return "redirect:/login";
    }
}

