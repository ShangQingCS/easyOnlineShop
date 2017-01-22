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
			queryUserManager();
		}
		
		var queryUserManager = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/userManager/userManager!queryNsUser.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		var formatteruser = function(value,rec) {
			var formatterStr = "";
			formatterStr += "<a href='#' onclick='modUserstatus(\""+rec.id+"\",\""+rec.name+"\"); return false;'>编辑</a>&nbsp;";
			return formatterStr;
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
		
		//打开编辑页面
		var modUserstatus = function(id,name) {
			$.ajax({ 
					type: "POST",  
					url: "${basePath }/view/userManager/userManager!queryUserById.action",  
					dataType: "json",
				  	data: "id="+id,
				  	success: function(json){
						if(json.message=='success'){
							$("#id").val(json.nsUser.id);
							$("#userName").val(json.nsUser.user_name);
							$("#nickName").val(json.nsUser.nick_name);
							$("#trueName").val(json.nsUser.true_name);
							$("#userPhone").val(json.nsUser.user_phone);
							$("#userMail").val(json.nsUser.user_mail);
							$("#user_pid").val(json.nsUser.user_pid);
							if(json.nsUser.user_sex=="0"){//女
								$("#radio0").attr("checked","checked");
							}else if(json.nsUser.user_sex=="1"){//男
								$("#radio1").attr("checked","checked");
							}else{//未知
								$("#radio2").attr("checked","checked");
							}
							$("#identityCard").val(json.nsUser.identity_card);
							$("#identityCardValidity").datebox("setValue", json.nsUser.identity_card_validity);
							$("#identityIssuing").val(json.nsUser.identity_issuing);
							$("#identityStatus").val(json.nsUser.identity_status);
							$("#userStatus").val(json.nsUser.user_status);
							$("#loginPwd").val(json.nsUser.login_pwd);
							$("#optionRemark").val(json.nsUser.option_remark);
							$("#userinfo_window").window({title: "会员信息编辑"});
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
				url: "${basePath }/view/userManager/userManager!updateNsUserFlagorStatus.action",  
				onSubmit: function() {
					return checkForm();
				},
	      		success:function(data) {
	      			alert("操作成功！");
					queryUserManager();//初始化用户信息列表
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
		
		
		var resetPassword = function (){
			window.confirm("提示","确定重置密码为：12345678?",function(r){
				if(r){
					$("#loginPwd").val("12345678");
				}
			});
		}
		
		
    		
    		
    	</script>
		<title>会员账户统计信息</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false"  style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">注册日期 从
	  					<input name="queryParams.startCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					到 
	  					<input name="queryParams.endCreateTime" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  				</td>
	  				<td align="left">
	  					认证状态:
	  					<select id=”cc” class=”easyui-combobox” name="queryParams.user_status">
	  						<option value = "">全部</option>
							<option value = "0">注销</option>
							<option value = "1">正常</option>
							<option value = "2">冻结</option>
						</select>
	  				</td>
	  				<td align="left">
	  					用户状态:
	  					<select id=”cc” class=”easyui-combobox” name="queryParams.user_status">
	  						<option value = "">全部</option>
							<option value = "0">注销</option>
							<option value = "1">正常</option>
							<option value = "2">冻结</option>
						</select>
	  				</td>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserManager(); return false;">查询统计</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">导出报表数据</a>
			 		</td>
	  			</tr>
	  		</table>
	  	</div>
    
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userManager!queryNsUser.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- onDblClickRow:showdictionariesDetailRow -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="userName" width="8%">用户名</th>
						<th field="trueName" width="10%">真实姓名</th>
						<th field="userName" width="8%">性别</th>
						<th field="userPhone" width="10%">手机号</th>
						<th field="identityCard" width="15%">身份证号</th>
						<th field="userKyBalance" width="5%">充值金额</th>
						<th field="userKyBalance" width="5%">分红金额</th>
						<th field="userKyBalance" width="5%">消费金额</th>
						<th field="userJfBalance" width="5%">积分</th>
						<th field="userKyBalance" width="5%">当前余额</th>
						<th field="createTime" width="15%" formatter='formatterDeliveryTime'>创建时间</th>
						<th field="userStatus" width="5%" formatter='formatterFlag'>状态</th>
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>