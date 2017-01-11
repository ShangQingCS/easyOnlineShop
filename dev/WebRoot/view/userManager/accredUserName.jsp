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
			formatterStr += "<a href='#' onclick='identityStatus(\""+rec.id+"\",\""+rec.trueName+"\",\"1\"); return false;'>通过审核</a>&nbsp;";
			formatterStr += "<a href='#' onclick='identityStatus(\""+rec.id+"\",\""+rec.trueName+"\",\"0\"); return false;'>不通过审核</a>&nbsp;";
			return formatterStr;
		}
		
		var formatterFlag = function(value,rec) {
			//身份状态0未认证1申请中2已认证
			if(value=="1") {
				return "<span style='color: green;'>申请中</span>";
			} else if(value=="0"){
				return "<span style='color: red;'>未认证</span>";
			}else if(value=="2"){
				return "<span style='color: green;'>已认证</span>";
			}else{
				return "";
			}
		}
		
		
		
		//通过审核
		var identityStatus = function(id,trueName,flag) {
			if(flag=="1"){
				window.confirm("提示","确定    "+trueName+"   通过审核?",function(r){
					if(r){
						$.ajax({
							url: "${basePath }/view/userManager/userManager!accredUserName.action",
							cache: false,
							dataType:"json",
							data: "id="+id+"&tag=1",
							success: function(json){
								alert(trueName+"审核通过！");
								queryUserManager();
							}
						});
					}
				});
			}else{
				window.confirm("提示","确定    "+trueName+"   不通过审核?",function(r){
					if(r){
						$.ajax({
							url: "${basePath }/view/userManager/userManager!accredUserName.action",
							cache: false,
							dataType:"json",
							data: "id="+id+"&tag=0",
							success: function(json){
								alert(trueName+"审核不通过！");
								queryUserManager();
							}
						});
					}
				});
			}
			
		}
		
		
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
    	</script>
		<title>会员实名认证</title>
  	</head>
  
 	<body class="easyui-layout">
 		
 		<div region="north" class="easyui-panel bgColor" collapsible="false" title="会员操作" style="display: none;">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					状态:
	  					<select id=”cc” class=”easyui-combobox” name="queryParams.identity_status">
	  						<option value = "1">全部</option>
						</select>
	  				</td>
	  			</tr>
	  		</table>
	  	</div>
 	
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userManager!queryNsUser.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- 身份状态0未认证1申请中2已认证 -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userName" width="8%">用户名</th>
						<th field="trueName" width="10%">真实姓名</th>
						<th field="identityCard" width="15%">身份证号</th>
						<th field="identityCardValidity" width="10%" formatter='formatterDeliveryTime'>有效期</th>
						<th field="identityIssuing" width="30%">发证机关</th>
						<th field="identityStatus" width="5%" formatter='formatterFlag'>状态</th>
						<th field="cz" width="10%" formatter='formatteruser'>操作</th> 
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>