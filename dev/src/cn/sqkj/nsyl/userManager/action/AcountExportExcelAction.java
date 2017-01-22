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
import cn.sqkj.nsyl.userManager.service.IUserPurseService;
import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;

public class AcountExportExcelAction extends PageAction {
	
	@Resource(name="userPurseService")
	private IUserPurseService userPurseService;
	private AuditLogger logger = AuditLogger.getLogger(); //审计日志对象
	private Logger log = Logger.getLogger(GoodsManagerAction_bak.class); //系统log日志对象
	private TXtUser user = (TXtUser) RequestHelper.getSession().getAttribute("user");
	private String messages;//通知
	private String fileName; // 下载文件名称
	private InputStream excelFile; // 下载文件流

	
	
	public String accountsDownload() throws Exception {
		PageBean resultData = this.userPurseService.queryUserPurseListCount(this.getPageBean());
		HSSFWorkbook workbook = acountexportExcel(resultData);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		workbook.write(output);
		byte[] ba = output.toByteArray();
		excelFile = new ByteArrayInputStream(ba);
		output.flush();
		output.close();
		return "excel";
	}
	
	public HSSFWorkbook acountexportExcel(PageBean resultData) throws Exception {
		HSSFWorkbook workbook = null;
		// 这里的数据即时你要从后台取得的数据
		// 创建工作簿实例
		workbook = new HSSFWorkbook();
		// 创建工作表实例
		HSSFSheet sheet = workbook.createSheet("账目统计表");
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
			this.createCell(row, 3, style, HSSFCell.CELL_TYPE_STRING, "账目类型");
			this.createCell(row, 4, style, HSSFCell.CELL_TYPE_STRING, "支付方式");
			this.createCell(row, 5, style, HSSFCell.CELL_TYPE_STRING, "操作类型");
			this.createCell(row, 6, style, HSSFCell.CELL_TYPE_STRING, "金额");
			this.createCell(row, 7, style, HSSFCell.CELL_TYPE_STRING, "时间");
			this.createCell(row, 8, style, HSSFCell.CELL_TYPE_STRING, "状态");
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
				if (map.get("purseType")!=null) {
	            	if(map.get("purseType").toString().equals("0")){
	            		this.createCell(row1, 3, style, HSSFCell.CELL_TYPE_STRING, "钱包");
	            	}else if(map.get("purseType").toString().equals("1")){
	            		this.createCell(row1, 3, style, HSSFCell.CELL_TYPE_STRING, "分红");
	            	}else if(map.get("purseType").toString().equals("2")){
	            		this.createCell(row1, 3, style, HSSFCell.CELL_TYPE_STRING, "积分");
	            	}
	            }
				if (map.get("tradeType")!=null) {
	            	if(map.get("tradeType").equals('0')){
	            		this.createCell(row1, 4, style, HSSFCell.CELL_TYPE_STRING, "系统操作");
	            	}else if(map.get("tradeType").equals('1')){
	            		this.createCell(row1, 4, style, HSSFCell.CELL_TYPE_STRING, "支付宝");
	            	}else if(map.get("tradeType").equals('2')){
	            		this.createCell(row1, 4, style, HSSFCell.CELL_TYPE_STRING, "微信");
	            	}
	            }
				if (map.get("optionType")!=null) {
	            	if(map.get("optionType").equals('0')){
	            		this.createCell(row1, 5, style, HSSFCell.CELL_TYPE_STRING, "增加");
	            	}else if(map.get("optionType").equals('1')){
	            		this.createCell(row1, 5, style, HSSFCell.CELL_TYPE_STRING, "减少");
	            	}
	            }
				if (map.get("tradeAmount")!=null) {
					this.createCell(row1, 6, style, HSSFCell.CELL_TYPE_STRING, map.get("tradeAmount"));
				}
				if (map.get("optionTime")!=null) {
					this.createCell(row1, 7, style, HSSFCell.CELL_TYPE_STRING, map.get("optionTime"));
				}
				if (map.get("userStatus")!=null) {
	            	if(map.get("userStatus").equals('0')){
	            		this.createCell(row1, 8, style, HSSFCell.CELL_TYPE_STRING, "注销");
	            	}else if(map.get("userStatus").equals('1')){
	            		this.createCell(row1, 8, style, HSSFCell.CELL_TYPE_STRING, "正常");
	            	}else if(map.get("userStatus").equals('2')){
	            		this.createCell(row1, 8, style, HSSFCell.CELL_TYPE_STRING, "冻结");
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
		String fileName = (sf.format(new Date()).toString())+ "账目统计信息.xls";
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

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	
}
