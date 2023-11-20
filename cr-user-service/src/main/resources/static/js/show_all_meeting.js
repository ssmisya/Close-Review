$(document).ready(function(){

  tab = $("#meetTable")
  tab.append('<tr class= "str" >' +
            								'<td>' + 深度学习引力力波探测 + '</td>' +
            								'<td>' + 物理 + '</td>' +
            								'<td>' + 2023/11/12 + '</td>' +
            								'<td>' + 2023/11/1 + '</td>' +
            								'<td>' + 2023/11/15 + '</td>' +
            								'<td>' + '<input type="button" value="邀请审稿人" class="add" id="invite'  + '"/>' + '</td>' +

            								'</tr>');

//  $.get("/api/v1/user/all",function(data,status){
////      alert("数据: " + data + "\n状态: " + status);
//      if(status=='success'){
//          students = data;
//          alert("共" + students.length + "人");
//
//          $.each(students,function(idx,obj){
//          						stu_tab.append('<tr class= "str" >' +
//          								'<td>' + obj.id + '</td>' +
//          								'<td>' + obj.name + '</td>' +
//          								'<td>' + obj.age + '</td>' +
//          								'<td>' + obj.gender + '</td>' +
//          								'</tr>');
//          					});
//
//      }
//
//	});

});
