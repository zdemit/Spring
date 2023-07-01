package com.example.employees.mappers;

import com.example.employees.dto.WebsiteDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WebsiteMapper {
    @Select("select * from website")
    WebsiteDto getWebsite();

}
