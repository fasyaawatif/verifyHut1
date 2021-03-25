<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	$ssmID = $_POST['ssmID'];
	$image = $_POST['image'];
	
	require_once('config.php');
	
	$sql = "UPDATE manufacturer SET image='$image' WHERE ssmID=$ssmID";
	//$sql = "INSERT INTO manufacturer (image) VALUES (?)";
	
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