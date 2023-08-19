    $(function() {
        $.ajax({
            type: "post",
            url: "/getDept",
            dataType: "json",
            success: function(res) {
                let output = "<option>Choose Dept.</option>";
                res.forEach((r) => {
                    output += `<option value=${r.grpDeptCode}>${r.grpDeptName}</option>`;
                });
                   document.querySelector("#selDept").innerHTML = output;
            },
            error: function(err) {
                console.log(err);
            }
        });
    });

    function getPos() {
        let selDept = document.querySelector("#selDept");
        let selDeptValue = selDept.options[selDept.selectedIndex].value;

        $.ajax({
            type: "post",
            url: "/getPos",
            dataType: "json",
            data: { selDeptValue },
            success : function(res) {
                let output = "<option>Choose Pos.</option>"
                res.forEach((r) => {
                    output += `<option value=${r.grpPosCode}>${r.grpPosName}</option>`
                });
                console.log(output);
                $("#selPos").html(output);
            },
            error: function(err) {
                console.log(err);
            }
        });
    }

    let grpEmpName = document.querySelector(".grpEmpName");
    let grpEmpUserid = document.querySelector(".grpEmpUserid");

    function chkUserid() {
        if( !grpEmpUserid.value ) {
            $("#chkPage").html("아이디를 입력해주세요.")
            grpEmpUserid.focus();
            return false;
        }else {
            $.ajax({
                type : "post",
                url : "/register/idCheck",
                dataType : "json",
                data : {userid : grpEmpUserid.value},
                success : function(res) {
                    if(res > 0){
                        $("#chkPage").html("사용할 수 없는 아이디입니다.");
                        grpEmpUserid.value = "";
                        grpEmpUserid.focus();
                    }else{
                        $("#chkPage").html("사용할 수 있는 아이디입니다.");
                    };
                },
                error: function(err) {
                    console.log(err);
                }
            });
        }
    }

    grpEmpName.addEventListener('focus', chkUserid);

    //----------------------------------------------------------------------

    function frmCheck() {
        let grpEmpUserid = document.querySelector(".grpEmpUserid");

        let grpEmpName = document.querySelector(".grpEmpName");
        let grpEmpPnum = document.querySelector(".grpEmpPnum");
        let grpEmpBirth = document.querySelector(".grpEmpBirth");
        let grpEmpPasswd = document.querySelector(".grpEmpPasswd");
        let grpEmpPasswd2 = document.querySelector(".grpEmpPasswd2");
        let selDept = document.querySelector("#selDept");
        let grpEmpEmail = document.querySelector(".grpEmpEmail");

        if( !grpEmpUserid.value ) {
            alert("아이디를 입력해주세요.");
            grpEmpUserid.value = "";
            grpEmpUserid.focus();
            return false;
        }
        if( !grpEmpName.value ) {
            alert("이름을 입력해주세요.");
            grpEmpName.value = "";
            grpEmpName.focus();
            return false;
        }
        if( !grpEmpPasswd.value ) {
            alert("비밀번호를 입력해주세요.");
            grpEmpPasswd.value = "";
            grpEmpPasswd.focus();
            return false;
        }
        if( !grpEmpPasswd2.value ) {
            alert("비밀번호를 한번 더 입력해주세요.");
            grpEmpPasswd2.value = "";
            grpEmpPasswd2.focus();
            return false;
        }

        if( grpEmpBirth.value ) {
            if( grpEmpBirth.value < 8 || grpEmpBirth > 8  ){
                alert("생년월일을 8자리로 다시 입력해주세요");
                grpEmpBirth.value = "";
                grpEmpBirth.focus();
                return false;
            }
        }else{
            alert("생년월일을 입력해주세요.");
            grpEmpBirth.value = "";
            grpEmpBirth.focus();
            return false;
        }

        if( !grpEmpPnum.value ) {
            alert("전화번호를 입력해주세요.");
            grpEmpPnum.value = "";
            grpEmpPnum.focus();
            return false;
        }
    }

    document.querySelector("#reg").addEventListener('click', frmCheck);

//--------------------------------------------------------------------
    let selDept = document.querySelector("#selDept");


    function chkEmail() {
        let selDeptValue = selDept.options[selDept.selectedIndex].value;
        let pattern =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([\-.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

            if( grpEmpEmail.value.match(pattern) == null ) {
                $("#chkEmail").html("이메일 형식에 맞게 작성해 주세요.");
                grpEmpEmail.value = "";
                grpEmpEmail.focus();
                return false;
            }
        if(grpEmpEmail.value) {
             $.ajax({
                type : "post",
                url : "/emailCheck",
                dataType : "json",
                data : {email : grpEmpEmail.value},
                success : function(res) {
                    if(res > 0 ){
                        $("#chkEmail").html("사용할 수 없는 이메일입니다.");
                        grpEmpEmail.value = "";
                        grpEmpEmail.focus();
                    }else{
                        $("#chkEmail").html("사용할 수 있는 이메일입니다.");
                    };
                },
                error: function(err) {
                    console.log(err);
                }
            });
        }else{
            $("#chkEmail").html("이메일을 입력해주세요.");
            grpEmpEmail.value = "";
            grpEmpEmail.focus();
            return false;
        }
    }

    selDept.addEventListener('focus', chkEmail);

    function frmCheck() {

        let selDeptValue = selDept.options[selDept.selectedIndex].value;

        let selPos = document.querySelector("#selPos");
        let selPosValue = selPos.options[selPos.selectedIndex].value;


         if(selDeptValue == 0){
            alert("부서를 선택하세요");
            selDept.focus();
            return false;
         }

        if(selPosValue == 0){
            alert("직책을 선택하세요");
            selPos.focus();
            return false;
        }

        if ( grpEmpPasswd.value != grpEmpPasswd2.value ) {
            alert("비밀번호가 일치하지 않습니다.\n다시 입력해 주세요.");
            grpEmpPasswd.value = "";
            grpEmpPasswd2.value = "";
            grpEmpPasswd.focus();
            return false;
        }

        let obj = {
            grpEmpUserid : $(".grpEmpUserid ").val(),
            grpEmpName  : $(".grpEmpName").val(),
            grpEmpPasswd: $(".grpEmpPasswd").val(),
            grpEmpBirth: $(".grpEmpBirth").val(),
            grpEmpPnum : $(".grpEmpPnum").val(),
            grpEmpEmail : $(".grpEmpEmail").val(),
            grpEmpDept  : $(".grpEmpDept").val(),
            grpEmpPos   : $(".grpEmpPos").val(),
            grpEmpAuth : $(".grpEmpAuth").val()
        };

        console.log(obj);

        $.ajax({
            type : "post",
            url : "/register",
            dataType: "json",
            data: obj,
            success: function(res) {
                if( res.msg == "success"){
                     alert("사원정보가 등록 되었습니다.\n관리자 승인 후 사용가능합니다.");
                     location.href = "/main";
                }
            },
            error: function(err) {
                console.log(err);
            }
        });
    };

    document.querySelector("#reg").addEventListener('click', frmCheck);