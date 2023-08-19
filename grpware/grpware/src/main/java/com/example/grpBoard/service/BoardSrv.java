package com.example.grpBoard.service;

import com.example.grpBoard.dto.BoardDto;
import com.example.grpBoard.dto.PageDto;
import com.example.grpBoard.mappers.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardSrv {

    @Autowired
    private BoardMapper boardMapper;

    public PageDto pageCalc(int page){

        PageDto pageDto = new PageDto();
        int totalCount = boardMapper.getTotalCount();


        int totalPage = (int)Math.ceil((double) totalCount / pageDto.getPageCount());

        int startPage = ((int)Math.ceil((double)page / pageDto.getBlockCount()) - 1) * pageDto.getStartPage() + 1;

        int endPage = startPage + pageDto.getBlockCount() - 1;

        if(endPage > totalPage) {
            endPage = totalPage;
        }
        pageDto.setTotalPage(totalPage);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setPage(page);


        return pageDto;

    }

    public List<BoardDto> getBoardList(String searchType, String words, int page) {

        PageDto pageDto = new PageDto();

        int startNum = (page - 1) * pageDto.getPageCount();


        String searchQuery = "";
        if(searchType.equals("grp_board_subject") || searchType.equals("grp_board_writer")) {
            searchQuery = "where " +searchType+" ='"+words+"'";

        } else if (searchType.equals("grp_board_content")) {
            searchQuery = "where grp_board_content like '%" + words +"%'";

        }else {
            searchQuery = "";
        }
        System.out.println(searchQuery);
        Map<String, Object> map = new HashMap<>();
        map.put("searchQuery", searchQuery);
        map.put("startNum", startNum);
        map.put("count", pageDto.getPageCount());


        return boardMapper.getList(map);
    }


}
