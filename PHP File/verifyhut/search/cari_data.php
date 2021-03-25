<?php 
	/* ===== www.dedykuncoro.com ===== */
	include_once "koneksi.php";

	
	$productName = $_POST['keyword'];
	$brand = $_POST['keyword'];
	$ingredient = $_POST['keyword'];
	$halalCertified = $_POST['keyword'];
	$productDesc = $_POST['keyword'];
	$type = $_POST['keyword'];
	$productID = $_POST['keyword'];

	$query = mysqli_query($con, "SELECT * FROM product WHERE productName LIKE '%".$productName."%' OR brand LIKE '%".$brand."%' OR ingredient LIKE '%".$ingredient."%' OR halalCertified LIKE '%".$halalCertified."%' OR productDesc LIKE '%".$productDesc."%' OR type LIKE '%".$type."%' OR productID LIKE '%".$productID."%' ");

	$num_rows = mysqli_num_rows($query);

	if ($num_rows > 0){
		$json = '{"value":1, "results": [';

		while ($row = mysqli_fetch_array($query)){
			$char ='"';

			$json .= '{
				"productName": "'.str_replace($char,'`',strip_tags($row['productName'])).'",
				"brand": "'.str_replace($char,'`',strip_tags($row['brand'])).'",
				"ingredient": "'.str_replace($char,'`',strip_tags($row['ingredient'])).'",
				"halalCertified": "'.str_replace($char,'`',strip_tags($row['halalCertified'])).'",
				"productDesc": "'.str_replace($char,'`',strip_tags($row['productDesc'])).'",
				"type": "'.str_replace($char,'`',strip_tags($row['type'])).'",
				"productID": "'.str_replace($char,'`',strip_tags($row['productID'])).'"
			},';
		}

		$json = substr($json,0,strlen($json)-1);

		$json .= ']}';

	} else {
		$json = '{"value":0, "message": "Data tidak ditemukan."}';
	}

	echo $json;

	mysqli_close($con);
?>