package curso.java.administracionTienda.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.client.RestTemplate;


public class JsonUtil {
	
	/**
	 * 
	 * @return
	 */
	
	public static ArrayList<String> obtenerProvincias(){
		ArrayList<String> provincias = new ArrayList<>();
		
		String url = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=provincias-espanolas&q=&sort=provincia&facet=provincia";
	    RestTemplate restTemplate = new RestTemplate();
	    String resp = restTemplate.getForObject(url, String.class);
	    JsonParser springParser = JsonParserFactory.getJsonParser();
	    Map < String, Object > map = springParser.parseMap(resp);
	    ArrayList<?> al =(ArrayList) map.get("facet_groups");
	    System.out.println(al);
	    LinkedHashMap<?,?> lhm= (LinkedHashMap) al.get(0);
		al=(ArrayList) lhm.get("facets");
		for(int i=0;i<al.size();i++) {
			 lhm= (LinkedHashMap) al.get(i);
			 provincias.add(lhm.get("name").toString());
		}
	    return provincias;
	}
	
}
