<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../javascript/pageLoader.js"></script>
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="../js/jquery-easyui-1.4/themes/jquery.cookie.js"></script>  
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.4/themes/gray/easyui.css">
<script type="text/javascript" charset="UTF-8" src="../js/jquery-easyui-1.4/themes/changeEasyuiTheme.js"></script> 
<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript" src="../js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<STYLE>
.bgColor{
	background-color: #EFEFEF;
}

.tablestyle01{background-color:#E1EDF7;}
.tablestyle01 td{ padding:3px 0px 3px 6px; border-bottom:1px dashed #ACB3B9; font-size:12px;}

.styleList{width: 80px; height: 80px; border:1px; border-color:gray; 
border-style:solid; font-size: 16px; margin-left: 10px; cursor: pointer; 
padding-top: 16px;margin-top: 10px;}

a:link { text-decoration: none;}
a:active { text-decoration:blink}
a:hover { text-decoration:underline;} 
a:visited { text-decoration: none;}
</STYLE>

	<title>sdfsdfsdf</title>
	<script>
		$(function(){
			$(window).resize(function(){
				var height = getTotalHeight();
				var width = getTotalWidth;
				var menu = $("#div_menu");
				var tabs = $("#div_tabs");
				var sheight = $("#div_south").height();
				var theight = $("#div_top").height();
				
				tabs.tabs("options").height= height-sheight-theight-4;
				tabs.tabs("options").width = width;
				tabs.tabs('resize');
				
				menu.accordion("options").height=height-$("#div_south").height()-$("#div_top").height()-4;
				menu.accordion('resize');
			});
			
			$("#ul_menu_tree").tree({
				onClick:function(node){
					addTab(node);
				}
			});//初始化菜单
			initMenuTree();//初始化菜单
			showSysTaskList(true);//系统任务窗口
		});
		
		function getTotalHeight(){
		 if($.browser.msie){
		  return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;
		 }else{
		  return self.innerHeight;
		 }
		}
		function getTotalWidth (){
		 if($.browser.msie){
		  return document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth;
		 }else{
		  return self.innerWidth;
		 }
		}

		
		//系统任务窗口
		function showSysTaskList(isHide){
			var msg = "<b>您初次登陆系统可做以下事项:</b><br>";
			$.messager.show({showType:'show',title:"提示",msg:msg, height:160 ,timeout:(isHide?6000:0)});
		}
		
		/*关闭当前选择的tab页*/
		function removeCurrSelectTab(){
			var tab = $("#div_tabs").tabs("getSelected");
			$("#div_tabs").tabs("close",tab.panel('options').title);
		}
		
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
		function initMenuTree(){
			$.ajax({
			  type: "POST",
			  url: "menuTree.action",
			  dataType: "json",
			  success: function(json){
			  	var root = json.trees[0];
			  	root.state = "open";
			  	root.children[root.children.length-1].state = "open";
			  	$("#ul_menu_tree").tree("loadData", json.trees);
			  }
			});
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
		
		//界面风格
		function showStyle(){
			$("#style_window").window("open");
		}
		
		//显示帮助面板
		function showHelp(){
			var tab = $("#div_tabs").tabs("getSelected");
			var options = tab.panel("options");
			var menuId = options.menuId;
			if(menuId!=null && menuId != ""){
				$.ajax({
				  type: "POST", url: "help.action", dataType: "json", data: "menuId="+menuId,
				  success: function(json){
				    if(json==null || json.pojo==null){alert("未查询到可用的帮助");}
				    else{
				    	$("#div_help_content").html(json.pojo.content);
				    	$("#help_window").window("open");
				    }
				    /*
				  	if(json.url!=null && json.url!=""){
				  		$("#help_window").window("open");
				  		document.getElementById("iframe_help").src = json.url;
				  	}else{
				  		alert("未查询到可用的帮助");
				  	}*/
				  }
				});
			}else{
				alert("当前操作功能未配置功能ID");
			}
		}
		
		function alert(msg){
			$.messager.alert("提示",msg);
		}
		
		function show(msg){
			$.messager.show({
				showType: 'show',
				title:"提示",
				msg:msg,
				timeout:3000
			});
		}
		
		function confirm(title, msg, fn){
			$.messager.confirm(title, msg, fn);
		}
		
		//下载调用
		function filedown(fileid, paramName){
			$("#iframe_download").attr("src", "FileDown?"+(paramName==null?"fileid":paramName)+"="+fileid);
		}
	</script>
</head>

<body class="easyui-layout">
	<div id="div_top" region="north" border="false" style="height:40px;" class="bgColor">
		<iframe id="iframe_download" src="" style="display: none;"></iframe>
		<div >
			<table width="100%" height="100%" border="0"><tr>
				<td style="font-weight: bold; background-image: url(images/aa.gif);background-repeat:no-repeat; padding-left: 18px;" valign="bottom">
						&nbsp;headTitle
				</td>
				<td align="center">
				</td>
				<td align="right" style="font-size: 12px;">
					<a href="#" onclick="showStyle();return false;">风格</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="#" onclick="showHelp();return false;">帮助</a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<a href="#" onclick="confirm('提示', '确认退出', userOut);return false;">退出</a>
					<div id="help_window" closed="true" closable="false" collapsible="true" resizable="false"  class="easyui-window" style="width: 640px; height: 360px;">
						<!--  <iframe id="iframe_help" src="" frameborder="0" style="width: 100%; height: 100%;"></iframe>-->
						<div  id="div_help_content"></div>
					</div>
					<div id="style_window" closed="true" minimizable="false"  maximizable="false" collapsible="false" title="界面风格" class="easyui-window" style="width: 500px; height: 130px;">
						<div align="center" >
							<span class="styleList" onclick="changeThemeFun('default');">蓝色</span>
							<span class="styleList" onclick="changeThemeFun('black');">黑色</span>
							<span class="styleList" onclick="changeThemeFun('gray');">灰色</span>
							<span class="styleList" onclick="changeThemeFun('bootstrap');">bootstrap</span>
							<span class="styleList" onclick="changeThemeFun('metro');">metro</span>
						</div>
					</div>
				</td>
			</tr></table>
		</div>
	</div>
	<div id="div_menu" region="west" class="easyui-accordion" split="false" title="" style="width:240px; ">
		<div title="功能菜单" style="width:240px;">
			<ul id="ul_menu_tree" ></ul>
		</div>
		<div id="id_user_msg" title="登陆信息" style="width: 100%; font-size: 12px;" class="bgColor" align="center">
			登陆信息
		</div>
	</div>
	<div id="div_tabs" region="center" class="easyui-tabs" >
		<div title="欢迎页面" closable="false"><iframe src="welcome.jsp" frameborder="0" style="width:100%; height: 100%;"></iframe></div>
	</div>
	<div id="div_south" region="south" border="false" align="center" style="height:46px; padding-top:4px; overflow: visible; "  class="bgColor">
		<table width="100%" style="font-size: 12px;">
			<tr >
				<td align="center">
					123123<br>
					sdfsdf<br>
				</td>
				<td style="width: 40px; color: blue;" align="right" valign="top">
					<img id="img_systask" src="../images/gif/clean_016.gif" style="cursor: pointer;" onclick="showSysTaskList(false);" />
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>