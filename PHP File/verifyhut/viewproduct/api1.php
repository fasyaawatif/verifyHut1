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
	
	$stmt = $conn->prepare("SELECT status, productName, brand, ingredient, halalCertified, productDesc, type, productID FROM product;");
	$stmt->execute();
	$stmt->bind_result($status, $productName, $brand, $ingredient, $halalCertified, $productDesc, $type, $productID);
	$products = array();
	
	while($stmt->fetch()){
		
		if($status == 'Approved'){
		$temp = array();
		$temp['productName'] = $productName;
		$temp['brand'] = $brand;
		$temp['ingredient'] = $ingredient;
		$temp['halalCertified'] = $halalCertified;
		$temp['productDesc'] = $productDesc;
		$temp['type'] = $type;
		$temp['productID'] = $productID;
		array_push($products, $temp);
		}
		
	}
	echo json_encode($products);

?>