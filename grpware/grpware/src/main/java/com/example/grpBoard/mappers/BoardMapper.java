package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.BoardDto;
import com.example.grpBoard.dto.EmployeesDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {


    @Insert("insert into grp_board values(null, #{grpBoardNotice}, #{grpBoardSubject}, #{grpBoardWriter}, #{grpBoardContent}, #{grpBoardUploadName}, #{grpBoardUploadUrl}, #{grpBoardUploadSize}, #{grpBoardUploadTrans}, 0, now())")
    void setBoardWrite(BoardDto boardDto);

    @Select("SELECT * FROM grp_board order by grp_board_id desc")
    List<BoardDto> getBoardList();

    @Delete("delete from grp_board where grp_board_id = #{grpBoardId}")
    void adminDeleteBoard(int grpBoardId);
    @Select("select * from grp_board where grp_board_id = #{grpBoardId}")
    BoardDto viewBoard(int grpBoardId);

    @Update("update grp_board set grp_board_notice = #{grpBoardNotice}, grp_board_subject = #{grpBoardSubject}, grp_board_writer = #{grpBoardWriter}, grp_board_content = #{grpBoardContent}, grp_board_upload_name = #{grpBoardUploadName}, grp_board_upload_url = #{grpBoardUploadUrl}, grp_board_upload_size = #{grpBoardUploadSize}, grp_board_upload_trans = #{grpBoardUploadTrans}, grp_board_regdate = now() where grp_board_id = #{grpBoardId}")
    void setModify(BoardDto boardDto);

    @Update("update grp_board set grp_board_visit = grp_board_visit + 1 where grp_board_id = #{grpBoardId}")
    void updateVisit(int grpBoardId);

    @Select("select count(*) from grp_board")
    int getBoardCount(String grpBoardId);

    @Select("select * from grp_board ${searchQuery} order by grp_board_notice desc, grp_board_id desc LIMIT #{startNum}, #{count}")
    List<BoardDto> getList(Map<String, Object> map);

    //페이지 처리를 하기 위한 전체 게시물 수
    @Select("select count(*) from grp_board ${searchQuery}")
    int getTotalCount();

    //메인에 표시
    @Select("SELECT grp_board_subject FROM grp_board WHERE grp_board_notice = 'Y'")
    List<BoardDto> getBoardSubject();

    //글 작성했을 때 작성자를 이메일로 자동으로 뽑아오게 할 예정
    @Select("SELECT * FROM grp_employees WHERE grp_emp_email = #{grpEmpEmail}")
    EmployeesDto getBoardEmployeesMail(String grpEmpEmail);

}












