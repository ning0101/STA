/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author alec
 */
public class HTTPConnector {
    
    public HTTPConnector(){
        
    }
    
    public String sendGet(String GET_url) throws Exception {
        if (!GET_url.contains("http://")) {
            GET_url = "http://" + GET_url;
        }
        
//        GET_url = GET_url.replace(" ", "%20");
        
        if(GET_url.contains("'")){
            String[] tmp = GET_url.split("'");
            if(tmp.length==2)
                GET_url = tmp[0] + "'" + URLEncoder.encode(tmp[1], "UTF-8")+ "'";
            else{
                GET_url = tmp[0];
                for(int i=1; i<tmp.length; i=i+2){
                    GET_url = GET_url + "'" + URLEncoder.encode(tmp[i], "UTF-8")+ "'" + tmp[i+1];
                }
            }
        }
        GET_url = GET_url.replace(" ", "%20");

        URL obj = new URL(GET_url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

        // optional default is GET
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String GET_resp = response.toString();

        return GET_resp;
    }
    
    public String sendGetJSON(String GET_url) throws Exception {
        if (GET_url.contains("https://")) {
            return sendGetJSON_HTTPS(GET_url);
        }
        
        if (!GET_url.contains("http://")) {
            GET_url = "http://" + GET_url;
        }
        
//        GET_url = GET_url.replace(" ", "%20");
        
        if(GET_url.contains("'")){
            String[] tmp = GET_url.split("'");
            if(tmp.length==2)
                GET_url = tmp[0] + "'" + URLEncoder.encode(tmp[1], "UTF-8")+ "'";
            else{
                GET_url = tmp[0];
                for(int i=1; i<tmp.length; i=i+2){
                    GET_url = GET_url + "'" + URLEncoder.encode(tmp[i], "UTF-8")+ "'" + tmp[i+1];
                }
            }
        }
        GET_url = GET_url.replace(" ", "%20");

        URL obj = new URL(GET_url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

        // optional default is GET
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String GET_resp = response.toString();

        return GET_resp;
    }
    
    public String sendGetJSON_HTTPS(String GET_url) throws Exception {
        setSSLCertificate();
        
//        GET_url = GET_url.replace(" ", "%20");
        
        if(GET_url.contains("'")){
            String[] tmp = GET_url.split("'");
            if(tmp.length==2)
                GET_url = tmp[0] + "'" + URLEncoder.encode(tmp[1], "UTF-8")+ "'";
            else{
                GET_url = tmp[0];
                for(int i=1; i<tmp.length; i=i+2){
                    GET_url = GET_url + "'" + URLEncoder.encode(tmp[i], "UTF-8")+ "'" + tmp[i+1];
                }
            }
        }
        GET_url = GET_url.replace(" ", "%20");

        URL obj = new URL(GET_url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

        // optional default is GET
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String GET_resp = response.toString();

        return GET_resp;
    }
    
    private void setSSLCertificate(){
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }};

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
    }
    
    public String sendPost (String url, String data) throws MalformedURLException, IOException {
        if (url.contains("https://")) {
            return sendPost_HTTPS(url, data);
        }
        
        if (!url.contains("http://")) {
            url = "http://" + url;
        }
//        System.out.print("url: ");System.out.println(url);

        StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json;charset=utf-8");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            
            if(data != null && data.length() > 0) {
                connection.setRequestProperty("Content-Length", String.valueOf(data.length()));

                DataOutputStream dos = null;
                try {
                    dos = new DataOutputStream(connection.getOutputStream());
                    // Use utf-8 encoding for the post data.
                    dos.write(data.getBytes(Charset.forName("utf-8")));
                    dos.flush();
                } finally {
                    if(dos != null) dos.close();
                }
            }
            // 若是出現錯誤則撈取伺服器的錯誤回傳訊息
            if(connection.getResponseCode()<HttpURLConnection.HTTP_BAD_REQUEST) {
            	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            	String inputLine;
            	StringBuilder responseBody = new StringBuilder();
            	while ((inputLine = in.readLine()) != null) {
            		responseBody.append(inputLine);
            	}
            	in.close();
            }else {
            	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream(),"utf-8"));
            	String inputLine;
            	StringBuilder responseBody = new StringBuilder();
            	while ((inputLine = in.readLine()) != null) {
            		responseBody.append(inputLine);
            	}
            	in.close();
            }  
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
//        // Read the response from server
//        String location = httpConnection.getHeaderField("location");
//        System.out.print("location: ");
//        System.out.println(location);
//        
//        int responseCode = httpConnection.getResponseCode();
//        System.out.println(responseCode);

        return response.toString();
    }
    
    public String sendPost_HTTPS(String url, String data) throws MalformedURLException, IOException {
        setSSLCertificate();

        StringBuilder response = new StringBuilder();
        try {
            URL obj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json;charset=utf-8");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            
            if(data != null && data.length() > 0) {
                connection.setRequestProperty("Content-Length", String.valueOf(data.length()));

                DataOutputStream dos = null;
                try {
                    dos = new DataOutputStream(connection.getOutputStream());
                    // Use utf-8 encoding for the post data.
                    dos.write(data.getBytes(Charset.forName("utf-8")));
                    dos.flush();
                } finally {
                    if(dos != null) dos.close();
                }
            }
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
//        // Read the response from server
//        String location = httpConnection.getHeaderField("location");
//        System.out.print("location: ");
//        System.out.println(location);
//        
//        int responseCode = httpConnection.getResponseCode();
//        System.out.println(responseCode);

        return response.toString();
    }
    
}
