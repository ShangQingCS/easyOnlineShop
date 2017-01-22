<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
		<title>物流信息录入</title>
		<!-- <style> .datagrid-cell-rownumber{ width:50px; text-align:center; margin:0px; padding:3px 0px; color:#000; } .datagrid-header-rownumber{ width:50px; text-align:center; margin:0px; padding:3px 0px; } </style> -->
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" style="height:130px; width:100%">
	  		<table id="from_query" border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">用户名:</td>
	  				<td align="left"><input name="queryParams.userid"/></td>
	  				<td align="left">收货人姓名:</td>
	  				<td align="left"><input name="queryParams.name"/></td>
	  				<td align="left">联系方式:</td>
	  				<td align="left"><input name="queryParams.contactnumb"/></td>
	  				<td align="left">支付编号:</td>
	  				<td align="left"><input name="queryParams.paynumb"/></td>
	  			</tr>
	  			<tr>
	  				<td align="left">订单状态 :</td>
	  				<td align="left">
	  					<select class="easyui-combobox" style="width:100px;" name="queryParams.orderstatus">
	  						<option value="2,3" selected="selected">请选择</option>
	  						<option value="2">已付款</option>
	  						<option value="3">已发货</option>
	  					</select>
	  				</td>
	  				<td align="left">快递单号:</td>
	  				<td align="left"><input name="queryParams.deliveryNumb"/></td>
	  				<td align="left">下单时间:</td>
	  				<td align="left">
	  					<input name="queryParams.startCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					-
	  					<input name="queryParams.endCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  				</td>
	  				<td align="left">发货时间:</td>
	  				<td align="left">
	  					<input name="queryParams.startDeliveryTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					-
	  					<input name="queryParams.endDeliveryTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  				</td>
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
		    	url="${basePath }/view/orderManager/orderManager!findOrder.action" style="width:auto;height:auto" title=""
		    	data-options="queryParams:{'queryParams.orderstatus':'2,3'},onDblClickRow:showOrderDetailRow" 
		    	pagination="true" singleSelect="true">
				<thead>
					<tr>
						<!-- <th style="display: block;" checkbox="true" field="id" width="5%">ID</th> -->
						<th field="id" width="5%">订单编号</th>
						<th field="userid" width="9%">用户名</th>
						<th field="paytype" width="5%" formatter='formatterPaytype'>支付方式</th>
						<th field="deliveryTime" width="8%" formatter='formatterDeliveryTime'>发货时间</th>
						<th field="createTime" width="8%" formatter='formatterDatetime'>下单时间</th>
						<th field="paynumb" width="10%">支付编号</th>
						<th field="name" width="10%">收货人姓名</th>
						<th field="contactnumb" width="10%">联系方式</th>
						<th field="orderstatus" width="5%" formatter='formatterOrderstatus'>订单状态</th>
						<th field="deliveryNumb" width="14%">快递单号</th>
						<th field="cz" width="15%" formatter='formatterAction'>操作</th>
						<!-- <th field="invoice" width="60" formatter='formatterInvoice'>是否发票</th>
						<th field="companyname" width="150">抬头</th>
						<th field="content" width="100">发票内容</th>
						<th field="address" width="300">地址</th> 
						<th field="outway" width="60" formatter='formatterOutway'>运送方式</th>
						<th field="total" width="100">总额</th>
						<th field="counts" width="50">件数</th> -->
					</tr>
				</thead>
			</table>
			
			<div title="编辑单号" id="div_window_new" class="easyui-window" modal="true" draggable="false" 
				buttons="#buts_window_new" style="width: 350px; height: 130px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<div style="height: 100%;" class="bgColor">
				<table id="form_edit" action="${basePath }/view/orderManager/orderManager!editOrderNum.action" class="tablestyle01" style="width:100%">
		  			<tr>
		  				<td align="right" style="width:80px;">快递单号:</td>
		  				<td>
		  					<input type="hidden" name="order.id" id="inp_id"/>
		  					<input name="order.deliveryNumb" id="inp_deliveryNumb" style="width:95%;"/>
		  				</td>
		  			</tr>
		  		</table>
		  		<div id="buts_window_new" align="right" >
		  			<a href="#" class="easyui-linkbutton" onclick="editOrderSubmit(null); return false;">提交</a>
		  			<a href="#" class="easyui-linkbutton" onclick="$('#div_window_new').dialog('close');return false;">关闭</a>&nbsp;
		  		</div>
		  		</div>
			</div>
			
			<div title="查看订单详细信息" id="order_detail_window" modal="true" draggable="false" class="easyui-window" style="width: 900px; height: 80%; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<table id="order_datail_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
		  			<tr>
		  				<td align="right">用户名:</td>
		  				<td><input name="userid" readonly="readonly" style="width: 100%;"/> </td>
		  				<td align="right">总额:</td>
		  				<td><input name="total" readonly="readonly" style="width: 100px;"/> </td>
		  				<td align="right">件数:</td>
		  				<td><input name="counts" readonly="readonly" style="width: 50px;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">订单状态:</td>
		  				<td><input name="orderstatus" id="orderstatus" readonly="readonly" style="width: 50px;"/> </td>
		  				<td align="right">支付方式:</td>
		  				<td><input name="paytype" id="paytype" readonly="readonly" style="width: 50px;"/> </td>
		  				<td align="right">是否发票:</td>
		  				<td><input name="invoice" id="invoice" readonly="readonly" style="width: 50px;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">下单时间:</td>
		  				<td><input name="createTime" id="createTime" readonly="readonly" style="width: 100%;"/> </td>
		  				<td align="right">发货时间:</td>
		  				<td><input name="deliveryTime" id="deliveryTime" id="ishidden" readonly="readonly" style="width: 100%;"/> </td>
		  				<td align="right">快递单号:</td>
		  				<td><input name="deliveryNumb" id="deliveryNumb" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">支付编号:</td>
		  				<td><input name="paynumb" id="paynumb" readonly="readonly" style="width: 100%;"/> </td>
		  				<td align="right">收货人姓名:</td>
		  				<td><input name="name" id="name" readonly="readonly" style="width: 100%;"/> </td>
		  				<td align="right">联系方式:</td>
		  				<td><input name="contactnumb" readonly="readonly" style="width: 100%;"/> </td>
		  			</tr>
		  			<tr>
		  				<td align="right">抬头:</td>
		  				<td><input name="companyname" id="companyname" readonly="readonly" style="width: 100%;"/> </td>
		  				<td align="right">运送方式 :</td>
		  				<td><input name="outway" id="outway" readonly="readonly" style="width: 50px;"/> </td>
		  				<td align="right">&nbsp;</td>
		  				<td>&nbsp;</td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width: 80px">发票内容:</td>
		  				<td colspan="5">
		  					<input name="content" id="content" readonly="readonly" style="width: 100%;"/>
		  				</td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width: 80px">收货地址:</td>
		  				<td colspan="5">
		  					<input name="address" readonly="readonly" style="width: 100%;"/>
		  				</td>
		  			</tr>
		  			<tr>
		  				<td align="right" style="width: 80px" valign="top">商品明细:</td>
		  				<td colspan="5">
		  					<table id="order_detail_list" rownumbers="true" region="center" fitColumns="false" class="easyui-datagrid" 
						    	style="width:auto;height:auto" title="" rownumbers="false"
						    	pagination="false" singleSelect="true">
								<thead>
									<tr>
										<th field="gname" width="63%">商品名称</th>
										<th field="categoryName" width="10%">类别</th>
										<th field="kindName" width="10%">类型</th>
										<th field="brandName" width="10%">品牌</th>
									</tr>
								</thead>
							</table>
		  				</td>
		  			</tr>
	  			</table>
	  			
				<div align="center" class="tablestyle01">
					<br/>
	 				<a href="#" class="easyui-linkbutton" onclick="$('#order_detail_window').window('close'); return false;">关闭</a>
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
			var formatterStr = "<a href='#' onclick='showOrderDetail(\""+rec.id+"\"); return false;'>查看详细</a>&nbsp;";
			formatterStr+="<a href='#' onclick='editOrder(\""+rec.id+"\",\""+rec.deliveryNumb+"\"); return false;'>编辑单号</a>&nbsp;";
			if(rec.orderstatus=="2" && rec.deliveryNumb!=""){  
				formatterStr+="<a href='#' onclick='changeStatus(\""+rec.id+"\",\""+rec.deliveryNumb+"\"); return false;'>发货</a>&nbsp;";
			}
			return formatterStr;
		}
		
		var queryOrder = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
		}
		
		var showOrderDetailRow = function() {
			var row = $('#tab_list').datagrid('getSelected');
			if (row) {
				showOrderDetail(row.id);
			}
		}
		
		var showOrderDetail = function(id) {
			$.ajax( {
				type: "POST",	
				url: "${basePath }/view/orderManager/orderManager!loadOrder.action",	
				data: {"order.id": id}, dataType: "json",
				success: function(json){
				    formSet("order_datail_table", json.order);
				    $("#orderstatus").val(formatterOrderstatus(json.order.orderstatus,null));
				    $("#paytype").val(formatterPaytype(json.order.paytype,null));
				    $("#invoice").val(formatterInvoice(json.order.invoice,null));
				    $("#outway").val(formatterOutway(json.order.outway,null));
				    $("#deliveryTime").val(formatterDeliveryTime(json.order.deliveryTime,null));
				    $("#createTime").val(formatterDeliveryTime(json.order.createTime,null));
				    $('#order_detail_list').datagrid("loadData", json.orderDetail);  
					$("#order_detail_window").window("open");
				},
				error: function(e) {alert("查询异常");}
			});
		}
		
		var changeStatus = function(id,deliveryNumb) {
			debugger;
			if(deliveryNumb.length==0||deliveryNumb=="null") {
				alert("快递单号未填写！");
				return;
			} 
        	window.confirm("提示","确认将快递单号为："+deliveryNumb+" 的订单变更为已发货?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/orderManager/orderManager!changeStatus.action",  dataType: "json",
					  	data: "order.id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryOrder();
							}else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var cancelStatus = function(id,deliveryNumb) {
        	window.confirm("提示","确认取消发货?",function(r){
				if(r){
					$.ajax({ type: "POST",  url: "${basePath }/view/orderManager/orderManager!cancelStatus.action",  dataType: "json",
					  	data: "order.id="+id,
					  	success: function(json){
							if(json.message=='success'){
								alert("操作成功！");
								queryOrder();
							}else if(json.message!=null && json.message!=''){
								alter(json.message);
							}
					  	}
					});
				}
			});
		}
		
		var editOrder = function(id,deliveryNumb) { 
        	formReset("form_edit");
        	$("#inp_id").val(id);
        	if(deliveryNumb=="null"){
        		$("#inp_deliveryNumb").val("");
        	}else{
        		$("#inp_deliveryNumb").val(deliveryNumb);
        	}
        	
			$("#div_window_new").dialog("open");
		}
		
		var editOrderSubmit = function(json) {
			if(json==null){
				formSubmit("form_edit",editOrderSubmit);
			}else{
				alert("保存成功");
				$('#div_window_new').dialog('close');
				queryOrder();
			}
		}
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
	</script>
</html>