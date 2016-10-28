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
						<th field="gname" width="10%">商品名称</th>
						<th field="categoryname" width="10%">类别</th>
						<th field="kindname" width="10%">类型</th>
						<th field="brandname" width="10%">品牌</th>
						<th field="price" width="10%">价格</th>
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
		
		//添加tab页
		function goodsEdit(id,name){
			addTab(name,'${basePath }/view/goodsManager/goodsAdd.jsp?id='+id);
		}
	
		function addTab(title, url) { 
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
        
		function queryGoods() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var formatterAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='goodsEdit(\""+rec.id+"\",\""+rec.gname+"\"); return false;'>编辑</a>&nbsp;"
			+"<a href='#' onclick='goodsDel(\""+rec.id+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		function formatterDate(date) {
			var d = date.split("T");
			return d[0]+" "+d[1];
		}
	</script>
</html>