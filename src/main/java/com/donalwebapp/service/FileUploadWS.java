package com.donalwebapp.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.donalwebapp.reader.*;

@Stateless
@LocalBean
@Path("/upload")
public class FileUploadWS {
	
	private Workbook uploadDoc;
	
	@POST
	@Path("/file")
	@Consumes("multipart/form-data")
	public Response uploadData(final MultipartFormDataInput input) {
		System.out.println("HERE I AM AAAAAA VVVVVV: ");
		 String fileName = "";
    	 final Map<String, List<InputPart>> formParts = input.getFormDataMap();
    	 final List<InputPart> inPart = formParts.get("file");
                for (final InputPart inputPart : inPart) {
                     final MultivaluedMap<String, String> headers = inputPart.getHeaders();
                     fileName = parseFileName(headers);
                     if(fileName.endsWith(".xlsx")||fileName.endsWith(".xls")){    
                    	 fileName = "../"+fileName;
                    	 //loadToDb.mapObjectsToDatabase(input, fileName, false);
                    	 return Response.status(200).entity("File saved to server location : " + fileName).build();
                     }
		
                }
		
		
		
		
		/**
		final InputPart inputPart = input.getFormDataMap().get("file").get(0);
		final MultivaluedMap<String, String> fileData = inputPart.getHeaders();
		final String filePath = parseFileName(fileData);
		System.out.println(filePath);
		final String UPLOAD_LOCATION = "../";
		
		try {
			final InputStream istream = inputPart.getBody(InputStream.class, null);
			if(!filePath.toLowerCase().endsWith(".xlsx") && !filePath.toLowerCase().endsWith(".xls")) {
				throw new IllegalArgumentException("Unsupported file type");
			}
			uploadDoc = new HSSFWorkbook(new FileInputStream(fileNameFinder()));
			final HSSFSheet wwwData = uploadDoc.getSheet("");
			System.out.println(filePath +"Donal!!!!!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}**/
		
		return Response.status(200).entity("Stuf").build();
	}
	
	public String parseFileName(final MultivaluedMap<String, String> headers) {
		final String[] contentDispositionHeader = headers.getFirst(
				"Content-Disposition").split(";");
		for (final String name : contentDispositionHeader) {
			if (name.trim().startsWith("filename")) {
				final String[] tmp = name.split("=");
				final String fileName = tmp[1].trim().replaceAll("\"", "");
				
				
				
				return fileName;
			}
		}
		throw new IllegalArgumentException("Error parsing headers. Could not find filename.");
	}
	
	public static File fileNameFinder() {
		final String dirpath = "../";
		String fileName;
		final File folder = new File(dirpath);
		File resultFile = null;
		System.out.println(folder.getAbsolutePath());
		final File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileName = listOfFiles[i].getName();
				if (fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx")) {
					resultFile = new File("../" + fileName);
				}
			}
		}
		return resultFile;
	}
}
