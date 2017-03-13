<?php
include("conn.php");
// $json=$_GET ['json'];
$json = file_get_contents ( 'php://input' );
$obj = json_decode ( $json );

$result = mysql_query ( "select * from user where user_name='" . $obj->{'user_name'} . "' and password='" . $obj->{'password'} . "'" );



header ( 'Content-type: application/json;charset=UTF-8' );

if (mysql_num_rows($result) < 1 ) {
	$response ["success"] = -1;
	
} else {
	$row = mysql_fetch_array($result);
	$response ["success"] = $row['user_id'];
	
}

header ( 'Content-type: application/json' );
		echo json_encode ( $response );

?>