package com.example.grpBoard.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private int grpBoardId;
    private String grpBoardNotice;
    private String grpBoardSubject; //제목
    private String grpBoardWriter; //작성자
    private String grpBoardContent; //본문
    private String grpBoardUploadName;
    private String grpBoardUploadUrl;
    private Long grpBoardUploadSize;
    private String grpBoardUploadTrans;
    private int grpBoardVisit;
    private LocalDateTime grpBoardRegdate;
}
