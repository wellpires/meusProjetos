package br.buscarenderecos.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ConsumirWS implements Serializable{

    private static final long serialVersionUID = 1L;

    private final String USER_AGENT = "Mozilla/5.0";
    
    public String sendGet(String url) throws Exception {

        HttpURLConnection conexao = (HttpURLConnection) new URL(url).openConnection();
        conexao.setRequestMethod("GET");
        conexao.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        
        int responseCode = conexao.getResponseCode();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();

    }

    public String sendPost(String url, String urlParameters, String method) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.USER_AGENT);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsumirWS other = (ConsumirWS) obj;
        return true;
    }
    
}
