<?php
if ($_SERVER['REQUEST_METHOD']=='POST'){
	$ssmID = $_POST['ssmID'];
	$password = $_POST['password'];
	$status = $_POST['status'];
	
	//$password = password_hash($password, PASSWORD_DEFAULT);
	
	require_once 'connect.php';
	
	//$sql = "INSERT INTO manufacturer (`ssmID`, `email`, `password`, `name`, `description`, `phone`, `address`, `type`, `created_at`, `updated_at`, `requestApprove`, `approvalStatus`) VALUES ('$ssmID', '', '$password', '', '', '', '', '', '', '', '', '')";

	$sql = "INSERT INTO manufacturer (ssmID, password, status) VALUES ('$ssmID', '$password', 'New')";

	if (mysqli_query($conn, $sql)) {
		$result["success"] = "1";
		$result["message"] = "success";
		
		echo json_encode($result);
		//echo 'Register Successfully';
		mysqli_close($conn);
		
	} else {
		$result["success"] = "0";
		$result["message"] = "error";
		
		echo json_encode($result);
		//echo 'Register Unsuccessfully';
		mysqli_close($conn);
	}
}


?>