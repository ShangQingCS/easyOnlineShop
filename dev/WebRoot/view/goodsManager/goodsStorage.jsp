<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
		<title>商品维护</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="商品列表" style="height:130px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">商品名称:<input name="queryParams.gname"/></td>
	  				<td align="left">商品编码:<input name="queryParams.goods_code"/></td>
	  				<td align="left">类别:<input class="easyui-combobox" name="queryParams.category" id="inp_category" data-options="editable: false"/></td>
	  				<td align="left">类型:<input class="easyui-combobox" name="queryParams.kind" id="inp_kind" data-options="editable: false"/></td>
	  				<td align="left">品牌:<input class="easyui-combobox" name="queryParams.brand" id="inp_brand" data-options="editable: false"/></td>
	  			</tr>
	  			<tr>
	  				<td align="left">可用库存:<input class="easyui-numberspinner" name="queryParams.storenumbone" data-options="increment:10" style="width:100px;"/>
	  				至<input class="easyui-numberspinner"name="queryParams.storenumbtwo" data-options="increment:10" style="width:100px;"/></td>
	  				<td align="left"></td>
	  				<td align="left"></td>
	  				<td align="left"></td>
	  			</tr>
	  		</table>
	  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryGoods(); return false;">查询</a>
	  					<a href="#" class="easyui-linkbutton" onclick="showUserImportWin(); return false;">导入库存</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 	</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="${basePath }/view/goodsManager/goodsManager!queryGoods.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="id" width="5%">商品编号</th>
						<th field="gname" width="39%">商品名称</th>
						<th field="categoryname" width="10%">类别</th>
						<th field="kindname" width="10%">类型</th>
						<th field="brandname" width="10%">品牌</th>
						<th field="price" width="5%">价格</th>
						<th field="storenumb" width="5%">可用销售库存</th>
						<th field="freazes" width="5%">冻结库存</th>
						<th field="costprice" width="5%">成本价</th>
						<th field="sellnumb" width="5%">实际库存</th>
						<th field="cl" width="10%" formatter='formatterAction'>操作</th>
					</tr>
				</thead>
			</table>
			
			<div title="修改库存" id="div_window_new" class="easyui-dialog bgColor" modal="true" draggable="false" 
				buttons="#buts_window_new"
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<div style="height: 100%;" class="bgColor">
				<table id="form_edit" action="${basePath }/view/goodsManager/goodsStorageManager!modifyStorage.action" class="tablestyle01" style="width:100%">
		  			<tr>
		  				<td align="right" style="width:80px;">商品名称:</td>
		  				<td>
		  					<input type="hidden" name="goods.id" id="inp_id"/>
		  					<input class="easyui-textbox" name="goods.gname" id="inp_gname" style="width:300px;" readonly="readonly"/>
		  				</td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">对外可用销售:</td>
		  				<td><input class="easyui-numberspinner" name="goods.storenumb" id="inp_storenumb" data-options="increment:10" style="width:150px;"/></td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">销售价格:</td>
		  				<td><input class="easyui-numberspinner" name="goods.price" id="inp_price" data-options="increment:10" style="width:150px;"/></td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">成本价:</td>
		  				<td><input class="easyui-numberspinner" name="goods.costprice" id="inp_costprice" data-options="increment:10" style="width:150px;"/></td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width:80px;">实际库存:</td>
		  				<td><input class="easyui-numberspinner" name="goods.sellnumb" id="inp_sellnumb" data-options="increment:10" style="width:150px;"/></td>
		  			</tr>
		  		</table>
		  		<div id="buts_window_new" align="right" >
		  			<a href="#" class="easyui-linkbutton" onclick="goodsStorageEditSubmit(null); return false;">提交</a>
		  			<a href="#" class="easyui-linkbutton" onclick="$('#div_window_new').dialog('close');return false;">关闭</a>
		  		</div>
		  		</div>
			</div>
			
			<div title="错误信息" id="err_msg_window" modal="true" draggable="false" class="easyui-dialog" style="width: 400px; height: 300px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true" buttons="#err_msg_buttons">
				<span id="err_msg_span"></span>
				<div id="err_msg_buttons" align="center" style="background-color:#EFEFEF;">
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr><td align="center">
	 						<a href="#" class="easyui-linkbutton" onclick="$('#err_msg_window').dialog('close'); return false;">关闭</a>
						</td></tr>
					</table>
				</div>
			</div>
		</div>
  	</body>
  
  	<script type="text/javascript">
		$(function(){
			initPage();
		});
		
		var initPage = function() {
			queryCategorys();
		}
	
		function showUserImportWin(){
			$('#err_msg_window').dialog('close');
			var params = {fileType:'xls'};
			showFileUpload(params, function(id){
				$.ajax( {
					type : "POST",url : "${basePath }/view/goodsManager/goodsStorageManager!importStorageInfo.action",dataType : "text", data:{fileId:id}, error:function(){alert("导入异常");},
					success : function(json) {
						if(json==""){
							//$('#but_import').css('display','none');
							$('#user_list').datagrid("reload");
							alert("导入成功");
						}else{
							//alert("导入失败,错误信息：<br>"+json);
							document.getElementById("err_msg_span").innerHTML = "导入失败,错误信息：<br>"+json;
							$("#err_msg_window").window("open");
						}
					}
				});
			});
		}
		
		var openEdit = function(id,gname,storenumb) { 
        	formReset("form_edit");
        	$("#inp_storenumb").textbox('setValue', storenumb=="null"?"0":storenumb);
        	$("#inp_gname").textbox('setValue', gname);
        	$("#inp_id").val(id);
			$("#div_window_new").dialog("open");
		}
        
        function goodsStorageEditSubmit(json){
			if(json==null){
				formSubmit("form_edit",goodsStorageEditSubmit);
			}else{
				alert("保存成功");
				$('#div_window_new').dialog('close');
				queryGoods();
			}
		}
		
		var queryGoods = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var formatterAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='openEdit(\""+rec.id+"\",\""+rec.gname+"\",\""+rec.storenumb+"\"); return false;'>修改库存</a>&nbsp;";
			return formatterStr;
		}
		
		function formatterDate(date) {
			var d = date.split("T");
			return d[0]+" "+d[1];
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