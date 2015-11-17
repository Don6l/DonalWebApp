package com.donalwebapp.reader;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.donalwebapp.entities.WorldWideWebData;
@Stateless
@LocalBean
public class PersistentObjectCreator {
	
	public static WorldWideWebData createWwwDataPersistObjects(final Map<String,String> map) throws ParseException{
		WorldWideWebData wwwData = null;
		wwwData = new WorldWideWebData();
		wwwData.setTest_id(Integer.parseInt(map.get("test_id")));
		//wwwData.setTest_date());
		wwwData.setClient_ip_id(Integer.parseInt(map.get("client_ip_id")));
		wwwData.setDownload_kbps(Integer.parseInt("download_kbps"));
		wwwData.setUpload_kbps(Integer.parseInt("upload_kbps"));
		wwwData.setLatency(Integer.parseInt("latency"));
		wwwData.setServer_name(map.get("latency"));
		wwwData.setServer_country(map.get("server_country"));
		wwwData.setServer_country_code(map.get("server_country_code"));
		wwwData.setServer_latitude(Double.parseDouble(map.get("server_latitude")));
		wwwData.setServer_longitude(Double.parseDouble(map.get("server_longitude")));
		wwwData.setServer_sponsor_name(map.get("server_sponsor_name"));
		wwwData.setUser_agent(map.get("user_agent"));
		wwwData.setIsp_name(map.get("isp_name"));
		wwwData.setClient_net_speed(Integer.parseInt(map.get("client_net_speed")));
		wwwData.setIs_isp(Integer.parseInt(map.get("is_isp")));
		wwwData.setClient_country(map.get("client_country"));
		wwwData.setClient_country_code(map.get("client_country_code"));
		wwwData.setClient_region_name(map.get("client_region_name"));
		wwwData.setClient_region_code(map.get("client_region_code"));
		wwwData.setClient_city(map.get("client_city"));
		wwwData.setClient_latitude(Double.parseDouble(map.get("client_latitude")));
		wwwData.setClient_longitude(Double.parseDouble(map.get("client_longitude")));
		wwwData.setMiles_between(Integer.parseInt(map.get("miles_between")));
		
		
		return wwwData;
				
	}
	
//	public static WorldWideWebData createWwwDataPersistObjects(final List<Object> list) throws ParseException{
//		WorldWideWebData wwwData = null;
//		wwwData = new WorldWideWebData();
//		wwwData.setTest_id(Integer.parseInt(map.get("test_id")));
//		//wwwData.setTest_date());
//		wwwData.setClient_ip_id(Integer.parseInt(map.get("client_ip_id")));
//		wwwData.setDownload_kbps(Integer.parseInt("download_kbps"));
//		wwwData.setUpload_kbps(Integer.parseInt("upload_kbps"));
//		wwwData.setLatency(Integer.parseInt("latency"));
//		wwwData.setServer_name(map.get("latency"));
//		wwwData.setServer_country(map.get("server_country"));
//		wwwData.setServer_country_code(map.get("server_country_code"));
//		wwwData.setServer_latitude(Double.parseDouble(map.get("server_latitude")));
//		wwwData.setServer_longitude(Double.parseDouble(map.get("server_longitude")));
//		wwwData.setServer_sponsor_name(map.get("server_sponsor_name"));
//		wwwData.setUser_agent(map.get("user_agent"));
//		wwwData.setIsp_name(map.get("isp_name"));
//		wwwData.setClient_net_speed(Integer.parseInt(map.get("client_net_speed")));
//		wwwData.setIs_isp(Integer.parseInt(map.get("is_isp")));
//		wwwData.setClient_country(map.get("client_country"));
//		wwwData.setClient_country_code(map.get("client_country_code"));
//		wwwData.setClient_region_name(map.get("client_region_name"));
//		wwwData.setClient_region_code(map.get("client_region_code"));
//		wwwData.setClient_city(map.get("client_city"));
//		wwwData.setClient_latitude(Double.parseDouble(map.get("client_latitude")));
//		wwwData.setClient_longitude(Double.parseDouble(map.get("client_longitude")));
//		wwwData.setMiles_between(Integer.parseInt(map.get("miles_between")));
//		
//		
//		return wwwData;
//				
//	}

}
