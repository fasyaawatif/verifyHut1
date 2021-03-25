<?php
	
	define('DB_HOST','localhost');
	define('DB_USER','root');
	define('DB_PASS','');
	define('DB_NAME','verifyhut');
	
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	$stmt = $conn->prepare("SELECT  status, name, email, description, address, phone, ssmID FROM manufacturer;");
	$stmt->execute();
	$stmt->bind_result($status, $name, $email, $description, $address, $phone, $ssmID);
	$manufacturers = array();
	
	
	while($stmt->fetch()){
		if($status == 'Approved'){
		$temp = array();
		$temp['name'] = $name;
		$temp['email'] = $email;
		$temp['description'] = $description;
		$temp['address'] = $address;
		$temp['phone'] = $phone;
		$temp['ssmID'] = $ssmID;
		array_push($manufacturers, $temp);
		}
	}
	echo json_encode($manufacturers);
	
?>