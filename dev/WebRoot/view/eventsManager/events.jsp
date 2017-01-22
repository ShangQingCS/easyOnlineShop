<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<title>活动管理</title>
  	</head>
  	
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" style="height:130px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">活动名称:</td>
	  				<td align="left"><input name="queryParams.name"/></td>
	  				<td align="left">起始时间:</td>
	  				<td align="left"><input name="queryParams.startTime" class="easyui-datebox" editable="false" id="inp_startTime"  required="true"/></td>
	  				<td align="left">结束时间:</td>
	  				<td align="left"><input name="queryParams.endTime"  class="easyui-datebox" editable="false" id="inp_endTime"  required="true"/></td>
	  				<td align="left">是否有效:</td>
	  				<td align="left"><select name="queryParams.isuse" id="inp_isuse">
	  								<option value="">请选择</option>
			  						<option value="1">否</option>
			  						<option value="0">是</option>
			  					</select> </td>
	  			</tr>
	  		</table>
	  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryEvents(); return false;">查询</a>
	  					<a href="#" class="easyui-linkbutton" onclick="showAddEventsWin(); return false;">新增</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 	</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="eventsManager!queryEvents.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options="onDblClickRow:showEventsDetailRow">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="name" width="29%">活动名称</th>
						<th field="startTime" width="10%" formatter='formatterDate'>活动开始时间</th>
						<th field="endTime" width="10%" formatter='formatterDate'>活动结束时间</th>
						<!-- <th field="brandName" width="5%">品牌</th>
						<th field="createTime" width="10%" formatter='formatterDatetime'>评论时间</th>
						<th field="comment" width="20%">评论内容</th>
						<th field="ishidden" width="5%" formatter='formatterIsHidden'>是否匿名</th>
						<th field="score" width="5%">评分</th>-->
						<th field="isuse" width="10%" formatter='formatterIsuse'>是否启用</th>
						<th field="cz" width="15%" formatter='formatterEventsAction'>操作</th> 
					</tr>
				</thead>
			</table>
			
			<div title="新增活动" id="events_add_window" modal="true" draggable="false" class="easyui-window" style="width: 800px; height: 600px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="events_add_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<td align="right" width="100">活动名称:</td>
			  				<td colspan="3">
			  					<input type="hidden" name="eve.id" id="id" /> 
			  					<input name="eve.name" id="name" style="width: 100%;" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">开始时间:</td>
			  				<td>
	  							<input name="eve.startTime" id="startTime" class="easyui-datebox" editable="false" required="true" style="width: 100px;"/>
			  				</td>
			  				<td align="right">结束时间:</td>
			  				<td>
			  					<input name="eve.endTime" id="endTime" class="easyui-datebox" editable="false" required="true" style="width: 100px;"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">图片预览:</td>
			  				<td colspan="3"><img id="minpicturePr" width="500" height="100" /> </td>
			  			</tr>
			  			<tr>
			  				<td align="right">小图:</td>
			  				<td colspan="3">
			  					<input type="file" style='width: 200px;' name="minpicture" id="minpicture" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">图片预览:</td>
			  				<td colspan="3"><img id="picturePr" width="500" height="100" /> </td>
			  			</tr>
			  			<tr>
			  				<td align="right">大图:</td>
			  				<td colspan="3">
			  					<input type="file" style='width: 200px;' name="picture" id="picture" class="easyui-validatebox" required="true"/> 
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td align="right">活动介绍:</td>
			  				<td colspan="3">
			  					<textarea name="eve.memo" id="memo" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
			  					</textarea>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right">是否启用:</td>
			  				<td colspan="3">
			  					<select name="eve.isuse" id="isuse">
			  						<option value="1">否</option>
			  						<option value="0">是</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			<td align="right">活动商品:</td>
			  				<td colspan="2" id="events_goods">
			  					
			  				</td>
			  				<td>
			  					<input type="hidden" name="events_goods_list" id="events_goods_list" />
			  					<input type="hidden" name="events_id" id="events_id" value=""/>
			  				</td>
			  			<tr>
			  				<td align="right">
			  					
			  				</td>
			  				<td colspan="3">
			  					<a href="#" class="easyui-linkbutton" onclick="showGoodsDialog(); return false;" id="chooseGoodsBtn">选择商品</a>
			  				</td>
			  			</tr>
		  			</table>
					<div align="center" class="tablestyle01" style="height:50px; padding-top: 3px; ">
		 				<a href="#" class="easyui-linkbutton" onclick="eventsSave(); return false;">保存</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#events_add_window').window('close'); return false;">关闭</a>
					</div>
				</form>
			</div>
			
			<!-- 活动商品选择 -->
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
							<th field="cl" width="10%" formatter='formatterActionGoods'>操作</th>
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
		</div>
  	</body>
  
  	<script type="text/javascript">
		$(function(){
			initPage();
		});
		
		var initPage = function() {
			$("#minpicture").uploadPreview({ Img: "minpicturePr", Width: 500, Height: 100 });
			$("#picture").uploadPreview({ Img: "picturePr", Width: 500, Height: 100 });
		}
		
		var queryEvents = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var formatterEventsAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='showEditEventsWin(\""+rec.id+"\"); return false;'>查看和编辑</a>&nbsp;"
			if(rec.isuse=="0") {
				formatterStr += "<a href='#' onclick='setNotIsuse(\""+rec.id+"\",\""+rec.name+"\"); return false;'>不启用</a>&nbsp;";
			} else if(rec.isuse=="1") {
				formatterStr += "<a href='#' onclick='setIsuse(\""+rec.id+"\",\""+rec.name+"\"); return false;'>启用</a>&nbsp;";
			}
			formatterStr += "<a href='#' onclick='deleteEvents(\""+rec.id+"\",\""+rec.name+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		var showAddEventsWin = function() {
			formReset("events_add_table");
			$("#events_add_window").window({title: "新增活动"});
			$("#events_add_window").window("open");
		}
		
		var eventsSave = function() {
			// 调用 form  插件的 'submit' 方法来提交 form   
			$('#ff').form('submit', {
				url: "${basePath }/view/eventsManager/eventsManager!save.action",
				onSubmit: function() {
					return checkForm();
				},
	      		success:function(data) {
	      			alert("操作成功！");
	          		clearForm();
	          		queryEvents();
					$("#events_add_window").window("close");
	     		}
	 		}); 
		}
		
		//表单验证
		var checkForm = function() {
			var check = $('#ff').form('validate');
			if(!check) {
				return false;
			}
			
			var st = $("#startTime").datebox("getValue");
			var et = $("#endTime").datebox("getValue");
			if(parseDate(st)>parseDate(et)) {
				alert("开始时间不能大于结束时间！");
				return false;
			}
			
			return true;
		}
		//选择产品
		var showGoodsDialog=function(){
			$('#goods_select_table').datagrid({   
				url:'${basePath }/view/goodsManager/goodsManager!queryGoods.action'
        	});
        	$('#goods_select_window').dialog('open');
		}
		//产品状态
		var formatterIsuse = function(value,rec) {
			if(value=="0") {
				return "<span style='color: green;'>有效</span>";
			} else {
				return "<span style='color: red;'>无效</span>";
			}
		}
		//选择产品操作
		var formatterActionGoods = function(value,rec) {
			var formatterStr = "<a href='#' onclick='selectGoods(\""+rec.id+"\",\""+rec.gname+"\"); return false;'>选择</a>&nbsp;";
			return formatterStr;
		}
		//产品添加方法
		var selectGoods = function(id,gname) {
			setImgLind(id,gname);
			$('#goods_select_window').dialog('close');
		} 
		//产品添加
		var setImgLind = function(id,gname) {
			 var idlist=$("#events_goods_list").val();
			 var namelist=$("#events_goods").html();
			 
			 var ishave=true;
			 if(idlist!=""){
			 	var arraygoods=idlist.split(",");
			 	for(var i = 0;i<arraygoods.length;i++){
					var nowgoods=arraygoods[i];	
					if(nowgoods == id){
						ishave=false;
					}
				}
			 }
			 if(ishave){
			 	idlist=id+","+idlist;
				namelist="<div class='"+id+"'><em>"+gname+"</em>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeChoose(\""+id+"\"); return false;' >x</a><br></div>"+namelist;	
			 }
			 $("#events_goods_list").val(idlist);
			 
			 $("#events_goods").html(namelist);
		}
		
		var clearForm = function() {
			$('#ff').form('clear');
		}
		
		var removeChoose = function(id){
			$("."+id).remove();
			var goodslist=$("#events_goods_list").val();
			var arraygoods=goodslist.split(",");
			var newgoodslist="";
			for(var i = 0;i<arraygoods.length;i++){
				var nowgoods=arraygoods[i];	
				if(nowgoods != id){
					newgoodslist=nowgoods+","+newgoodslist;
				}
			}
			$("#events_goods_list").val(newgoodslist);
		}
		
		var showEventsDetailRow = function() {
			var row = $('#tab_list').datagrid('getSelected');
			if (row) {
				showEditEventsWin(row.id);
			}
		}
		
		var showEditEventsWin = function(id) {
			$("#minpicture").uploadPreview({ Img: "minpicturePr", Width: 500, Height: 100 });
			$("#picture").uploadPreview({ Img: "picturePr", Width: 500, Height: 100 });
			$.ajax( {
				type: "POST",	
				url: "${basePath }/view/eventsManager/eventsManager!loadEvents.action",	
				data: {"eve.id": id}, dataType: "json",
				success: function(json){
					$("#id").val(json.eve.id);
					$("#name").val(json.eve.name);
					$("#memo").val(json.eve.memo);
					$("#isuse").val(json.eve.isuse);
					$("#minpicturePr").attr("src", "${imgPathPrefix}/"+json.eve.minpicture);
					$("#picturePr").attr("src", "${imgPathPrefix}/"+json.eve.picture);
					$("#startTime").datebox("setValue", formatterDate(json.eve.startTime,null,null));
					$("#endTime").datebox("setValue", formatterDate(json.eve.endTime,null,null));
					$("#minpicture").validatebox({required:false});
					$("#picture").validatebox({required:false});
					$("#events_id").val(id);
					var idlist="";
					var namelist="";
					var objlist=json.evegslist;
					if(objlist){
						for(var i=0;i<objlist.length;i++){
							var eventsgoods=objlist[i];
							idlist=eventsgoods.goodsId+","+idlist;
							namelist="<div class='"+eventsgoods.goodsId+"'><em>"+eventsgoods.gname+"</em>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='removeChoose(\""+eventsgoods.goodsId+"\"); return false;' >x</a><br></div>"+namelist;	
						}
					}
					$("#events_goods_list").val(idlist);
					$("#events_goods").html(namelist);
					
					$("#events_add_window").window({title: "查看和编辑活动"});
					$("#events_add_window").window("open");
				},	
				error: function(e) {alert("查询异常");}
			});
		}
		
		var setIsuse = function(id, name) {
			window.confirm("提示","启用："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/eventsManager/eventsManager!isuse.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryEvents();
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
					$.ajax({ type: "POST",  url: "${basePath }/view/eventsManager/eventsManager!notIsuse.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryEvents();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var deleteEvents = function(id, name) {
			window.confirm("提示","确定删除："+name+"?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/eventsManager/eventsManager!delete.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryEvents();
							} else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var formatterIsuse = function(value,rec) {
			if(value=="0")
				return "是"; 
			return "否";
		}
	</script>
</html>
