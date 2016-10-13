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
    
    //添加tab页
	function addTab(node){
		if(node!=null && node.attributes!=null && node.attributes.url!=null && node.attributes.url!=''){
			var id = node.id ;
			var url = node.attributes.url;
			var title = node.text;
			var stitle = title.length>12?title.substr(0,12)+"...":title;
			sttile = "<span markid=\""+id+"\">"+stitle+"</span>";//必须的时候缩写
			
			var tabTmp = $("#div_tabs").tabs("getTab",stitle);
			if(tabTmp!=null){//防止打开多个
				$("#div_tabs").tabs("select",stitle);
				return false;
			}
			if(url.indexOf('/')==0){//斜杠开头的 添加域
				url = '<%=request.getContextPath()%>'+url;
			}
			var bj = url.indexOf("?")>-1?"&":"?";
			url += bj+"break_time_number_"+Math.random()+"="+Math.random();
			var content = "<iframe src=\""+url+"\" frameborder=\"0\" style=\"width:100%; height: 100%;\"></iframe>";
			var tab = $("#div_tabs").tabs("add",{
				title: stitle,
				content: content,
				closable: true,
				menuId: id,
				mtitle: title
			});	
			$("#div_tabs").tabs("select",stitle);
		}
	}
    
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
    	addTab(node);
    	/*if(node==null || node.attributes==null || node.attributes.url==null || node.attributes.url==""){
    		return false;
    	}
    	$('#iframe1').attr('src',"${basePath}"+node.attributes.url+"?timestamp="+new Date().getTime());*/
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
			<!-- <a href="index.jsp">切换到普通版</a>&nbsp;&nbsp;&nbsp; -->
			<span>当前用户: ${user.UName}</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="confirm('提示', '确认退出', userOut);return false;">安全退出</a>
		</div>
	</div>

	<div region="south" title="版权声明"  noheader="true" style="height:50px;padding:15px;background:#eee;">
		<div align="center" style="font-weight: bold;"><%=application.getInitParameter("copyright") %></div>	
	</div>

	<div region="west" split="true" title="系统菜单" style="width:220px;">
		<ul id="menu_tree" class="easyui-tree" data-options="onClick:treeClick"></ul>
	</div>

	<div id="div_tabs" region="center" class="easyui-tabs" style="padding:0px;background:#eee;" noheader="true">
		<div title="欢迎页面" closable="false"><iframe id="iframe1" src="welcome.jsp" frameBorder="0" style="width: 100%; height: 100%;"></iframe></div>
	</div>
</body>
</html>
