package com.example.verifyhut;

public class CommentProActivity extends ConnectionActivity {
    // Source Code untuk URL -> URL menggunakan IP Address yang didapat dari komputer / laptop
    String URL = " http://192.168.43.131/verifyhut/comment/server.php";
    String url = "";
    String response = "";

    // Menampilkan biodata dari database
    public String tampilComment() {
        try {
            url = URL + "?operasi=view";
            System.out.println("URL View Comment : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Memasukkan data biodata baru ke database
    public String insertComment(String userID, String body) {
        try {
            url = URL + "?operasi=insert&userID=" + userID + "&body=" + body;
            System.out.println("URL Insert Comment : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return  response;
    }

    // Melihat biodata yang diinginkan melalui ID
    public String getCommentById (int id) {
        try {
            url = URL + "?operasi=get_comment_by_id&id=" + id;
            System.out.println("URL Insert Comment : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Mengubah isi biodata yang sudah ada di database
    public String updateComment (String id, String userID, String body) {
        try {
            url = URL + "?operasi=update&id=" + id + "&userID=" + userID + "&body=" + body;
            System.out.println("URL Insert Comment: " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Menghapus salah satu biodata pada database
    public String deleteComment (int id) {
        try {
            url = URL + "?operasi=delete&id=" + id;
            System.out.println("URL Insert Comment : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }
}

