<?php 
	/* ===== www.dedykuncoro.com ===== */
	include_once "koneksi.php";

	
	$name = $_POST['keyword'];
	$email = $_POST['keyword'];
	$description = $_POST['keyword'];
	$address = $_POST['keyword'];
	$phone = $_POST['keyword'];
	$ssmID = $_POST['keyword'];
	

	$query = mysqli_query($con, "SELECT * FROM manufacturer WHERE name LIKE '%".$name."%' OR email LIKE '%".$email."%' OR description LIKE '%".$description."%' OR address LIKE '%".$address."%' OR phone LIKE '%".$phone."%' OR ssmID LIKE '%".$ssmID."%' ");

	$num_rows = mysqli_num_rows($query);

	if ($num_rows > 0){
		$json = '{"value":1, "results": [';

		while ($row = mysqli_fetch_array($query)){
			$char ='"';

			$json .= '{
				"name": "'.str_replace($char,'`',strip_tags($row['name'])).'",
				"email": "'.str_replace($char,'`',strip_tags($row['email'])).'",
				"description": "'.str_replace($char,'`',strip_tags($row['description'])).'",
				"address": "'.str_replace($char,'`',strip_tags($row['address'])).'",
				"phone": "'.str_replace($char,'`',strip_tags($row['address'])).'",
				"ssmID": "'.str_replace($char,'`',strip_tags($row['ssmID'])).'"
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