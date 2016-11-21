<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="framework.config.Config"%>
<%
String id = request.getParameter("id");
request.setAttribute("id",id);
String imgPathPrefix = Config.get("img.server.basepath");
request.setAttribute("imgPathPrefix",imgPathPrefix);
%>
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
    <script type="text/javascript" charset="utf-8" src="${basePath }/js/test.js"></script>
	<title>商品编辑</title>
</head>
  
<body class="easyui-layout">
	<div region="center" class="easyui-panel bgColor" title="商品录入" style="width:100%;height:100%;overflow-x: hidden;overflow-y: auto;">
	    <form id="ff" method="post" enctype="multipart/form-data">
	    	<table class="tablestyle01" style="width:100%" border="0">
	    		<tr>
	    			<td width="100" class="righttd">商品名称:</td>
	    			<td width="400">
	    				<input class="easyui-textbox" type="text" id="inp_gname" name="goods.gname" data-options="required:true" style="width: 90%"></input>
	    				<input type="hidden" id="inp_id" name="goods.id" value="${id }"></input>
	    			</td>
	    			<td  width="100" class="righttd" rowspan="7" valign="top">封面:</td>
	    			<td rowspan="7">
	    				<div id="goodimgDiv">
	    					<img id="goodimgPr" width="200" height="200" /><br>
	    					<input type="file" style='width: 200px;' name="goodimg" id="goodimg"/>
	    				</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">全称:</td>
	    			<td>
	    				<input class="easyui-textbox" data-options="required:true" style="width: 90%" id="inp_gfullname" name="goods.gfullname"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">价格:</td>
	    			<td>
	    				<input class="easyui-numberbox" data-options="required:true,precision:1,maxlength:10,groupSeparator:',',decimalSeparator:'.'" maxlength="10" id="inp_price" name="goods.price"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">类别:</td>
	    			<td>
	    				<input class="easyui-combobox" name="goods.category" id="inp_category" data-options="required:true,editable: false"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">类型:</td>
	    			<td>
	    				<input class="easyui-combobox" name="goods.kind" id="inp_kind" data-options="required:true,editable: false"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">品牌:</td>
	    			<td>
	    				<input class="easyui-combobox" name="goods.brand" id="inp_brand" data-options="required:true,editable: false"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd">可用库存:</td>
	    			<td>
	    				<input class="easyui-numberbox" name="goods.storenumb" id="inp_storenumb" data-options="required:true,maxlength:10"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="righttd" valign="top">图片:</td>
	    			<td colspan="3">
	    				<div id='goodimglistDiv'>
	    					<input type="hidden" name="removeImgs" id="removeImgs"/>
	    					<table border="0" cellpadding="0" cellspacing="0" style="margin-left: -5px;">
	    						<tr>
	    							<td width="200"><img id='goodimglistPr1' name='goodimglistPr' width='200' height='200' /></td>
	    							<td width="200"><img id='goodimglistPr2' name='goodimglistPr' width='200' height='200' /></td>
	    							<td width="200"><img id='goodimglistPr3' name='goodimglistPr' width='200' height='200' /></td>
	    							<td width="200"><img id='goodimglistPr4' name='goodimglistPr' width='200' height='200' /></td>
	    							<td width="200"><img id='goodimglistPr5' name='goodimglistPr' width='200' height='200' /></td>
	    						</tr>
	    						<tr>
	    							<td><input type='file' name='img1' id='img1' style='width:200px;'/><a href="javascript:void(0)" onclick="removeImg('1');">清空</a></td>
	    							<td><input type='file' name='img2' id='img2' style='width:200px;'/><a href="javascript:void(0)" onclick="removeImg('2');">清空</a></td>
	    							<td><input type='file' name='img3' id='img3' style='width:200px;'/><a href="javascript:void(0)" onclick="removeImg('3');">清空</a></td>
	    							<td><input type='file' name='img4' id='img4' style='width:200px;'/><a href="javascript:void(0)" onclick="removeImg('4');">清空</a></td>
	    							<td><input type='file' name='img5' id='img5' style='width:200px;'/><a href="javascript:void(0)" onclick="removeImg('5');">清空</a></td>
	    						</tr>
	    					</table>
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
	<div region="south" class="easyui-panel bgColor" align="center" style="height:50px; padding-top: 3px; ">
    	<a href="javascript:void(0);" class="easyui-linkbutton" style="width: 80px; height: 40px;" onclick="submitForm();return false;">确定</a>
	 	<!-- <a href="javascript:void(0);" class="easyui-linkbutton" style="width: 80px; height: 40px;" onclick="clearForm();return false;">清空</a> -->
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
		$("#goodimg").uploadPreview({ Img: "goodimgPr", Width: 150, Height: 150 });
		$("#img1").uploadPreview({ Img: "goodimglistPr1", Width: 200, Height: 200 });
		$("#img2").uploadPreview({ Img: "goodimglistPr2", Width: 200, Height: 200 });
		$("#img3").uploadPreview({ Img: "goodimglistPr3", Width: 200, Height: 200 });
		$("#img4").uploadPreview({ Img: "goodimglistPr4", Width: 200, Height: 200 });
		$("#img5").uploadPreview({ Img: "goodimglistPr5", Width: 200, Height: 200 });
		
		var id = "${id}";
		if(id!="") {
			loadGoods(id);
		}
	});
	
	var initPage = function() {
		queryCategorys();
		goodimglistDivHtml = $("#goodimglistDiv").html();
	}
	
	var loadGoods = function(id) {
		$.ajax({
			url: "${basePath }/view/goodsManager/goodsManager!loadGoods.action?goods.id="+id,
			cache: false,
			dataType:"json",
			async: false,
			success: function(json) {
				$("#inp_gname").textbox('setValue', json.goods.gname);
				$("#inp_gfullname").textbox('setValue', json.goods.gfullname);
				$("#inp_storenumb").textbox('setValue', json.goods.storenumb);
				$("#inp_price").textbox('setValue', json.goods.price);
				$("#inp_category").combobox('setValue', json.goods.category);
				$("#goodimgPr").attr("src", "${imgPathPrefix}/"+json.goods.goodimg);
				$("#goodimglistPr1").attr("src",  "${imgPathPrefix}/"+json.goods.img1);
				$("#goodimglistPr2").attr("src",  "${imgPathPrefix}/"+json.goods.img2);
				$("#goodimglistPr3").attr("src",  "${imgPathPrefix}/"+json.goods.img3);
				$("#goodimglistPr4").attr("src",  "${imgPathPrefix}/"+json.goods.img4);
				$("#goodimglistPr5").attr("src",  "${imgPathPrefix}/"+json.goods.img5);
				queryKinds(function(json1) {
					callbackQueryKinds(json1);
					$('#inp_kind').combobox('setValue', json.goods.kind);
				});
				queryBrands(function(json2) {
					callbackQueryBrands(json2);
					$('#inp_brand').combobox('setValue', json.goods.brand);
				});
				ue.addListener("ready", function() {
		        	// editor准备好之后才可以使用
		        	ue.setContent(json.goods.detail);
		        });
			}
		});
	}
		
	var clearForm = function() {
		$('#ff').form('clear');
		resetEditor();
		$("#goodimglistDiv").html(goodimglistDivHtml);
		$("#goodimg").val("");
		$("#goodimgPr").attr("src","");
	}
	
	var submitForm = function() {
		// 调用 form  插件的 'submit' 方法来提交 form   
		$('#ff').form('submit', {
			url: "${basePath }/view/goodsManager/goodsManager!saveGoods.action",
			onSubmit: function() {
				return checkForm();
			},
      		success:function(data) {
      			//修改后直接关闭tab
      			window.parent.removeCurrSelectTab();
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
		/*var goodimg = $("#goodimg").val();
		if(goodimg.length==0) {
			alert("请选择封面！");
			return false;
		}*/
		
		//验证图片
		/*var valueCount = 0;
		$("input[name='goodimglist']").each(function(index,data){ 
			if(data.value.length>0) {
				valueCount++;
			}
		});
		
		if(valueCount==0) {
			alert("请选择图片！");
			return false;
		}*/
		
		if(!hasContent()) {
			alert("请填写详情！");
			return false;
		}
		return true;
	}
	
	var removeImg = function(index) {
		var src = $("#goodimglistPr"+index).attr('src');
		if(src==undefined || src.length==0) {
			return;
		}
		
		//验证图片
		var valueCount = 0;
		for (var i=1;i<=5;i++) {
			var imgValue = $("#goodimglistPr"+i).attr("src");
			if(imgValue!=undefined && imgValue.length>0) {
				valueCount++;
			}
		}
		
		//如果只剩一个 图片不允许删除
		if(valueCount==1) {
			alert("至少保留一张图片！");
			return;
		}
		
		$("#img"+index).val('');
		$("#goodimglistPr"+index).attr('src','');
		var temp = $("#removeImgs").val();
		if(temp.indexOf("img"+index)<0) {
			temp += ",img"+index;
			$("#removeImgs").val(temp);
		}
	}
	
	//查询一级类别  
	var queryCategorys = function() {
		$.ajax({
			url: "${basePath }/view/goodsManager/goodsCagegoryManager!queryCategorys.action",
			cache: false,
			dataType:"json",
			async: false,
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
						queryKinds(callbackQueryKinds);
					}
				});
			}
		});
	}
	
	//查询二级类别
	var queryKinds = function(callbackFun) {
		$.ajax({
			url: "${basePath }/view/goodsManager/goodsCagegoryManager!queryKinds.action?categoryVo.id="+$('#inp_category').combobox('getValue'),
			cache: false,
			dataType:"json",
			async: false,
			success: callbackFun
		});
	}
	
	var callbackQueryKinds = function (json) {
		 $("#inp_kind").combobox({
		   	required:true,
		   	editable:false,
			data:json.kinds,
			valueField:'id',
		   	textField:'cateName',
			onChange: function (n,o) {
				$('#inp_brand').combobox("clear");
				$('#inp_brand').combobox('loadData', []);
				queryBrands(callbackQueryBrands);
			}/*,
			onLoadSuccess: function(data) {
	        }*/
		});
	}
	
	//查询三级类别
	var queryBrands = function(callbackFun) {
		$.ajax({
			url: "${basePath }/view/goodsManager/goodsCagegoryManager!queryBrands.action?categoryVo.id="+$('#inp_kind').combobox('getValue'),
			cache: false,
			dataType:"json",
			async: false,
			success: callbackFun
		});
	}
	
	var callbackQueryBrands = function (json) {
		$("#inp_brand").combobox({
	    	required:true,
	    	editable:false,
			data:json.brands,
			valueField:'id',
	    	textField:'cateName'/*,
			onLoadSuccess: function(data) {
				if(loadjosn!=null && loadjosn.goods!=null) {
	            	$('#inp_brand').combobox('setValue', loadjosn.goods.brand);
	            }
	        }*/
		});
	}
</script>
</html>