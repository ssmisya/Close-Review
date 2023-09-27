$(document).ready(function(){
	$('#add_submit').click(function(event){
	    event.preventDefault();
	    var name = $("#name").val();
	    var age = $("#age").val();
	    var gender = $("#gender").val();
	    var student = {};
	    student.name = name;
	    student.gender = gender;
	    student.age = parseInt(age);

	    if (name != "" && age!="" && gender != ""){
	            stu_json = JSON.stringify(student);
	            console.log(stu_json)
//	            alert(stu_json)
                $.ajax({
                    type:"POST",
                    url:"/api/v1/user",
                    data: stu_json,
                    contentType: "application/json",
                    success:function(s){
                        alert("插入成功！")
                    },
                    error:function(e){
                        console.log(e);
                        alert("插入失败请重试！")
                    }

                });

	    	    $.post('api/v1/user',
                    stu_json,
                	function success(date){
                	    alert(date);
                    });
	    }else{
	        alert('关键信息缺失，请补充！')
	    }
	});




});
