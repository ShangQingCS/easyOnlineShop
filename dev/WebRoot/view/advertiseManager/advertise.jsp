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
			
			<div title="用户信息" id="user_detail_window" modal="true" draggable="false" class="easyui-window" style="width: 800px; height: 600px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<table id="user_datail_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
		  			<tr>
		  				<td align="right">用户名:</td>
		  				<td><input name="userid" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">商品编号:</td>
		  				<td><input name="goodid" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">商品名称:</td>
		  				<td><input name="gname" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">类别:</td>
		  				<td><input name="categoryName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">类型:</td>
		  				<td><input name="kindName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">品牌:</td>
		  				<td><input name="brandName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">评论时间:</td>
		  				<td><input name="createTime" id="createTime" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">是否匿名:</td>
		  				<td><input name="ishidden" id="ishidden" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">评分:</td>
		  				<td><input name="score" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width: 80px">描述:</td>
		  				<td colspan="3">
		  					<textarea name="comment" readonly="readonly" style="width: 100%;" rows="15"></textarea>
		  				</td>
		  			</tr>
	  			</table>
				<div align="center" class="tablestyle01">
	 				<a href="#" class="easyui-linkbutton" onclick="$('#user_detail_window').window('close'); return false;">关闭</a>
				</div>
			</div>
		</div>
  	</body>
  
  	<script type="text/javascript">
		$(function(){
			initPage();
		});
		
		var initPage = function() {
			//queryCategorys();
		}
		
		var formatterIsHidden = function(value,rec) {
			if(value=="1")
				return "不匿名"; 
			return "匿名";
		}
		
		var formatterAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='showComment(); return false;'>查看详细</a>&nbsp;"
			formatterStr="<a href='#' onclick='delComment(\""+rec.id+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
				
		var queryComment = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var showComment = function() {
			var row = $('#tab_list').datagrid('getSelected');
			if (row) {
				formSet("user_datail_table", row);
				$("#createTime").val(row.createTime.replace("T"," "));
				$("#ishidden").val(row.ishidden=="1"?"不匿名":"匿名");
				$("#user_detail_window").window("open");
			}
		}
		
		var delComment = function(id) {
			window.confirm("提示","确认删除该评论?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/commentManager/commentManager!deleteComment.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("删除评论成功！");
								queryComment();
							}else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		//查询一级类别  
		var queryCategorys = function() {
			$.ajax({
				url: "${basePath }/view/goodsManager/goodsCagegoryManager!queryCategorys.action",
				cache: false,
				dataType:"json",
				success: function(json){
				    $("#inp_category").combobox({
				    	required:false,
				    	editable:false,
						data:json.categorys,
						valueField:'id',
				    	textField:'cateName',
						onChange: function (n,o) {
							$('#inp_kind').combobox("clear");
							$('#inp_brand').combobox("clear");
							$('#inp_kind').combobox('loadData', []);
							$('#inp_brand').combobox('loadData', []);
							queryKinds(callbackQueryKinds);
						}
					});
				}
			});
		}
		
		//查询二级类别
		var queryKinds = function(callbackFun) {
			$.ajax({
				url: "${basePath }/view/goodsManager/goodsCagegoryManager!queryKinds.action?categoryVo.id="+$('#inp_category').combobox('getValue'),
				cache: false,
				dataType:"json",
				success: callbackFun
			});
		}
		
		var callbackQueryKinds = function (json, defaultValue) {
			 $("#inp_kind").combobox({
			   	required:false,
			   	editable:false,
				data:json.kinds,
				valueField:'id',
			   	textField:'cateName',
				onChange: function (n,o) {
					$('#inp_brand').combobox("clear");
					$('#inp_brand').combobox('loadData', []);
					queryBrands(callbackQueryBrands);
				}
			});
		}
		
		//查询三级类别
		var queryBrands = function(callbackFun) {
			$.ajax({
				url: "${basePath }/view/goodsManager/goodsCagegoryManager!queryBrands.action?categoryVo.id="+$('#inp_kind').combobox('getValue'),
				cache: false,
				dataType:"json",
				success: callbackFun
			});
		}
		
		var callbackQueryBrands = function (json, defaultValue) {
			$("#inp_brand").combobox({
		    	required:false,
		    	editable:false,
				data:json.brands,
				valueField:'id',
		    	textField:'cateName'
			});
		}
	</script>
</html>