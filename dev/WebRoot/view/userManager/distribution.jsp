<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.config.Config"%>
<%
	String imgPathPrefix = Config.get("img.server.basepath");
	request.setAttribute("imgPathPrefix", imgPathPrefix);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/include/default.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8"
	src="${basePath }/js/test.js"></script>
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
				if(json.pageBean.pageData.length>0){
					$("#grade_fc_level1").val(json.pageBean.pageData[0].gradeFcLevel1);
					$("#grade_fc_level2").val(json.pageBean.pageData[0].gradeFcLevel2);
					$("#grade_fc_level3").val(json.pageBean.pageData[0].gradeFcLevel3);
				}
			}
		});
	}

	//表单验证
	var checkForm = function() {
		var check = $("#ff").form("validate");
		if (!check) {
			return false;
		}
		return true;
	}
	
	
	function check(e) { 
	    var re = /^\d+(?=\.{0,1}\d+$|$)/ 
	    if (e.value != "") { 
	        if (!re.test(e.value)) { 
	            alert("请输入正确的数字"); 
	            e.value = ""; 
	            e.focus(); 
	        } 
	    } 
	} 
	

	var operateFlag = function() {
		$("#ff") .form( "submit", {
			url : "${basePath }/view/userManager/userGrade!updateUserGrade2.action",
			onSubmit : function() {
				return checkForm();
			},
			success : function(data) {
				alert("操作成功！");
				queryUserGrade();//初始化用户等级列表
			}
		});
	}

	var formatterDeliveryTime = function(val, data, index) {
		return val == null ? "" : val.replace("T", " ");
	}

	var formatterNum = function(value, rec) {
		if (value != null) {
			return parseFloat(value).toFixed(2);
		}
	}
</script>
<title>分销分成设置</title>
</head>

<body class="easyui-layout">
	<div region="center" style="width: 100%">
		<form id="ff" method="post" enctype="multipart/form-data">
			<table id="userinfo_table" border="0" dataType="text"
				class="tablestyle01" style="width:100%">
				<tr>
					<td align="left" width="200px" colspan="2">
						<span style="color: red;font-weight: bold;">此处设置分销比例为  最多3级分销收益的【固定分成比例】 最终比例 还会根据会员等级增长</span>
					</td>
				</tr>
				<tr>
					<input type="hidden" id="id" name="nsUserGrade.id" />
					<td align="right" width="150px">一级分销收益比例:</td>
					<td><input type="text" id="grade_fc_level1"
						name="nsUserGrade.grade_fc_level1" onblur="check(this)" /></td>
				</tr>
				<tr>
					<td align="right" width="150px">二级分销收益比例:</td>
					<td><input type="text" id="grade_fc_level2"
						name="nsUserGrade.grade_fc_level2" onblur="check(this)" /></td>
				</tr>
				<tr>
					<td align="right" width="150px">三级分销收益比例:</td>
					<td><input type="text" id="grade_fc_level3"
						name="nsUserGrade.grade_fc_level3" onblur="check(this)" /></td>
				</tr>
				
			</table>
			<div align="left" class="tablestyle01"
				style="height:50px; padding-top: 3px; padding-left: 100px">
				<a href="#" class="easyui-linkbutton"
					onclick="operateFlag(); return false;">保存</a> 
				
				<a href="#"
					class="easyui-linkbutton"
					onclick="queryUserGrade(); return false;">重置</a>
			</div>
		</form>
	</div>
</body>
</html>