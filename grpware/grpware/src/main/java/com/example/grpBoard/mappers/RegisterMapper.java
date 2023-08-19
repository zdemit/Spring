package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.DeptDto;
import com.example.grpBoard.dto.EmployeesDto;
import com.example.grpBoard.dto.PosDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Select("SELECT * FROM grp_dept")
    List<DeptDto> getDept();

    @Select("SELECT * FROM grp_pos WHERE grp_dept_code = #{selDeptValue}")
    List<PosDto> getPos(String selDeptValue);

    @Select("SELECT COUNT(*) FROM grp_employees WHERE grp_emp_userid = #{userid}")
    int idCheck(String userid);

    @Select("SELECT COUNT(*) FROM grp_employees WHERE grp_emp_email = #{email}")
    int emailCheck(String email);

    @Select("SELECT grp_emp_email from grp_employees where grp_emp_email = #{email}")
    String getemailCheck(String email);

    @Insert("INSERT INTO grp_employees VALUES(NULL, #{grpEmpUserid}, #{grpEmpPasswd}, #{grpEmpName}, #{grpEmpEmail}, #{grpEmpPnum}, #{grpEmpTel}, #{grpEmpAuth}, #{grpEmpDept}, #{grpEmpPos}, #{grpEmpBirth}, now(), now(), now(), '', 0, '' )")
    void saveEmployees(EmployeesDto employeesDto);

}
