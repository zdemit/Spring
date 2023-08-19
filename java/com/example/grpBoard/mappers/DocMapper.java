package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.DocDto;
import com.example.grpBoard.dto.EmployeesDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DocMapper {
    @Select("select * from grp_doc where grp_doc_writer_userid = #{grpDocWriterUserid} order by grp_doc_id desc")
    List<DocDto> getDocListAll(DocDto docDto);

    @Select("select * from grp_doc where grp_doc_approver = #{grpDocApprover} order by grp_doc_id desc")
    List<DocDto> getDocSignListAll(DocDto docDto);

    @Select("select distinct grp_doc_writer from grp_doc where grp_doc_writer_userid = #{grpDocWriterUserid}")
    String getDocWriterUserid(DocDto docDto);

    @Insert("insert into grp_doc values(null, #{grpDocSubject}, #{grpDocWriter}, #{grpDocWriterUserid}, #{grpDocContent}, #{grpDocUploadName}, #{grpDocUploadUrl}, #{grpDocUploadSize}, #{grpDocUploadTrans}, now(), null, #{grpDocApprover}, 'N', '결재대기', '')")
    void setDocWrite(DocDto docDto);

    @Select("select grp_emp_name from grp_employees where grp_emp_userid = #{grpEmpUserid}")
    String getDocEmpName(EmployeesDto employeesDto);

    @Select("select grp_emp_userid from grp_employees where grp_emp_dept = #{grpEmpDept} and grp_emp_pos = #{grpEmpPos}")
    String getDocApproverId(EmployeesDto employeesDto);
    @Select("select grp_emp_name from grp_employees where grp_emp_userid = #{grpDocApprover}")
    String getDocApproverNameOne(DocDto docDto);

    @Select("select grp_emp_name from grp_employees where grp_emp_dept = #{selDeptValue} and grp_emp_pos = #{selPosValue}")
    String getDocApproverNameTwo(String selDeptValue, String selPosValue);

    @Select("select * from grp_doc where grp_doc_id = #{grpDocId}")
    DocDto viewDoc(int grpDocId);

    @Update("update grp_doc set grp_doc_regdate_sign = now(), grp_doc_accept = 'Y', grp_doc_state = '결재완료', grp_doc_rfr = '' where grp_doc_id = #{grpDocId}")
    void acceptDoc(int grpDocId);

    @Update("update grp_doc set grp_doc_accept = 'N', grp_doc_state = '반려', grp_doc_rfr = #{grpDocRfr} where grp_doc_id = #{grpDocId}")
    void rejectDoc(DocDto docDto);

    @Delete("delete from grp_doc where grp_doc_id = #{grpDocId}")
    void deleteDoc(int grpDocId);

    @Update("update grp_doc set grp_doc_approver = #{grpDocApprover}, grp_doc_subject = #{grpDocSubject}, grp_doc_content = #{grpDocContent}, grp_doc_upload_name = #{grpDocUploadName}, grp_doc_upload_url = #{grpDocUploadUrl}, grp_doc_upload_size = #{grpDocUploadSize}, grp_doc_upload_trans = #{grpDocUploadTrans} where grp_doc_id = #{grpDocId}")
    void setDocModify(DocDto docDto);

    @Select("select E.grp_emp_name from grp_employees E inner join grp_doc D on E.grp_emp_userid = D.grp_doc_approver where D.grp_doc_id = #{grpDocId}")
    String getDocAppName(int grpDocId);

    @Select("select * from grp_employees where grp_emp_userid = #{grpEmpUserid}")
    EmployeesDto getEmployees(EmployeesDto employeesDto);
}
