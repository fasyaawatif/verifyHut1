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

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    CommentProActivity CommentProActivity = new CommentProActivity();
    TableLayout tableLayout;
    Button buttonAddComment;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button>buttonDelete = new ArrayList<Button>();
    JSONArray arrayComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // Jika SDK Android diatas API Ver.9

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Mendapatkan data widget dari XML Activity melalui ID
        tableLayout = (TableLayout) findViewById(R.id.tableComment);
        buttonAddComment = (Button) findViewById(R.id.buttonAddComment);
        buttonAddComment.setOnClickListener(this);

        // Menambahkan baris untuk tabel
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        // Menambahkan tampilan teks untuk judul pada tabel
        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderUserID = new TextView(this);
        TextView viewHeaderBody = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderUserID.setText("Username");
        viewHeaderBody.setText("Comment");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderUserID.setPadding(5, 1, 5, 1);
        viewHeaderBody.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        // Menampilkan tampilan TextView ke dalam tabel
        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderUserID);
        barisTabel.addView(viewHeaderBody);
        barisTabel.addView(viewHeaderAction);

        // Menyusun ukuran dari tabel
        tableLayout.addView(barisTabel, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            // Mengubah data dari BiodataActivity yang berupa String menjadi array
            arrayComment = new JSONArray(CommentProActivity.tampilComment());
            for (int i = 0; i < arrayComment.length(); i++) {
                JSONObject jsonChildNode = arrayComment.getJSONObject(i);
                String userID = jsonChildNode.optString("userID");
                String body = jsonChildNode.optString("body");
                String id = jsonChildNode.optString("id");

                System.out.println("Username : " + userID);
                System.out.println("Comment : " + body);
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

                TextView viewUserID = new TextView(this);
                viewUserID.setText(userID);
                viewUserID.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewUserID);

                TextView viewBody = new TextView(this);
                viewBody.setText(body);
                viewBody.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewBody);

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

        if (view.getId() == R.id.buttonAddComment) {
            addComment();
        }
        else {
            for (int  i= 0; i < buttonEdit.size(); i++) {
                // Jika ingin mengedit data pada biodata
                if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                    Toast.makeText(CommentActivity.this, "Edit : " + buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonEdit.get(i).getId();
                    getDataByID(id);
                }
                // Menghapus data di Tabel
                else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")){
                    Toast.makeText(CommentActivity.this, "Delete : " + buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonDelete.get(i).getId();
                    deleteComment(id);
                }
            }
        }
    }


    public void deleteComment (int id) {
        CommentProActivity.deleteComment(id);
        finish();
        startActivity(getIntent());
    }

    // Mendapatkan Biodata melalui ID
    public void getDataByID (int id) {
        String userIDEdit = null, bodyEdit = null;
        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(CommentProActivity.getCommentById(id));
            for (int i  = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                userIDEdit = jsonChildNode.optString("userID");
                bodyEdit = jsonChildNode.optString("body");
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

        final EditText editUserID= new EditText(this);
        editUserID.setText(userIDEdit);
        layoutInput.addView(editUserID);

        final EditText editBody = new EditText(this);
        editBody.setText(bodyEdit);
        layoutInput.addView(editBody);

        // Membuat AlertDialog untuk mengubah data di Biodata
        AlertDialog.Builder builderEditComment = new AlertDialog.Builder(this);
        builderEditComment.setIcon(R.drawable.webse);
        builderEditComment.setTitle("Update Comment");
        builderEditComment.setView(layoutInput);
        builderEditComment.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userID = editUserID.getText().toString();
                String body = editBody.getText().toString();
                System.out.println("Username : " + userID + "Comment : " + body);

                String laporan = CommentProActivity.updateComment(viewId.getText().toString(), editUserID.getText().toString(),
                        editBody.getText().toString());

                Toast.makeText(CommentActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        // Jika tidak ingin mengubah data pada Biodata
        builderEditComment.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builderEditComment.show();
    }

    public void addComment() {
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editUserID = new EditText(this);
        editUserID.setHint("Username");
        layoutInput.addView(editUserID);

        final EditText editBody= new EditText(this);
        editBody.setHint("Comment");
        layoutInput.addView(editBody);

        // Membuat AlertDialog untuk menambahkan data pada Biodata
        AlertDialog.Builder builderInsertComment= new AlertDialog.Builder(this);
        builderInsertComment.setIcon(R.drawable.webse);
        builderInsertComment.setTitle("Insert Comment");
        builderInsertComment.setView(layoutInput);
        builderInsertComment.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userID = editUserID.getText().toString();
                String body = editBody.getText().toString();
                System.out.println("Username : " + userID + "Comment : " + body);

                String laporan  = CommentProActivity.insertComment(userID, body);
                Toast.makeText(CommentActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        builderInsertComment.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertComment.show();

    }
}
