<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
    <script type="text/javascript" charset="utf-8" src="${basePath }/js/test.js"></script>
		<title>广告管理</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="广告列表" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">广告标题:</td>
	  				<td align="left"><input name="queryParams.name"/></td>
	  				<td align="left">广告分类:</td>
	  				<td align="left">
	  					<select name="queryParams.linkkind">
	  						<option value="">请选择</option>
	  						<option value="0">商品</option>
	  						<option value="1">活动</option>
	  						<option value="2">其它链接</option>
	  						<option value="3">类别</option>
	  					</select>
	  				</td>
	  				<td align="left">广告类型:</td>
	  				<td align="left">
	  					<select name="queryParams.type">
	  						<option value="">请选择</option>
	  						<option value="1">首页头部</option>
	  						<option value="2">其它位置</option>
	  					</select> 
	  				</td>
	  				<td align="left">所属分组:</td>
	  				<td align="left">
	  					<input class="easyui-combobox" name="queryParams.kind" id="inp_kind" data-options="editable: false"/>
					</td>
	  			</tr>
	  		</table>
	  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryAdv(); return false;">查询</a>
	  					<a href="#" class="easyui-linkbutton" onclick="addAdv(); return false;">新增</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 	</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="advertiseManager!queryAdv.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<!-- <th field="id" width="5%">用户名</th> -->
						<th field="name" width="39%">广告标题</th>
						<th field="linkkind" width="10%" formatter='formatterLinkKind'>广告分类</th>
						<!-- <th field="imglink" width="10%">广告对象</th> -->
						<th field="type" width="10%" formatter='formatterAdvType'>广告类型</th>
						<th field="ordernumb" width="10%">显示顺序</th>
						<th field="typename" width="10%">所属分组</th>
						<th field="isuse" width="10%" formatter='formatterAdvIsuse'>是否启用</th>
						<th field="cz" width="10%" formatter='formatterAdvAction'>操作</th>
					</tr>
				</thead>
			</table>
			
			<div title="新增广告" id="adv_add_window" modal="true" draggable="false" class="easyui-window" style="width: 800px; height: 600px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="adv_add_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<td align="right"  width="100">广告标题:</td>
			  				<td>
			  					<input type="hidden" name="adv.id" id="id" /> 
			  					<input name="adv.name" id="name" style="width: 100%;" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">图片预览:</td>
			  				<td><img id="imgurlPr" width="500" height="100" /> </td>
			  			</tr>
			  			<tr>
			  				<td align="right">广告图片:</td>
			  				<td>
			  					<input type="file" style='width: 200px;' name="imgurl" id="imgurl" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">广告分类:</td>
			  				<td>
			  					<select name="adv.linkkind" id="linkkind" onchange="showBtn();">
			  						<option value="0">商品</option>
			  						<option value="1">活动</option>
			  						<option value="2">其它链接</option>
			  						<option value="3">类别</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">广告对象:</td>
			  				<td>
			  					<input type="hidden" name="adv.imglink" id="imglink" /> 
			  					<input id="imglinkLabel" readonly="readonly" style="width: 80%;" class="easyui-validatebox" required="true"/> 
			  					<a href="#" class="easyui-linkbutton" onclick="showQueryGoodsWin();return false;" id="goodsBtn">选择商品</a>
			  					<a href="#" class="easyui-linkbutton" onclick="showQueryEventsWin();return false;" style="display: none;" id="eventBtn">选择活动</a>
			  					<a href="#" class="easyui-linkbutton" onclick="showQueryCateInfoWin();return false;" style="display: none;" id="cateBtn">选择分类</a>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">显示顺序:</td>
			  				<td><input name="adv.ordernumb" id="ordernumb" class="easyui-numberspinner" data-options="increment:1" required="true" /> </td>
			  			</tr>
			  			<tr>
			  				<td align="right">广告类型:</td>
			  				<td>
			  					<select name="adv.type" id="type" onchange="showKind();">
			  						<option value="1">首页头部</option>
			  						<option value="2">其它位置</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			<tr id="kindTr" style="display: none;">
			  				<td align="right">所属分组:</td>
			  				<td>
			  					<input class="easyui-combobox" name="adv.kind" id="kind" data-options="editable: false"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">广告介绍:</td>
			  				<td>
			  					<textarea name="adv.memo" id="memo" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
			  					</textarea>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">是否启用:</td>
			  				<td>
			  					<select name="adv.isuse" id="isuse">
			  						<option value="1">否</option>
			  						<option value="0">是</option>
			  					</select> 
			  				</td>
			  			</tr>
		  			</table>
					<div align="center" class="tablestyle01" style="height:50px; padding-top: 3px; ">
		 				<a href="#" class="easyui-linkbutton" onclick="advSave(); return false;">保存</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#adv_add_window').window('close'); return false;">关闭</a>
					</div>
				</form>
			</div>
			
			<div title="商品选择" id="goods_select_window" modal="true" draggable="false" class="easyui-dialog" 
				style="width: 800px; height: 500px; background-color:#EFEFEF;"  buttons="#goods_select_buts"
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<table id="form_goods_query"  dataType="text" class="tablestyle01" style="width:100%">
		  			<tr>
		  				<td align="right" style="width: 60">商品名称:</td>
		  				<td><input name="queryParams.gname" /></td>
		  			</tr>
		  		</table>
				<table id="goods_select_table" rownumbers="true" region="center" class="easyui-datagrid" fitColumns="true"
					style="width:auto;height:auto" title="" pagination="true" singleSelect="false">
					<thead>
						<tr>
							<!-- <th field="id" width="5%">商品编号</th> -->
							<th field="gname" width="49%">商品名称</th>
							<th field="categoryname" width="10%">类别</th>
							<th field="kindname" width="10%">类型</th>
							<th field="brandname" width="10%">品牌</th>
							<!-- <th field="price" width="5%">价格</th> -->
							<th field="isuse" width="10%" formatter='formatterIsuse'>状态</th>
							<th field="cl" width="10%" formatter='formatterAction'>操作</th>
						</tr>
					</thead>
				</table>
				
				<div id="goods_select_buts" align="center" style="background-color:#EFEFEF;">
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr><td align="center">
	 						<a href="#" class="easyui-linkbutton" onclick="queryGoods(); return false;">查询</a>
			 				<a href="#" class="easyui-linkbutton" onclick="$('#goods_select_window').dialog('close'); return false;">关闭</a>
						</td></tr>
					</table>
				</div>
			</div>
			
			<div title="商品类别选择" id="gcate_tree_window" modal="true" draggable="false" class="easyui-dialog" style="width: 400px; height: 340px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true" buttons="#div_gcate_tree_buttons">
				<ul id="ul_gcate_tree" class="easyui-tree"></ul>
				<div id="div_gcate_tree_buttons" align="center" style="background-color:#EFEFEF;">
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr><td align="center">
							<a href="#" class="easyui-linkbutton" onclick="selectGoodsCategory(); return false;">确定</a>
	 						<a href="#" class="easyui-linkbutton" onclick="$('#gcate_tree_window').dialog('close'); return false;">关闭</a>
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
			$("#imgurl").uploadPreview({ Img: "imgurlPr", Width: 500, Height: 100 });
			queryBanner();
		}
		
		var formatterAdvAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='showEditAdv(\""+rec.id+"\"); return false;'>查看和编辑</a>&nbsp;"
			if(rec.isuse=="0") {
				formatterStr += "<a href='#' onclick='setNotIsuse(\""+rec.id+"\",\""+rec.name+"\"); return false;'>不启用</a>&nbsp;";
			} else if(rec.isuse=="1") {
				formatterStr += "<a href='#' onclick='setIsuse(\""+rec.id+"\",\""+rec.name+"\"); return false;'>启用</a>&nbsp;";
			}
			formatterStr += "<a href='#' onclick='deleteAdv(\""+rec.id+"\",\""+rec.name+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		var formatterAdvIsuse = function(value,rec) {
			if(value=="0")
				return "是"; 
			return "否";
		}
		
		var formatterLinkKind = function(value,rec) {
			if(value=="0")
				return "商品"; 
			else if(value=="1")
				return "活动";
			else if(value=="2")
				return "其它链接";
			else if(value=="3")
				return "类别";	
			return "";
		}
		
		var formatterAdvType = function(value,rec) {
			if(value=="1")
				return "首页头部"; 
			else if(value=="2")
				return "其它位置";
			return "";
		}
		
		var showEditAdv = function(id) {
			$.ajax( {
				type: "POST",	
				url: "${basePath }/view/advertiseManager/advertiseManager!loadAdv.action",	
				data: {"adv.id": id}, dataType: "json",
				success: function(json){
					//formSet("adv_add_table", json.adv);
					$("#id").val(json.adv.id);
					$("#name").val(json.adv.name);
					$("#imgurlPr").attr("src", json.adv.imgurl);
					$("#linkkind").val(json.adv.linkkind);
					$("#imglink").val(json.adv.imglink);
					$("#imglinkLabel").val(json.adv.imglink);
					$("#ordernumb").numberspinner("setValue",json.adv.ordernumb);
					$("#type").val(json.adv.type);
					$("#kind").combobox("setValue",json.adv.kind);
					$("#memo").val(json.adv.memo);
					$("#isuse").val(json.adv.isuse);
					$("#imgurl").validatebox({required:false});
					var objId = json.adv.imglink;
					if(json.adv.imglink=="2") {
						$("#imglinkLabel").val(json.adv.imglink);
					} else {
						$.ajax({ type: "POST",  url: "${basePath }/view/advertiseManager/advertiseManager!loadObj.action",  dataType: "json",
						  	data: "objType="+json.adv.imglink+"&objId="+objId,
						  	success: function(json){
								$("#imglinkLabel").val(json.objectName);
						  	}
						});
					}
					showKind();
					$("#adv_add_window").window({title: "查看和编辑广告"});
					$("#adv_add_window").window("open");
				},	
				error: function(e) {alert("查询异常");}
			});
		}
		
		var queryAdv = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var addAdv = function() {
			formReset("adv_add_table");
			$("#adv_add_window").window({title: "新增广告"});
			$("#adv_add_window").window("open");
		}
		
		var showBtn = function() {
			var linkkind = $("#linkkind").val();
			if(linkkind=="0") {
				$("#cateBtn").css("display","none");
				$("#goodsBtn").css("display","");
				$("#eventBtn").css("display","none");
			} else if(linkkind=="1") {
				$("#cateBtn").css("display","none");
				$("#goodsBtn").css("display","none");
				$("#eventBtn").css("display","");
			} else if(linkkind=="2") {
				$("#cateBtn").css("display","none");
				$("#goodsBtn").css("display","none");
				$("#eventBtn").css("display","none");
			} else if(linkkind=="3") {
				$("#cateBtn").css("display","");
				$("#goodsBtn").css("display","none");
				$("#eventBtn").css("display","none");
			}
		}
		
		var showKind = function() {
			var type = $("#type").val();
			if(type=="2") {
				$("#kindTr").css("display","");
			} else {
				$("#kindTr").css("display","none");
			} 
		}
		
		var queryBanner = function() {
			$.ajax({
				url: "${basePath }/view/advertiseManager/advertiseManager!queryBanner.action",
				cache: false,
				dataType:"json",
				success: function(json){
					json.banner.unshift({  
               			'code': '-1',  
                  		'name': '请选择'  
                 	}); 
				    $("#kind").combobox({
				    	required:false,
				    	editable:false,
						data:json.banner,
						valueField:'code',
				    	textField:'name'
					});
				    $("#inp_kind").combobox({
				    	required:false,
				    	editable:false,
						data:json.banner,
						valueField:'code',
				    	textField:'name'
					});
					$("#inp_kind").combobox("select","-1");
				}
			});
		}
		
		var formatterAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='selectGoods(\""+rec.id+"\",\""+rec.gname+"\"); return false;'>选择</a>&nbsp;";
			return formatterStr;
		}
		
		var formatterIsuse = function(value,rec) {
			if(value=="0") {
				return "<span style='color: green;'>有效</span>";
			} else {
				return "<span style='color: red;'>无效</span>";
			}
		}
		
		var showQueryGoodsWin = function() {
			$('#goods_select_table').datagrid({   
				url:'${basePath }/view/goodsManager/goodsManager!queryGoods.action'
        	});
        	$('#goods_select_window').dialog('open');
		}
		
		var queryGoods = function() {
			var data = formGet("form_goods_query");
			$('#goods_select_table').datagrid('load',data);
		}
		
		var showQueryEventsWin = function() {
			
		}
		
		var showQueryCateInfoWin = function() {
			$.ajax( {
				type : "POST",
				url : "${basePath }/view/goodsManager/goodsCagegoryManager!goodsCategoryTree.action",
				dataType : "json",
				success : function(json) {
					var root = json.goodsCategoryTree[0];
					root.state = "open";
					//root.children[root.children.length - 1].state = "open";
					$("#ul_gcate_tree").tree("loadData", json.goodsCategoryTree);
					$("#gcate_tree_window").window("open");
				}
			});
		}
		
		var selectGoodsCategory = function() {
			var node = $('#ul_gcate_tree').tree("getSelected");
			if(node==null){
				alert("请选择一个商品类别！");
				return false;
			}
			if(node.attributes.level=="0") {
				alert("不能选择"+node.text+"！");
			}
			setImgLind(node.id, node.text);
			$("#gcate_tree_window").window("close");
		} 
		
		var selectGoods = function(id,gname) {
			setImgLind(id, gname);
			$('#goods_select_window').dialog('close');
		} 
		
		var setImgLind = function(id,text) {
			//$("#imglinkLabel").attr("required","true");
			$("#imglinkLabel").val(text);
			$("#imglink").val(id);
			/* if($("#imglinkLabel").val().length>0) {
				$("#imglinkLabel").removeAttr("required");
			} */
		}
		
		var advSave = function() {
			// 调用 form  插件的 'submit' 方法来提交 form   
			$('#ff').form('submit', {
				url: "${basePath }/view/advertiseManager/advertiseManager!save.action",
				onSubmit: function() {
					return checkForm();
				},
	      		success:function(data) {
	      			alert("操作成功！");
	          		clearForm();
	          		queryAdv();
					$("#adv_add_window").window("close");
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
		
		var clearForm = function() {
			$('#ff').form('clear');
			$("#linkkind").get(0).selectedIndex=0;
			$("#type").get(0).selectedIndex=0;
			$("#isuse").get(0).selectedIndex=0;
		}
		
		var setIsuse = function(id, name) {
			window.confirm("提示","启用："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/advertiseManager/advertiseManager!isuse.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryAdv();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var setNotIsuse = function(id, name) {
			window.confirm("提示","不启用："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/advertiseManager/advertiseManager!notIsuse.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryAdv();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var deleteAdv = function(id, name) {
			window.confirm("提示","确定删除："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/advertiseManager/advertiseManager!delete.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryAdv();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
	</script>
</html>