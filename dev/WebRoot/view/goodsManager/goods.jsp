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
	  				<td align="left">商品名称:<input name="queryParams.gname"/></td>
	  				<td align="left">商品编码:<input name="queryParams.goods_code"/></td>
	  				<td align="left">类别:<input class="easyui-combobox" name="queryParams.category" id="inp_category" data-options="editable: false"/></td>
	  				<td align="left">类型:<input class="easyui-combobox" name="queryParams.kind" id="inp_kind" data-options="editable: false"/></td>
	  				<td align="left">品牌:<input class="easyui-combobox" name="queryParams.brand" id="inp_brand" data-options="editable: false"/></td>
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
		    	pagination="true" singleSelect="true" data-options="onDblClickRow:showGoodsDetailRow">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="id" width="5%">商品编号</th>
						<th field="gname" width="39%">商品名称</th>
						<th field="categoryname" width="10%">类别</th>
						<th field="kindname" width="10%">类型</th>
						<th field="brandname" width="10%">品牌</th>
						<th field="price" width="5%">价格</th>
						<th field="isuse" width="10%" formatter='formatterIsuse'>状态</th>
						<th field="cl" width="10%" formatter='formatterAction'>操作</th>
						<!-- <th field="categoryname.cateName" width="15%" formatter='formatterCategory'>操作</th> -->
						<!-- <th field="kindname.cateName" width="15%" formatter='formatterKind'>操作</th> -->
						<!-- <th field="brandname.cateName" width="15%" formatter='formatterBrand'>操作</th> -->
					</tr>
				</thead>
			</table>
		</div>
  	</body>
  
  	<script type="text/javascript">
  		/* function formatterCategory(value,rec){
			return rec.categoryname.cateName;
		}
  		function formatterKind(value,rec){
			return rec.kindname.cateName;
		}
  		function formatterBrand(value,rec){
			return rec.brandname.cateName;
		} */
		$(function(){
			initPage();
		});
		
		var initPage = function() {
			queryCategorys();
		}
	
		var showGoodsDetailRow = function() {
			var row = $('#tab_list').datagrid('getSelected');
			if (row) {
				goodsEdit(row.id,row.gname);
			}
		}
		
		//添加tab页
		var goodsEdit = function(id,name){
			openEdit(name,'${basePath }/view/goodsManager/goodsEdit.jsp?id='+id);
		}
	
		var openEdit = function(title, url) { 
            var jq = top.jQuery;
            if (jq("#div_tabs").tabs('exists', title)) {   
				jq("#div_tabs").tabs('select', title);
            } else {
				var content = "<iframe src=\""+url+"\" scrolling=\"no\" frameborder=\"0\" style=\"width:100%; height: 99.5%;overflow: hidden;\"></iframe>"; 
				jq("#div_tabs").tabs('add',{    
					title:title,
					content:content,
					closable:true
				});    
			}
		}
        
		var queryGoods = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var goodsDel = function(id,gname) {
			var name = gname;
			if(id==null){
				id = ""; var m=""; name="所选商品";
				var rows= $('#user_list').datagrid("getSelections");
				if(rows!=null){
					for(var i=0;i<rows.length;i++){
						id+= m+rows[i].id;m=",";
					}
				}
			}
			
			if(id==""){alert("至少选择一个商品");return false;}
			window.confirm("提示","删除"+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/goodsManager/goodsManager!deleteGoods.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("删除商品成功！");
								queryGoods();
							} else if(json.message!=null && json.message!=''){
								alert(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var goodsRecover = function(id,gname) {
			window.confirm("提示","设为有效："+gname+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/goodsManager/goodsManager!recoverGoods.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryGoods();
							} else if(json.message!=null && json.message!=''){
								alert(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var invalidGoods = function(id,gname) {
			window.confirm("提示","设为无效："+gname+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/goodsManager/goodsManager!invalidGoods.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryGoods();
							} else if(json.message!=null && json.message!=''){
								alert(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var formatterAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='goodsEdit(\""+rec.id+"\",\""+rec.gname+"\"); return false;'>查看和编辑</a>&nbsp;";
			if(rec.isuse=="0") {
				formatterStr += "<a href='#' onclick='invalidGoods(\""+rec.id+"\",\""+rec.gname+"\"); return false;'>设为无效</a>&nbsp;";
			} else {
				formatterStr += "<a href='#' onclick='goodsRecover(\""+rec.id+"\",\""+rec.gname+"\"); return false;'>设为有效</a>&nbsp;";
			}
			//formatterStr += "<a href='#' onclick='goodsDel(\""+rec.id+"\",\""+rec.gname+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		var formatterIsuse = function(value,rec) {
			if(value=="0") {
				return "<span style='color: green;'>有效</span>";
			} else {
				return "<span style='color: red;'>无效</span>";
			}
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