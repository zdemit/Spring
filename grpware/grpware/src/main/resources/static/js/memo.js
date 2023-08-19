    //팝업 띄우기
    function openPop() {
        document.getElementById("popup_layer").style.display = "block";

<!--        $.ajax({-->
<!--                type :"post",-->
<!--                url : "/main",-->
<!--                dataType: "json",-->
<!--                data : {grpNum: grpNum, grpContent: grpContent},-->
<!--                success: function(res){-->
<!--                    if(res.msg == "success") {-->
<!--                        alert("메모가 추가되었습니다.");-->
<!--                        location.href = "/index";-->
<!--                    }-->
<!--                },-->
<!--                error: function(err) {-->
<!--                console.log(err)-->
<!--                }-->
<!--            });-->

    }

    function openPopEdit() {
        document.getElementById("popup_edit").style.display = "block";
    }

    //팝업 닫기
    function closePop() {
        document.getElementById("popup_layer").style.display = "none";
        document.getElementById("popup_edit").style.display = "none";
    }

//삭제
<!--    function deleteMemo(grpNum) {-->
<!--      if(confirm("메모를 삭제하시겠습니까?")) {-->
<!--        $.ajax({-->
<!--          type: "get",-->
<!--          url: "/deleteMemo",-->
<!--          dataType: "json",-->
<!--          data: {grpNum},-->
<!--          success: function(res) {-->
<!--            if(res.msg == "success") {-->
<!--              alert("메모가 삭제되었습니다.");-->
<!--              location.href = "/index";-->
<!--            }-->
<!--          },-->
<!--          error: function(err) {-->
<!--            console.log(err);-->
<!--          }-->
<!--        });-->
<!--      }else {-->
<!--        return false;-->
<!--      }-->
<!--    }-->