package com.example.verifyhut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PriceActivity extends AppCompatActivity implements View.OnClickListener{

    PriceProActivity PriceProActivity = new PriceProActivity();
    TableLayout tableLayout;
    Button buttonAddPrice;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button>buttonDelete = new ArrayList<Button>();
    JSONArray arrayPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        // Jika SDK Android diatas API Ver.9

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Mendapatkan data widget dari XML Activity melalui ID
        tableLayout = (TableLayout) findViewById(R.id.tablePrice);
        buttonAddPrice = (Button) findViewById(R.id.buttonAddPrice);
        buttonAddPrice.setOnClickListener(this);

        // Menambahkan baris untuk tabel
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        // Menambahkan tampilan teks untuk judul pada tabel
        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderPrice = new TextView(this);
        TextView viewHeaderStore = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderPrice.setText("Price");
        viewHeaderStore.setText("Store Location");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderPrice.setPadding(5, 1, 5, 1);
        viewHeaderStore.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        // Menampilkan tampilan TextView ke dalam tabel
        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderPrice);
        barisTabel.addView(viewHeaderStore);
        barisTabel.addView(viewHeaderAction);

        // Menyusun ukuran dari tabel
        tableLayout.addView(barisTabel, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            // Mengubah data dari BiodataActivity yang berupa String menjadi array
            arrayPrice = new JSONArray(PriceProActivity.tampilPrice());
            for (int i = 0; i < arrayPrice.length(); i++) {
                JSONObject jsonChildNode = arrayPrice.getJSONObject(i);
                String price = jsonChildNode.optString("price");
                String store = jsonChildNode.optString("store");
                String id = jsonChildNode.optString("id");

                System.out.println("Price : " + price);
                System.out.println("Store Location : " + store);
                System.out.println("ID : " + id);

                barisTabel = new TableRow(this);

                // Memberi warna pada baris tabel
                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewId);

                TextView viewPrice = new TextView(this);
                viewPrice.setText(price);
                viewPrice.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewPrice);

                TextView viewStore = new TextView(this);
                viewStore.setText(store);
                viewStore.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewStore);

                // Menambahkan button Edit
                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(id));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTabel.addView(buttonEdit.get(i));

                // Menambahkan tombol Delete
                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(id));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                tableLayout.addView(barisTabel, new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.buttonAddPrice) {
            addPrice();
        }
        else {
            for (int  i= 0; i < buttonEdit.size(); i++) {
                // Jika ingin mengedit data pada biodata
                if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                    Toast.makeText(PriceActivity.this, "Edit : " + buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonEdit.get(i).getId();
                    getDataByID(id);
                }
                // Menghapus data di Tabel
                else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")){
                    Toast.makeText(PriceActivity.this, "Delete : " + buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonDelete.get(i).getId();
                    deletePrice(id);
                }
            }
        }
    }


    public void deletePrice (int id) {
        PriceProActivity.deletePrice(id);
        finish();
        startActivity(getIntent());
    }

    // Mendapatkan Biodata melalui ID
    public void getDataByID (int id) {
        String priceEdit = null, storeEdit = null;
        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(PriceProActivity.getPriceById(id));
            for (int i  = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                priceEdit = jsonChildNode.optString("price");
                storeEdit = jsonChildNode.optString("store");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // Membuat id tersembunyi pada AlertDialog
        final TextView viewId = new TextView(this);
        viewId.setText(String.valueOf(id));
        viewId.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewId);

        final EditText editPrice= new EditText(this);
        editPrice.setText(priceEdit);
        layoutInput.addView(editPrice);

        final EditText editStore = new EditText(this);
        editStore.setText(storeEdit);
        layoutInput.addView(editStore);

        // Membuat AlertDialog untuk mengubah data di Biodata
        AlertDialog.Builder builderEditPrice = new AlertDialog.Builder(this);
        builderEditPrice.setIcon(R.drawable.websee);
        builderEditPrice.setTitle("Update Price");
        builderEditPrice.setView(layoutInput);
        builderEditPrice.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String price = editPrice.getText().toString();
                String store = editStore.getText().toString();
                System.out.println("Price : " + price + "Store Location : " + store);

                String laporan = PriceProActivity.updatePrice(viewId.getText().toString(), editPrice.getText().toString(),
                        editStore.getText().toString());

                Toast.makeText(PriceActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        // Jika tidak ingin mengubah data pada Biodata
        builderEditPrice.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builderEditPrice.show();
    }

    public void addPrice() {
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editPrice = new EditText(this);
        editPrice.setText("RM ");
        layoutInput.addView(editPrice);

        final EditText editStore= new EditText(this);
        editStore.setHint("Store Location");
        layoutInput.addView(editStore);

        // Membuat AlertDialog untuk menambahkan data pada Biodata
        AlertDialog.Builder builderInsertPrice= new AlertDialog.Builder(this);
        builderInsertPrice.setIcon(R.drawable.websee);
        builderInsertPrice.setTitle("Insert Price");
        builderInsertPrice.setView(layoutInput);
        builderInsertPrice.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String price = editPrice.getText().toString();
                String store = editStore.getText().toString();
                System.out.println("RM Price : " + price + "Store Location : " + store);

                String laporan  = PriceProActivity.insertPrice(price, store);
                Toast.makeText(PriceActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        builderInsertPrice.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertPrice.show();

    }
}

