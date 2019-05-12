package com.example.vejalivros.rest;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    public String get(String requestURL){
        StringBuilder sb = new StringBuilder();

        try{
            URL url = new URL(requestURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;

            while ((s=br.readLine())!= null){
                sb.append(s+"\n");
            }

        }catch (Exception e){
            int a = 1;
            System.out.println(a);
        }
        return sb.toString();
    }

    public String sendGetRequestParamsQuest(String requestURL, String [] params){
        StringBuilder sb = new StringBuilder();

        try{

            for(int i = 0; i < params.length; i++){
                requestURL = requestURL + params[i];
            }

            URL url = new URL(requestURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;

            while ((s=br.readLine())!= null){
                sb.append(s+"\n");
            }

        }catch (Exception e){
        }
        return sb.toString();
    }

    public String getPostDataString(HashMap<String,String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry: params.entrySet()){
            if(first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
