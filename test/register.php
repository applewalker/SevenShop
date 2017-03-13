<?php

header("Content-Type: text/html; charset=UTF-8");
include("conn.php");
// $json=$_GET ['json'];
$json = file_get_contents ( 'php://input' );
$obj = json_decode ( $json );
// echo $json;
// Save
$result0 = mysql_query ("SELECT * FROM user WHERE user_name='" . $obj->{'user_name'} . "'");

if (mysql_num_rows($result0) < 1){
	
	$result1 = mysql_query ( "INSERT INTO user VALUES ('', 
			'".$obj->{'user_name'}."','".$obj->{'password'}."','".$obj->{'sex'}."','0','0', 
			'0')" );
	if ($result1) {
		//注册成功
		$response ["success"] = 0;
	} else {
		//注册失败
		$response ["success"] = 1;
	}
}else {
    //用户已存在
	$response ["success"] = 2;
}
header ( 'Content-type: application/json' );
echo json_encode ( $response );

?>