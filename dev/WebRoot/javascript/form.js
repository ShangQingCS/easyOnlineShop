//--------- 表单js begin
var oldFileIds = "";
var oldFfileGroupIds = "";
var fileIds = "";
var fileGroupIds = "";
	
function initFileOldIds(){
	$("#tab_form :input[fileMark]").each(function(i,n){
		var type = $(n).attr('fileMark');
		if(type=="file"){
			oldFileIds += ((oldFileIds!="")?",":"")+n.value;
		}else if(type=="files"){
			oldFfileGroupIds += ((oldFfileGroupIds!="")?",":"")+n.value;
		}
	});
}

function initFileNewIds(){
	$("#tab_form :input[fileMark]").each(function(i,n){
		var type = $(n).attr('fileMark');
		if(type=="file"){
			fileIds += ((fileIds!="")?",":"")+n.value;
		}else if(type=="files"){
			fileGroupIds += ((fileGroupIds!="")?",":"")+n.value;
		}
	});
}

//表单保存
function formSave(async){
	async = async==null?true:async;
	if(window.onSaveBefore){//保存之前事件
		var r = window.onSaveBefore();
		if(r==false){return false;}
	}
	if(!$("#form_item").form("validate")){return false;}//校验
	var r = formSaveExecute(async);//执行保存操作
	if(window.onSaveAfter){//保存之后事件
		window.onSaveAfter();
	}
	return r;
}
function formSaveExecute(async){
	async = async==null?true:async;
	var data = formGetJson("form");
	data["formValue.formValue"] = JSON.stringify(formGetJson("tab_form"));
	initFileNewIds();
	data["oldFileIds"] = oldFileIds;
	data["oldFfileGroupIds"] = oldFfileGroupIds;
	data["fileIds"] = fileIds;
	data["fileGroupIds"] = fileGroupIds;
	
	var result = true;
	var url = basePath+"/view/form/form!save.action";
	$.ajax( {
		type: "POST",	url: url,	data: data, dataType: "json", async:async,
		success: function(json){
			$("#formValueId").val(json.formValue.formValueId);
			window.returnValue = json.formValue.formValueId;
			try{
				var tid = $('#inp_workflow_task_id');
				if(async && (tid==null || tid.length==0 || tid.val()!="")){
					alert("保存成功");
				}
				if(async && window.workflowInit!=null){
					workflowInit(false);//初始化审批数据
				}
			}catch(e){alert('error');}
		},	error: function(e) {result=false;alert("保存失败");}
	});
	return result;
}


//表单提交
function formSubmit(){
	if(window.onSubmitBefore){
		var r = window.onSubmitBefore();
		if(r==false){return false;}
	}
	formSubmitExecute();
	if(window.onSubmitAfter){
		window.onSsubmitAfter();
	}
}

function formSubmitExecute(){
	
}

//--------- 表单js end


//--------- 表单文件上传 begin
function deleteFileupload(tab_id){
  	var row = $('#'+tab_id).datagrid("getSelected");
  	if(row==null){alert('请选择需要删除的文件');return false;}
  	if(row.fileId==null || row.fileId==""){alert('无效的文件');return false;}
  	window.confirm("提示","删除所选文件?<br><span style='color:red;'>非本人上传文件也不可删除</span>",function(r){
  		if(r){
  			var url = basePath+"/view/public/uploadfileDelete.action";
  			var params = {ids:row.fileId};
  			$.ajax( {type: "POST",	url: url,	data: params, dataType: "json",error: function(e) {alert('删除失败');},
			success: function(){
				$('#'+tab_id).datagrid("reload");
				}
			});
  		}
  	});
}
  		
function downFileupload(tab_id){
  	var row = $('#'+tab_id).datagrid("getSelected");
  	if(row==null){alert('请选择需要下载的文件');return false;}
  	if(row.fileId==null || row.fileId==""){alert('无效的文件');return false;}
  	window.open(basePath+'/view/FileDown?fileid='+row.fileId);
 }
//--------- 表单文件上传 end



