        function chkUpdate() {

            let grpEmpName = document.querySelector(".grpEmpName");
            let grpEmpPasswd = document.querySelector(".grpEmpPasswd");
            let grpEmpPasswd2 = document.querySelector(".grpEmpPasswd2");
            let grpEmpUserid = document.querySelector(".grpEmpUserid");
            let grpEmpEmail = document.querySelector(".grpEmpEmail");
            let grpEmpPnum = document.querySelector(".grpEmpPnum");
            let grpEmpTel = document.querySelector(".grpEmpTel");
            let grpEmpDept = document.querySelector(".grpEmpDept");
            let grpEmpPos = document.querySelector(".grpEmpPos");
            let grpEmpBirth = document.querySelector(".grpEmpBirth");
            let grpEmpStartdate = document.querySelector(".grpEmpStartdate");
            let grpEmpModified = document.querySelector(".grpEmpModified");
            let grpEmpId = document.querySelector(".grpEmpId");

                if( !grpEmpName.value ){
                    alert("이름을 입력해 주세요.");
                    grpEmpName.focus();
                    return false;
                }
                if( !grpEmpPasswd2.value ){
                    alert("비밀번호를 한번 더 입력해 주세요");
                    grpEmpPasswd2.focus();
                    return false;
                }
                if( grpEmpPasswd.value != grpEmpPasswd2.value ){
                    alert("비밀번호가 일치하지 않습니다. 비밀번호를 다시 입력해 주세요.");
                    grpEmpPasswd2.value = "";
                    grpEmpPasswd2.focus();
                    return false;
                }

                if( !grpEmpEmail.value ) {
                    alert("이메일을 입력해 주세요.");
                    grpEmpEmail.focus();
                    return false;

                }else{
                    $.ajax({
                        type : "post",
                        url : "/main/mypage/update/emailCheck",
                        dataType : "json",
                        data : {email : grpEmpEmail.value},
                        success : function(res) {
                            if( res.message == "ok" ){
                                console.log("이메일정보가 변경되지 않았습니다.");
                            }else{
                                alert("사용하실 수 없는 이메일입니다.");
                                grpEmpEmail.value = "";
                                grpEmpEmail.focus();
                            }
                        },
                        error: function(err) {
                            console.log(err);
                        }
                    });
                }

            let obj = {
                  "grpEmpName" : grpEmpName.value,
                  "grpEmpUserid" : grpEmpUserid.value,
                  "grpEmpPasswd" : grpEmpPasswd.value,
                  "grpEmpEmail" : grpEmpEmail.value,
                  "grpEmpPnum" : grpEmpPnum.value,
                  "grpEmpTel" : grpEmpTel.value,
                  "grpEmpBirth" : grpEmpBirth.value,
                  "grpEmpId" : grpEmpId.value
            };

            $.ajax({
                type: "post",
                url: "/main/mypage/update",
                dataType: "json",
                data: obj,
                success: function(res) {
                    if(res.msg == "success") {
                        alert("사원 정보가 수정되었습니다.\n다시 로그인하세요.");
                        location.href = "/login";
                    }
                }
            });
        }