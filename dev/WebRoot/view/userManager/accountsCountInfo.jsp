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
			queryUserPurseListCount();
		}
		
		var queryUserPurseListCount = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/userManager/userPurse!queryUserPurseListCount.action",
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
		
		var formatterpurseType = function(value,rec) {
			if(value=="1") {
				return "<span style='color: green;'>分红</span>";
			} else if(value=="0"){
				return "<span style='color: green;'>钱包</span>";
			}else if(value=="2"){
				return "<span style='color: green;'>积分</span>";
			}else{
				return "";
			}
		}
		
		var formattertradeType = function(value,rec) {
			if(value=="1") {
				return "<span style='color: green;'>支付宝</span>";
			} else if(value=="0"){
				return "<span style='color: green;'>系统操作</span>";
			}else if(value=="2"){
				return "<span style='color: green;'>微信</span>";
			}else{
				return "";
			}
		}
		
		var formatteroptionType = function(value,rec) {
			if(value=="1") {
				return "<span style='color: red;'>减少</span>";
			} else if(value=="0"){
				return "<span style='color: green;'>增加</span>";
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
	        var href = "${basePath }/view/userManager/acountExportExcel!accountsDownload.action";
	        obj.href = href;
		}
    		
    	</script>
		<title>会员账户统计信息</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false"  style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">充值日期  
	  					从<input name="queryParams.startCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					到
	  					<input name="queryParams.endCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  				</td>
	  				<td align="left">
	  					账目类型:
	  					<select id="purse_type" class=”easyui-combobox” name="queryParams.purse_type">
	  						<option value = "">全部</option>
							<option value = "0">钱包</option>
							<option value = "1">分红</option>
							<option value = "2">积分</option>
						</select>
	  				</td>
	  				<td align="left">
	  					支付方式:
	  					<select id="trade_type" class=”easyui-combobox” name="queryParams.trade_type">
	  						<option value = "">全部</option>
							<option value = "0">系统操作</option>
							<option value = "1">支付宝</option>
							<option value = "2">微信</option>
						</select>
	  				</td>
	  				<td align="left">
	  					操作类型:
	  					<select id="option_type" class=”easyui-combobox” name="queryParams.option_type">
	  						<option value = "">全部</option>
							<option value = "0">增加</option>
							<option value = "1">减少</option>
						</select>
	  				</td>
	  				
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserPurseListCount(); return false;">查询统计</a>
				 		<a href="#" class="easyui-linkbutton" onclick="exportExcel(this);">导出报表数据</a> 
			 		</td>
	  			</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userPurse!queryUserPurseListCount.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!--identityStatus-->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userName" width="10%">用户名</th>
						<th field="trueName" width="10%">真实姓名</th>
						<th field="purseType" width="10%" formatter='formatterpurseType'>账目类型</th>
						<th field="tradeType" width="15%" formatter='formattertradeType'>支付方式</th>
						<th field="optionType" width="10%" formatter='formatteroptionType'>操作类型</th>
						<th field="tradeAmount" width="10%">金额</th>
						<th field="optionTime" width="20%" formatter='formatterDeliveryTime'>时间</th>
						<th field="userStatus" width="10%" formatter='formatterFlag'>状态</th>
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>