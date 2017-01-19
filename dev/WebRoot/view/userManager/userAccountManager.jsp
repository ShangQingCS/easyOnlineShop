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
    	<script type="text/javascript">
    	
    	$(function(){
			$("#footer").hide();
		});
		
		var queryUserManager = function (){
			var data = formGet("from_query");
			if(checkForm()){
				$.ajax({
					url: "${basePath }/view/userManager/userManager!queryNsUserByCondition.action",  
					cache: false,
					dataType:"json",
					data:$("#ff1").serialize(),
					success: function(json){
						if(json.nsUser!=null){
		      				$("#content").show();
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
		      			$("#footer").show();//显示div  
						queryUserPurse();
					}
				});
				
			}
		}
		
		//操作积分信息
		var operateFlag = function() {
			var list= $("input:radio[name='radiojf']:checked").val();
            if(list==null){
                alert("增加或者减少至少请选中一个!");
                return false;
            }
            else{
            	var yjf = $("#userKyBalance").val() ;//余额
            	var ejf = $("#_user_ky_balance").val() ;//编辑余额
                if(list==0){//增加
                	//alert("增加");
                	yjf = Math.floor(yjf)+Math.floor(ejf);
                }else if(list==1){//减少
                	//alert("减少");
                	yjf = Math.floor(yjf)-Math.floor(ejf);
                	if(yjf<0){
                		alert("余额不能小于0");
                		return false;
                	}
                }
                $("#userKyBalance").val(yjf);//余额
                var radiocheck = $("input[name='radiojf']:checked").val(); 
                $("#ff").form("submit", {
					url: "${basePath }/view/userManager/userPurse!addUserPurse.action",  
					data:$("#ff").serialize(),
		      		success:function(data) {
		      			alert("操作成功！");
						queryUserPurse();//初始化用户信息列表
		     		}
		 		});  
            }
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
		
		var queryUserPurse = function (){
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/userManager/userPurse!queryUserPurseKY.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
		var formatterState = function(value,rec) {
			if(value=="1") {
				return "<span style='color: red;'>冻结</span>";
			} else if(value=="0"){
				return "<span style='color: red;'>未支付</span>";
			}else if(value=="2"){
				return "<span style='color: green;'>成功</span>";
			}else{
				return "";
			}
		}
		
		var formatteroType = function(value,rec) {
			if(value=="1") {
				return "<span style='color: red;'>减少</span>";
			} else if(value=="0"){
				return "<span style='color: green;'>增加</span>";
			}else{
				return "";
			}
		}
    		
    	</script>
    	
		<title>会员账户管理</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div id="header" region="north" class="easyui-panel bgColor" collapsible="false" title="会员账户管理" style="height:100px; width:100%">
	  		<form id="ff1" method="post" enctype="multipart/form-data">
	  		<table id="from_query"  border=0 dataType="text" class="tablestyle01" style="width:100%">
	  			<tr>
	  				<td align="left">用户名:<input id="f_userName" name="queryParams.user_name"  /></td>
	  				<td align="left">真实姓名:<input id="f_trueName" name="queryParams.true_name"  /></td>
	  				<td align="left">手机号 :<input id="f_userPhone" name="queryParams.user_phone"  /></td>
	  				<td align="left">身份证号 :<input id="f_identityCard" name="queryParams.identity_card"  /></td>
	  				<td align="left">
	  					<a href="#" class="easyui-linkbutton" onclick="queryUserManager(); return false;">查询</a>
				 		<a href="#" class="easyui-linkbutton" onclick="winReload();">刷新</a>
			 		</td>
	  			</tr>
	  		</table>
	  		</form>
	  	</div>
    
	  	<div id="content" region="center" style="width: 100%;display: none;" ><!--  -->
	  		<form id="ff" method="post" enctype="multipart/form-data">
	  			<table id="userinfo_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
	  				<tr>
	  					<td>
	  						<input type="hidden" id="purse_type" name="purse_type" value = "0" /><!-- 余额 -->
	  						<input type="hidden" id="id" name="nsUser.id" />
	  						登录帐号:<input type="text" id="userName" name="nsUser.user_name" readonly="readonly" />
	  					</td>
	  					<td>
	  						可用金额:<input type="text" id="userKyBalance" name="nsUser.user_ky_balance" readonly="readonly" />
	  					</td>
	  					<td>
	  						<input type="radio" id="radio1" name="radiojf" value="0">增加
	  						<input type="radio" id="radio2" name="radiojf" value="1">减少
	  						可用余额:<input type="text" id="_user_ky_balance" name=_balance />
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						真实姓名:<input type="text" id="trueName" name="nsUser.true_name" readonly="readonly" />
	  					</td>
	  					<td>
	  						分销收益:<input type="text" id="userFxBalance" name="nsUser.user_fx_balance" readonly="readonly" />
	  					</td>
	  					<td rowspan="2">
	  						操作备注:<br>
		  					<textarea name="nsUser.option_remark" id="optionRemark" rows="5" cols="50" class="easyui-validatebox" required="true">
		  					</textarea> 
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						身份证号:<input type="text" id="identityCard" name="nsUser.identity_card" readonly="readonly"/>
	  					</td>
	  					<td>
	  						可用积分:<input type="text" id="userJfBalance" name="nsUser.user_jf_balance" readonly="readonly" />
	  					</td>
	  				</tr>
	  				<tr>
	  					<td>
	  						身份证有效期:
	  						<input id="identityCardValidity" name="nsUser.identity_card_validity" readonly="readonly" class="easyui-datebox" editable="false" style="width: 100px;"/>
	  					</td>
	  					<td>
	  						用户状态:
	  						<select name="nsUser.user_status" id="userStatus" readonly="readonly">
		  						<option value="1">正常</option>
		  						<option value="0">注销</option>
		  						<option value="2">冻结</option>
		  					</select> 
	  					</td>
	  					<td>
	  						<a href="#" class="easyui-linkbutton" onclick="operateFlag(); return false;">提交</a>
	  					</td>
	  				</tr>
	  			</table>
	  		</form>
		</div>
		
		<div id="footer" region="center" style="width: 100%;margin-top: 300px" >
			<table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userPurse!queryUserPurseKY.action" style="width:auto;height:auto;padding-top: 500px;" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- onDblClickRow:showdictionariesDetailRow -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="typeName" width="8%"  >交易类型</th>
						<th field="tradeSn" width="30%" >交易流水号</th>
						<th field="tradeState" width="10%" formatter = 'formatterState'>交易状态</th>
						<th field="optionType" width="10%" formatter = 'formatteroType'>操作类型</th>
						<th field="tradeAmount" width="20%">金额</th>
						<th field="optionTime" width="20%" formatter='formatterDeliveryTime'>操作时间</th>
					</tr>
				</thead>
			</table>
		</div>
  	</body>
</html>