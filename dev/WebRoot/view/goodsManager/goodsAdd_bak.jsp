<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<jsp:include page="/include/default.jsp"></jsp:include>
	<script type="text/javascript" src="${basePath }/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" charset="utf-8" src="${basePath }/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath }/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${basePath }/ueditor/lang/zh-cn/zh-cn.js"></script>
	<title>商品维护</title>
</head>
  
<body class="easyui-layout">
	<div region="center" class="easyui-panel bgColor" title="商品录入" style="width:100%;height:100%;overflow-x: hidden;overflow-y: auto;">
	    <form id="ff" method="post" enctype="multipart/form-data">
	    	<table class="tablestyle01" style="width:100%">
	    		<tr>
	    			<td width="100" class="righttd">商品名称:</td>
	    			<td colspan="3">
	    				<input class="easyui-textbox" type="text" name="goods.gname" data-options="required:true" style="width:90%;"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">价格:</td>
	    			<td>
	    				<input class="easyui-numberbox" data-options="required:true,precision:2,maxlength:10,groupSeparator:',',decimalSeparator:'.'" maxlength="10" name="goods.price"></input>
	    			</td>
	    			<td width="100" class="righttd">类别:</td>
	    			<td>
	    				<input class="easyui-combobox" type="text" name="goods.category" id="inp_category" data-options="required:true,editable: false"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">类型:</td>
	    			<td>
	    				<input class="easyui-combobox" type="text" name="goods.kind" id="inp_kind" data-options="required:true,editable: false"></input>
	    			</td>
	    			<td width="100" class="righttd">品牌:</td>
	    			<td>
	    				<input class="easyui-combobox" type="text" name="goods.brand" id="inp_brand" data-options="required:true,editable: false"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">封面:</td>
	    			<td colspan="3">
	    				<div id="goodimgDiv">
	    					<input type="file" style='width: 350px;' name="goodimg" id="goodimg"/>
	    				</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">图片:</td>
	    			<td colspan="3">
	    				<div id="goodimglistDiv">
	    					<input type="file" name="goodimglist" style='width: 350px;'/><a href="javascript:void(0);" id="button" style='margin-left: 5px;'>添加</a><br/>
	    				</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd" valign="top">详情:</td>
	    			<td colspan="3">
	    				<div style="width:100%;">
							<script id="editor" name="goods.detail" type="text/plain" style="width:95%;height:500px;"></script>
							<!--<input class="easyui-textbox" name="message" data-options="multiline:true" style="height:60px"></input>-->
						</div>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	<div region="south" class="easyui-panel bgColor" align="center" style="height:32px; padding-top: 3px; ">
    	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitForm();return false;">确定</a>
	 	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="clearForm();return false;">清空</a>
    </div>
</body>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    //重置编辑器
    var resetEditor = function() {
        if(ue){
            ue.setContent("");
            ue.reset();
            clearLocalData();
        }
    }
	//清空编辑器草稿箱
    var clearLocalData = function() {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
    }
    //判断编辑器是否有内容
    var hasContent = function() {
        return UE.getEditor('editor').hasContents();
    }
</script>
<script>
	var goodimglistDivHtml;
	$(function(){
		initPage();
	});
	
	var initPage = function() {
		queryCategorys();
		goodimglistDivHtml = $("#goodimglistDiv").html();
		$("#button").click(addFile);
	}
	
	var clearForm = function() {
		$('#ff').form('clear');
		resetEditor();
		$("#goodimglistDiv").html(goodimglistDivHtml);
		$("#button").click(addFile);
		$("#goodimg").val("");
	}
	
	var submitForm = function() {
		// 调用 form  插件的 'submit' 方法来提交 form   
		$('#ff').form('submit', {
			url: "${basePath }/view/goodsManager/goodsManager!saveGoods.action",
			onSubmit: function() {
				return checkForm();
			},
      		success:function(data){   
          		alert("操作成功！");
          		clearForm();
     		}
 		}); 
		//$('#ff').form('submit');
		/*formSubmit("ff",function() {
		});*/
	}
	
	//表单验证
	var checkForm = function() {
		var check = $('#ff').form('validate');
		if(!check) {
			return false;
		}
		
		//验证封面
		var goodimg = $("#goodimg").val();
		if(goodimg.length==0) {
			alert("请选择封面！");
			return false;
		}
		
		//验证图片
		var imagFlag = true; 
		$("input[name='goodimglist']").each(function(index,data){ 
			if(data.value.length==0) {
				imagFlag = false;
				return false;
			}
		});
		
		if(!imagFlag) {
			alert("请选择图片！");
			return false;
		}
		
		if(!hasContent()) {
			alert("请填写详情！");
			return false;
		}
		return true;
	}
		
	//查询一级类别  
	var queryCategorys = function() {
		$.ajax({
			url: "${basePath }/view/goodsManager/goodsManager!queryCategorys.action",
			cache: false,
			dataType:"json",
			success: function(json){
			    $("#inp_category").combobox({
			    	required:true,
			    	editable:false,
					data:json.categorys,
					valueField:'id',
			    	textField:'cateName',
					onChange: function (n,o) {
						$('#inp_kind').combobox("clear");
						$('#inp_brand').combobox("clear");
						$('#inp_kind').combobox('loadData', []);
						$('#inp_brand').combobox('loadData', []);
						queryKinds();
					}
				});
			}
		});
	}
	
	//查询二级类别
	var queryKinds = function() {
		$.ajax({
			url: "${basePath }/view/goodsManager/goodsManager!queryKinds.action?categoryVo.id="+$('#inp_category').combobox('getValue'),
			cache: false,
			dataType:"json",
			success: function(json){
			    $("#inp_kind").combobox({
			    	required:true,
			    	editable:false,
					data:json.kinds,
					valueField:'id',
			    	textField:'cateName',
					onChange: function (n,o) {
						$('#inp_brand').combobox("clear");
						$('#inp_brand').combobox('loadData', []);
						queryBrands();
					}
				});
			}
		});
	}
	
	//查询三级类别
	var queryBrands = function() {
		$.ajax({
			url: "${basePath }/view/goodsManager/goodsManager!queryBrands.action?categoryVo.id="+$('#inp_kind').combobox('getValue'),
			cache: false,
			dataType:"json",
			success: function(json){
			    $("#inp_brand").combobox({
			    	required:true,
			    	editable:false,
					data:json.brands,
					valueField:'id',
			    	textField:'cateName'
				});
			}
		});
	}
	
	//添加文件
	var addFile = function() {
		if($("input[name='goodimglist']").length==5) {
			alert("最多上传5张图片");
			return;
		}
		var html = $("<input type='file' style='width: 350px;' name='goodimglist'>");
		var button = $("<a href='javascript:void(0);' name='button' style='margin-left: 5px;'>删除</a><br/>");
		$("#goodimglistDiv").append(html).append(button);
		button.click(function() {
			html.remove();
			button.remove();
		});
	}
</script>
</html>