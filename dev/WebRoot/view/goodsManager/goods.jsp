<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
		<title>商品维护</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="商品列表" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left" style="width: 100">商品名称:<input name="queryParams.gname"/>
	  				 操作时间:
	  				<input name="queryParams.beginDate" class="easyui-datebox" value="${currBeginDate }" editable="false"/>~
					<input name="queryParams.endDate" class="easyui-datebox" value="${currEndDate }" editable="false"/>
					</td>
	  			</tr>
	  		</table>
	  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryGoods(); return false;">查询</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 	</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="goodsManager!queryGoods.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="gname" width="20%">商品名称</th>
					</tr>
				</thead>
			</table>
		</div>
  	</body>
  
  	<script type="text/javascript">
		function queryGoods() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		function formatterAction(date) {
			var d = date.split("T");
			return d[0]+" "+d[1];
		} 
	</script>
</html>