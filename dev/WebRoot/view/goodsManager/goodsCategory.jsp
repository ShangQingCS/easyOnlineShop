<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/default.jsp"></jsp:include>
<title>商品分类管理</title>
<script type="text/javascript">
		
		
		
		
		function saveTree(json){
			var pid = document.getElementById("inp_menuParentIds").value;
			var url = document.getElementById("inp_url").value;
			if(pid != null && pid.length > 0) {
				var pids = pid.split(",");
				if(pids.length >= 3) {
					if(url.length == 0) {
						//alert("请填写链接地址！");
						//return;
					}
					//$('#inp_url').validatebox({   
						//required:true  
					//}); 
				} else {
					if(url.length > 0) {
						alert("此节点不为根节点，不能输入链接地址！");
						return;
					}
				}
			}
			
			if(json == null) {
				$("#div_config").attr("action","treeSave.action");
				formSubmit('div_config',saveTree);
			} else {
				document.getElementById("inp_menuId").value = json.tree.menuId;
				document.getElementById("inp_menuParentIds").value = json.tree.menuParentIds;
				initMenuTree();//初始化菜单
				alert("保存成功");
			}
		}
		
		function deleteTree(json){
			if(json==null){
				window.confirm("提示","确认删除?", function(r){
					if(r){
						$("#div_config").attr("action","treeDelete.action");
						formSubmit('div_config',deleteTree);
					}
				});
			}else{
				$("#div_config input").each(function(i,n){n.value = "";});
				initMenuTree();//初始化菜单
				alert("删除成功");
			}
		}
	</script>
</head>
  
<body class="easyui-layout">
   	<div region="north" class="easyui-panel bgColor" title="人员列表" style="height:65px; ">
  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left">
		  			<a href="#" class="easyui-linkbutton" onclick="saveTree();">保存</a>
			 		<a href="#" class="easyui-linkbutton" onclick="addGoodsCategory();">新增</a>
			 		<a href="#" class="easyui-linkbutton" onclick="deleteTree();">删除</a>
			 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
		 		</td>
		 	</tr>
  		</table>
  	</div>
  	<div region="west" class="easyui-panel bgColor" split="true" title="商品分类列表" style="width:200px;overflow: auto;">
  		<ul id="ul_tree" class="easyui-tree" data-options="onClick:treeClick"></ul>
  	</div>
  	<div id="div_config" region="center" title="商品分类信息" class="easyui-panel bgColor" style="overflow: auto;">
		<table class="tablestyle01" style="margin-top:5px;" width="100%">
			<tr>
				<td width="110">分类ID：</td>
				<td><input name="tree.id" id="inp_id" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>上级分类ID：</td>
				<td><input name="tree.parentId" id="inp_parentId" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>上级分类名称：</td>
				<td><input name="parentCateName" id="inp_parentCateName" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>排序序号：</td>
				<td><input name="tree.cateOrder" id="inp_cateOrder" class="easyui-validatebox" required="true" validType="number" maxlength="3" style="width: 50px;" /></td>
			</tr>
			<tr>
				<td>商品名称：</td>
				<td><input name="tree.cateName" id="inp_cateName" class="easyui-validatebox" required="true" maxlength="100" style="width: 320px;" /></td>
			</tr>
		</table>
  	</div>
</body>
  
<script type="text/javascript">
	$(function(){
		initTree();
	});
	
	//初始化功能菜单
	function initTree() {
		$.ajax( {
			type : "POST",
			url : "${basePath }/view/goodsManager/goodsManager!goodsCategoryTree.action",
			dataType : "json",
			success : function(json) {
				var root = json.trees[0];
				root.state = "open";
				//root.children[root.children.length - 1].state = "open";
				$("#ul_tree").tree("loadData", json.trees);
			}
		});
	}
	
	var treeClick = function(node) {
		var nodeInfo = getNodeInfo(node);
		$("#div_config :input[name]").each(function(i,n){
			var ns = n.name.split(".");
			$(n).val(nodeInfo[ns.length>1?ns[1]:ns[0]]);
		});
    }
    
    var getNodeInfo = function(node) {
		var obj = new Object();
		obj.id = node.id;
		obj.cateName = node.text;
		if(node.attributes!=null){
			obj.cateOrder = node.attributes.cateOrder;
			obj.level = node.attributes.level;
		}
		var pnode = $('#ul_tree').tree('getParent',node.target); 
		if(pnode!=null){
			obj.parentId = pnode.id;
			obj.parentCateName = pnode.text;
		}
		return obj;
	}
	
	//添加分类
	function addGoodsCategory() {
		var node = $("#ul_tree").tree("getSelected");
		if(node==null){
			alert("请选择一个分类");
			return false;
		}
		var nodeInfo = getNodeInfo(node);
		if(nodeInfo.level==3) {
			alert("该分类下不允许添加子分类");
			return false;
		}
		$("#div_config input").each(function(i,n){n.value = "";});
		document.getElementById("inp_parentId").value = nodeInfo.id;
		document.getElementById("inp_parentCateName").value = nodeInfo.cateName;
	}
</script>
</html>