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
	
	$stmt = $conn->prepare("SELECT ssmID, email, name, description, phone, address FROM manufacturer;");
	$stmt->execute();
	$stmt->bind_result($ssmID, $email, $name, $description, $phone, $address);
	$manufacturers = array();
	
	while($stmt->fetch()){
		$temp = array();
		$temp['ssmID'] = $ssmID;
		$temp['email'] = $email;
		$temp['name'] = $name;
		$temp['description'] = $description;
		$temp['phone'] = $phone;
		$temp['address'] = $address;
		
		array_push($manufacturers, $temp);
		
	}
	echo json_encode($manufacturers);

?>