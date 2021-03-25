package com.example.verifyhut;

public class PriceProActivity extends Connection2Activity{

    String URL = " http://192.168.43.131/verifyhut/pricereview/server.php";
    String url = "";
    String response = "";

    // Menampilkan biodata dari database
    public String tampilPrice() {
        try {
            url = URL + "?operasi=view";
            System.out.println("URL View Price : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Memasukkan data biodata baru ke database
    public String insertPrice(String price, String store) {
        try {
            url = URL + "?operasi=insert&price=" + price + "&store=" + store;
            System.out.println("URL Insert Price : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return  response;
    }

    // Melihat biodata yang diinginkan melalui ID
    public String getPriceById (int id) {
        try {
            url = URL + "?operasi=get_price_by_id&id=" + id;
            System.out.println("URL Insert Price : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Mengubah isi biodata yang sudah ada di database
    public String updatePrice (String id, String price, String store) {
        try {
            url = URL + "?operasi=update&id=" + id + "&price=" + price + "&store=" + store;
            System.out.println("URL Insert Price: " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }

    // Menghapus salah satu biodata pada database
    public String deletePrice (int id) {
        try {
            url = URL + "?operasi=delete&id=" + id;
            System.out.println("URL Insert Price : " + url);
            response = call(url);
        }
        catch (Exception e) {
        }
        return response;
    }
}
