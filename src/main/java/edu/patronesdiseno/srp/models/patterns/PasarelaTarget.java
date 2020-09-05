package edu.patronesdiseno.srp.models.patterns;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PasarelaTarget {
    public void execute(String request){
        System.out.println("Executing ofuscated request: " + request);

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://reqbin.com/echo/post/form");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("data", request));
        //params.add(new BasicNameValuePair("param-2", "Hello!"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Execute and get the response.
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                System.out.println(instream.toString());
                System.out.println("Order paid successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
