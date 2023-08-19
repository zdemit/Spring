package com.example.grpBoard.Controller;


import com.example.grpBoard.dto.DeptDto;
import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.dto.PosDto;
import com.example.grpBoard.mappers.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private RegisterMapper registerMapper;

    @GetMapping("/register")
    public String GetEmployees() {
        return"employees/register";
    }

    @PostMapping("/getDept")
    @ResponseBody
    public List<DeptDto> getDept(){
        return registerMapper.getDept();
    }

    @PostMapping("/getPos")
    @ResponseBody
    public List<PosDto> getPos(@RequestParam String selDeptValue) {

        return registerMapper.getPos(selDeptValue);
    }

    @PostMapping("/register/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam String userid) {
//        System.out.println(userid);

        return registerMapper.idCheck(userid);
    }

    @PostMapping("/emailCheck")
    @ResponseBody
    public int emailCheck(@RequestParam String email) {
//        System.out.println(userid);

        return registerMapper.emailCheck(email);
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> saveEmployees(@ModelAttribute EmployeesDto employeesDto) {
        Map<String, Object> map = new HashMap<>();

        if(employeesDto != null) {
            registerMapper.saveEmployees(employeesDto);
            map.put("msg", "success");
        }
        return map;
    }





}
