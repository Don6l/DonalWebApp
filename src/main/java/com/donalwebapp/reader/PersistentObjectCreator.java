package com.donalwebapp.reader;

import java.text.ParseException;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.donalwebapp.entities.WorldWideWebData;
@Stateless
@LocalBean
public class PersistentObjectCreator {
	
	public static WorldWideWebData createWwwDataPersistObjects(final Map<String,String> map){
		WorldWideWebData wwwData = null;
		try{
			wwwData = new WorldWideWebData();
			wwwData.setTest_id(Integer.parseInt(map.get("test_id")));
			//wwwData.setTest_date());
			wwwData.setClient_ip_id(Integer.parseInt(map.get("client_ip_id")));
			wwwData.setDownload_kbps(Integer.parseInt("download_kbps"));
			wwwData.setUpload_kbps(Integer.parseInt("upload_kbps"));
			wwwData.setLatency(Integer.parseInt("latency"));
			wwwData.setServer_name(map.get("latency"));
			wwwData.setServer_country(map.get("server_country"));
		} catch(ParseException e){
			e.printStackTrace();
		}
		
		
		return wwwData;
				
	}
}
