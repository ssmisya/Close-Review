$(document).ready(function(){

  stu_tab = $("#stu_tab")
  $.get("/api/v1/user/all",function(data,status){
//      alert("数据: " + data + "\n状态: " + status);
      if(status=='success'){
          students = data;
          alert("共" + students.length + "人");

          $.each(students,function(idx,obj){
          						stu_tab.append('<tr class= "str" >' +
          								'<td>' + obj.id + '</td>' +
          								'<td>' + obj.name + '</td>' +
          								'<td>' + obj.age + '</td>' +
          								'<td>' + obj.gender + '</td>' +
          								'</tr>');
          					});

      }

	});

});
