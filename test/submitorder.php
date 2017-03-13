<?php

	header("Content-Type: text/html; charset=UTF-8");
	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
    $json1 = json_decode($json);
	/*$sql = "select * from goods where goods_id = ".$obj->{'goods_id'};	
    $result=mysql_query($sql);
	$row = mysql_fetch_array($result,MYSQL_ASSOC);*/
	//$date=timetostring(date("Y-m-d-h-m-s"));
	$showtime=date("Y-m-d H:i:s");
	$result0 = mysql_query ("INSERT INTO myorder VALUES ('', 
		'".$json1->{'order_state'}."',
		'".$showtime."',
		'".$json1->{'address_id'}."',
		'".$json1->{'total_price'}."',
		'".$json1->{'user_id'}."',
		'".$json1->{'title'}."',
		'".$json1->{'goods_id'}."')");
		
	$count_json = count($json1->{'carBeans'});
	for ($i = 0; $i < $count_json; $i++)
	{
			$result0 = mysql_query ("UPDATE goodscar SET state = 1 where goodscar_id = ".$json1->{'carBeans'}[$i]->{'goodscar_id'});
	}	
		
	$sql1 = "select * from myorder where user_id = ".$json1->{'user_id'};
	$result1=mysql_query($sql1);
	while($row1 = mysql_fetch_array($result1,MYSQL_ASSOC)){
		$a = $row1['order_id'];
	}
	$response ["success"] = $a;
	$str = json_encode($response);                           //将数组转化为json格式的字符串  
			  
    echo ($str);
	/*$date=timetostring(date("Y-m-d-h-m-s"));
	$count_json = count($json1->{'orders'});
	for ($i = 0; $i < $count_json; $i++)
	{
			$result0 = mysql_query ("INSERT INTO order VALUES ('', 
		'".$json1->{'orders'}[$i]->{'order_state'}."','".$date."','".$json1->{'orders'}[$i]->{'address_id'}."','".$json1->{'orders'}[$i]->{'total_price'}.
		"','".$json1->{'orders'}[$i]->{'title'}."')");
	}  */ 
	//$result0 = mysql_query ( "UPDATE goodscar SET state = 1 where goodscar_id = '".$obj->{'goodscar_id'} );
	//暂时不写返回值了

?>