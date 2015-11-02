package com.donalwebapp.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.Workbook;

import com.donalwebapp.admin.FileUpload;

@Path("/upload")
@Stateless
@LocalBean
public class FileUploadWS {

	private Workbook uploadDoc;
	
	@GET
	@Path("/file/{path}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response uploadData(@PathParam("path") final String path) {
		final String parsedPath = path.replaceAll("&fs&", "/");
		System.out.println("HERE: "+parsedPath);
		uploadDoc = new FileUpload(parsedPath).getUploadDocument();
		return Response.status(200).entity(uploadDoc.getSheetName(0)).build();
	}
}
