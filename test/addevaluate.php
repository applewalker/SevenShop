<?php
	
	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
	$showtime=date("Y-m-d H:i:s");
	$count_json = count($obj->{'evaluates'});
	for ($i = 0; $i < $count_json; $i++)
	{
			$result0 = mysql_query ( "INSERT INTO evaluate VALUES('', 
		'".$obj->{'evaluates'}[$i]->{'goods_id'}."','".$obj->{'evaluates'}[$i]->{'evaluate'}."','".$showtime."','".$obj->{'evaluates'}[$i]->{'user_name'}."')" );
	} 
	/*if ($result1) {
		//评论成功
		$response ["success"] = 0;
	} else {
		//评论失败
		$response ["success"] = 1;
	}*/

?>