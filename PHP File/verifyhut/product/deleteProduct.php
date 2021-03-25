<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	include 'DatabaseConfig.php';
	
	$con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
	
	$productID = $_POST['productID'];
	
	$Sql_Query = "DELETE FROM product WHERE productID='$productID'";
	
	if(mysqli_query($con,$Sql_Query)){
		echo 'Record Deleted Successfully';
	}
	else{
		echo 'Something went wrong';
	}
}
mysqli_close($con);
?>