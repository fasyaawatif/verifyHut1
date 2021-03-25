<?php
if ($_SERVER['REQUEST_METHOD']=='POST'){
	$userID = $_POST['userID'];
	$password = $_POST['password'];
	
	//$password = password_hash($password, PASSWORD_DEFAULT);
	
	require_once 'connect.php';
	
	$sql = "INSERT INTO consumer (userID, password) VALUES ('$userID', '$password')";
	
	if (mysqli_query($conn, $sql)) {
		$result["success"] = "1";
		$result["message"] = "success";
		
		echo json_encode($result);
		mysqli_close($conn);
		
	} else {
		$result["success"] = "0";
		$result["message"] = "error";
		
		echo json_encode($result);
		mysqli_close($conn);
	}
}


?>