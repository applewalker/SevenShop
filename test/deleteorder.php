<?php

	header("Content-Type: text/html; charset=UTF-8");
	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
    $obj = json_decode($json);
    $result0 = mysql_query ( "UPDATE myorder SET order_state = 2 where order_id = ".$obj->{'order_id'});

?>