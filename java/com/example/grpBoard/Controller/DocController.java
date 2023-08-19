package com.example.grpBoard.Controller;


import com.example.grpBoard.dto.DeptDto;
import com.example.grpBoard.dto.DocDto;
import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.dto.PosDto;
import com.example.grpBoard.mappers.DocMapper;
import com.example.grpBoard.mappers.RegisterMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doc")
public class DocController {
    @Value("${spring.servlet.multipart.location}")
    String UPLOAD_LOCATION;

    @Autowired
    private DocMapper docMapper;

    @Autowired
    private RegisterMapper registerMapper;

    @GetMapping("/list")
    public String getDocList(Model model, @ModelAttribute DocDto docDto, HttpServletRequest req) {
        EmployeesDto employeesDto = new EmployeesDto();
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("user");
        if(employeesDto == null) {
            return "redirect:/login";
        } else {
            employeesDto.setGrpEmpUserid(((EmployeesDto) session.getAttribute("user")).getGrpEmpUserid());
            docDto.setGrpDocWriterUserid(employeesDto.getGrpEmpUserid());
            model.addAttribute("doc", docMapper.getDocListAll(docDto));
            return "document/doc_list";
        }
    }
    @GetMapping("/signList")
    public String getDocSignList(Model model, @ModelAttribute DocDto docDto, HttpServletRequest req) {
        EmployeesDto employeesDto = new EmployeesDto();
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("user");
        if(employeesDto == null) {
            return "redirect:/login";
        } else {
            employeesDto.setGrpEmpUserid(((EmployeesDto) session.getAttribute("user")).getGrpEmpUserid());
            docDto.setGrpDocApprover(employeesDto.getGrpEmpUserid());
            model.addAttribute("doc", docMapper.getDocSignListAll(docDto));
            return "document/doc_sign_list";
        }
    }

    @GetMapping("/write")
    public String getDocWrite(Model model, @ModelAttribute DocDto docDto, @ModelAttribute EmployeesDto employeesDto, HttpServletRequest req) {
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("user");
        if(employeesDto == null) {
            return "redirect:/login";
        } else {
            employeesDto.setGrpEmpUserid(((EmployeesDto) session.getAttribute("user")).getGrpEmpUserid());
            model.addAttribute("docWriter", docMapper.getDocEmpName(employeesDto));
            model.addAttribute("uid", employeesDto.getGrpEmpUserid());
            return "document/doc_write";
        }
    }