//--------- 表单表格  beign
		function formTableValueValidate(tid){
			var tab = $('#'+tid);
			if(tab.attr("lastIndex")!=null){alert("请先保存正在编辑的行");return false;}
			return true;
		}
  		function formTableValueAddRow(tid,gid){
  			var tab = $('#'+tid);
  			if(!formTableValueValidate(tid)){return false;}
  			
			tab.datagrid('appendRow', {groupId:gid}); 
			tab.attr("lastIndex", (tab.datagrid('getRows').length-1))
			tab.datagrid('selectRow', tab.attr("lastIndex"));
			tab.datagrid('beginEdit', tab.attr("lastIndex"));
  		}
  		
  		function formTableValueDelete(tid){
  			var tab = $('#'+tid);
  			//if(!formTableValueValidate(tid)){return false;}
  			
  			var row = tab.datagrid("getSelected");
  			if(row==null){alert("请选择需要删除的行");return false;}
  			if(row.id==null || row.id==""){
  				tab.datagrid("deleteRow", tab.datagrid("getRowIndex",row));
  				tab.attr("lastIndex", null);
  				return;
  			}
  			var url = basePath+"/view/form/formTable!delete.action";
  			var params = {"tableValue.id":row.id};
  			$.ajax({type: "POST",url: url,data: params,dataType: 'json',error: function(e) {alert("删除异常");},
				success:function(json){
					if(json.message==null || json.message==''){
						alert("删除失败,原因:"+json.message);
					}else{
						alert("删除成功");
						tab.datagrid("reload");
						tab.attr("lastIndex", null);
					}
				}
			});
  			
  		}
  		
  		function formTableValueEdit(tid){
  			var tab = $('#'+tid);
  			if(!formTableValueValidate(tid)){return false;}
  			
  			var row = tab.datagrid("getSelected");
  			if(row==null){alert("请选择需要编辑的行");return false;}
  			if(row.id==null || row.id==""){return false;}
  			
  			var rowIndex = tab.datagrid("getRowIndex",row);
  			tab.datagrid('beginEdit',rowIndex);
  			tab.attr("lastIndex", rowIndex);
  		}
  		
  		function formTableValueSave(tid){
  			var tab = $('#'+tid);
  			if(tab.attr("lastIndex")==null){alert('无可保存的编辑行');return false;}
  			if(!tab.datagrid('validateRow', tab.attr("lastIndex"))){return false;}
  			
  			var lastIndex = tab.attr("lastIndex");
  			var url = basePath+"/view/form/formTable!save.action";
  			tab.datagrid('endEdit', lastIndex);
  			var data = tab.datagrid("getData");
  			var rowData = data.rows[lastIndex];
  			tab.datagrid('beginEdit', lastIndex);
  			var params = formTableValueGetValue(tab, data.rows[lastIndex]);
  			$.ajax({type: "POST",url: url,data: params,dataType: 'json', async:false, error: function(e) {alert("保存异常");},
				success:function(json){
					if(json.tableValue==null || json.tableValue.id==null || json.tableValue.id==''){
						alert("保存失败,原因:"+json.message==null?"保存异常":json.message);
					}else{
						//tab.datagrid("reload");
						tab.datagrid('endEdit', lastIndex);
						data.rows[lastIndex].id = json.tableValue.id;
						tab.attr("lastIndex", null);
						alert("保存成功");
					}
				}
			});
  		}
  		
  		function formTableValueGetValue(tab, data){
  			var cols = tab.datagrid('options').columns[0];
  			var obj = new Object;
  			for(var i=0;i<cols.length;i++){
  				obj[cols[i].field] = data[cols[i].field];
  			}
  			var map = {'tableValue.id':data.id, 'tableValue.groupId':data.groupId, 'tableValue.tableValue':JSON.stringify(obj)};
  			return map;
  		}
