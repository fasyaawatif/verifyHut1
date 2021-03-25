package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewMnfDetail extends AppCompatActivity {

    private static final String HttpURL = "http://192.168.43.131/verifyhut/viewmanufacturer/api2.php";

    List<listMnf> mnfList;
    RecyclerView recyclerView;
    MnfAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mnf_detail);

        mnfList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewMnf);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMnfs();

    }

    private void loadMnfs(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i<array.length(); i++){
                                JSONObject manufacturer = array.getJSONObject(i);

                                String mID = manufacturer.getString("ssmID");
                                String mEmail = manufacturer.getString("email");
                                String mName = manufacturer.getString("name");
                                String mDesc = manufacturer.getString("description");
                                String mPhone = manufacturer.getString("phone");
                                String mAddress = manufacturer.getString("address");


                                listMnf listMnf = new listMnf(mID, mEmail, mName, mDesc, mPhone, mAddress );
                                mnfList.add(listMnf);

                            }
                            adapter = new MnfAdapter(ViewMnfDetail.this, mnfList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

}