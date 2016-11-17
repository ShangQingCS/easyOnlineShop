<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
    	<script type="text/javascript" charset="utf-8" src="${basePath }/js/test.js"></script>
		<title>活动管理</title>
  	</head>
  	
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="评论列表" style="height:130px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">商品名称:</td>
	  				<td align="left"><input name="queryParams.gname"/></td>
	  				<td align="left">类别:</td>
	  				<td align="left"><input class="easyui-combobox" name="queryParams.category" id="inp_category" data-options="editable: false"/></td>
	  				<td align="left">类型:</td>
	  				<td align="left"><input class="easyui-combobox" name="queryParams.kind" id="inp_kind" data-options="editable: false"/></td>
	  				<td align="left">品牌:</td>
	  				<td align="left"><input class="easyui-combobox" name="queryParams.brand" id="inp_brand" data-options="editable: false"/></td>
	  			</tr>
	  			<tr>
	  				<td align="left">商品编号:</td>
	  				<td align="left"><input name="queryParams.goodsid"/></td>
	  				<td align="left">用户id:</td>
	  				<td align="left"><input name="queryParams.userid" /></td>
	  				<td align="left">评论关键字:</td>
	  				<td align="left" colspan="3"><input name="queryParams.comment" style="width: 90%;"/></td>
	  			</tr>
	  		</table>
	  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryEvents(); return false;">查询</a>
	  					<a href="#" class="easyui-linkbutton" onclick="showAddEventsWin(); return false;">新增</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 	</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="eventsManager!queryEvents.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options="onDblClickRow:showEventsDetailRow">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="name" width="29%">活动名称</th>
						<th field="startTime" width="10%" formatter='formatterDate'>活动开始时间</th>
						<th field="endTime" width="10%" formatter='formatterDate'>活动结束时间</th>
						<!-- <th field="brandName" width="5%">品牌</th>
						<th field="createTime" width="10%" formatter='formatterDatetime'>评论时间</th>
						<th field="comment" width="20%">评论内容</th>
						<th field="ishidden" width="5%" formatter='formatterIsHidden'>是否匿名</th>
						<th field="score" width="5%">评分</th>-->
						<th field="isuse" width="10%" formatter='formatterIsuse'>是否启用</th>
						<th field="cz" width="10%" formatter='formatterEventsAction'>操作</th> 
					</tr>
				</thead>
			</table>
			
			<div title="新增活动" id="events_add_window" modal="true" draggable="false" class="easyui-window" style="width: 800px; height: 600px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="events_add_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<td align="right" width="100">活动名称:</td>
			  				<td colspan="3">
			  					<input type="hidden" name="eve.id" id="id" /> 
			  					<input name="eve.name" id="name" style="width: 100%;" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">开始时间:</td>
			  				<td>
	  							<input name="eve.startTime" id="startTime" class="easyui-datebox" editable="false" required="true" style="width: 100px;"/>
			  				</td>
			  				<td align="right">结束时间:</td>
			  				<td>
			  					<input name="eve.endTime" id="endTime" class="easyui-datebox" editable="false" required="true" style="width: 100px;"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">图片预览:</td>
			  				<td colspan="3"><img id="minpicturePr" width="500" height="100" /> </td>
			  			</tr>
			  			<tr>
			  				<td align="right">小图:</td>
			  				<td colspan="3">
			  					<input type="file" style='width: 200px;' name="minpicture" id="minpicture" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">图片预览:</td>
			  				<td colspan="3"><img id="picturePr" width="500" height="100" /> </td>
			  			</tr>
			  			<tr>
			  				<td align="right">大图:</td>
			  				<td colspan="3">
			  					<input type="file" style='width: 200px;' name="picture" id="picture" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td align="right">活动介绍:</td>
			  				<td colspan="3">
			  					<textarea name="eve.memo" id="memo" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
			  					</textarea>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">是否启用:</td>
			  				<td colspan="3">
			  					<select name="eve.isuse" id="isuse">
			  						<option value="1">否</option>
			  						<option value="0">是</option>
			  					</select> 
			  				</td>
			  			</tr>
		  			</table>
					<div align="center" class="tablestyle01" style="height:50px; padding-top: 3px; ">
		 				<a href="#" class="easyui-linkbutton" onclick="eventsSave(); return false;">保存</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#events_add_window').window('close'); return false;">关闭</a>
					</div>
				</form>
			</div>
		</div>
  	</body>
  
  	<script type="text/javascript">
		$(function(){
			initPage();
		});
		
		var initPage = function() {
			$("#minpicture").uploadPreview({ Img: "minpicturePr", Width: 500, Height: 100 });
			$("#picture").uploadPreview({ Img: "picturePr", Width: 500, Height: 100 });
		}
		
		var queryEvents = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var formatterEventsAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='showEditEventsWin(\""+rec.id+"\"); return false;'>查看和编辑</a>&nbsp;"
			if(rec.isuse=="0") {
				formatterStr += "<a href='#' onclick='setNotIsuse(\""+rec.id+"\",\""+rec.name+"\"); return false;'>不启用</a>&nbsp;";
			} else if(rec.isuse=="1") {
				formatterStr += "<a href='#' onclick='setIsuse(\""+rec.id+"\",\""+rec.name+"\"); return false;'>启用</a>&nbsp;";
			}
			formatterStr += "<a href='#' onclick='deleteEvents(\""+rec.id+"\",\""+rec.name+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		var showAddEventsWin = function() {
			formReset("events_add_table");
			$("#events_add_window").window({title: "新增活动"});
			$("#events_add_window").window("open");
		}
		
		var eventsSave = function() {
			// 调用 form  插件的 'submit' 方法来提交 form   
			$('#ff').form('submit', {
				url: "${basePath }/view/eventsManager/eventsManager!save.action",
				onSubmit: function() {
					return checkForm();
				},
	      		success:function(data) {
	      			alert("操作成功！");
	          		clearForm();
	          		queryEvents();
					$("#events_add_window").window("close");
	     		}
	 		}); 
		}
		
		//表单验证
		var checkForm = function() {
			var check = $('#ff').form('validate');
			if(!check) {
				return false;
			}
			
			var st = $("#startTime").datebox("getValue");
			var et = $("#endTime").datebox("getValue");
			if(parseDate(st)>parseDate(et)) {
				alert("开始时间不能大于结束时间！");
				return false;
			}
			
			return true;
		}
		
		var clearForm = function() {
			$('#ff').form('clear');
		}
		
		var showEventsDetailRow = function() {
			var row = $('#tab_list').datagrid('getSelected');
			if (row) {
				showEditEventsWin(row.id);
			}
		}
		
		var showEditEventsWin = function(id) {
			$.ajax( {
				type: "POST",	
				url: "${basePath }/view/eventsManager/eventsManager!loadEvents.action",	
				data: {"eve.id": id}, dataType: "json",
				success: function(json){
					$("#id").val(json.eve.id);
					$("#name").val(json.eve.name);
					$("#memo").val(json.eve.memo);
					$("#isuse").val(json.eve.isuse);
					$("#minpicturePr").attr("src", json.eve.minpicture);
					$("#picturePr").attr("src", json.eve.picture);
					$("#startTime").datebox("setValue", formatterDate(json.eve.startTime,null,null));
					$("#endTime").datebox("setValue", formatterDate(json.eve.endTime,null,null));
					$("#minpicture").validatebox({required:false});
					$("#picture").validatebox({required:false});
					$("#events_add_window").window({title: "查看和编辑活动"});
					$("#events_add_window").window("open");
				},	
				error: function(e) {alert("查询异常");}
			});
		}
		
		var setIsuse = function(id, name) {
			window.confirm("提示","启用："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/eventsManager/eventsManager!isuse.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryEvents();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var setNotIsuse = function(id, name) {
			window.confirm("提示","不启用："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/eventsManager/eventsManager!notIsuse.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryEvents();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var deleteEvents = function(id, name) {
			window.confirm("提示","确定删除："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/eventsManager/eventsManager!delete.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryEvents();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var formatterIsuse = function(value,rec) {
			if(value=="0")
				return "是"; 
			return "否";
		}
	</script>
</html>