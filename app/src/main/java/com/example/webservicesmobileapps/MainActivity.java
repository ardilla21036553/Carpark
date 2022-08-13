package com.example.webservicesmobileapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    AsyncHttpClient client;
    ArrayAdapter<Carpark> aaCarpark;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCarpark = findViewById(R.id.lvCarpark);
        client = new AsyncHttpClient();


    }
    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList();


        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            int lots;
            String type;
            int available;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrCarparkData = firstObj.getJSONArray("carpark_data");

                    for (int i = 0; i < jsonArrCarparkData.length(); i++) {
                        JSONObject secondObj = jsonArrCarparkData.getJSONObject(i);
                        JSONArray jsonArrCarparkInfo = secondObj.getJSONArray("carpark_info");
                        JSONObject jsonObjCarpark = jsonArrCarparkInfo.getJSONObject(0);

                        lots = jsonObjCarpark.getInt("total_lots");
                        type = jsonObjCarpark.getString("lot_type");
                        available = jsonObjCarpark.getInt("lots_available");
                        Carpark carpark = new Carpark(lots, type, available);

                        //add into arraylist
                        alCarpark.add(carpark);




                    }
                }
                catch(JSONException e){

                }

                //POINT X – Code to display List View

                ArrayAdapter aaAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                lvCarpark.setAdapter(aaAdapter);

            }//end onSuccess
        });
    }//end onResume

}