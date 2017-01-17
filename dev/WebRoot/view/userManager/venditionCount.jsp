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
		<title>销售统计报表</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="销售统计报表" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">销售日期  
	  					从<input name="queryParams.startCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					到
	  					<input name="queryParams.endCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  				</td>
	  				<td align="left">
	  					产品名称:
	  					<select id="purse_type" class=”easyui-combobox” name="queryParams.purse_type">
	  						<option value = "">全部</option>
							<option value = "0">钱包</option>
							<option value = "1">分红</option>
							<option value = "2">积分</option>
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
		    	url="" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options="">
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userName" width="10%">产品编号</th>
						<th field="trueName" width="10%">产品名称</th>
						<th field="trueName" width="5%">销售数量</th>
						<th field="trueName" width="10%">销售单价</th>
						<th field="trueName" width="10%">销售小计</th>
						<th field="trueName" width="10%">成本单价</th>
						<th field="trueName" width="10%">成本小计</th>
						<th field="trueName" width="10%">毛利</th>
						<th field="trueName" width="10%">毛利率</th>
						<th field="optionTime" width="18%" formatter='formatterDeliveryTime'>时间</th>
						
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>