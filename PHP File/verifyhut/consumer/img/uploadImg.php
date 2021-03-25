<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	
	
	$userID = $_POST['userID'];
	$image = $_POST['image'];
	
	require_once('config1.php');
	
	$sql = "UPDATE consumer SET image='$image' WHERE userID=$userID";
	
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