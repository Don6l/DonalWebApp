package com.donalwebapp.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.donalwebapp.entities.WorldWideWebData;

@Stateless
@LocalBean
public class ReadExcelData {


	final private List<Object> wwwDataList = new ArrayList<>();
	
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
		
		//final WorldWideWebData wwwData = PersistentObjectCreator.createWwwDataPersistObjects(wwwDataList);
		//wwwDataList.add(wwwData);
	return null;
	}
	
	
	
	public List<Object> readDataFields(final Row row){
		final List<Object> dataList = new ArrayList<>();
		int counter = 0;
		for (final Cell cell : row) {

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				dataList.add(counter,cell.getBooleanCellValue());
				break;
				
			case Cell.CELL_TYPE_NUMERIC:{
				if (DateUtil.isCellDateFormatted(cell)) {
					final Date date = cell.getDateCellValue();
					final SimpleDateFormat sdf = 
							new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					final String currentTime = sdf.format(date);
					dataList.add(counter,currentTime);
					break;
				} 
				else {						
					final long longValue = (long)cell.getNumericCellValue();	
					dataList.add(counter, longValue);
					break;
				}
			}
			
			case Cell.CELL_TYPE_STRING:
				dataList.add(counter, cell.getStringCellValue());
				break;
			}
			counter++;
		}
		return dataList;
	} 
}
