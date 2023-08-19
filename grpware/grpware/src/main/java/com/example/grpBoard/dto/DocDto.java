package com.example.grpBoard.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocDto {
    private int grpDocId;
    private String grpDocSubject;
    private String grpDocWriter;
    private String grpDocWriterUserid;
    private String grpDocContent;
    private String grpDocUploadName;
    private String grpDocUploadUrl;
    private Long grpDocUploadSize;
    private String grpDocUploadTrans;
    private LocalDateTime grpDocRegdate;
    private LocalDateTime grpDocRegdateSign;
    private String grpDocApprover;
    private String grpDocAccept;
    private String grpDocState;
    private String grpDocRfr;
}
