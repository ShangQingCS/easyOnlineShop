package cn.sqkj.nsyl;

import java.io.File;

import framework.config.Config;
import framework.util.DateUtils;

public class Test {
	public static void main(String[] args) {
		String imgUploadPath = "D:/test";
		String currDate = DateUtils.toDate("yyyyMMdd",DateUtils.getDate());
		System.out.println(currDate);
		File dir = new File(imgUploadPath);
		System.out.println(dir.exists());
		if(!dir.exists()) {
			System.out.println("create");
			dir.mkdir();
		}
		dir = new File(dir.getAbsolutePath()+"/"+currDate);
		if(!dir.exists()) {
			System.out.println("create1");
			dir.mkdir();
		} else {
			System.out.println(dir.getName());
		}
	}
}
