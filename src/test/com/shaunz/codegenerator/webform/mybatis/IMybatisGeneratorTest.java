package com.shaunz.codegenerator.webform.mybatis;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shaunz.codegenerator.webform.generator.mybatis.IMybatisPostgreGenerator;

public class IMybatisGeneratorTest {

	@Test
	public void IMybatisGenerator() {
		IMybatisPostgreGenerator iMybatisGenerator = new IMybatisPostgreGenerator();
		assertNotNull(iMybatisGenerator.getDbType());
	}
	
	@Test
	public void generate(){
		IMybatisPostgreGenerator iMybatisGenerator = new IMybatisPostgreGenerator();
		try {
			assertTrue(iMybatisGenerator.generate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
