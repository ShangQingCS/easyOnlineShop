<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.config.Config"%>
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
			queryDictionaries();
		}
		
		var queryDictionaries = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$.ajax({
				url: "${basePath }/view/dictionariesManager/dictionariesManager!queryNsDictionaries.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		
		var formatterDictionariesAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='showEditDictionariesWin(\""+rec.id+"\"); return false;'>查看和编辑</a>&nbsp;"
			formatterStr += "<a href='#' onclick='deleteDictionaries(\""+rec.id+"\",\""+rec.name+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		//打开编辑页面
		var showEditDictionariesWin = function(id) {
			$.ajax( {
				type: "POST",	
				url: "${basePath }/view/dictionariesManager/dictionariesManager!loadDictionaries.action",	
				data: {"nd.id": id}, 
				dataType: "json",
				success: function(json){
					$("#id").val(json.nd.id);
					$("#name").val(json.nd.name);
					$("#code").val(json.nd.code);
					$("#type").val(json.nd.type);
					$("#parentcode").val(json.nd.parentcode);
					$("#remark").val(json.nd.remark);
					$("#isuse").val(json.nd.isuse);
					$("#isedit").val(json.nd.isedit);
					$("#sort").val(json.nd.sort);
					$("#dictionaries_add_window").window({title: "查看和编辑基础数据"});
					$("#dictionaries_add_window").window("open");
				},	
				error: function(e) {alert("查询异常");}
			});
		}
		
		
		
		//删除一条数据
		var deleteDictionaries = function(id, name) {
			window.confirm("提示","确定删除："+name+"?",function(r){
				if(r){
					$.ajax({ 
						type: "POST",  
						url: "${basePath }/view/dictionariesManager/dictionariesManager!deleteNsDictionaries.action",  
						dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryDictionaries();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var showAddDictionariesWin = function() {
			$("#parentcode").empty(); //清空
			$.ajax({
				url: "${basePath }/view/dictionariesManager/dictionariesManager!queryNsDictionaries.action",
				cache: false,
				dataType:"json",
				success: function(json){
					$("#parentcode").append("<option value=''>--请选择--</option>");
					for(var i=0;i<json.rows.length;i++){
						$("#parentcode").append('<option value="' + json.rows[i].code + '">' + json.rows[i].code + '</option>'); 
					}
				}
			});
			
			formReset("dictionaries_add_table");
			$("#dictionaries_add_window").window({title: "新增基础数据"});
			$("#dictionaries_add_window").window("open");
		}
		
		var clearForm = function() {
			$('#ff').form('clear');
		}
		
		var dictionariesSave = function() {
			// 调用 form  插件的 'submit' 方法来提交 form   
			$('#ff').form('submit', {
				url: "${basePath }/view/dictionariesManager/dictionariesManager!saveNsDictionaries.action",
	      		success:function(data) {
	          		clearForm();
	          		queryDictionaries();
					$("#dictionaries_add_window").window("close");
					alert("操作成功！");
	     		}
	 		}); 
		}
		
		var showdictionariesDetailRow = function() {
			var row = $('#tab_list').datagrid('getSelected');
			if (row) {
				showEditDictionariesWin(row.id);
			}
		}
		
		
    		
    		
    	</script>
		<title>基础数据管理</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">名称:<input name="queryParams.name"  /></td>
	  				<td align="left">代码类型:<input name="queryParams.type"  /></td>
	  				<td align="left">
	  					是否有效:
	  					<select id=”cc” class=”easyui-combobox” name="queryParams.isuse" style=”width:200px;”>
	  						<option value = "0">--请选择--</option>
							<option value = "0">有效</option>
							<option value = "1">无效</option>
						</select>
	  				</td>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryDictionaries(); return false;">查询</a>
	  					<a href="#" class="easyui-linkbutton" onclick="showAddDictionariesWin(); return false;">新增</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
	  			</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="dictionariesManager!queryNsDictionaries.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options="onDblClickRow:showdictionariesDetailRow">
				<thead>
					<tr>
						<th field="name" width="39%">名称</th>
						<th field="type" width="10%">类型代码</th>
						<th field="code" width="10%">代码</th>
						<th field="parentcode" width="10%">父代码</th>
						<th field="remark" width="15%">说明/描述</th>
						<th field="cz" width="15%" formatter='formatterDictionariesAction'>操作</th> 
					</tr>
				</thead>
			</table>
		</div>
		
		<div title="新增基础数据" id="dictionaries_add_window" modal="true" draggable="false" class="easyui-window" style="width: 800px; height: 300px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="dictionaries_add_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<td align="right" width="100">名称:</td>
			  				<td >
			  					<input type="hidden" name="nd.id" id="id" /> 
			  					<input type="hidden" name="nd.sort" id="sort" /> 
			  					<input name="nd.name" id="name" style="width: 100%;" class="easyui-validatebox" required="true"/> 
			  				</td>
			  				<td align="right" width="100">代码:</td>
			  				<td >
			  					<input name="nd.code" id="code" style="width: 100%;" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">父代码:</td>
			  				<td>
			  					<select name="nd.parentcode" id="parentcode">
			  						<option value="">--请选择--</option>
			  					</select> 
			  				</td>
			  				<td align="right" width="100">类型代码:</td>
			  				<td >
			  					<input name="nd.type" id="type" style="width: 100%;" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">说明/描述:</td>
			  				<td colspan="3">
			  					<textarea name="nd.remark" id="remark" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
			  					</textarea>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">是否有效:</td>
			  				<td >
			  					<select name="nd.isuse" id="isuse">
			  						<option value="0">是</option>
			  						<option value="1">否</option>
			  					</select> 
			  				</td>
			  			
			  				<td align="right">是否可编辑:</td>
			  				<td >
			  					<select name="nd.isedit" id="isedit">
			  						<option value="0">是</option>
			  						<option value="1">否</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			
		  			</table>
					<div align="center" class="tablestyle01" style="height:50px; padding-top: 3px; ">
		 				<a href="#" class="easyui-linkbutton" onclick="dictionariesSave(); return false;">保存</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#dictionaries_add_window').window('close'); return false;">关闭</a>
					</div>
				</form>
			</div>
  	</body>
</html>