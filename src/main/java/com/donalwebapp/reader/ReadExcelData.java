package com.donalwebapp.reader;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Stateless
@LocalBean
public class ReadExcelData {

	public void readData(final HSSFSheet sheet){
		for(final Row row : sheet){
			final Map<String, String> map;
			
			
			
			
		}
		
	}
	
	public Map<String,String> readFields(final Row row){
		final Map<String, String> map = new HashMap<String, String>();
		
		for(final Cell cell: row){
////			
		}
		
		return map;
	}
}
