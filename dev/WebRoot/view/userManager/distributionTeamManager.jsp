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
			queryNsUserTeam();
			$("#content").hide();
			$("#footer").hide();
		}
		
		//表单验证
		var checkForm = function() {
			var f_userName = $("#f_userName").val();
			var f_trueName = $("#f_trueName").val();
			var f_userPhone = $("#f_userPhone").val();
			var f_identityCard = $("#f_identityCard").val();
			if(f_userName==""&&f_trueName==""&&f_userPhone==""&&f_identityCard==""){
				alert("至少填写一项条件");
				return false;
			}
			return true;
		}
		
		var queryNsUserTeam = function() {
			var data = formGet("userinfo_table");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/userManager/userManager!queryNsUserTeam.action",  
				cache: false,
				dataType:"json",
				data:$("#ff").serialize(),
				success: function(json){
					//do nothing
				}
			});
		}
		
		var queryUserManager = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			if(checkForm()){
				$.ajax({
					url: "${basePath }/view/userManager/userManager!queryNsUserByCondition.action",  
					cache: false,
					data:$("#ff1").serialize(),
					dataType:"json",
					success: function(json){
						if(json.nsUser!=null){
		      				$("#content").show();//显示div
							$("#footer").show();//显示div
		      				$("#id").val(json.nsUser.id);
							$("#userName").val(json.nsUser.user_name);
							$("#userKyBalance").val(json.nsUser.user_ky_balance);
							$("#trueName").val(json.nsUser.true_name);
							$("#userFxBalance").val(json.nsUser.user_fx_balance);
							$("#identityCard").val(json.nsUser.identity_card);
							$("#identityCardValidity").datebox("setValue", json.nsUser.identity_card_validity);
							$("#userJfBalance").val(json.nsUser.user_jf_balance);
							$("#userStatus").val(json.nsUser.user_status);
		      			}
		      			queryNsUserTeam();
					}
				});
			}
			
		}
		
		var formatterFlag = function(value,rec) {
			if(value=="1") {
				return "<span style='color: green;'>正常</span>";
			} else if(value=="0"){
				return "<span style='color: red;'>注销</span>";
			}else if(value=="2"){
				return "<span style='color: red;'>冻结</span>";
			}else{
				return "";
			}
		}
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
    	</script>
		<title>分销团队管理</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false"  style="height:100px; width:100%">
	  		<form id="ff1" method="post" enctype="multipart/form-data">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">用户名:<input id="f_userName" name="queryParams.user_name"  /></td>
	  				<td align="left">真实姓名:<input id="f_trueName" name="queryParams.true_name"  /></td>
	  				<td align="left">手机号 :<input id="f_userPhone" name="queryParams.user_phone"  /></td>
	  				<td align="left">身份证号 :<input id="f_identityCard" name="queryParams.identity_card"  /></td>
	  				</tr>
	  				<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserManager(); return false;">查询</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
	  			</tr>
	  		</table>
	  		</form>
	  	</div>
	  	
	  	<div id="content" region="center" style="width: 100%;" ><!--  -->
	  		<form id="ff" method="post" enctype="multipart/form-data">
	  			<table id="userinfo_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
	  				<tr>
	  					<td>
	  						<input type="hidden" id="id" name="id" />
	  						登录帐号:<input type="text" id="userName" name="user_name" readonly="readonly" />
	  					</td>
	  					<td>
	  						可用金额:<input type="text" id="userKyBalance" name="user_ky_balance" readonly="readonly" />
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						真实姓名:<input type="text" id="trueName" name="true_name" readonly="readonly" />
	  					</td>
	  					<td>
	  						分销收益:<input type="text" id="userFxBalance" name="user_fx_balance" readonly="readonly" />
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						身份证号:<input type="text" id="identityCard" name="identity_card" readonly="readonly"/>
	  					</td>
	  					<td>
	  						可用积分:<input type="text" id="userJfBalance" name="user_jf_balance" readonly="readonly" />
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						身份证有效期:
	  						<input id="identityCardValidity" name="identity_card_validity" readonly="readonly" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					</td>
	  					<td>
	  						用户状态:
	  						<select name="user_status" id="userStatus" readonly="readonly">
		  						<option value="1">正常</option>
		  						<option value="0">注销</option>
		  						<option value="2">冻结</option>
		  					</select> 
	  					</td>
	  				</tr>
	  			</table>
	  		</form>
		</div>
    
	  	<div id="footer" region="center" style="width: 100%;margin-top: 220px">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userManager!queryNsUserTeam.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- onDblClickRow:showdictionariesDetailRow -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userName" width="8%">用户名</th>
						<th field="trueName" width="10%">真实姓名</th>
						<th field="userPhone" width="10%">手机号</th>
						<th field="identityCard" width="15%">身份证号</th>
						<th field="createTime" width="20%" formatter='formatterDeliveryTime'>创建时间</th>
						<th field="userKyBalance" width="10%">可用余额</th>
						<th field="userFxBalance" width="10%">分销金额</th>
						<th field="userJfBalance" width="10%">积分</th>
						<th field="userStatus" width="5%" formatter='formatterFlag'>状态</th>
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>