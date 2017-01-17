package cn.sqkj.nsyl.userManager.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.sqkj.nsyl.goodsManager.action.GoodsManagerAction_bak;
import cn.sqkj.nsyl.userManager.pojo.NsUser;
import cn.sqkj.nsyl.userManager.service.IUserManagerService;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class ExportExcelAction extends PageAction {
	
	@Resource(name="userManagerService")
	private IUserManagerService userManagerService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private NsUser nsUser;
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	private String fileName; // 下载文件名称
	private InputStream excelFile; // 下载文件流

	public String download() throws Exception {
		PageBean resultData = this.userManagerService.queryNsUserCount(this.getPageBean());
		HSSFWorkbook workbook = exportExcel(resultData);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		workbook.write(output);
		byte[] ba = output.toByteArray();
		excelFile = new ByteArrayInputStream(ba);
		output.flush();
		output.close();
		return "excel";
	}
	
	public HSSFWorkbook exportExcel(PageBean resultData) throws Exception {
		HSSFWorkbook workbook = null;
		// 这里的数据即时你要从后台取得的数据
		// 创建工作簿实例
		workbook = new HSSFWorkbook();
		// 创建工作表实例
		HSSFSheet sheet = workbook.createSheet("会员统计表");
		// 设置列宽
		this.setSheetColumnWidth(sheet);
		// 获取样式
		HSSFCellStyle style = this.createTitleStyle(workbook);
		if (resultData != null && resultData.getTotal() > 0) {
			// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
			HSSFRow row = sheet.createRow((short) 0);// 建立新行
			this.createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "序号");
			this.createCell(row, 1, style, HSSFCell.CELL_TYPE_STRING,"用户名");
			this.createCell(row, 2, style, HSSFCell.CELL_TYPE_STRING, "真实姓名");
			this.createCell(row, 3, style, HSSFCell.CELL_TYPE_STRING, "性别");
			this.createCell(row, 4, style, HSSFCell.CELL_TYPE_STRING, "手机号");
			this.createCell(row, 5, style, HSSFCell.CELL_TYPE_STRING, "身份证号");
			this.createCell(row, 6, style, HSSFCell.CELL_TYPE_STRING, "创建时间");
			this.createCell(row, 7, style, HSSFCell.CELL_TYPE_STRING, "充值金额");
			this.createCell(row, 8, style, HSSFCell.CELL_TYPE_STRING, "分红金额");
			this.createCell(row, 9, style, HSSFCell.CELL_TYPE_STRING, "消费金额");
			this.createCell(row, 10, style, HSSFCell.CELL_TYPE_STRING, "积分");
			this.createCell(row, 11, style, HSSFCell.CELL_TYPE_STRING, "当前余额");
			this.createCell(row, 12, style, HSSFCell.CELL_TYPE_STRING, "状态");
			// 给excel填充数据
			HSSFRow row1 = null;
			int i = 0 ;
			for(Iterator iterator = resultData.getPageData().iterator();iterator.hasNext();){
				i++;
				Map map = new HashMap();
				map = (Map) iterator.next();
				row1 = sheet.createRow((short) (i));// 建立新行
				this.createCell(row1, 0, style, HSSFCell.CELL_TYPE_STRING, i);
				if (map.get("userName")!=null) {
					this.createCell(row1, 1, style, HSSFCell.CELL_TYPE_STRING, map.get("userName"));
				}
				if (map.get("trueName")!=null) {
					this.createCell(row1, 2, style, HSSFCell.CELL_TYPE_STRING, map.get("trueName"));
				}
				if (map.get("userSex")!=null) {
	            	if(map.get("userSex").equals('0')){
	            		this.createCell(row1, 3, style, HSSFCell.CELL_TYPE_STRING, "女");
	            	}else if(map.get("userSex").equals('1')){
	            		this.createCell(row1, 3, style, HSSFCell.CELL_TYPE_STRING, "男");
	            	}else if(map.get("userSex").equals('2')){
	            		this.createCell(row1, 3, style, HSSFCell.CELL_TYPE_STRING, "未知");
	            	}
	            }
				if (map.get("userPhone")!=null) {
					this.createCell(row1, 4, style, HSSFCell.CELL_TYPE_STRING, map.get("userPhone"));
				}
				if (map.get("identityCard")!=null) {
					this.createCell(row1, 5, style, HSSFCell.CELL_TYPE_STRING, map.get("identityCard"));
				}
				if (map.get("createTime")!=null) {
					this.createCell(row1, 6, style, HSSFCell.CELL_TYPE_STRING, map.get("createTime"));
				}
				if (map.get("cz")!=null) {
					this.createCell(row1, 7, style, HSSFCell.CELL_TYPE_STRING, map.get("cz"));
				}
				if (map.get("fhje")!=null) {
					this.createCell(row1, 8, style, HSSFCell.CELL_TYPE_STRING, map.get("fhje"));
				}
				if (map.get("xf")!=null) {
					this.createCell(row1, 9, style, HSSFCell.CELL_TYPE_STRING, map.get("xf"));
				}
				if (map.get("jf")!=null) {
					this.createCell(row1, 10, style, HSSFCell.CELL_TYPE_STRING, map.get("jf"));
				}
				if (map.get("dqye")!=null) {
					this.createCell(row1, 11, style, HSSFCell.CELL_TYPE_STRING, map.get("dqye"));
				}
				if (map.get("userStatus")!=null) {
	            	if(map.get("userStatus").equals('0')){
	            		this.createCell(row1, 12, style, HSSFCell.CELL_TYPE_STRING, "注销");
	            	}else if(map.get("userStatus").equals('1')){
	            		this.createCell(row1, 12, style, HSSFCell.CELL_TYPE_STRING, "正常");
	            	}else if(map.get("userStatus").equals('2')){
	            		this.createCell(row1, 12, style, HSSFCell.CELL_TYPE_STRING, "冻结");
	            	}
	            }
			}
			
		}else{
			this.createCell(sheet.createRow(0), 0, style,
			HSSFCell.CELL_TYPE_STRING, "查无资料");
		}
		return workbook;
	}

	// 设置excel的title样式
	private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	private void setSheetColumnWidth(HSSFSheet sheet) {
		// 根据你数据里面的记录有多少列，就设置多少列
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 8000);
		sheet.setColumnWidth(5, 8000);
		sheet.setColumnWidth(6, 8000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 5000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
	}

	// 创建Excel单元格

	private void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType, Object value) {
		HSSFCell cell = row.createCell(column);
		if (style != null) {
			cell.setCellStyle(style);
		}
		switch (cellType) {
		case HSSFCell.CELL_TYPE_BLANK: {
		}
			break;
		case HSSFCell.CELL_TYPE_STRING: {
			cell.setCellValue(value.toString());
		}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.parseDouble(value.toString()));
		}
			break;
		default:
			break;
		}
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		String fileName = (sf.format(new Date()).toString())+ "会员统计信息.xls";
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		}
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public NsUser getNsUser() {
		return nsUser;
	}

	public void setNsUser(NsUser nsUser) {
		this.nsUser = nsUser;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	
}
