<?php
    include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );	
	$result1= mysql_query ( "INSERT INTO address VALUES ('','".$obj->{'name'}."','"
		.$obj->{'user_id'}."','".$obj->{'smearedAddress'}."','".$obj->{'detailAddress'}."')");
	if ($result1) {
		//添加成功
		$response ["success"] = 0;
	} else {
		//添加失败
		$response ["success"] = 1;
	}
	echo json_encode ( $response );
		


?>