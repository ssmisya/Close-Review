/**
 * 
 */
$(document).ready(function(){
	var groupTableShop = $('#shopTable');
	$('#skey').click(function search(){
		var key = $('#keyword').val();
		var groupTable = $('#searchTable');
		$.post('book_search.action',
				{
			        keyword:key,
			        order:'ajax'
			    },
				function susccess(date){
					alert(date);
					var json =JSON.parse(date);
					$('.str').remove();
					groupTable.append('<tr class= "str" >' +
							'<td>' + '图书号' + '</td>' + 
							'<td>' + '书名' + '</td>' +
							'<td>' + '作者'  + '</td>' +
							'<td>' + '价格' + '</td>' +
							'<td>' + '出版社' + '</td>' +
							'<td>' + '库存' + '</td>' +
							'<td>' + '操作' + '</td>' +
							'</tr>');
					$.each(json,function(idx,obj){
						groupTable.append('<tr class= "str" >' +
								'<td id="' + idx + '_id' + '">' + obj.id + '</td>' + 
								'<td id="' + idx + '_name' + '">' + obj.name + '</td>' +
								'<td id="' + idx + '_author' + '">' + obj.author + '</td>' +
								'<td id="' + idx + '_cost' + '">' + obj.cost + '</td>' +
								'<td id="' + idx + '_press' + '">' + obj.press + '</td>' +
								'<td id="' + idx + '_stock' + '">' + obj.stock + '</td>' +
								'<td>' + '<input type="button" value="加入购物清单" class="add" id="'+idx+'"/>' + '</td>' +
								'</tr>');
					});
				}
				);
	});
	
	//每个添加购物车的触发事件
	$('#searchTable').on('click','.add',function(){
		var cid = $(this).attr("id");
		groupTableShop.append('<tr id="'+ cid + '_tr' + '">' +
				'<td>' + $('#'+cid+'_id').text() + '</td>' + 
				'<td>' + $('#'+cid+'_name').text()  + '</td>' +
				'<td>' + $('#'+cid+'_author').text()  + '</td>' +
				'<td>' + $('#'+cid+'_cost').text()  + '</td>' + 
				'<td>' + $('#'+cid+'_press').text()  + '</td>' +
				'<td>' + $('#'+cid+'_stock').text()  + '</td>' +
				'<td>' +'<input type="text" placeholder="请输入购买数量" class="num" id="' + cid + '_num' + '"/>'+ '</td>' +
				'<td>' + '<input type="button" value="取消选定" class="delete" id="'+cid+'"/>'+ '</td>' +
				'</tr>');
		});
	
	//购物车的去除触发事件
	$('#shopTable').on('click','.delete',function(){
		var did = $(this).attr("id");
		$('#'+ did + '_tr').remove();
		});
	
	$('#submit_record').click(function(){
		alert('确定提交订单？');
		var trs =$('#shopTable tr');//获得tr数组
	    var titles = trs[0].getElementsByTagName("td");  //获得表头td数组
	    var json = "";
	    for(var i = 1; i < trs.length; i++) {
	      var tds = trs[i].getElementsByTagName("td");  
	      var num = tds[6].children[0].value;
	      json = json + '{id:' + tds[0].innerHTML+ ',num:' + num + ',';
	      json = json.substring(0, json.length - 1) + "},";
	    }
	    json = "[" + json.substring(0, json.length - 1) + "]";
	    $.post('addRecord.action',
	    		{jsonStr:json},
	    		function success(date){
	    			alert('购买成功！');
	            }
	    		);
	});
});