    @PostMapping("/write")
    @ResponseBody
    public Map<String, Object> setDocWrite(Model model,@ModelAttribute DocDto docDto, @ModelAttribute EmployeesDto employeesDto, MultipartFile uploadFile) {
        Map<String, Object> map = new HashMap<>();

        try {
            if(uploadFile != null) {
                String saveDir = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());

                File f = new File(UPLOAD_LOCATION + "\\" + saveDir);

                if(!f.exists()) {
                    f.mkdir();
                }
                String oName = uploadFile.getOriginalFilename();
                String ext = oName.substring(oName.lastIndexOf("."));
                String tName = System.currentTimeMillis() + ext;

                Path p = Paths.get(String.valueOf(f), tName);
                Files.write(p, uploadFile.getBytes());

                //첨부파일
                docDto.setGrpDocUploadName(uploadFile.getOriginalFilename());
                docDto.setGrpDocUploadUrl(saveDir);
                docDto.setGrpDocUploadSize(uploadFile.getSize());
                docDto.setGrpDocUploadTrans(tName);
            }
            map.put("msg", "success");

            docDto.setGrpDocWriterUserid(employeesDto.getGrpEmpUserid());
            docDto.setGrpDocApprover(docMapper.getDocApproverId(employeesDto));
            docMapper.setDocWrite(docDto);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/view")
    public String viewDoc(@RequestParam int grpDocId, Model model, HttpServletRequest req) {
        EmployeesDto employeesDto = new EmployeesDto();
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("user");
        if(employeesDto == null) {
            return "redirect:/login";
        } else {
            employeesDto.setGrpEmpUserid(((EmployeesDto) session.getAttribute("user")).getGrpEmpUserid());
            model.addAttribute("doc", docMapper.viewDoc(grpDocId));
            model.addAttribute("docApprover", docMapper.getDocAppName(grpDocId));
            model.addAttribute("emp", docMapper.getEmployees(employeesDto));
            return "document/doc_view";
        }
    }

    @GetMapping ("/accept")
    @ResponseBody
    public Map<String, Object> acceptDoc(@RequestParam int grpDocId, HttpServletRequest req) {
        Map<String, Object> map = new HashMap<>();

        EmployeesDto employeesDto = new EmployeesDto();
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("user");
        employeesDto.setGrpEmpUserid(((EmployeesDto) session.getAttribute("user")).getGrpEmpUserid());

        docMapper.acceptDoc(grpDocId);
        map.put("msg", "success");
        return map;
    }
    @GetMapping ("/reject")
    public String getRejectDoc(Model model, @RequestParam int grpDocId) {
        model.addAttribute("doc", docMapper.viewDoc(grpDocId));
        return "document/doc_reject";
    }

    @PostMapping("/reject")
    @ResponseBody
    public Map<String, Object> setRejectDoc(@ModelAttribute DocDto docDto) {
        Map<String, Object> map = new HashMap<>();

        docMapper.rejectDoc(docDto);
        map.put("msg", "success");

        return map;
    }

    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteDoc(@RequestParam int grpDocId) {
        Map<String, Object> map = new HashMap<>();
        docMapper.deleteDoc(grpDocId);
        map.put("msg", "success");
        return map;
    }
    @GetMapping("/modify")
    public String getDocModify(@RequestParam int grpDocId, Model model, @ModelAttribute EmployeesDto employeesDto, @ModelAttribute DocDto docDto, HttpServletRequest req) {
        HttpSession session = req.getSession();
        employeesDto = (EmployeesDto) session.getAttribute("user");
        if(employeesDto == null) {
            return "redirect:/login";
        } else {
            employeesDto.setGrpEmpUserid(((EmployeesDto) session.getAttribute("user")).getGrpEmpUserid());

            model.addAttribute("doc", docMapper.viewDoc(grpDocId));
            model.addAttribute("docWriter", docMapper.getDocEmpName(employeesDto));
            model.addAttribute("uid", employeesDto.getGrpEmpUserid());
            return "document/doc_modify";
        }
    }
    @PostMapping("/modify")
    @ResponseBody
    public Map<String, Object> setDocModify(@ModelAttribute DocDto docDto, @ModelAttribute EmployeesDto employeesDto, MultipartFile uploadFile, Model model) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(uploadFile != null) {
                String saveDir = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
                File f = new File(UPLOAD_LOCATION + "\\" + saveDir);
                if(!f.exists()) {
                    f.mkdir();
                }
                String oName = uploadFile.getOriginalFilename();
                String ext = oName.substring(oName.lastIndexOf("."));
                String tName = System.currentTimeMillis() + ext;

                Path p = Paths.get(String.valueOf(f), tName);
                Files.write(p, uploadFile.getBytes());

                docDto.setGrpDocUploadName(uploadFile.getOriginalFilename());
                docDto.setGrpDocUploadUrl(saveDir);
                docDto.setGrpDocUploadSize(uploadFile.getSize());
                docDto.setGrpDocUploadTrans(tName);

            }else {
                if(docDto.getGrpDocUploadName().equals("") ) {
                    docDto.setGrpDocUploadName(null);
                    docDto.setGrpDocUploadSize(docDto.getGrpDocUploadSize());
                    docDto.setGrpDocUploadTrans(null);
                }else{
                    docDto.setGrpDocUploadName(docDto.getGrpDocUploadName());
                    docDto.setGrpDocUploadSize(docDto.getGrpDocUploadSize());
                    docDto.setGrpDocUploadTrans(docDto.getGrpDocUploadTrans());
                }
            }
            map.put("msg", "success");

            docDto.setGrpDocWriterUserid(employeesDto.getGrpEmpUserid());
            docDto.setGrpDocApprover(docMapper.getDocApproverId(employeesDto));
            docMapper.setDocModify(docDto);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @PostMapping("/getDept")
    @ResponseBody
    public List<DeptDto> getDept() {
        return registerMapper.getDept();
    }

    @PostMapping("/getPos")
    @ResponseBody
    public List<PosDto> getPos(@RequestParam String selDeptValue) {
        return registerMapper.getPos(selDeptValue);
    }
    @PostMapping("/getName")
    @ResponseBody
    public Map<String, Object> getName(@RequestParam String selDeptValue, @RequestParam String selPosValue) {

        return Map.of("appName", docMapper.getDocApproverNameTwo(selDeptValue, selPosValue));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getDownload(@RequestParam String dir, @RequestParam String filename) throws IOException { //폴더이름, 파일이름

        //다운로드 할 저장 위치
        Path p = Paths.get(UPLOAD_LOCATION + "\\" + dir + "\\" + filename);

        /* header 정보 */
        String contentType = Files.probeContentType(p);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename, StandardCharsets.UTF_8).build());

        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(p));


        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
