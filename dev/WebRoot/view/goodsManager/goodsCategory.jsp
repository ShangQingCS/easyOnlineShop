<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="framework.config.Config"%>
<%
String imgPathPrefix = Config.get("img.server.basepath");
request.setAttribute("imgPathPrefix",imgPathPrefix);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/default.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8" src="${basePath }/js/test.js"></script>
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
  		 <form id="ff" method="post" enctype="multipart/form-data">
			<table class="tablestyle01" style="margin-top:5px;" width="100%">
				<tr>
					<td width="110">类别ID：</td>
					<td>
						<input name="goodsCategory.level" id="inp_level" type="hidden" />
						<input name="goodsCategory.id" id="inp_id" readonly="readonly" style="width: 460px;" />
					</td>
				</tr>
				<tr>
					<td>上级类别ID：</td>
					<td><input name="goodsCategory.parentId" id="inp_parentId" readonly="readonly" style="width: 460px;" /></td>
				</tr>
				<tr>
					<td>上级类别名称：</td>
					<td><input name="parentCateName" id="inp_parentCateName" readonly="readonly" style="width: 460px;" /></td>
				</tr>
				<tr>
					<td>显示顺序：</td>
					<td><input name="goodsCategory.cateOrder" id="inp_cateOrder" class="easyui-validatebox" required="true" validType="number" maxlength="3" style="width: 50px;" /></td>
				</tr>
				<tr>
					<td>类别名称：</td>
					<td><input name="goodsCategory.cateName" id="inp_cateName" class="easyui-validatebox" required="true" maxlength="100" style="width: 320px;" /></td>
				</tr>
				<tr id="codeTr" style="display:none;">
					<td>类别编码：</td>
					<td>
		    			<input name="goodsCategory.cateCode" id="inp_cateCode" style="width: 50px;" class="easyui-validatebox" required="false"/>				
					</td>
				</tr> 
				<tr id="logoTr" style="display:none;">
					<td>图片：</td>
					<td>
						<img id="logoPr" width="100" height="100" /><br>
		    			<input type="file" style='width: 200px;' name="logo" id="logo"/>					
					</td>
				</tr> 
				<tr>
					<td>是否有效：</td>
					<td>
						<select name="goodsCategory.isuse" id="inp_isuse">
							<option value="1">无效</option>
							<option value="0">有效</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
  	</div>
</body>

<script type="text/javascript">
	$(function(){
		$("#logo").uploadPreview({ Img: "logoPr", Width: 100, Height: 100 });
		queryGoodsCategoryTree();
	});
	
	//初始化商品类别树
	function queryGoodsCategoryTree() {
		$.ajax( {
			type : "POST",
			url : "${basePath }/view/goodsManager/goodsCagegoryManager!goodsCategoryTree.action",
			dataType : "json",
			success : function(json) {
				var root = json.goodsCategoryTree[0];
				root.state = "open";
				//root.children[root.children.length - 1].state = "open";
				$("#ul_tree").tree("loadData", json.goodsCategoryTree);
			}
		});
	}
	
	var currNodeId = null;
	var treeClick = function(node) {
		var nodeInfo = getNodeInfo(node);
		if(currNodeId!=null && currNodeId==nodeInfo.id)	{
			return;
		}
		
		$("#div_config :input[name]").each(function(i,n){
			var ns = n.name.split(".");
			var fname = ns.length>1?ns[1]:ns[0];
			if(fname=="logo") {
				if(nodeInfo[fname]!=null)
					$("#logoPr").attr("src",  "${imgPathPrefix}/"+nodeInfo[fname]);
			} else {
				$(n).val(nodeInfo[fname]);
			}
		});
		
		/*if(nodeInfo.logo!=null) {
			//$("#logoPr").attr("src",  "${imgPathPrefix}/"+nodeInfo.logo);
		}*/
		
		//只有三级裁显示logo和编码
		if(nodeInfo != null && nodeInfo.level != null && nodeInfo.level=="3") {
			$("#codeTr").css("display","");
			$("#logoTr").css("display","");
			$('#inp_cateCode').validatebox({required: true});
		} else {
			$("#codeTr").css("display","none");
			$("#logoTr").css("display","none");
			$('#inp_cateCode').validatebox({required: false});
		}
		
		/* if(currNodeId != nodeInfo.id) {
			$("#logoPr").attr("src", "");
		} */
		currNodeId = nodeInfo.id;
    }
    
    var getNodeInfo = function(node) {
		var obj = new Object();
		obj.id = node.id;
		obj.cateName = node.text;
		if(node.attributes!=null){
			obj.cateOrder = node.attributes.cateOrder;
			obj.level = node.attributes.level;
			obj.isuse = node.attributes.isuse;
			obj.logo = node.attributes.logo;
			obj.cateCode=node.attributes.cateCode;
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
		$("#inp_isuse").val("1");
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
	
	/* var saveGoodsCategory = function(json) {
		if(json == null) {
			$("#div_config").attr("action","${basePath }/view/goodsManager/goodsCagegoryManager!saveGoodsCategory.action");
			formSubmit('div_config',saveGoodsCategory);
		} else {
			alert("保存成功");
			$("#inp_id").val(json.goodsCategory.id);
			queryGoodsCategoryTree();//初始化菜单
		}
	} */
	
	var saveGoodsCategory = function(json) {
		$('#ff').form('submit', {
			url: "${basePath }/view/goodsManager/goodsCagegoryManager!saveGoodsCategory.action",
			onSubmit: function() {
				return checkForm();
			},
      		success:function(data) {
				queryGoodsCategoryTree();//初始化菜单
     		}
 		});  
	}
	
	//表单验证
	var checkForm = function() {
		var check = $('#ff').form('validate');
		if(!check) {
			return false;
		}
		return true;
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
					$("#div_config").attr("action","${basePath }/view/goodsManager/goodsCagegoryManager!deleteGoodsCategory.action");
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