//--------- 表单表格  end
  		
  		
//--------- 表单元素禁用 beigin
  	//表单元素禁用
   	function fromElementDisable(disabled,elements){
   		disabled = disabled==null?"disabled":(disabled?"disabled":"");
   		var show = disabled=="";
   		var inps = elements!=null?elements:$("#tab_form :input[elementType]");//elements为空则是表单所有的元素，否则是指定的元素
   		if(show){
   			inps.removeAttr("disabled");
   			inps.removeClass("fromElementDisable");
   		}else{
   			inps.attr("disabled",disabled);
   			inps.addClass("fromElementDisable");
   		}
   		inps.each(function(i,n){
   			var node = $(n);
   			var elementType = node.attr("elementType");
   			if(elementType=="file"){
   				node.next().css("display",show?"":"none");
   				var clearA = node.next().next();
   				if(clearA!=null && clearA.attr("fileIsClear")=="1"){
   					clearA.css("display",show?"":"none");
   				}
   			}else if(elementType=="files"){
   				$('#'+node.attr('datagridId')+'_buttons a[editItem]').css("display",show?"":"none");
   			}else if(elementType=="table"){
   				$('#'+node.attr('datagridId')+'_buttons a[editItem]').css("display",show?"":"none");
   			}else if(elementType=="date"){
   				node.datebox(show?"enable":"disable");
   			}else if(elementType=="form"){
   				node.next().css("display",show?"":"none");
   			}else if(elementType=="orgCombotree"){
   				node.combotree(show?"enable":"disable");
   			}else if(elementType=="userCombogrid"){
   				node.combogrid(show?"enable":"disable");
   			}
   			
   		});
   	}
   	
  	//表单元素 只读
   	function fromElementReadonly(readonly,elements){
   		readonly = readonly==null?"readonly":(readonly?"readonly":"");
   		var show = readonly=="";
   		var inps = elements!=null?elements:$("#tab_form :input[elementType]");//elements为空则是表单所有的元素，否则是指定的元素
   		if(show){
   			inps.removeAttr("readonly");
   		}else{
   			inps.attr("readonly",readonly);
   		}
   		inps.each(function(i,n){
   			var node = $(n);
   			var elementType = node.attr("elementType");
   			if(elementType=="file"){
   				node.next().css("display",show?"":"none");
   				var clearA = node.next().next();
   				if(clearA!=null && clearA.attr("fileIsClear")=="1"){
   					clearA.css("display",show?"":"none");
   				}
   			}else if(elementType=="files"){
   				$('#'+node.attr('datagridId')+'_buttons a[editItem]').css("display",show?"":"none");
   			}else if(elementType=="table"){
   				$('#'+node.attr('datagridId')+'_buttons a[editItem]').css("display",show?"":"none");
   			}else if(elementType=="date"){
   				node.datebox(show?"enable":"disable");
   			}else if(elementType=="form"){
   				node.next().css("display",show?"":"none");
   			}else if(elementType=="orgCombotree"){
   				node.combotree(show?"enable":"disable");
   			}else if(elementType=="userCombogrid"){
   				node.combogrid(show?"enable":"disable");
   			}else if(elementType=='select' ){
   				if(show){
   					node.removeAttr("disabled");
   					node.removeClass("fromElementDisable");
   				}else{
   					node.attr("disabled","disabled");
   					node.addClass("fromElementDisable");
   				}
   			}else if(elementType=='radio' || elementType=='checkbox'){
   				var nodes = $(':input[name="'+node.attr('name')+'"]', node.parent());
   				if(show){
   					nodes.removeAttr("disabled");
   					nodes.removeClass("fromElementDisable");
   				}else{
   					nodes.attr("disabled","disabled");
   					nodes.addClass("fromElementDisable");
   				}
   			}
   			validateSet(node,show ,elementType);
   		});
   	}
	//表单元素 隐藏
   	function fromElementHide(hide,elements){
   		var cl = elements.parent().attr('colspan')+"";
   		if(hide){
   			elements.parent().hide();
   			if(cl!='2'){elements.parent().prev().hide();}
   		}else{
   			elements.parent().show();
   			if(cl!='2'){elements.parent().prev().show();}
   		}
   	}
   	
   	function validateSet(jq, valid, elementType){
   		//alert(jq.attr('name')+":"+valid+":"+elementType);
   		if(jq.attr('name')==undefined)	return;
   		var dops = jq.attr('data-options');
   		if(dops==null || dops==""){return false;}
   		if(jq.data('dops')==null){
   			jq.data('dops',eval("({"+dops+"})"));
   		}
   		dops = jq.data('dops');
   		if(dops.required!="true"){return false;}
   		
   		var fn = valid?"reduce":"remove";
		if(valid){
			jq.data().validatebox.options.required = true;  
		}
		if(elementType=="date"){
			jq.datebox(fn);
		}else if(elementType=="orgCombotree"){
			jq.combotree(fn);
		}else if(elementType=="userCombogrid"){
			jq.combogrid(fn);
		}else{
			jq.validatebox(fn);
		}
   	}
//--------- 表单元素禁用 end