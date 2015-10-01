package com.besga.jonander.buycheap;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkTask extends AsyncTask<String, Void, Void> {

    private Context context;
    public final static String EXTRA_MESSAGE = "com.besga.buycheap.MESSAGE";

    public NetworkTask(Context context){
        this.context=context;
    }
    protected Void doInBackground(String... params) {
        try {
            sendGet(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendGet(String product_code) throws Exception {

        String url = "https://buy-cheap-jabesga.c9.io/api/v1/product/" + product_code.toString();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        if (responseCode == 200){
            Intent intent = new Intent(context, ResultActivity.class);
            intent.putExtra(EXTRA_MESSAGE, response.toString());
            context.startActivity(intent);
        }
    }
}