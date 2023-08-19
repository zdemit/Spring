package com.example.grpBoard.service;

import com.example.grpBoard.dto.EmpPagingDto;
import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.mappers.AdminEmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpPagingSrv {

    @Autowired
    AdminEmpMapper adminEmpMapper;

    //1번객체: 알고리즘 구현
    public EmpPagingDto PageCalc(int page) {
        int totalCount = adminEmpMapper.getTotalCount();
        EmpPagingDto pDto = new EmpPagingDto();
        int totalPage = (int) Math.ceil(((double)totalCount / pDto.getPageCount()));
        int startPage = (((int)Math.ceil((double) page / pDto.getBlockCount())) -1 ) * pDto.getPageCount() + 1;

        int endPage = startPage + pDto.getBlockCount() - 1;

        if( endPage > totalPage) {
            endPage = totalPage;
        }

        pDto.setPage(page);
        pDto.setTotalPage(totalPage);
        pDto.setStartPage(startPage);
        pDto.setEndPage(endPage);

        return pDto;

    }

    //2번객체: 구현한 알고리즘을 mapper start, limit 변수로 보내주기
    //페이지 잘라서 화면에 표시하기
    //Controller에서 넘겨준 page 값을 가져오기
    public List<EmployeesDto> getPagingEmp(int page) {
        Map<String, Object> map = new HashMap<>();
        EmpPagingDto pagingDto = new EmpPagingDto();

        int pageStartNum = (page - 1) * pagingDto.getPageCount();
        map.put("start", pageStartNum);
        map.put("limit", pagingDto.getPageCount());

        return adminEmpMapper.getAdminEmpList(map);
    }
}
