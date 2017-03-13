<?php
include("conn.php");
// $json=$_GET ['json'];
$json = file_get_contents ( 'php://input' );
$obj = json_decode ( $json );
$result1 = mysql_query ( "INSERT INTO order VALUES ('', 
			'".$obj->{'order_state'}."', '".$obj->{'user_id'}."', '".$boj->{'date'}."','".$obj->{'address_id'}."', 
			'".$obj->{'total_price'}."')" );
	if ($result1) {
		//提交成功
		$response ["success"] = 0;
	} else {
		//提交失败
		$response ["success"] = 1;
	}

?>