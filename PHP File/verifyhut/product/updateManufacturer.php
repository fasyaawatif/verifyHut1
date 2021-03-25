<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

	$con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
	
	$ssmID = $_POST['ssmID'];
	//$password = $_POST['password'];
	$email = $_POST['email'];
	$name = $_POST['name'];
	$description = $_POST['description'];
	$phone = $_POST['phone'];
	$address = $_POST['address'];
	//$status = $_POST['status'];
	
	$Sql_Query = "UPDATE manufacturer SET email='$email', name='$name', description='$description', phone='$phone', address='$address' WHERE ssmID=$ssmID";
	
	if(mysqli_query($con,$Sql_Query)){
		echo 'Record Updated Successfully';
	}
	else{
		echo 'Something went wrong';
	}
}
mysqli_close($con);

?>