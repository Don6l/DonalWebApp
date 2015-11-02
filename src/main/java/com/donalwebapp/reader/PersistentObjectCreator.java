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
			wwwData.setTest_id(Map.get("test_id");
			
			
		} catch(ParseException e){
			e.printStackTrace();
		}
		
		
		return wwwData;
				
	}
}
