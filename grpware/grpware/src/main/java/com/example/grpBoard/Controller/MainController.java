package com.example.grpBoard.Controller;

import com.example.grpBoard.dto.DeptDto;
import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.dto.MemoDto;
import com.example.grpBoard.mappers.BoardMapper;
import com.example.grpBoard.mappers.MemoMapper;
import com.example.grpBoard.mappers.MyPageMapper;
import com.example.grpBoard.mappers.RegisterMapper;
import com.example.grpBoard.service.BoardSrv;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MyPageMapper myPageMapper;

    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private MemoMapper memoMapper;

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BoardSrv boardSrv;

    @GetMapping("/main")
    public String getMain(Model model, @RequestParam(required = false) String getBoardId,
                          @RequestParam(value = "searchType", defaultValue = "") String searchType,
                          @RequestParam(value = "words", defaultValue = "") String words,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          HttpServletRequest req) {

        EmployeesDto employeesDto = new EmployeesDto();
        System.out.println("세션 통: " + employeesDto);
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("user");
        System.out.println("세션 값 :" + employeesDto);

        if (employeesDto == null) {
            System.out.println("로그아웃됩니다.");
            return "redirect:/login";
        } else {

            model.addAttribute("sub", boardMapper.getBoardSubject());
            model.addAttribute("memo", memoMapper.getMemoList());
            model.addAttribute("board", boardMapper.getBoardList());
            model.addAttribute("grp", boardSrv.getBoardList(searchType, words, page));
            model.addAttribute("pagination", boardSrv.pageCalc(page));
            model.addAttribute("totalCount", boardMapper.getBoardCount(getBoardId));
            System.out.println("grpEmpId : " + employeesDto.getGrpEmpId());
            model.addAttribute("employees", myPageMapper.getEmpMypage(employeesDto.getGrpEmpId()));
            System.out.println("마이페이지 정보:" + myPageMapper.getEmpMypage(employeesDto.getGrpEmpId()));
            model.addAttribute("dept", registerMapper.getDept());

            return "index2";
        }
    }

    @PostMapping("/main")
    @ResponseBody
    public Map<String, Object> saveMemo(@ModelAttribute MemoDto memoDto) {
        memoMapper.saveMemo(memoDto);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "success");
        return map;
    }
    @GetMapping("/not")
    public String getNot(Model model) {


        return "settings/pages-account-settings-notifications";
    }

    @GetMapping("/admin/not")
    public String getAdminNot(Model model) {

        return "settings/admin-notifications";
    }

    @GetMapping("/card")
    public String getCard() {
        return "components/cards-basic";
    }

    @GetMapping("/inputs")
    public String getInputs() {
        return "forms/forms-basic-inputs";
    }

    @GetMapping("/groups")
    public String getGroups() {
        return "forms/forms-input-groups";
    }

    @GetMapping("/vertical")
    public String getVertical() {
        return "forms/form-layouts-vertical";
    }

    @GetMapping("/horizontal")
    public String getHorizontal() {
        return "forms/form-layouts-horizontal";
    }

    @GetMapping("/announcement")
    public String getA() {
        return "announcement/announcement";
    }

    @GetMapping("/calendar")
    public String getCalendar() {
        return "calendar/calendar";
    }

    @GetMapping("/employees/login")
    public String getLogin() {
        return "employees/login";
    }

}
