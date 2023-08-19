package com.example.grpBoard.Controller;

import com.example.grpBoard.dto.DeptDto;
import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.mappers.BoardMapper;
import com.example.grpBoard.mappers.MyPageMapper;
import com.example.grpBoard.mappers.RegisterMapper;
import com.example.grpBoard.service.BoardSrv;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    private String UPLOAD_LOCATION = "C:\\Users\\ITPS\\Downloads\\grp_0817_최종\\src\\main\\resources\\static\\upload";
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private MyPageMapper myPageMapper;

    @Autowired
    private BoardSrv boardSrv;

    @Autowired
    private RegisterMapper registerMapper;

    @GetMapping("/admin/main")
    public String adminMain(Model model, @RequestParam(required = false) String getBoardId,
                            @RequestParam(value = "searchType", defaultValue = "") String searchType,
                            @RequestParam(value = "words", defaultValue = "") String words,
                            @RequestParam(value = "page", defaultValue = "1") int page,
                            HttpServletRequest req) {

        EmployeesDto employeesDto = new EmployeesDto();
        System.out.println("세션 통: " + employeesDto);
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("admin");
        System.out.println("세션 값 :" + employeesDto);

        if (employeesDto == null) {
            System.out.println("로그아웃됩니다.");
            return "redirect:/login";
        } else {

            model.addAttribute("board", boardMapper.getBoardList());
            model.addAttribute("sub", boardMapper.getBoardSubject());
            model.addAttribute("grp", boardSrv.getBoardList(searchType, words, page));
            model.addAttribute("pagination", boardSrv.pageCalc(page));
            model.addAttribute("totalCount", boardMapper.getBoardCount(getBoardId));
            System.out.println(employeesDto.getGrpEmpId());
            model.addAttribute("employees", myPageMapper.getEmpMypage(employeesDto.getGrpEmpId()));
            model.addAttribute("dept", registerMapper.getDept());

            return "admin/adminMain";
        }


    }
    @GetMapping("/admin/mypage")
    public String adminMyPage(Model model , @ModelAttribute DeptDto deptDto, HttpServletRequest req) {
        EmployeesDto employeesDto = new EmployeesDto();
        System.out.println("세션 통: " + employeesDto);
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto)session.getAttribute("admin");
        System.out.println("세션 값 :" + employeesDto);

        if(employeesDto == null) {
            System.out.println("로그아웃됩니다.");
            return "redirect:/login";
        }else {
//            System.out.println("grpEmpId : " + employeesDto.getGrpEmpId());
            model.addAttribute("employees", myPageMapper.getEmpMypage(employeesDto.getGrpEmpId()));
            System.out.println("마이페이지 정보:" + myPageMapper.getEmpMypage(employeesDto.getGrpEmpId()));
            return "settings/admin-account";
        }

    }

    @PostMapping("admin/mypage/update/emailCheck")
    @ResponseBody
    public Map<String, Object> emailCheck(@RequestParam String email, @SessionAttribute ("user")EmployeesDto employeesDto) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(email);
        int eCheck = registerMapper.emailCheck(email);
        String sEmail = employeesDto.getGrpEmpEmail();
        registerMapper.getemailCheck(email);
        System.out.println("저장된 이메일 수 : " + eCheck);

        if( eCheck == 0 ||  employeesDto.getGrpEmpEmail() == sEmail) {
            map.put("message", "ok");
        }else{
            map.put("message", "no");
        }
        return map;
    }

    @PostMapping("/admin/mypage/upload")
    @ResponseBody
    public Map<String, Object> fileUpload(MultipartFile uploadFile, int grpEmpId) {
        Map<String, Object> map = new HashMap<>();
        try {

            if( uploadFile != null) {
                System.out.println( "원본이름: " + uploadFile.getOriginalFilename()) ;
                System.out.println(grpEmpId);
                System.out.println( "저장경로: " + UPLOAD_LOCATION);
                System.out.println( "파일크기" + uploadFile.getSize());
                EmployeesDto employeesDto = new EmployeesDto();
                employeesDto.setGrpEmpImageName(uploadFile.getOriginalFilename());
                employeesDto.setGrpEmpImageSize(uploadFile.getSize());
                employeesDto.setGrpEmpId(grpEmpId);

                myPageMapper.fileUpload(employeesDto);

                Path path = Paths.get(UPLOAD_LOCATION, uploadFile.getOriginalFilename());
                Files.write(path, uploadFile.getBytes());

                map.put("msg" ,"success");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @PostMapping("/admin/mypage/update")
    @ResponseBody
    public Map<String, Object> setEmpUpdate(@ModelAttribute EmployeesDto employeesDto) {
        System.out.println("원래정보"+employeesDto);
        Map<String, Object> map = new HashMap<>();


        myPageMapper.setEmpUpdate(employeesDto);
        System.out.println("변경 될 정보" + employeesDto);
        map.put("msg", "success");


        return map;
    }

    @GetMapping("/admin/calendar")
    public String getCalendar() {
        return "calendar/admin-calendar";
    }




}
