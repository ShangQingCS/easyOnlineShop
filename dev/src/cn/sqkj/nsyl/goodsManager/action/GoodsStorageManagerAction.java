package cn.sqkj.nsyl.goodsManager.action;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.sqkj.nsyl.goodsManager.pojo.NsGoods;
import cn.sqkj.nsyl.goodsManager.service.IGoodsManagerService;

import com.opensymphony.xwork2.Action;

import framework.action.PageAction;
import framework.db.DBUtil;
import framework.db.pojo.TPbUploadfile;
import framework.helper.RequestHelper;

/**
 * @author yangchaowen
 * 2016年10月18日
 * 商品库存Action类
 */
public class GoodsStorageManagerAction extends PageAction {
	private static final Logger log = Logger.getLogger(GoodsStorageManagerAction.class);
	@Resource(name="goodsManagerService")
	private IGoodsManagerService goodsManagerService;
	
	private NsGoods goods;
	
	public String modifyStorage() {
		try {
			DBUtil db = DBUtil.getDBUtilByRequest();
			NsGoods ns = this.goodsManagerService.queryGoodsById(this.goods.getId());
			ns.setStorenumb(this.goods.getStorenumb());
			db.update(ns);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 导入库存信息
	 * @return
	 */
	public String importStorageInfo() {
		DBUtil db = DBUtil.getDBUtilByRequest();
		String fileId = RequestHelper.getParameter("fileId");
		TPbUploadfile pojo = (TPbUploadfile)db.get(TPbUploadfile.class, fileId);
		
		StringBuffer msg = new StringBuffer("");
		int rowIndex = 0;
		try {
			String[][] data = getExcel2003Data(pojo.getFilePath());
			for(;rowIndex<data.length;rowIndex++){
				try {
					String[] row = data[rowIndex];
					System.out.println(row[0]+"\t"+row[2]);
					
					if(StringUtils.isBlank(row[0])||StringUtils.isBlank(row[2])) {
						continue;
					}
					Long id = null;
					try {
						id = new Long(row[0]);
					} catch (Exception e){
						msg.append("插入数据异常，发生在"+(rowIndex+2)+"行，异常信息:id不正确<br>");
						continue;
					}
					Integer storenumb = null;
					try {
						storenumb = new Integer(row[2]);
					} catch (Exception e){
						msg.append("插入数据异常，发生在"+(rowIndex+2)+"行，异常信息:库存格式不正确<br>");
						continue;
					}
					NsGoods ng = this.goodsManagerService.queryGoodsById(id);
					if(ng==null) {
						msg.append("插入数据异常，发生在"+(rowIndex+2)+"行，异常信息:商品ID不存在<br>");
						continue;
					}
					ng.setStorenumb(storenumb);
					db.update(ng);
				} catch (Exception e1) {
					msg.append("插入数据异常，发生在"+(rowIndex+2)+"行，异常信息:"+e1.getMessage()+"<br>");
					log.error("",e1);
				}
			}
		} catch (Exception e) {
			msg.append(e.getMessage());
			log.error("",e);
		}
		if(msg.length()>0){
			db.rollback();
			msg.append("数据库已回滚导入操作!!!<br>");
		}
		try {
			RequestHelper.wirte(msg.toString());
		} catch (IOException e) {
			log.error("",e);
		}
		return null;
	}
	
	/**
	 * 获取excel数据
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private String[][] getExcel2003Data(String filePath) throws Exception{
		FileInputStream fis = new FileInputStream(filePath);
		String[][] data = null;
		int rowIndex=0, colIndex = 0;
		try {
			Workbook wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			data = new String[rowCount-1][];
			for (int i = 1; i < rowCount; i++) {
				rowIndex = i+1;
				Row row = sheet.getRow(i);
				//int colCount = row.getLastCellNum();
				int colCount = 13; //固定位13列
				String[] rowdata = new String[colCount];
				for(int j=0;j<colCount;j++){
					colIndex  = j+1;
					if(row.getCell(j)!=null){
						row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
						rowdata[j] = row.getCell(j).getStringCellValue();
					}else{
						rowdata[j] = "";
					}
				}
				data[i-1] = rowdata;
			}
		}catch(Exception e){
			log.error("",e);
			throw new Exception("解析Excel异常，错误发生在"+rowIndex+"行,"+colIndex+"列<br>");
		}finally{
			fis.close();
		}
		return data;
	}
	
	/**
	 * 获取excel数据
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private String[][] getExcel2007Data(String filePath) throws Exception{
		FileInputStream fis = new FileInputStream(filePath);
		String[][] data = null;
		int rowIndex=0, colIndex = 0;
		/*try {
			Workbook wb = new XSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			data = new String[rowCount-1][];
			for (int i = 1; i < rowCount; i++) {
				rowIndex = i+1;
				HSSFRow row = sheet.getRow(i);
				//int colCount = row.getLastCellNum();
				int colCount = 13; //固定位13列
				String[] rowdata = new String[colCount];
				for(int j=0;j<colCount;j++){
					colIndex  = j+1;
					if(row.getCell(j)!=null){
						row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
						rowdata[j] = row.getCell(j).getStringCellValue();
					}else{
						rowdata[j] = "";
					}
				}
				data[i-1] = rowdata;
			}
		}catch(Exception e){
			log.error("",e);
			throw new Exception("解析Excel异常，错误发生在"+rowIndex+"行,"+colIndex+"列<br>");
		}finally{
			fis.close();
		}*/
		return data;
	}
	
	public NsGoods getGoods() {
		return goods;
	}

	public void setGoods(NsGoods goods) {
		this.goods = goods;
	}
	
}