package org.dimigo.gui.project;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//재료정보
public class Class2 {
    final String api_key = "e3d2732ab64267e96705673e971a638cfbe16aefdc6393cc7b17f1987a918109";
    final String type = "json";
    final String api_url = "Grid_20150827000000000227_1";
    final int start_index = 1;
    final int end_index = 30;
    String recipe_id = "";


    URL url;

    public String jaryo(String id) {
        String go="";
        try {
            recipe_id = id;
            String apiURL = "http://211.237.50.150:7080/openapi/" + api_key + "/" + type + "/" + api_url + "/" + start_index + "/" + end_index + "?RECIPE_ID=" + recipe_id;

            url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br2.readLine()) != null) {
                response.append(inputLine);
            }
            br2.close();
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(response.toString(), Map.class);
            Map<String, String> map2 = (Map<String, String>) map.get("Grid_20150827000000000227_1");


            Object row = map2.get("row");

            ArrayList row2 = (ArrayList) row;


            for (Object value : row2) {
                String im=((HashMap<String, Object>) value).get("IRDNT_NM") + " " + ((HashMap<String, Object>) value).get("IRDNT_CPCTY")+"\n";
                go=go.concat(im);
            }

        } catch (Exception e) {
            return "레시피가 없습니다";
        }
        return go;
    }


}