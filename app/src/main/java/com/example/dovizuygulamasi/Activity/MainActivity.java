package com.example.dovizuygulamasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dovizuygulamasi.Models.ApiModel;
import com.example.dovizuygulamasi.Adapter.NewAdapter;
import com.example.dovizuygulamasi.Pagination.Pagination;
import com.example.dovizuygulamasi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private ListView mListView;
    private Button btnNext, btnPrev;
    private NewAdapter adapter;
    private int totalPages;
    private int currentPage = 0;
    Pagination p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mListView = (ListView) findViewById(R.id.listView);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrevious);

        mQueue = Volley.newRequestQueue(this);

        jsonParse();//json islemleri fonksiyonu

    }

    private void jsonParse() {

        String url = "https://api.coinranking.com/v1/public/coins";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //json root ayarlamasi yapilir
                            ArrayList<ApiModel> borsaListesi = new ArrayList<ApiModel>();

                            JSONObject jSonRoot = response.getJSONObject("data");

                            JSONArray jSonArrayBook = jSonRoot.getJSONArray("coins");

                            int length = jSonArrayBook.length();

                            for (int i = 0; i < length; i++) {
                                JSONObject object = jSonArrayBook.getJSONObject(i);
//                                JSONObject allTimeHighList = object.getJSONObject("allTimeHigh");
//
//                                String price = allTimeHighList.getString("price");

                                int id = object.getInt("id");
                                String name = object.getString("name");
                                String color = object.getString("color");
                                String price = object.getString("price");
                                String iconType = object.getString("iconType");
                                String iconUrl = object.getString("iconUrl");
                                String symbol = object.getString("symbol");
                                String desc = object.getString("description");
                                String change = object.getString("change");

                                ApiModel bordor = new ApiModel(id, symbol, name, desc, color, iconType, iconUrl, price, change);
                                borsaListesi.add(bordor);
                            }
                            //borsaListesi adapter yuklenmesi icin gonderilir.
                             p = new Pagination(10,borsaListesi);
                            totalPages = p.getTotalPages();
                            AdapterLoad(p, totalPages);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage+=1;
                AdapterLoad(p, currentPage);

            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage-=1;
                AdapterLoad(p, currentPage);
                toggleButtons();

            }
        });
    }

    //Adaptor yukleme islemi bu fonksiyonda yapılır.(New Adapter ile baglantili).
    private void AdapterLoad(Pagination p,  int page) {

        adapter = new NewAdapter(p.generateData(page), this, MainActivity.this);
        mListView.setAdapter(adapter);

    }

   //buton aktifligi liste sayisina gore kontrol edilir.
    private void toggleButtons(){
        if (totalPages <= 1) {
            btnNext.setEnabled(false);
            btnPrev.setEnabled(false);
        }
        else if (currentPage == totalPages) {
            btnNext.setEnabled(false);
            btnPrev.setEnabled(false);
        }
        else if (currentPage == 0) {
            btnNext.setEnabled(false);
            btnPrev.setEnabled(false);
        }
        else if (currentPage >= 1 && currentPage <= totalPages) {
            btnNext.setEnabled(true);
            btnPrev.setEnabled(true);
        }
    }
}