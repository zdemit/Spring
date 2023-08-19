package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.EmployeesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT * FROM grp_employees WHERE grp_emp_userid = #{grpEmpUserid} AND grp_emp_passwd = #{grpEmpPasswd}")
    EmployeesDto CheckLogin(EmployeesDto employeesDto);
}
