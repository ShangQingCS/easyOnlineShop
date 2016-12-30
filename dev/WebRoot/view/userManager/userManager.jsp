<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.config.Config"%>
<%
String imgPathPrefix = Config.get("img.server.basepath");
request.setAttribute("imgPathPrefix",imgPathPrefix);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
    	<script type="text/javascript" charset="utf-8" src="${basePath }/js/test.js"></script>
    	<script type="text/javascript">
    		
   		$(function(){
			initPage();
		});
		
		var initPage = function() {
			queryUserManager();
		}
		
		var queryUserManager = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/userManager/userManager!queryNsUser.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		var formatteruser = function(value,rec) {
			var formatterStr = "";
			if(rec.status=="1") {
				formatterStr += "<a href='#' onclick='invalidUserstatus(\""+rec.id+"\",\""+rec.name+"\"); return false;'>设为冻结</a>&nbsp;";
			} else {
				formatterStr += "<a href='#' onclick='uninvalidUserstatus(\""+rec.id+"\",\""+rec.name+"\"); return false;'>设为非冻结</a>&nbsp;";
			}
			if(rec.flag=="1") {
				formatterStr += "<a href='#' onclick='invalidUserflag(\""+rec.id+"\",\""+rec.name+"\"); return false;'>设为注销</a>&nbsp;";
			} else {
				formatterStr += "<a href='#' onclick='uninvalidUserflag(\""+rec.id+"\",\""+rec.name+"\"); return false;'>设为非注销</a>&nbsp;";
			}
			return formatterStr;
		}
		
		
		
		var formatterFlag = function(value,rec) {
			if(value=="0") {
				return "<span style='color: red;'>注销</span>";
			} else {
				return "<span style='color: green;'>非注销</span>";
			}
		}
		
		var formatterStatus = function(value,rec) {
			if(value=="0") {
				return "<span style='color: red;'>冻结</span>";
			} else {
				return "<span style='color: green;'>非冻结</span>";
			}
		}
		
		var invalidUserstatus = function(id,name) {
			window.confirm("提示","设："+name+"为冻结?",function(r){
				if(r){
					$("#flagID").val(id);
					$("#userReson_window").window({title: "冻结原由"});
					$("#reason").val("");
					$("#userReson_window").window("open");
				}
			});
		}
		
		var operateFlag = function() {
			var reason = $.trim($("#reason").val());
			if(reason==""){
				alert("必须填写冻结原由！");
   				return;
			}
			$.ajax({ 
					type: "POST",  
					url: "${basePath }/view/userManager/userManager!updateNsUserFlagorStatus.action",  
					dataType: "json",
				  	data: "id="+$("#flagID").val()+"&tag=1&reason="+reason,
				  	success: function(json){
						if(json.message=='success'){
							alert("操作成功！");
							queryUserManager();
							$("#userReson_window").window("close");
						} else if(json.message!=null && json.message!=''){
							alert(json.message);
							$("#userReson_window").window("close");
						}
				  	}
				});
		}
		
		
		
		
		
		
		var uninvalidUserstatus = function(id,name) {
			window.confirm("提示","设："+name+"为非冻结?",function(r){
				if(r){
					$.ajax({ 
						type: "POST",  
						url: "${basePath }/view/userManager/userManager!updateNsUserFlagorStatus.action",  
						dataType: "json",
					  	data: "id="+id+"&tag=2",
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryUserManager();
							} else if(json.message!=null && json.message!=''){
								alert(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var invalidUserflag = function(id,name) {
			window.confirm("提示","设："+name+"为注销?",function(r){
				if(r){
					$("#userReson1_window").window({title: "注销原由"});
					$("#userReson1_window").window("open");	
					$("#reason1").val("");
					$("#statusID").val(id);		
				}
			});
		}
		
		var operateStatus = function() {
			 	
			var reason1 = $.trim($("#reason1").val());
			if(reason1==""){
				alert("必须填写注销原由！");
   				return;
			}
			$.ajax({ 
				type: "POST",  
				url: "${basePath }/view/userManager/userManager!updateNsUserFlagorStatus.action",  
				dataType: "json",
			  	data: "id="+$("#statusID").val()+"&tag=3&reason="+reason1,
			  	success: function(json){
					if(json.message=='success'){
						alert("操作成功！");
						queryUserManager();
						$("#userReson1_window").window("close");
					} else if(json.message!=null && json.message!=''){
						alert(json.message);
						$("#userReson1_window").window("close");
					}
			  	}
			});
		}
		
		
		var uninvalidUserflag = function(id,name) {
			window.confirm("提示","设："+name+"为非注销?",function(r){
				if(r){
					$.ajax({ 
						type: "POST",  
						url: "${basePath }/view/userManager/userManager!updateNsUserFlagorStatus.action",  
						dataType: "json",
					  	data: "id="+id+"&tag=4",
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryUserManager();
							} else if(json.message!=null && json.message!=''){
								alert(json.message);
							}
					  	}
					});
				}
			});
		}
    		
    		
    	</script>
		<title>会员操作</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="会员操作" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">会员id:<input name="queryParams.userid"  /></td>
	  				<td align="left">昵称:<input name="queryParams.nickname"  /></td>
	  				<td align="left">姓名:<input name="queryParams.name"  /></td>
	  				<td align="left">手机号 :<input name="queryParams.phone"  /></td>
	  				<td align="left">
	  					是否冻结:
	  					<select id=”cc” class=”easyui-combobox” name="queryParams.status" style=”width:200px;”>
	  						<option value = "">--请选择--</option>
							<option value = "0">冻结</option>
							<option value = "1">非冻结</option>
						</select>
	  				</td>
	  				<td align="left">
	  					是否注销:
	  					<select id=”cc” class=”easyui-combobox” name="queryParams.flag" style=”width:200px;”>
	  						<option value = "">--请选择--</option>
							<option value = "0">注销</option>
							<option value = "1">非注销</option>
						</select>
	  				</td>
	  				
	  				</tr>
	  				<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserManager(); return false;">查询</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
	  			</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userManager!queryNsUser.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- onDblClickRow:showdictionariesDetailRow -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userid" width="8%">会员id</th>
						<th field="nickname" width="10%">昵称</th>
						<th field="username" width="10%">登陆账户</th>
						<th field="name" width="10%">姓名</th>
						<th field="idcard" width="15%">身份证</th>
						<th field="status" width="5%" formatter='formatterStatus'>是否冻结</th>
						<th field="flag" width="5%" formatter='formatterFlag'>是否注销</th>
						<th field="phone" width="15%" >手机号</th>
						<th field="cz" width="20%" formatter='formatteruser'>操作</th> 
					</tr>
				</thead>
			</table>
		</div>
		
		<div title="冻结原由" id="userReson_window" modal="true" draggable="false" class="easyui-window" style="width: 400px; height: 200px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="userReson_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<td align="right" width="100">冻结原由:</td>
			  				<td >
			  					<input type="hidden" id="flagID" value="" />
			  					<textarea name="reason" id="reason" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
			  					</textarea> 
			  				</td>
		  			</table>
					<div align="center" class="tablestyle01" style="height:50px; padding-top: 3px; ">
		 				<a href="#" class="easyui-linkbutton" onclick="operateFlag(); return false;">保存</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#userReson_window').window('close'); return false;">关闭</a>
					</div>
				</form>
		</div>
		
		<div title="注销原由" id="userReson1_window" modal="true" draggable="false" class="easyui-window" style="width: 400px; height: 200px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="userReson1_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<td align="right" width="100">注销原由:</td>
			  				<td >
			  					<input type="hidden" id="statusID" value="" />
			  					<textarea name="reason1" id="reason1" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
			  					</textarea> 
			  				</td>
		  			</table>
					<div align="center" class="tablestyle01" style="height:50px; padding-top: 3px; ">
		 				<a href="#" class="easyui-linkbutton" onclick="operateStatus(); return false;">保存</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#userReson1_window').window('close'); return false;">关闭</a>
					</div>
				</form>
		</div>
		
  	</body>
</html>