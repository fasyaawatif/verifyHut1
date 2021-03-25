<?php include "dbconfig.php";

if (isset($_POST['method'])) {
	
	// for login process
	if ($_POST['method']=='login') {
		
		$stmt = $db_con->prepare("SELECT * FROM consumer WHERE userID=:userID AND password=:password");
		$stmt->bindParam(":userID", $_POST['userID']);
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
