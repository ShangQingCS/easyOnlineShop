<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.config.Config"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
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
				url: "${basePath }/view/userManager/userManager!queryNsUserCount.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		var formatterFlag = function(value,rec) {
			if(value=="1") {
				return "<span style='color: green;'>正常</span>";
			} else if(value=="0"){
				return "<span style='color: red;'>注销</span>";
			}else if(value=="2"){
				return "<span style='color: red;'>冻结</span>";
			}else{
				return "";
			}
		}
		
		
		//表单验证
		var checkForm = function() {
			var check = $("#ff").form("validate");
			if(!check) {
				return false;
			}
			return true;
		}
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
		//导出Excel
	    function exportExcel(obj) {
	        var href = "${basePath }/view/userManager/exportExcel!download.action";
	        obj.href = href;
	        //window.history.back();
		}
    		
    	</script>
		<title>会员账户统计信息</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="会员账户统计信息" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">注册日期  
	  					从<input name="queryParams.startCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					到
	  					<input name="queryParams.endCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  				</td>
	  				<td align="left">
	  					认证状态:
	  					<select id="identity_status" class=”easyui-combobox” name="queryParams.identity_status">
	  						<option value = "">全部</option>
							<option value = "0">未认证</option>
							<option value = "1">申请中</option>
							<option value = "2">已认证</option>
						</select>
	  				</td>
	  				<td align="left">
	  					用户状态:
	  					<select id="user_status" class=”easyui-combobox” name="queryParams.user_status">
	  						<option value = "">全部</option>
							<option value = "0">注销</option>
							<option value = "1">正常</option>
							<option value = "2">冻结</option>
						</select>
	  				</td>
	  				
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserManager(); return false;">查询统计</a>
				 		<a href="#" class="easyui-linkbutton" onclick="exportExcel(this);">导出报表数据</a> 
			 		</td>
	  			</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userManager!queryNsUserCount.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!--identityStatus-->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userName" width="9%">用户名</th>
						<th field="trueName" width="10%">真实姓名</th>
						<th field="userSex" width="5%">性别</th>
						<th field="userPhone" width="10%">手机号</th>
						<th field="identityCard" width="15%">身份证号</th>
						<th field="createTime" width="20%" formatter='formatterDeliveryTime'>创建时间</th>
						<th field="cz" width="5%">充值金额</th>
						<th field="fhje" width="5%">分红金额</th>
						<th field="xf" width="5%">消费金额</th>
						<th field="jf" width="5%">积分</th>
						<th field="dqye" width="5%">当前余额</th>
						<th field="userStatus" width="5%" formatter='formatterFlag'>状态</th>
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>