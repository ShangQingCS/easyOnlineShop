<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.config.Config"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
    	<script type="text/javascript" charset="utf-8" src="${basePath }/js/test.js"></script>
    	<script type="text/javascript">
    		
   		$(function(){
			initPage();
		});
		
		var initPage = function() {
			queryNsCoupon();
			$("#coupon_status").change(function(){ 
				queryNsCoupon();
			});
		}
		
		
		
		var queryNsCoupon = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/coupon/coupon!queryNsCoupon.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		var formatteruser = function(value,rec) {
			var formatterStr = "";
			formatterStr += "<a href='#' onclick='queryCouponById(\""+rec.id+"\",\""+rec.name+"\"); return false;'>编辑</a>&nbsp;";
			return formatterStr;
		}
		
		var formatterStatus = function(value,rec) {
			if(value=="1") {//0作废1可用
				return "<span style='color: green;'>可用</span>";
			} else if(value=="0"){
				return "<span style='color: red;'>作废</span>";
			}else{
				return "";
			}
		}
		
		var formatterType = function(value,rec) {
			if(value=="1") {// 0充值1消费2都可
				return "<span style='color: green;'>消费</span>";
			} else if(value=="0"){
				return "<span style='color: green;'>充值</span>";
			}else if(value=="2"){
				return "<span style='color: green;'>都可</span>";
			}else{
				return "";
			}
		}
		
		var addNsCoupon = function() {
			$("#ff")[0].reset();
			$("#id").val(null);
			$("#userinfo_window").window({title: "新增优惠券信息"});
			$("#userinfo_window").window("open");
		}
		
		
		//打开编辑页面
		var queryCouponById = function(id,name) {
			$.ajax({ 
					type: "POST",  
					url: "${basePath }/view/coupon/coupon!queryCouponById.action",  
					dataType: "json",
				  	data: "id="+id,
				  	success: function(json){
						if(json.messages=='success'){
							$("#id").val(json.coupon.id);
							$("#coupon_name").val(json.coupon.coupon_name);
							$("#coupon_type").val(json.coupon.coupon_type);
							$("#coupon_zs_balance").val(json.coupon.coupon_zs_balance);
							$("#coupon_blance").val(json.coupon.coupon_blance);
							$("#coupon_xf_balance").val(json.coupon.coupon_xf_balance);
							$("#coupon_expiry_date").val(json.coupon.coupon_expiry_date);
							$("#create_time").datebox("setValue", json.coupon.create_time);
							$("#couponStatus").val(json.coupon.coupon_status);//select
							$("#coupon_remark").val(json.coupon.coupon_remark);
							$("#userinfo_window").window({title: "优惠券信息编辑"});
							$("#userinfo_window").window("open");
						} else if(json.message!=null && json.message!=''){
							alert(json.message);
						}
				  	}
			});
			
		}
		
		//保存用户信息
		var operateFlag = function() {
			$("#ff").form("submit", {
				url: "${basePath }/view/coupon/coupon!updateNsCoupon.action",
				onSubmit: function() {
					return checkForm();
				},
	      		success:function(data) {
	      			alert("操作成功！");
					queryNsCoupon();//初始化优惠券信息列表
					$("#userinfo_window").window("close");
	     		}
	 		});  
		}
		
		//表单验证
		var checkForm = function() {
			var check = $("#ff").form("validate");
			if(!check) {
				return false;
			}
			return true;
		}
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
    		
    	</script>
		<title>优惠券管理</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="优惠券管理" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="addNsCoupon(); return false;">新增</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
			 		<td align="left">
	  					状态:
	  					<select id="coupon_status" class=”easyui-combobox” name="queryParams.coupon_status" style="width: 100px">
	  						<option value = "">全部</option>
							<option value = "1">可用</option>
							<option value = "0">作废</option>
						</select>
	  				</td>
	  			</tr>
	  		</table>
	  	</div>
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="coupon!queryNsCoupon.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- onDblClickRow:showdictionariesDetailRow -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="couponName" width="8%">优惠卷名称</th>
						<th field="couponType" width="10%" formatter='formatterType' >优惠卷类型</th>
						<th field="couponZsBalance" width="10%">满额赠送</th>
						<th field="couponBlance" width="15%">优惠券面值</th>
						<th field="couponXfBalance" width="10%">满额使用条件</th>
						<th field="couponExpiryDate" width="10%">有效期(天)</th>
						<th field="couponRemark" width="10%">说明</th>
						<th field="createTime" width="10%" formatter='formatterDeliveryTime'>创建时间</th>
						<th field="couponStatus" width="5%" formatter='formatterStatus'>状态</th>
						<th field="cz" width="10%" formatter='formatteruser'>操作</th> 
					</tr>
				</thead>
			</table>
		</div>
		
		<div title="优惠券信息编辑" id="userinfo_window" modal="true" draggable="false" class="easyui-window" style="width: 500px; height: 600px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="userinfo_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<input type="hidden" id="id" name="coupon.id" />
			  				<td align="right" width="100">优惠券名称:</td>
			  				<td >
			  					<input type="text" id="coupon_name" name="coupon.coupon_name" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">优惠券类型:</td>
			  				<td >
			  					<select name="coupon.coupon_type" id="coupon_type">
			  						<option value="0">充值</option>
			  						<option value="1">消费</option>
			  						<option value="2">都可</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">满额赠送:</td>
			  				<td >
			  					<input type="text" id="coupon_zs_balance" name="coupon.coupon_zs_balance" />
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td align="right" width="100">优惠券面值:</td>
			  				<td >
			  					<input type="text" id="coupon_blance" name="coupon.coupon_blance" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">满额使用条件:</td>
			  				<td >
			  					<input type="text" id="coupon_xf_balance" name="coupon.coupon_xf_balance" />
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td align="right" width="100">有效期(天):</td>
			  				<td >
			  					<input type="text" id="coupon_expiry_date" name="coupon.coupon_expiry_date"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">创建时间:</td>
			  				<td >
			  					<input id="create_time" name="coupon.create_time" class="easyui-datebox" editable="false" style="width: 100px;"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">状态:</td>
			  				<td >
			  					<select name="coupon.coupon_status" id="couponStatus">
			  						<option value="0">作废</option>
			  						<option value="1">可用</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">说明:</td>
			  				<td >
			  					<textarea name="coupon.coupon_remark" id="coupon_remark" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
			  					</textarea> 
			  				</td>
			  			</tr>
		  			</table>
					<div align="center" class="tablestyle01" style="height:50px; padding-top: 3px; ">
		 				<a href="#" class="easyui-linkbutton" onclick="operateFlag(); return false;">保存</a>
		 				<a href="#" class="easyui-linkbutton" onclick="$('#userinfo_window').window('close'); return false;">关闭</a>
					</div>
				</form>
		</div>
		
  	</body>
</html>