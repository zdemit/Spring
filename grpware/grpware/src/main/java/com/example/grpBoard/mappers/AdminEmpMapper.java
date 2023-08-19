package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.DeptDto;
import com.example.grpBoard.dto.EmployeesDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminEmpMapper {

    @Select("SELECT * FROM grp_employees ORDER BY grp_emp_id DESC LIMIT #{start}, #{limit}")
    List<EmployeesDto> getAdminEmpList(Map<String, Object> map);

    @Delete("DELETE FROM grp_employees WHERE grp_emp_id = #{grpEmpId}")
    void deleteEmp(int grpEmpId);

    @Select("select * from grp_dept D inner join grp_employees E on D.grp_dept_code = E.grp_emp_dept inner join grp_pos P on E.grp_emp_pos = P.grp_pos_code where E.grp_emp_id = #{grpEmpId}")
    EmployeesDto getEmpView(int grpEmpId);

    //    페이징 처리를 하기 위한 전체 게시물 수
    @Select("SELECT COUNT(*) FROM grp_employees")
    int getTotalCount();

    @Update("UPDATE grp_employees SET grp_emp_name = #{grpEmpName}, grp_emp_pnum = #{grpEmpPnum}, grp_emp_tel= #{grpEmpTel}, grp_emp_dept=#{grpEmpDept}, grp_emp_modified=now() WHERE grp_emp_id = #{grpEmpId}")
    void setEmpUpdate(EmployeesDto employeesDto);

    @Select("SELECT D.grp_dept_code, D.grp_dept_name, COUNT(P.grp_pos_code) as grp_dept_cnt FROM grp_dept D INNER JOIN grp_pos P ON D.grp_dept_code = P.grp_dept_code GROUP BY D.grp_dept_code, D.grp_dept_name")
    List<DeptDto> getCategoryDept();
}
