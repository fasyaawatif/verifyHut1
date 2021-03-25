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
	
	$sql_Query = "UPDATE product SET productName='$productName', brand='$brand', productDesc='$productDesc', type='$type', ingredient='$ingredient', halalCertified='$halalCertified' WHERE productID=$productID";
	
	if (mysqli_query($con, $sql_Query)) {
		echo 'Product Update Successfully';
		
	} else {
		echo 'Something went wrong';
	}
	
	mysqli_close($con);

?>