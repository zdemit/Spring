package com.example.grpBoard.Controller;

import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.dto.PosDto;
import com.example.grpBoard.mappers.AdminEmpMapper;
import com.example.grpBoard.mappers.MyPageMapper;
import com.example.grpBoard.mappers.RegisterMapper;
import com.example.grpBoard.service.EmpPagingSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminEmpController {

    @Autowired
    private AdminEmpMapper adminEmpMapper;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private EmpPagingSrv empPagingSrv;

    @Autowired
    private MyPageMapper myPageMapper;

    @GetMapping("/admin/emp")
    public String getAdminEmpList(Model model, @RequestParam(defaultValue = "1" , value="page") int page, @ModelAttribute EmployeesDto employeesDto) {

        model.addAttribute("dept" , registerMapper.getDept());
//        System.out.println(empPagingSrv.getPagingEmp(page));
        // paging 처리한 목록 출력하기
        model.addAttribute("emp", empPagingSrv.getPagingEmp(page));

        // page 번호출력
        model.addAttribute("pagination", empPagingSrv.PageCalc(page));


        return "admin/adminEmp";
    }

    @GetMapping("/admin/emp/delete")
    @ResponseBody
    public Map<String, Object> deleteEmp(@RequestParam int grpEmpId) {
        Map<String, Object> map = new HashMap<>();

        if(grpEmpId > 0) {
            adminEmpMapper.deleteEmp(grpEmpId);
            map.put("msg", "success");
        }

        return map;
    }

    @GetMapping("/admin/empView")
    public String getEmpUpdate(@ModelAttribute EmployeesDto employeesDto, Model model) {

        System.out.println( "emp : " + adminEmpMapper.getEmpView(employeesDto.getGrpEmpId()));
        model.addAttribute("emp", myPageMapper.getEmpMypage(employeesDto.getGrpEmpId()));
        model.addAttribute("dept", registerMapper.getDept());

        return "admin/adminEmpView";
    }

    @PostMapping("/admin/adminEmpView/dept")
    @ResponseBody
    public List<PosDto> getDept(@RequestParam String selDeptValue) {
        System.out.println("부서코드" + selDeptValue);

        return registerMapper.getPos(selDeptValue);
    }


    @PostMapping("/admin/emp/update")
    @ResponseBody
    public Map<String,Object> setEmpUpdate(@ModelAttribute EmployeesDto employeesDto) {
        Map<String,Object> map = new HashMap<>();
        if(employeesDto.getGrpEmpId() > 0) {
            adminEmpMapper.setEmpUpdate(employeesDto);
            System.out.println("emp: " + employeesDto);
            map.put("msg" , "success");
        }
        return map;
    }

}
