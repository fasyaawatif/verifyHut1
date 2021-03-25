<?php 
	include 'DatabaseConfig.php';
	$con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

	$productID = $_POST['productID'];
	//$ssmID = $_POST['ssmID'];
	$productName = $_POST['productName'];
	$brand = $_POST['brand'];
	$productDesc = $_POST['productDesc'];
	$type = $_POST['type'];
	$ingredient = $_POST['ingredient'];
	$halalCertified = $_POST['halalCertified'];
	$barcode = $_POST['barcode'];
	$productImg = $_POST['productImg'];
	//$status = $_POST['status'];
	
	
	$sql_Query = "INSERT INTO product (productID, productName, brand, productDesc, type, ingredient, halalCertified, barcode, productImg, status, updated_at) VALUES ('$productID', '$productName', '$brand', '$productDesc', '$type', '$ingredient', '$halalCertified', '$barcode', '$productImg', 'New', '')";
	
	if (mysqli_query($con, $sql_Query)) {
		echo 'Product Insert Successfully';
		
	} else {
		echo 'Something went wrong';
	}
	
	mysqli_close($con);

?>