<?php

    include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
	$result = mysql_query ( "UPDATE user SET user_name = '".$obj->{'user_name'}."' where user_id = ".$obj->{'user_id'});
	


?>