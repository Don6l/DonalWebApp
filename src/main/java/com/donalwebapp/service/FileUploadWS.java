package com.donalwebapp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

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
		final InputPart inputPart = input.getFormDataMap().get("file").get(0);
		final MultivaluedMap<String, String> fileData = inputPart.getHeaders();
		final String filePath = parseFileName(fileData);
		System.out.println(filePath);
		final String UPLOAD_LOCATION = "../";
		
		try {
			final InputStream istream = inputPart.getBody(InputStream.class, null);
			if(!filePath.toLowerCase().endsWith("xlsx") && !filePath.toLowerCase().endsWith("xls")) {
				throw new IllegalArgumentException("Unsupported file type");
			}
			uploadDoc = new HSSFWorkbook(new FileInputStream(XlsLocator.fileNameFinder()));
			System.out.println(filePath +"Donal!!!!!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//TODO GET FUCKED
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
}
