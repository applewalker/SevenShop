<?php

	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
	
	$result0 = mysql_query ( "INSERT INTO address VALUES ('', 
			'".$obj->{'address'}."', '".$obj->{'user_id'}."')" );

    if ($result0){
		$response['success'] = 0;
	}else {
		$response['success'] = 1;
	}

?>