<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.config.Config"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="/include/default.jsp"></jsp:include>
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
			$("#tab_list").datagrid("pageSize","15");//隐藏id
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
		
		var formatterIDStatus = function(value,rec) {
			if(value=="1") {//身份状态0未认证1申请中2已认证
				return "<span style='color: red;'>申请中</span>";
			} else if(value=="0"){
				return "<span style='color: red;'>未认证</span>";
			}else if(value=="2"){
				return "<span style='color: green;'>已认证</span>";
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
		<title>会员操作</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="north" class="easyui-panel bgColor" collapsible="false" title="会员操作" style="height:100px; width:100%">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">用户名:<input name="queryParams.user_name"  /></td>
	  				<td align="left">真实姓名:<input name="queryParams.true_name"  /></td>
	  				<td align="left">手机号 :<input name="queryParams.user_phone"  /></td>
	  				<td align="left">身份证号 :<input name="queryParams.identity_card"  /></td>
	  				<td align="left">
	  					状态:
	  					<select id=”cc” class=”easyui-combobox” name="queryParams.user_status">
	  						<option value = "">全部</option>
							<option value = "0">注销</option>
							<option value = "1">正常</option>
							<option value = "2">冻结</option>
						</select>
	  				</td>
	  				</tr>
	  				<tr>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserManager(); return false;">查询</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
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
						<th field="userPhone" width="10%">手机号</th>
						<th field="identityCard" width="15%">身份证号</th>
						<th field="identityStatus" width="10" formatter='formatterIDStatus'>实名认证状态</th>
						<th field="createTime" width="10%" formatter='formatterDeliveryTime'>创建时间</th>
						<th field="userKyBalance" width="5%">可用余额</th>
						<th field="userFxBalance" width="5%">分销金额</th>
						<th field="userJfBalance" width="10%">积分</th>
						<th field="userStatus" width="5%" formatter='formatterFlag'>状态</th>
						<th field="cz" width="10%" formatter='formatteruser'>操作</th> 
					</tr>
				</thead>
			</table>
		</div>
		
		
		
		<div title="会员信息编辑" id="userinfo_window" modal="true" draggable="false" class="easyui-window" style="width: 500px; height: 600px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="userinfo_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<input type="hidden" id="id" name="nsUser.id" />
			  				<input type="hidden" id="user_pid" name="nsUser.user_pid" />
			  				<td align="right" width="100">用户名:</td>
			  				<td >
			  					<input type="text" id="userName" name="nsUser.user_name" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">用户昵称:</td>
			  				<td >
			  					<input type="text" id="nickName" name="nsUser.nick_name" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">真实姓名:</td>
			  				<td >
			  					<input type="text" id="trueName" name="nsUser.true_name" />
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td align="right" width="100">手机号:</td>
			  				<td >
			  					<input type="text" id="userPhone" name="nsUser.user_phone" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">邮箱:</td>
			  				<td >
			  					<input type="text" id="userMail" name="nsUser.user_mail" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">性       别:</td>
			  				<td>
			  					男<input type="radio" id="radio1" name="nsUser.user_sex" value="1"> 女<input type="radio" id="radio0" name="nsUser.user_sex" value="0"> 
			  					未知<input type="radio" id="radio2" name="nsUser.user_sex" value="2"> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">身份证号:</td>
			  				<td >
			  					<input type="text" id="identityCard" name="nsUser.identity_card"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">身份证有效期:</td>
			  				<td >
			  					<input id="identityCardValidity" name="nsUser.identity_card_validity" class="easyui-datebox" editable="false" style="width: 100px;"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">发证机关:</td>
			  				<td >
			  					<input type="text" id="identityIssuing" name="nsUser.identity_issuing"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">身份状态:</td>
			  				<td >
			  					<select name="nsUser.identity_status" id="identityStatus">
			  						<option value="0">未认证</option>
			  						<option value="1">申请中</option>
			  						<option value="2">已认证</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">登陆密码:</td>
			  				<td >
			  					<input type="password" id="loginPwd" name="nsUser.login_pwd"  />
			  					<a href="#" class="easyui-linkbutton" onclick="resetPassword(); return false;">重置密码</a>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">用户状态:</td>
			  				<td >
			  					<select name="nsUser.user_status" id="userStatus">
			  						<option value="1">正常</option>
			  						<option value="0">注销</option>
			  						<option value="2">冻结</option>
			  					</select> 
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">操作备注:</td>
			  				<td >
			  					<textarea name="nsUser.option_remark" id="optionRemark" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
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