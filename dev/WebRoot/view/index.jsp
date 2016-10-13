<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/include/include.jsp"></jsp:include>
    <title>${_title}-首页</title>
    
    <script type="text/javascript">
    $(function(){
    	initMenuTree();//初始化菜单
    });
    
    //初始化功能菜单
	function initMenuTree() {
    	$.ajax({
		  type: "POST",
		  url: "menuTree.action",
		  dataType: "json",
		  success:function(json) {
				//var trees = eval("("+data+")");
		   		var root = json.trees[0];
				//root.state = "open";
				//root.children[root.children.length - 1].state = "open";
				$("#menu_tree").tree("loadData", root.children);
				$("#menu_tree").tree("expandAll");
				$("#menu_tree").css("remove", root.target);    
	   		},
	   		error:function(XMLHttpRequest, textStatus, errorThrown) {
	   			alert('e:'+XMLHttpRequest.responseText);
	   		}
		});
	}
    
    var treeClick = function(node){
    	if(node==null || node.attributes==null || node.attributes.url==null || node.attributes.url==""){
    		return false;
    	}
    	$('#iframe1').attr('src',"${basePath}"+node.attributes.url+"?timestamp="+new Date().getTime());
    }
    
    function userOut(r){
    	window.location.href = window.location.href;
    }
    
    //用户退出调用
	function userOut(b){
		if(!b){return false;}
		$.ajax({
		  type: "POST",
		  url: "../userOut.action",
		  dataType: "text",
		  success: function(json){
		  	window.location = "../login.jsp";
		  }
		  ,error: function(a){alert("退出异常");}
		});
	}
    
    function confirm(title, msg, fn){
		$.messager.confirm(title, msg, fn);
	}
    </script>
  </head>
  
  <body class="easyui-layout">
	<div region="north" title="${_title }" split="true" noheader="true" style="height:50px;padding:5px;background:#eee;">
		<div style="float: left; font-size: 18px; font-weight: bold; font-family:黑体; margin-left: 10px; padding-top: 8px;">${_title }</div>
		<div style="float: right; margin-top: 8px;">
			<a href="index2.jsp">切换到tab版</a>&nbsp;&nbsp;&nbsp;
			<span>当前用户: ${user.UName}</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="confirm('提示', '确认退出', userOut);return false;">安全退出</a>
		</div>
	</div>

	<div region="south" title="版权声明"  noheader="true" style="height:50px;padding:15px;background:#eee;">
		<div align="center" style="font-weight: bold;"><%=application.getInitParameter("copyright") %></div>	
	</div>

	<div region="west" split="true" title="系统菜单" style="width:220px;">
		<ul id="menu_tree" class="easyui-tree" data-options="onClick:treeClick"></ul>
	</div>

	<div region="center" title="内容区" style="padding:0px;background:#eee;" noheader="true">
		<iframe id="iframe1" frameBorder="0" style="width: 100%; height: 100%;  " ></iframe>
	</div>
</body>
</html>
