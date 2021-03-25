<?php 
	include 'DatabaseConfig.php';
	$con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

	$userID = $_POST['userID'];
	$email = $_POST['email'];
	$name = $_POST['name'];
	
	$sql_Query = "UPDATE consumer SET email='$email', name='$name' WHERE userID='$userID'";
	
	if (mysqli_query($con, $sql_Query)) {
		echo 'Profile Update Successfully';
		
	} else {
		echo 'Something went wrong';
	}
	
	mysqli_close($con);

?>