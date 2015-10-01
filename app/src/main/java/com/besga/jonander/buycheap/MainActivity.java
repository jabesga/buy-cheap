package com.besga.jonander.buycheap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {

    Button scan_button;
    TextView above_text;
    TextView below_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface agencyr = Typeface.createFromAsset(getAssets(), "fonts/AGENCYR.TTF");
        Typeface agencyb = Typeface.createFromAsset(getAssets(), "fonts/AGENCYB.TTF");

        above_text = (TextView) findViewById(R.id.above_text);
        below_text = (TextView) findViewById(R.id.below_text);
        scan_button = (Button) findViewById(R.id.scan_button);

        below_text.setTypeface(agencyr);
        above_text.setTypeface(agencyr);
        scan_button.setTypeface(agencyb);

        scan_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 49374) { // REQUEST_CODE = 0x0000c0de;
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                //String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                NetworkTask t = new NetworkTask(this);
                t.execute(contents);
            } else if (resultCode == RESULT_CANCELED) {
                // TODO: 9/6/2015 Handle error
            }
        }
    }
}
