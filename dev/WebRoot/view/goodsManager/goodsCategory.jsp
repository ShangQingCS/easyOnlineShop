<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/default.jsp"></jsp:include>
<title>商品类别维护</title>
</head>
  
<body class="easyui-layout">
   	<div region="north" class="easyui-panel bgColor" title="商品类别维护" style="height:66px; ">
  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
  			<tr>
  				<td align="left">
		  			<a href="#" class="easyui-linkbutton" onclick="saveGoodsCategory();">保存</a>
			 		<a href="#" class="easyui-linkbutton" onclick="addGoodsCategory();">新增</a>
			 		<a href="#" class="easyui-linkbutton" onclick="delGoodsCategory();">删除</a>
			 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
		 		</td>
		 	</tr>
  		</table>
  	</div>
  	<div region="west" class="easyui-panel bgColor" split="true" title="商品类别" style="width:200px;overflow: auto;">
  		<ul id="ul_tree" class="easyui-tree" data-options="onClick:treeClick"></ul>
  	</div>
  	<div id="div_config" region="center" title="类别信息" class="easyui-panel bgColor" style="overflow: auto;">
		<table class="tablestyle01" style="margin-top:5px;" width="100%">
			<tr>
				<td width="110">类别ID：</td>
				<td>
					<input name="goodsCategoryVO.level" id="inp_level" type="hidden" />
					<input name="goodsCategoryVO.id" id="inp_id" readonly="readonly" style="width: 460px;" />
				</td>
			</tr>
			<tr>
				<td>上级类别ID：</td>
				<td><input name="goodsCategoryVO.parentId" id="inp_parentId" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>上级类别名称：</td>
				<td><input name="parentCateName" id="inp_parentCateName" readonly="readonly" style="width: 460px;" /></td>
			</tr>
			<tr>
				<td>显示顺序：</td>
				<td><input name="goodsCategoryVO.cateOrder" id="inp_cateOrder" class="easyui-validatebox" required="true" validType="number" maxlength="3" style="width: 50px;" /></td>
			</tr>
			<tr>
				<td>类别名称：</td>
				<td><input name="goodsCategoryVO.cateName" id="inp_cateName" class="easyui-validatebox" required="true" maxlength="100" style="width: 320px;" /></td>
			</tr>
			<tr>
				<td>是否有效：</td>
				<td>
					<select name="goodsCategoryVO.isuser" id="inp_isuser">
						<option value="1">无效</option>
						<option value="0">有效</option>
					</select>
				</td>
			</tr>
		</table>
  	</div>
</body>
  
<script type="text/javascript">
	$(function(){
		queryGoodsCategoryTree();
	});
	
	//初始化功能菜单
	function queryGoodsCategoryTree() {
		$.ajax( {
			type : "POST",
			url : "${basePath }/view/goodsManager/goodsManager!goodsCategoryTree.action",
			dataType : "json",
			success : function(json) {
				var root = json.goodsCategoryTree[0];
				root.state = "open";
				//root.children[root.children.length - 1].state = "open";
				$("#ul_tree").tree("loadData", json.goodsCategoryTree);
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
			obj.isuser = node.attributes.isuser;
		}
		var pnode = $('#ul_tree').tree('getParent',node.target); 
		if(pnode!=null){
			obj.parentId = pnode.id;
			obj.parentCateName = pnode.text;
		}
		return obj;
	}
	
	//添加类别
	var addGoodsCategory = function() {
		var node = $("#ul_tree").tree("getSelected");
		if(node==null){
			alert("请选择一个类别");
			return;
		}
		
		var nodeInfo = getNodeInfo(node);
		if(nodeInfo.level==3) {
			alert("该类别下不允许添加子类别");
			return;
		}
		//清空和重置
		$("#inp_isuser").val("1");
		$("#div_config input").each(function(i,n){n.value = "";});
		//赋值
		$("#inp_level").val(parseInt(nodeInfo.level)+1);
		$("#inp_parentId").val(nodeInfo.id);
		$("#inp_parentCateName").val(nodeInfo.cateName);
		if(node.children && node.children.length > 0) {
			var lastChildNode = node.children[node.children.length-1];
			if(lastChildNode.attributes!=null) {
				$("#inp_cateOrder").val(parseInt(lastChildNode.attributes.cateOrder)+1);
			}
		} else {
			$("#inp_cateOrder").val(1);
		}
	}
	
	var saveGoodsCategory = function(json) {
		if(json == null) {
			$("#div_config").attr("action","${basePath }/view/goodsManager/goodsManager!saveGoodsCategory.action");
			formSubmit('div_config',saveGoodsCategory);
		} else {
			alert("保存成功");
			$("#inp_id").val(json.goodsCategoryVO.id);
			queryGoodsCategoryTree();//初始化菜单
		}
	}
	
	var delGoodsCategory = function(json) {
		var node = $("#ul_tree").tree("getSelected");
		if(node==null){
			alert("请选择一个类别");
			return;
		}
		var pnode = $('#ul_tree').tree('getParent',node.target); 
		if(pnode==null) {
			alert(node.text+"不能删除！");
			return;
		}
		
		if(json==null) {
			window.confirm("提示","确认删除?", function(r){
				if(r){
					$("#div_config").attr("action","${basePath }/view/goodsManager/goodsManager!deleteGoodsCategory.action");
					formSubmit('div_config',delGoodsCategory);
				}
			});
		} else {
			alert("删除成功");
			$("#div_config input").each(function(i,n){n.value = "";});
			queryGoodsCategoryTree();//初始化菜单
		}
	}
</script>
</html>