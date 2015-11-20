package com.donalwebapp.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.donalwebapp.reader.ReadExcelData;

@Stateless
@LocalBean
@Path("/upload")
public class FileUploadWS {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadWS.class);
	private Workbook uploadDoc;
	private final String fileLocation = "C://WWW_Data//";
	@Inject
	private ReadExcelData readExcelData;
	
	@POST
	@Path("/file")
	@Consumes("multipart/form-data")
	public Response uploadData(final MultipartFormDataInput input) {
		LOGGER.debug("HERE I AM AAAAAA VVVVVV: ");
		String fileName = "";
    	final Map<String, List<InputPart>> formParts = input.getFormDataMap();
    	final List<InputPart> inPart = formParts.get("file");
		for (final InputPart inputPart : inPart) {
			final MultivaluedMap<String, String> headers = inputPart
					.getHeaders();
			fileName = parseFileName(headers);
			LOGGER.debug("fileName: [{}]", fileName);
			if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
				LOGGER.debug("fileName ends with .xls* == true");
				// save file to server
				final InputStream istream;
				try {
					istream = inputPart.getBody(InputStream.class, null);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				//parse xls(x)
				final List<Object> readEntities = readExcelData.getListOfDataEntities(istream);
				LOGGER.debug("readEntities: [{}]", readEntities);

				//parse entities and persist
				
				// loadToDb.mapObjectsToDatabase(input, fileName, false);
				return Response.status(200)
						.entity("File saved to server location : " + fileName)
						.build();
			}

		}
		
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
