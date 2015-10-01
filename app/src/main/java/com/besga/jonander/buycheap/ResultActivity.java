package com.besga.jonander.buycheap;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ResultActivity extends AppCompatActivity {

    TableLayout tl;
    int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(NetworkTask.EXTRA_MESSAGE);
        setContentView(R.layout.activity_result);
        System.out.println("CONFIGURANDO TABLA");

        tl = (TableLayout) findViewById(R.id.main_table);

        try {
            JSONObject json_response = new JSONObject(message).getJSONObject("result");

            Iterator<String> iter = json_response.keys();
            Object value = "";
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    value = json_response.get(key);
                } catch (JSONException e) {
                    // Something went wrong!
                }

                TableRow tr = new TableRow(this);
                tr.setId(100+current);
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1));

                TextView labelCompany = new TextView(this);
                labelCompany.setId(200 + current);
                labelCompany.setText(key);
                labelCompany.setTextColor(Color.BLACK);
                labelCompany.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        0.6f));
                tr.addView(labelCompany);

                TextView product_value = new TextView(this);
                product_value.setId(300 + current);
                product_value.setText(value.toString() + "EUR");
                product_value.setTextColor(Color.BLACK);
                product_value.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        0.4f));
                tr.addView(product_value);

                // Add the TableRow to the TableLayout
                tl.addView(tr, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
