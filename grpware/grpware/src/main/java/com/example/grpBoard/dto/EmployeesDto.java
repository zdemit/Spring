package com.example.grpBoard.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeesDto {
    private int grpEmpId;
    private String grpEmpUserid;
    private String grpEmpPasswd;
    private String grpEmpName;
    private String grpEmpEmail;
    private String grpEmpPnum;
    private String grpEmpTel;
    private String grpEmpAuth;
    private String grpEmpDept;
    private String grpEmpPos;
    private String grpEmpBirth;
    private LocalDateTime grpEmpStartdate;
    private LocalDateTime grpEmpLastdate;
    private LocalDateTime grpEmpModified;
    private String grpEmpImageName;
    private Long grpEmpImageSize;
    private String grpEmpTransName;

    private String grpDeptName;
    private String grpPosName;

}
