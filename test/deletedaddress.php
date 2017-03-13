<?php
    include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
	$result0 = mysql_query ( "delete from address where address_id = ".$obj->{'address_id'});
		if ($result0) {
			//地址删除成功
			$response ["success"] = 0;
		} else {
			//地址删除失败
			$response ["success"] = 1;
		}
		echo json_encode ( $response );

?>