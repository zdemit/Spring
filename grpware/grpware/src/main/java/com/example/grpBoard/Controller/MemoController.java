package com.example.grpBoard.Controller;


import com.example.grpBoard.dto.MemoDto;
import com.example.grpBoard.mappers.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MemoController {

    @Autowired
    private MemoMapper memoMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/memo/delete")
    public String deleteMemo(@RequestParam int grpNum) {
        memoMapper.deleteMemo(grpNum);

        return "redirect:/main";
    }

    @GetMapping("/memo/deleteAll")
    public String deleteAllMemo() {
        String tableName = "grp_memo"; // 테이블 이름 설정

        // 테이블 데이터 삭제
        String clearQuery = "DELETE FROM " + tableName;
        jdbcTemplate.execute(clearQuery);

        // 시퀀스 리셋
        String resetSequenceQuery = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
        jdbcTemplate.execute(resetSequenceQuery);

        return "redirect:/main";
    }

    @GetMapping("/memoEdit")
    public String getMemo(@RequestParam int grpNum, Model model) {

        model.addAttribute("memo", memoMapper.viewMemo(grpNum));

        return "memo/memoEdit";
    }

    @PostMapping("/memoEdit")
    @ResponseBody
    public Map<String, Object> updateMemo(@ModelAttribute MemoDto memoDto) {

        Map<String, Object> map = new HashMap<>();

        if(memoDto.getGrpNum() > 0) {
            memoMapper.updateMemo(memoDto);
            map.put("msg" , "success");
        }

        return map;
    }

}
