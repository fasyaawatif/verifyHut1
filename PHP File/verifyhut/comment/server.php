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
$query_tampil_biodata = mysqli_query($con,"SELECT * FROM comment") or die (mysqli_error($con));
$data_array = array();
while ($data = mysqli_fetch_assoc($query_tampil_biodata)) {
$data_array[]=$data;
}
echo json_encode($data_array);

break;

case "insert":
@$userID = $_GET['userID'];
@$body = $_GET['body'];
$query_insert_data = mysqli_query($con, "INSERT INTO comment (userID,body) VALUES('$userID','$body')");
if ($query_insert_data) {
echo "Data Inserted";
}
else {
echo "Error Inserting" . mysqli_error($con);
}

break;

case "get_comment_by_id":
@$id = $_GET['id'];
$query_tampil_biodata = mysqli_query($con, "SELECT * FROM comment WHERE id='$id'") or die (mysqli_error($con));
$data_array = array();
$data_array = mysqli_fetch_assoc($query_tampil_biodata);
echo "[" .json_encode ($data_array). "]";

break;

case "update":
@$userID = $_GET['userID'];
@$body = $_GET['body'];
@$id = $_GET['id'];
$query_update_biodata = mysqli_query($con, "UPDATE comment SET userID='$userID', body='$body' WHERE id='$id'");
if ($query_update_biodata) {
echo "Update Successful";
}
else {
echo mysqli_error($con);
}

break;

case "delete":
@$id = $_GET['id'];
$query_delete_biodata = mysqli_query($con, "DELETE FROM comment WHERE id='$id'");
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