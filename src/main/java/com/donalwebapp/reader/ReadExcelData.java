package com.donalwebapp.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.donalwebapp.entities.WorldWideWebData;
import com.donalwebapp.service.FileUploadWS;

@Stateless
@LocalBean
public class ReadExcelData {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadWS.class);
	final private List<WorldWideWebData> wwwDataList;
	
	@Inject
	public ReadExcelData() {
		wwwDataList = new ArrayList<>();
	}
	
	public List<Object> getListOfDataEntities(final InputStream istream){
		LOGGER.debug("Inside it now");
		try {
			Workbook workbook;
			workbook = WorkbookFactory.create(istream);
	
			final Sheet sheet1 = workbook.getSheetAt(0);
			
		
			
		} catch (IOException | InvalidFormatException e) {
			throw new RuntimeException(e);
		}
		
//		try {
//			final WorldWideWebData wwwData = PersistentObjectCreator.createWwwDataPersistObjects(mapOfSheetData);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		//wwwDataList.add(wwwData);
	return null;
	}
	
	
	private Map<String, String> readSheetInfo(Sheet sheet){
		
		Map<String, String> infoMap = new HashMap<String, String>();
		
		final Iterator<Row> iteratorRow = sheet.rowIterator();
		while(iteratorRow.hasNext()) {
			Row row = iteratorRow.next();
			final Iterator<Cell> iteratorCell = row.cellIterator();
			while(iteratorCell.hasNext()) {
				final Cell cell = iteratorCell.next();
				final String cellValue = cell.getStringCellValue();
//				infoMap.add();
			}
		}
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

enum CellType {
	TEST_ID("test_id"),
	TEST_DATE("test_date");
	
	
	
	
	private String name;
	private int index;
	
	private CellType(final String name) {
		this.name = name;
	}
	public static void setIndex(String name, int index) {
		boolean isSet = false;
		for(CellType cellType : CellType.values()) {
			if(cellType.name == name) {
				cellType.index = index;
				isSet = true;
			}
		}
		if(!isSet) {
			throw new IllegalArgumentException("Name not found: "+name);
		}
	}
	
	public static CellType getCellTypeForIndex(int index) {
		for(CellType cellType : CellType.values()) {
			if(cellType.index == index) {
				return cellType;
			}
		}
		throw new IllegalArgumentException("No CellType found for given index: "+index);
	}
}
