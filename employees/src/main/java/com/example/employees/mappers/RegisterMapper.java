package com.example.employees.mappers;

import com.example.employees.dto.DeptDto;
import com.example.employees.dto.PosDto;
import com.example.employees.dto.RegisterDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {
    @Select("select * from kor_dept order by kor_dept_code desc")
    List<DeptDto> getDept();

    @Select("select * from kor_pos where kor_dept_code = #{selDeptValue}")
    List<PosDto> getPos(String selDeptValue);

    @Select("select count(*) from kor_employees where kor_emp_email = #{email}")
    int emailCheck(String email);

    @Insert("INSERT INTO kor_employees VALUES( NULL, #{korEmpEmail}, #{korEmpPasswd}, #{korEmpName}, #{korEmpGender}, #{korEmpDept}, #{korEmpPos}, 1, 'N', now(), now(), '', 0, '' )")
    void saveRegister(RegisterDto registerDto);
}
