<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<jsp:include page="/include/default.jsp"></jsp:include>
    <title>键值对管理</title>
    
	<script type="text/javascript">
		var lastIndex = -1;
		var isNew = false;
		var isEditing = false;
		$(function(){
			$("#tab_list").datagrid({onDblClickRow: clickTabRow});
		});
		
		function addTabRow(){
			if(isEditing){
				alert("有未编辑完成的内容");
				return false;
			}
			isNew = true;
			isEditing = true;
			$('#tab_list').datagrid('appendRow',{kvKey:'', kvValue:'', lrSj:''});
			lastIndex = $('#tab_list').datagrid('getRows').length-1;
			$('#tab_list').datagrid('selectRow', lastIndex);
			$('#tab_list').datagrid('beginEdit', lastIndex);
	    }
    	
    	function clickTabRow(index, data){
    		isNew = false;
    		var editorts = $('#tab_list').datagrid('getEditors', lastIndex);
    		if(editorts.length>0){alert("有内容可未保存");return false;}
	    	if (lastIndex != index){
				$('#tab_list').datagrid('endEdit', lastIndex);
			}
			$('#tab_list').datagrid('beginEdit', index);
			lastIndex = index;
			isEditing = true;
	    }
    
    	function save(){
    		var editorts = $('#tab_list').datagrid('getEditors', lastIndex);
    		if(editorts.length==0){alert("无内容可保存");return false;}
    		var key = editorts[0].target.val();
    		var value = editorts[1].target.val();
    		if(value==''){return false;}
    		var data = {"queryParams.kv_key":key};
    		$.ajax( {
				type: "POST",	url: "keyvalue!query.action", data: data, dataType: "json",
				success: function(json){
					if(isNew && json.rows.length>0){
						alert("已存在该键值对");
					}else{
						data = {"kv.kvKey":key,"kv.kvValue":value};
						$.ajax( {
							type: "POST",	url: "keyvalue!save.action",	data: data, dataType: "json",
							success: function(json){
								$('#tab_list').datagrid('endEdit', lastIndex);
			    				isEditing = false;
			    				alert("保存成功");
							},	
							error: function(e) {alert("保存异常");}
						});
					}
				},	
				error: function(e) {alert("保存异常");}
			});
    	}
    	
    	function deleteKv(){
    		var sels = $('#tab_list').datagrid("getSelections");
    		var ids = "";
    		var bz = "";
    		$(sels).each(function(i,n){
    			ids += bz+n.kvKey;
    			bz = ",";
    		});
			$.ajax( {
				type: "POST",	url: "keyvalue!delete.action",	data: {"kv.kvKey":ids}, dataType: "json",
				success: function(json){
					query();
				},	
				error: function(e) {alert("删除异常");}
			});
    	}
    	
		function query(){
			$('#tab_list').datagrid({queryParams:{"queryParams.key":$('#queryParams_key').val()}});
			
		}
		function timeFormatter(val){
			return val.replace("T"," ");
		}
		
	</script>
  </head>
  <body class="easyui-layout">
  	<div region="north" class="easyui-panel bgColor" title="角色列表" style="height:68px; ">
  		<table id="from_query"  dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="right" style="width: 100">关键字:</td>
  				<td ><input id="queryParams_key" name="queryParams.key" maxlength="30" /> </td>
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  				<td align="right">&nbsp;</td>
  				<td >&nbsp;</td>
  			</tr>
  		</table>
  	</div>
  	
  	<div region="center">
	    <table id="tab_list" rownumbers="true"  region="center" class="easyui-datagrid" url="keyvalue!list.action" style="width:auto;height:auto" title=""   pagination="true">
			<thead>
				<tr>
					<th field="kvKey" width="200"  editor="{type:'validatebox',options:{required:true}}" >键</th>
					<th field="kvValue" width="220" editor="{type:'validatebox',options:{required:true}}" >值</th>
					<th field="lrSj" width="120" formatter="timeFormatter">录入时间</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
		<a href="#" class="easyui-linkbutton" onclick="query(); return false;">查询</a>
		<a href="#" class="easyui-linkbutton" onclick="addTabRow(); return false;">新建</a>
		<a href="#" class="easyui-linkbutton" onclick="save(); return false;">保存</a>
		<a href="#" class="easyui-linkbutton" onclick="deleteKv(); return false;">删除</a>
 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
 		<a href="#" class="easyui-linkbutton" onclick="winClose();">关闭</a>
    </div>
  </body>
</html>
