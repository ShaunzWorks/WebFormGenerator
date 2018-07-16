package com.shaunz.codegenerator.webform.main;

import java.util.HashMap;
import java.util.Map;

import com.shaunz.codegenerator.webform.generator.mybatis.IMybatisPostgreGenerator;

public class GeneratorFactory {
	private static GeneratorFactory generatorFactory;
	private static Map<GeneratorType, Generator> generators;
	private GeneratorFactory(){
		generators = new HashMap<GeneratorType, Generator>();
	}
	
	public static enum GeneratorType{
		MYBATIS,SPRINGMVC,SERVICE,JSP
	}
	
	public synchronized static GeneratorFactory getInstance(){
		if(generatorFactory == null){
			generatorFactory = new GeneratorFactory();
		}
		return generatorFactory;
	}
	
	public Generator getGenrator(GeneratorType generatorType){
		if(GeneratorType.MYBATIS.equals(generatorType)){
			if(!generators.containsKey(GeneratorType.MYBATIS)){
				generators.put(GeneratorType.MYBATIS, new IMybatisPostgreGenerator());
			}
			return generators.get(GeneratorType.MYBATIS);
			
		}
		return null;
	}
}
