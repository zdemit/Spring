package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.MemoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemoMapper {

    @Insert("insert into grp_memo values(null, #{grpContent})")
    void saveMemo(MemoDto memoDto);

    @Select("select * from grp_memo")
    List<MemoDto> getMemoList();

    @Select("select * from grp_memo where grp_num = #{grpNum}")
    MemoDto viewMemo(int grpNum);

    @Update("update grp_memo set grp_content=#{grpContent} where grp_num = #{grpNum}")
    void updateMemo(MemoDto memoDto);

    @Delete("delete from grp_memo where grp_num = #{grpNum}")
    void deleteMemo(int grpNum);
}
