package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.EmployeesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MyPageMapper {

    @Select("SELECT D.*, P.*, E.* FROM grp_dept D INNER JOIN grp_employees E ON D.grp_dept_code = E.grp_emp_dept INNER JOIN grp_pos P ON E.grp_emp_pos = P.grp_pos_code WHERE E.grp_emp_id = #{grpEmpId}")
    EmployeesDto getEmpMypage(int grpEmpId);

    @Select("SELECT * FROM grp_employees WHERE grp_emp_id = #{grpEmpId}")
    List<EmployeesDto> getEmpOne(int grpEmpId);

    @Update("UPDATE grp_employees SET grp_emp_userid = #{grpEmpUserid}, grp_emp_passwd = #{grpEmpPasswd}, grp_emp_name=#{grpEmpName},  grp_emp_email=#{grpEmpEmail}, grp_emp_pnum= #{grpEmpPnum}, grp_emp_birth=#{grpEmpBirth}, grp_emp_modified=now() WHERE grp_emp_id=#{grpEmpId}")
    void setEmpUpdate(EmployeesDto employeesDto);

    @Update("UPDATE grp_employees SET grp_emp_image_name = #{grpEmpImageName}, grp_emp_image_size = #{grpEmpImageSize} WHERE grp_emp_id = #{grpEmpId}")
    void fileUpload(EmployeesDto employeesDto);

    @Select("SELECT grp_emp_image_name FROM grp_employees WHERE grp_emp_id = #{grpEmpId}")
    EmployeesDto getImageName(int grpEmpId);



}
