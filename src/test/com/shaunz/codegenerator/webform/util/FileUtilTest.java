package com.shaunz.codegenerator.webform.util;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void getClazzPath() {
		String clazzPath = FileUtil.getClazzPath();
		assertNotNull(clazzPath);
	}
	
	@Test
	public void readFile(){
		InputStream io = FileUtil.readFile("jdbc.properties");
		assertNotNull(io);
	}

}
