package com.shaunz.codegenerator.webform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);
	public static InputStream readFile(String fileNm) {
		InputStream inputStream = null;
		try {
			String prePath = getClazzPath()+"/";
			inputStream = new FileInputStream(new File(prePath+fileNm));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		return inputStream;
	}
	
	public static String getClazzPath(){
		//String clazzPath = System.getProperty("user.dir", ".");
		String clazzPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
		return clazzPath;
	}
}
