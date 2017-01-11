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
			initPage();
		});
		
		var initPage = function() {
			queryUserGrade();
		}
		
		var queryUserGrade = function() {
			var data = formGet("from_query");
			$("#tab_list").datagrid({"queryParams":data});
			$("#tab_list").datagrid("hideColumn","id");//隐藏id
			$.ajax({
				url: "${basePath }/view/userManager/userGrade!queryUserGrade.action",
				cache: false,
				dataType:"json",
				success: function(json){
					//do nothing
				}
			});
		}
		
		var formatteruser = function(value,rec) {
			var formatterStr = "";
			formatterStr += "<a href='#' onclick='eidtUserGrade(\""+rec.id+"\",\""+rec.trueName+"\"); return false;'>编辑</a>&nbsp;";
			return formatterStr;
		}
		
		//编辑会员等级
		var eidtUserGrade = function(id,trueName) {
			$.ajax({
				url: "${basePath }/view/userManager/userGrade!queryUserGradeById.action",
				cache: false,
				dataType:"json",
				data: "id="+id,
				success: function(json){
					debugger;
					if(json.messages=='success'){
						$("#id").val(json.nsUserGrade.id);
						$("#grade_name").val(json.nsUserGrade.grade_name);
						$("#grade_fc_level").val(json.nsUserGrade.grade_fc_level);
						$("#grade_balance").val(json.nsUserGrade.grade_balance);
						$("#grade_tx_balance").val(json.nsUserGrade.grade_tx_balance);
						$("#option_remark").val(json.nsUserGrade.option_remark);
						$("#userinfo_window").window({title: "会员等级编辑"});
						$("#userinfo_window").window("open");
					}
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
		
		var operateFlag = function() {
			$("#ff").form("submit", {
				url: "${basePath }/view/userManager/userGrade!updateUserGrade.action",  
				onSubmit: function() {
					return checkForm();
				},
	      		success:function(data) {
	      			alert("操作成功！");
					queryUserGrade();//初始化用户等级列表
					$("#userinfo_window").window("close");
	     		}
	 		});  
		}
		
		
		
		
		var formatterDeliveryTime = function(val, data, index){
			return val==null?"":val.replace("T"," ");
		}
		
		var formatterNum = function(value,rec) {
			if (value != null) {
                return parseFloat(value).toFixed(2);
            }
		}
		
    	</script>
		<title>提现额度设置</title>
  	</head>
  
 	<body class="easyui-layout">
	  	<div region="center" style="width: 100%">
		     <table id="tab_list" rownumbers="true" region="center" fitColumns="true" class="easyui-datagrid" 
		    	url="userGrade!queryUserGrade.action" style="width:auto;height:auto" title="" 
		    	pagination="true" singleSelect="true" data-options=""><!-- 身份状态0未认证1申请中2已认证 -->
				<thead>
					<tr>
						<th field="id" style="display: none;"></th>
						<th field="gradeName" width="8%">等级</th>
						<th field="gradeFcLevel" width="10%" formatter='formatterNum'  >团队分成比例</th>
						<th field="gradeBalance" width="25%" formatter='formatterNum'>升级标准额度(关联收益额度)</th>
						<th field="gradeTxBalance" width="10%" formatter='formatterNum'>提现额度</th>
						<th field="optionRemark" width="35%">说明</th>
						<th field="cz" width="10%" formatter='formatteruser'>操作</th> 
					</tr>
				</thead>
			</table>
		</div>
		
		<div title="会员等级编辑" id="userinfo_window" modal="true" draggable="false" class="easyui-window" style="width: 500px; height: 350px; background-color:#EFEFEF;" 
				resizable="false" collapsible="false" maximizable="false" minimizable="false" closed="true">
				<form id="ff" method="post" enctype="multipart/form-data">
					<table id="userinfo_table" border="0" dataType="text" class="tablestyle01" style="width:100%">
			  			<tr>
			  				<input type="hidden" id="id" name="nsUserGrade.id" />
			  				<td align="right" width="100">等级:</td>
			  				<td >
			  					<input type="text" id="grade_name" name="nsUserGrade.grade_name" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">团队分成比例:</td>
			  				<td >
			  					<input type="text" id="grade_fc_level" name="nsUserGrade.grade_fc_level" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">升级标准额度        （关联收益额度）:</td>
			  				<td >
			  					<input type="text" id="grade_balance" name="nsUserGrade.grade_balance" />
			  				</td>
			  			</tr>
			  			<tr>
			  				<td align="right" width="100">提现额度:</td>
			  				<td >
			  					<input type="text" id="grade_tx_balance" name="nsUserGrade.grade_tx_balance" />
			  				</td>
			  			</tr>
			  			
			  			<tr>
			  				<td align="right" width="100">操作备注:</td>
			  				<td >
			  					<textarea name="nsUserGrade.option_remark" id="option_remark" rows="5" style="width: 100%;" class="easyui-validatebox" required="true">
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