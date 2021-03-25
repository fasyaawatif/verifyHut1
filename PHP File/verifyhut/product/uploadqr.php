<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	$productID = $_POST['productID'];
	$barcode = $_POST['barcode'];
	
	require_once('config.php');
	
	//$sql = "UPDATE product SET barcode='$barcode' WHERE productID=$productID";
	$sql = "INSERT INTO product (barcode) VALUES (?)";
	
	$stmt = mysqli_prepare($con,$sql);
	
	if(mysqli_query($con,$sql)){
		echo 'Record Updated Successfully';
	}
	else{
		echo 'Something went wrong';
	}
}
mysqli_close($con);
	

?>