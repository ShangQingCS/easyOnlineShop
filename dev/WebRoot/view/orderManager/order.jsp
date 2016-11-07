<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
		<title>评论维护</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="评论列表" style="height:130px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">商品名称:</td>
	  				<td align="left"><input name="queryParams.gname"/></td>
	  				<td align="left">类别:</td>
	  				<td align="left"><input class="easyui-combobox" name="queryParams.category" id="inp_category" data-options="editable: false"/></td>
	  				<td align="left">类型:</td>
	  				<td align="left"><input class="easyui-combobox" name="queryParams.kind" id="inp_kind" data-options="editable: false"/></td>
	  				<td align="left">品牌:</td>
	  				<td align="left"><input class="easyui-combobox" name="queryParams.brand" id="inp_brand" data-options="editable: false"/></td>
	  			</tr>
	  			<tr>
	  				<td align="left">商品编号:</td>
	  				<td align="left"><input name="queryParams.goodid"/></td>
	  				<td align="left">用户id:</td>
	  				<td align="left"><input name="queryParams.userid" /></td>
	  				<td align="left">评论关键字:</td>
	  				<td align="left" colspan="3"><input name="queryParams.comment" style="width: 90%;"/></td>
	  			</tr>
	  		</table>
	  		<table border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryOrder(); return false;">查询</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 	</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="orderManager!findOrder.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="userid" width="5%">用户名</th>
						<th field="total" width="5%">总额</th>
						<th field="counts" width="5%">件数</th>
						<th field="paytype" width="5%" formatter='formatterPaytype'>支付方式</th>
						<th field="outway" width="5%" formatter='formatterOutway'>运送方式</th> 
						<th field="orderstatus" width="5%" formatter='formatterOrderstatus'>订单状态</th>
						<th field="deliveryNumb" width="5%">快递单号</th>
						<th field="createTime" width="10%" formatter='formatterDatetime'>评论时间</th>
						<th field="deliveryTime" width="5%" formatter='formatterDeliveryTime'>物流发送时间</th>
						<th field="paynumb" width="5%">支付编号</th>
						<th field="address" width="5%">地址</th>
						<th field="name" width="5%">收货人姓名</th>
						<th field="contactnumb" width="5%">联系方式</th>
						<th field="invoice" width="5%" formatter='formatterInvoice'>是否发票</th>
						<th field="companyname" width="5%">抬头</th>
						<th field="content" width="20%">发票内容</th>
						<th field="cz" width="15%" formatter='formatterAction'>操作</th>
					</tr>
				</thead>
			</table>
			
			<div title="用户信息" id="user_detail_window" modal="true" draggable="false" class="easyui-window" style="width: 800px; height: 600px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<table id="order_datail_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
		  			<tr>
		  				<td align="right">用户名:</td>
		  				<td><input name="userid" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">商品编号:</td>
		  				<td><input name="goodid" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">商品名称:</td>
		  				<td><input name="gname" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">类别:</td>
		  				<td><input name="categoryName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">类型:</td>
		  				<td><input name="kindName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">品牌:</td>
		  				<td><input name="brandName" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">评论时间:</td>
		  				<td><input name="createTime" id="createTime" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">是否匿名:</td>
		  				<td><input name="ishidden" id="ishidden" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">评分:</td>
		  				<td><input name="score" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width: 80px">描述:</td>
		  				<td colspan="3">
		  					<textarea name="comment" readonly="readonly" style="width: 100%;" rows="15"></textarea>
		  				</td>
		  			</tr>
	  			</table>
				<div align="center" class="tablestyle01">
	 				<a href="#" class="easyui-linkbutton" onclick="$('#user_detail_window').window('close'); return false;">关闭</a>
				</div>
			</div>
		</div>
  	</body>
  
  	<script type="text/javascript">
		$(function(){
			initPage();
		});
		
		var initPage = function() {
			//queryCategorys();
		}
		
		var formatterPaytype = function(value,rec) {
			if(value=="1")
				return "账户";
			else if(value=="2")  
				return "微信";
			else if(value=="3")
				return "支付宝";  
			return "";
		}
		var formatterOutway = function(value,rec) {
			if(value=="1")
				return "快递"; 
			return "";
		}
		var formatterOrderstatus = function(value,rec) {
			if(value=="1")
				return "待付款";
			else if(value=="2")  
				return "已付款";
			else if(value=="3")
				return "已发货";  
			return "";
		}
		var formatterInvoice = function(value,rec) {
			if(value=="1")
				return "是";
			return "否";
		}
		
		var formatterAction = function(value,rec) {
			var formatterStr = "<a href='#' onclick='showOrderDetail(\""+rec.id+"\"); return false;'>查看详细</a>&nbsp;"
			formatterStr+="<a href='#' onclick='delComment(\""+rec.id+"\"); return false;'>删除</a>&nbsp;";
			return formatterStr;
		}
		
		var queryOrder = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var showOrderDetail = function(id) {
			$.ajax( {
				type: "POST",	
				url: "${basePath }/view/orderManager/orderManager!loadOrder.action",	
				data: {"order.id": id}, dataType: "json",
				success: function(json){
				    /* formSet("order_datail_table", json.user);
					$("#order_datail_table").window("open"); */
				},
				error: function(e) {alert("查询异常");}
			});
		}
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
		/* var showComment = function() {
			var row = $('#tab_list').datagrid('getSelected');
			if (row) {
				formSet("order_datail_table", row);
				$("#createTime").val(row.createTime.replace("T"," "));
				$("#ishidden").val(row.ishidden=="1"?"不匿名":"匿名");
				$("#user_detail_window").window("open");
			}
		}
		
		var delComment = function(id) {
			window.confirm("提示","确认删除该评论?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/commentManager/commentManager!deleteComment.action",  dataType: "json",
					  	data: "id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("删除评论成功！");
								queryOrder();
							}else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		} */
	</script>
</html>