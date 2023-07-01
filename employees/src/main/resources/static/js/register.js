
    $(function() {
        $.ajax({
            type:"post",
            url:"/main/getDept",
            dataType:"json",
            success:function(res) {
                let output = "<option value=0>-- 부서 선택 --</option>";
                res.forEach( (r) => {
                    output += `<option value=${r.korDeptCode}>${r.korDeptName}</option>`;
                });
                $("#selDept").html(output);
            },
            error:function(err) {
                console.log(err);
            }
        });
    });
    function getPos() {
        let selDept = document.querySelector("#selDept");
        let selDeptValue = selDept.options[selDept.selectedIndex].value

        $.ajax({
            type : "post",
            url : "/main/getPos",
            dataType : "json",
            data : { selDeptValue },
            success : function(res) {
                let output = "<option value=0>-- 직책 선택 --</option>";
                res.forEach( (r) => {
                    output += `<option value=${r.korPosCode}>${r.korPosName}</option>`;
                });
                $("#selPos").html(output);
            },
            error : function(err) {
                console.log(err);
                }
        });
    }

    let korEmpEmail = document.querySelector(".korEmpEmail");
    let korEmpPasswd = document.querySelector(".korEmpPasswd");

    function chkEmail() {
        if(!korEmpEmail.value) {
            $(".alert > p").html("이메일을 입력해주세요.");
            $(".alert > p").css("padding", "20px 0");
            $("#chkPage").html("이메일을 입력해주세요.");
            korEmpEmail.focus();
            return false;
        } else {
            $.ajax({
                type : "post",
                url : "/main/emailCheck",
                dataType : "json",
                data : { email : korEmpEmail.value },
                success : function(res) {
                    if(res > 0) {
                        $("#chkPage").html("사용할 수 없는 이메일입니다.");
                        korEmpEmail.value = "";
                        korEmpEmail.focus();
                    } else {
                        $("#chkPage").html("사용 가능한 이메일입니다.");
                    }
                },
                error : function(err) {
                    console.log(err);
                }
            });
        }
    }
    korEmpPasswd.addEventListener('focus', chkEmail);

    function frmCheck() {
        let korEmpPasswd = document.querySelector(".korEmpPasswd");
        let korEmpPasswd2 = document.querySelector(".korEmpPasswd2");
        let korEmpName = document.querySelector(".korEmpName");

        if(!korEmpPasswd.value) {
            alert("비밀번호를 입력해주세요.");
            korEmpPasswd.focus();
            return false;
        }
        if(!korEmpPasswd2.value) {
            alert("비밀번호 확인을 입력해주세요.");
            korEmpPasswd2.focus();
            return false;
        }
        if (korEmpPasswd.value != korEmpPasswd2.value) {
            alert("비밀번호가 일치하지 않습니다. 비밀번호를 다시 입력해주세요.");
            korEmpPasswd.value = "";
            korEmpPasswd2.value = "";
            korEmpPasswd.focus();
            return false;
        }
        if(!korEmpName.value) {
            alert("이름을 입력해주세요.");
            korEmpName.focus();
            return false;
        }

        let selDept = document.querySelector("#selDept");
        let selDeptValue = selDept.options[selDept.selectedIndex].value;

        if(selDeptValue == 0) {
            alert("부서를 선택하세요.");
            selDept.focus();
            return false;
        }

        let selPos = document.querySelector("#selPos");
        let selPosValue = selPos.options[selPos.selectedIndex].value;

        if(selPosValue == 0) {
            alert("직책을 선택하세요.");
            selPos.focus();
            return false;
        }

        let obj = {
            korEmpEmail : $(".korEmpEmail").val(),
            korEmpPasswd: $(".korEmpPasswd").val(),
            korEmpName  : $(".korEmpName").val(),
            korEmpGender: $("input[type='radio']:checked").val(),
            korEmpDept  : $(".korEmpDept").val(),
            korEmpPos   : $(".korEmpPos").val()
        };

        console.log(obj);

        $.ajax({
            type :"post",
            url : "/main/register",
            dataType: "json",
            data : obj,
            success: function(res){
                if(res.msg == "success") {
                    alert("사원 정보가 등록되었습니다.\n관리자 접속 승인 후 이용 가능합니다.");
                    // 승인 여부가 Y -> 관리자 페이지, N -> main 이동
                    location.href = "/home";
                }
            },
            error: function(err) {
                console.log(err)
            }
        });
    }
    document.querySelector("#reg").addEventListener('click', frmCheck);