package com.donalwebapp.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.donalwebapp.entities.WorldWideWebData;

@Stateless
@LocalBean
public class ReadExcelData {


	final private Map<String, String> wwwDataList = new HashMap<>();
	
	public List<Object> getListOfDataEntities(final MultipartFormDataInput input , final String fileName){
		
		FileInputStream file;
		try {
		file = new FileInputStream(new File(fileName));
		
		HSSFWorkbook workbook;
		workbook = new HSSFWorkbook(file);

		final HSSFSheet sheet1 = workbook.getSheetAt(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			final WorldWideWebData wwwData = PersistentObjectCreator.createWwwDataPersistObjects(wwwDataList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//wwwDataList.add(wwwData);
	return null;
	}
	
	
	
		public List<Object> readDataFields(final Row row){
		final List<Object> dataList = new ArrayList<>();
		int counter = 0;
		for (final Cell cell : row) {

				dataList.add(counter, cell.getStringCellValue());
				
			}
			counter++;
		
		return dataList;
	} 
}
