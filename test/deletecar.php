<?php

	header("Content-Type: text/html; charset=UTF-8");
	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
    $json1 = json_decode($json);
         $count_json = count($json1->{'carBeans'});
        for ($i = 0; $i < $count_json; $i++)
        {
				$result0 = mysql_query ( "UPDATE goodscar SET state = 1 where goodscar_id = ".$json1->{'carBeans'}[$i]->{'goodscar_id'});
        }   
	//$result0 = mysql_query ( "UPDATE goodscar SET state = 1 where goodscar_id = '".$obj->{'goodscar_id'} );
	//暂时不写返回值了


?>