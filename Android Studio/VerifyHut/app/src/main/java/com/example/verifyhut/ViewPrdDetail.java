package com.example.verifyhut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.HttpResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class ViewPrdDetail extends AppCompatActivity {

    HttpResponse httpResponse;
    TextView prID, prName, prBrand, prDesc, prType, prIngredient, prHalal;
    JSONObject jsonObject = null;
    String prdIDholder, prdNameholder, prdBrandholder, prdDescholder, prdTypeholder, prdIngredientholder, prdHalalholde;
    ProgressBar progressBar;
    String HttpURL = "http://192.168.43.131/verifyhut/viewproduct/viewDetailPrd.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prd_detail);

        prID = (TextView)findViewById(R.id.viewPrdID);
        prName = (TextView)findViewById(R.id.viewPrdName);
        prBrand = (TextView)findViewById(R.id.viewPrdBrand);
        prDesc = (TextView)findViewById(R.id.viewPrdDesc);
        prType = (TextView)findViewById(R.id.viewPrdType);
        prIngredient = (TextView)findViewById(R.id.viewPrdIngredient);
        prHalal = (TextView)findViewById(R.id.viewPrdHalal);

        //new GetDataFromServerIntoTextView(ViewPrdDetail.this).execute();

    }
/*
    public class GetDataFromServerIntoTextView extends AsyncTask<Void, Void, Void>{

        public Context context;


        public GetDataFromServerIntoTextView(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(HttpURL);

            try{
                httpResponse = httpClient.execute(httpPost);
                StringHolder = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            }catch (ClientProtocolException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            try{
                JSONArray jsonArray = new JSONArray(prdIDholder, prdNameholder, prdBrandholder, prdDescholder, prdTypeholder, prdIngredientholder, prdHalalholder);
                jsonObject = jsonArray.getJSONObject(0);
            }catch (JSONException e){
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                prID.setText(jsonObject.getString("productID"));
                prName.setText(jsonObject.getString("productName"));
                prBrand.setText(jsonObject.getString("brand"));
                prDesc.setText(jsonObject.getString("productDesc"));
                prType.setText(jsonObject.getString("type"));
                prIngredient.setText(jsonObject.getString("ingredient"));
                prHalal.setText(jsonObject.getString("halalCertified"));
            }catch (JSONException e){
                e.printStackTrace();
            }
            progressBar.setVisibility(View.GONE);

        }
    }

 */

}
