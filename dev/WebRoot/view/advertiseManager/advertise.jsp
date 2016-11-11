<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
		<title>广告管理</title>
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
	  					<a href="#" class="easyui-linkbutton" onclick="queryComment(); return false;">查询</a>
	  					<a href="#" class="easyui-linkbutton" onclick="addAdv(); return false;">新增</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 	</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="advertiseManager!queryAdv.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options="onDblClickRow:showComment">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="id" width="5%">用户名</th>
						<!-- <th field="goodid" width="5%">商品编号</th>
						<th field="gname" width="29%">商品名称</th>
						<th field="categoryName" width="5%">类别</th>
						<th field="kindName" width="5%">类型</th>
						<th field="brandName" width="5%">品牌</th>
						<th field="createTime" width="10%" formatter='formatterDatetime'>评论时间</th>
						<th field="comment" width="20%">评论内容</th>
						<th field="ishidden" width="5%" formatter='formatterIsHidden'>是否匿名</th>
						<th field="score" width="5%">评分</th>
						<th field="cz" width="5%" formatter='formatterAction'>操作</th> -->
					</tr>
				</thead>
			</table>
			
			<div title="新增广告" id="adv_add_window" modal="true" draggable="false" class="easyui-window" style="width: 90%; height: 90%; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<table id="adv_add_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
		  			<tr>
		  				<td align="right">广告标题:</td>
		  				<td><input name="userid" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<!-- <tr>
		  				<td align="right">广告介绍:</td>
		  				<td><input name="goodid" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr> -->
		  			<tr>
		  				<td align="right">广告图片:</td>
		  				<td><input name="gname" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">广告分类:</td>
		  				<td><input name="categoryName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">活动对象:</td>
		  				<td>
		  					<input name="kindName" readonly="readonly" style="width: 100%;"/> 
		  				</td>
		  			</tr>
		  			<tr>
		  				<td align="right">显示顺序:</td>
		  				<td><input name="brandName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">广告类型:</td>
		  				<td>
		  					<select>
		  						<option value="1">首页头部</option>
		  						<option value="2">其它位置</option>
		  					</select> 
		  				</td>
		  			</tr>
		  			<tr>
		  				<td align="right">所属分组:</td>
		  				<td><input name="ishidden" id="ishidden" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
	  			</table>
				<div align="center" class="tablestyle01">
	 				<a href="#" class="easyui-linkbutton" onclick="$('#adv_add_window').window('close'); return false;">关闭</a>
				</div>
			</div>
		</div>
  	</body>
  
  	<script type="text/javascript">
		$(function(){
			initPage();
		});
		
		var initPage = function() {
		}
		
		var formatterAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='showComment(); return false;'>查看详细</a>&nbsp;"
			formatterStr="<a href='#' onclick='delComment(\""+rec.id+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		var showComment = function() {
		}
		
		var queryComment = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var addAdv = function() {
			formReset("adv_add_table");
			$("#adv_add_window").window("open");
		}
	</script>
</html>