package com.donalwebapp.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileUpload {

	private Workbook uploadDocument;
	
	public FileUpload(final String filePath) {
		InputStream is;
		try {
			is = new FileInputStream(filePath);
			uploadDocument = new XSSFWorkbook(is);
			System.out.println("It's doing something, fileName="+filePath);
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}
	
	public Workbook getUploadDocument() {
		return uploadDocument;
	}
	
}
