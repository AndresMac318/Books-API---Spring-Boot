package com.company.books.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
	
	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();
	
	public ArrayList<HashMap<String, String>> getMetadata(){
		return metadata;
	}
	
	public void setMetadata(String tipo, String codigo, String date) {
		HashMap<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("type", tipo);
		mapa.put("code", codigo);
		mapa.put("date", date);
		
		metadata.add(mapa);
	}

}
