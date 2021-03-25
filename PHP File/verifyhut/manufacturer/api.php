<?php include "dbconfig.php";

if (isset($_POST['method'])) {
	
	// for login process
	if ($_POST['method']=='login') {
		
		$stmt = $db_con->prepare("SELECT * FROM manufacturer WHERE ssmID=:ssmID AND password=:password");
		$stmt->bindParam(":ssmID", $_POST['ssmID']);
		$stmt->bindParam(":password", $_POST['password']);
		$stmt->execute();
		$data = $stmt->fetch();

		if ($data >0) {
			$arr = array ('query_result'=>'SUCCESS');
		}

		else {
			$arr = array ('query_result'=>'FAILED');
		}
	}
	echo json_encode($arr);
		
}
 
?>
