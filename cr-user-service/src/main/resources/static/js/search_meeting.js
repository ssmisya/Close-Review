$(document).ready(function(){
  exist_list = [];
  stu_tab = $("#stu_tab")
    $('#search').click(function(event){
        event.preventDefault();
        uid = $("#xh").val();
        if ($.inArray(uid, exist_list)!=-1) {
            alert('查询结果已存在')
        }else{
                  console.log(uid)
                  $.ajax({
                           type:"GET",
                           url:"/api/v1/user/" + uid,
                           success:function(data,status){
                           if(status == 'success'){
                                         exist_list.push(uid);
                                         obj = data;
                                         stu_tab.append('<tr class= "str" id="tr_' +  obj.id + '" >' +
                                                         '<td>' + obj.id + '</td>' +
                                                          '<td>' + '<input type="text" value="' + obj.name + '" id="name_'+ obj.id + '"/>' + '</td>' +
                                                          '<td>' + '<input type="text" value="' + obj.age + '" id="age_'+ obj.id + '"/>' + '</td>' +
                                                          '<td>' + '<input type="text" value="' + obj.gender + '" id="gender_' + obj.id + '"/>' + '</td>' +
                                                          '<td>' + '<input type="button" value="修改" class="add" id="update_' + obj.id + '"/>' + '</td>' +
                                                          '<td>' + '<input type="button" value="删除" class="add" id="delete_' + obj.id + '"/>' + '</td>' +
                                                          '</tr>');

                                         $("#update_" + obj.id).click(function(event){
                                             event.preventDefault();
                                             student = {}
                                             student.name = $("#name_" + obj.id).val();
                                             student.age = parseInt($("#age_" + obj.id).val());
                                             student.gender = $("#gender_" + obj.id).val();

                                             stu_json = JSON.stringify(student)
                                             console.log(stu_json);
                                             $.ajax({
                                                 type:"PUT",
                                                 url:"api/v1/user/" + obj.id,
                                                 data: stu_json,
                                                 contentType:"application/json",
                                                 success:function(data, status){
                                                     alert("学生信息修改成功!请重新查询");
                                                     $("#tr_" + obj.id).remove();
                                                     exist_list = $.grep(exist_list, function(list_obj){
                                                             return list_obj != obj.id;});
                                                 },
                                                 error:function(e){
                                                     alert("学生信息修改失败");
                                                 }
                                             })
                                         });

                                         $("#delete_" + obj.id).click(function(event){
                                             event.preventDefault();
                                             $.ajax({
                                                 type: "DELETE",
                                                 url:"/api/v1/user/" + obj.id,
                                                 success:function(data,status){
                                                     if(status=="success"){
                                                         alert("学号为：" + obj.id + "的学生信息已删除");
                                                         // 删除表中的数据以及数组中的数据
                                                         $("#tr_" + obj.id).remove();
                                                         exist_list = $.grep(exist_list, function(list_obj){
                                                             return list_obj != obj.id;
                                                         });
                                                     }else{
                                                         alert("学生信息删除失败");
                                                     }

                                                 }

                                             });

                                         });

                           }else{
                                     alert("查询失败！");
                                 }
                           },
                           error:function(data,status){
                                           alert("无该学号的学生！");
                                         }


                  });

             }

    });


});


