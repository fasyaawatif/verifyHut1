<?php

$server = "localhost";
$username = "root";
$password = "";
$database = "verifyhut";
$con = mysqli_connect($server, $username, $password) or die("<h1>Connection Mysqli Error : </h1>".mysqli_connect_error());
mysqli_select_db($con, $database) or die("<h1>Connection Database Error : </h1>".mysqli_error($con));

@$operasi = $_GET['operasi'];

switch($operasi) {
case "view":
$query_tampil_biodata = mysqli_query($con,"SELECT * FROM pricereview") or die (mysqli_error($con));
$data_array = array();
while ($data = mysqli_fetch_assoc($query_tampil_biodata)) {
$data_array[]=$data;
}
echo json_encode($data_array);

break;

case "insert":
@$price = $_GET['price'];
@$store = $_GET['store'];
$query_insert_data = mysqli_query($con, "INSERT INTO pricereview (price,store) VALUES('$price','$store')");
if ($query_insert_data) {
echo "Data Inserted";
}
else {
echo "Error Inserting" . mysqli_error($con);
}

break;

case "get_price_by_id":
@$id = $_GET['id'];
$query_tampil_biodata = mysqli_query($con, "SELECT * FROM pricereview WHERE id='$id'") or die (mysqli_error($con));
$data_array = array();
$data_array = mysqli_fetch_assoc($query_tampil_biodata);
echo "[" .json_encode ($data_array). "]";

break;

case "update":
@$price = $_GET['price'];
@$store = $_GET['store'];
@$id = $_GET['id'];
$query_update_biodata = mysqli_query($con, "UPDATE pricereview SET price='$price', store='$store' WHERE id='$id'");
if ($query_update_biodata) {
echo "Update Successful";
}
else {
echo mysqli_error($con);
}

break;

case "delete":
@$id = $_GET['id'];
$query_delete_biodata = mysqli_query($con, "DELETE FROM pricereview WHERE id='$id'");
if ($query_delete_biodata) {
echo "Data Deleted";
}
else {
echo mysqli_error($con);
}

break;

default:
break;
}
?>