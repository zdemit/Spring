package com.example.grpBoard.dto;

import lombok.Data;

@Data
public class EmpPagingDto {
    private int pageCount = 5; // 한페이지에 표시 될 게시물 수
    private int blockCount = 5; //표시할 페이지 번호의 개수

    private int page; // 현재 페이지 설정..?
    private int totalPage; // 전체게시물  수
    private int startPage; // 첫페이지 있을 경우
    private int endPage; // 끝페이지에 있을경우
}
