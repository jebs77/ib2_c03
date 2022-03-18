import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class DBMethods {
    public DBMethods(){

    }
    public String makeGETRequest(String urlName){
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        try {
            URL url = new URL(urlName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }
            conn.disconnect();
            return sb.toString();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (ProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "";

    }

    public ArrayList<String> returnJSONNames(String jsonString){
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonString);
            int size = array.length();
            //String[] info = new String[size];
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                //info[i] = (curObject.getString("Name"));
                list.add(curObject.getString("Name"));
            }
            return list;
        }
        catch (JSONException e){
            //String[] error = new String[1];
            return list;
        }
    }
    public ArrayList<String>returnJSONWeight(String jsonString){
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonString);
            int size = array.length();
            //String[] info = new String[size];
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                list.add(curObject.getString("MaxWeight"));
                list.add(curObject.getString("MinWeight"));
            }
            return list;
        }
        catch (JSONException e){
            //String[] error = new String[1];
            return list;
        }

    }

    public ArrayList<String>returnJSONDeviceStatus(String jsonString){
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonString);
            int size = array.length();
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                //info[i] = (curObject.getString("Name"));
                //current_measured, in/out, component_on, manua
                list.add(curObject.getString("last_measured"));
                list.add(curObject.getString("current_measured"));
                list.add(curObject.getString("in/out"));
                list.add(curObject.getString("component_on"));
                list.add(curObject.getString("manual"));

            }
            for(String s: list){
                System.out.println(s);
            }
            return list;
        }
        catch (JSONException e){
            //String[] error = new String[1];
            return list;
        }
    }
    public ArrayList<String>returnJSONUserInfo(String jsonString,String key){
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonString);
            int size = array.length();
            for (int i = 0; i < array.length(); i++) {
                JSONObject curObject = array.getJSONObject(i);
                list.add(curObject.getString(key));
            }
            for(String s: list){
                System.out.println(s);
            }
            return list;
        }
        catch (JSONException e){
            //String[] error = new String[1];
            return list;
        }
    }



    }
