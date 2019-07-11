package org.dimigo.gui.project;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//기본정보
public class Class1 {
    final String api_key = "e3d2732ab64267e96705673e971a638cfbe16aefdc6393cc7b17f1987a918109";
    final String type = "json";
    final String api_url = "Grid_20150827000000000226_1";
    final int start_index = 1;
    final int end_index = 1;
    String recipe_nm_ko = "";
    private String id;


    public String gibonn(String name) {
        if(name.equals(""))
            return "none";
        recipe_nm_ko = name;
        String apiURL = "http://211.237.50.150:7080/openapi/" + api_key + "/" + type + "/" + api_url + "/" + start_index + "/" + end_index + "?RECIPE_NM_KO=" + recipe_nm_ko;
        try {
            Document doc = Jsoup.connect(apiURL).ignoreContentType(true).get();

            String result1 = doc.select("body").toString();
            String result = result1.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").substring(2);

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(result, Map.class);
            Map<String, String> map2 = (Map<String, String>) map.get("Grid_20150827000000000226_1");

            Object row = map2.get("row");
            ArrayList row2 = (ArrayList) row;

            id=((HashMap<String, Object>) row2.get(0)).get("RECIPE_ID").toString();


        } catch (Exception e) {
            return "wrong";
        }
        return id;
    }
}