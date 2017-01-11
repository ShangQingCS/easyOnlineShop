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
			queryUserCoupon();
		}
		
		var queryUserCoupon = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/coupon/userCoupon!queryNsUserCoupon.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		var formatterFlag = function(value,rec) {
			if(value=="0") {//0未使用1已使用2作废
				return "<select onchange='changeStatus(this,\""+value+"\",\""+rec.id+"\")'><option value='0' selected='selected'>未使用</option><option value='1'>已使用</option><option value='2'>作废</option></select>";
			} else if(value=="1"){
				return "<select onchange='changeStatus(this,\""+value+"\",\""+rec.id+"\")'><option value='0'>未使用</option><option value='1' selected='selected'>已使用</option><option value='2'>作废</option></select>";
			}else if(value=="2"){
				return "<select onchange='changeStatus(this,\""+value+"\",\""+rec.id+"\")'><option value='0' >未使用</option><option value='1'>已使用</option><option value='2' selected='selected'>作废</option></select>";
			}else{
				return "";
			}
		}
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
		var changeStatus = function(selectobj,value,id) {
			//selectobj.selectedIndex	updateNsUserCoupon
			alert(selectobj.selectedIndex);
			$.ajax({
					url: "${basePath }/view/coupon/userCoupon!updateNsUserCoupon.action",
					cache: false,
					dataType:"json",
					data: "id="+id+"&couponStatus="+selectobj.selectedIndex,
					success: function(json){
						
						queryUserCoupon();
					}
				});
		}
		
		
		
    		
    		
    	</script>
		<title>优惠卷发放明细管理</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="会员操作" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">用户名:<input name="queryParams.user_name"  /></td>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserCoupon(); return false;">查询</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
	  			</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userCoupon!queryNsUserCoupon.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- onDblClickRow:showdictionariesDetailRow -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userName" width="15%">用户名</th>
						<th field="couponName" width="15%">优惠卷名称</th>
						<th field="couponBlance" width="15%">优惠券面值</th>
						<th field="couponXfBalance" width="15%">满额使用条件</th>
						<th field="couponExpirydate" width="22%" formatter='formatterDeliveryTime'>有效期</th>
						<th field="couponStatus" width="15%" formatter='formatterFlag'>状态</th>
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>