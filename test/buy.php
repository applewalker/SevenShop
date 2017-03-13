<?php
    include("conn.php");
// $json=$_GET ['json'];
$json = file_get_contents ( 'php://input' );
$obj = json_decode ( $json );
$result0 = mysql_query ( "UPDATE myorder SET order_state = 1 WHERE order_id = ".$obj->{'order_id'});
	if ($result0) {
		  //$result1 = mysql_query("UPDATE goods SET number = 'number-1' WHERE goods_id = '".$obj->{'Goods'}->{'goods_id'}."'");
		 // if ($result1){
			  //成功
	   //$count_json = count($obj->{'carBeans'});
        //for ($i = 0; $i < $count_json; $i++)
        //{
		//		$result0 = mysql_query ( "UPDATE goodscar SET state = 1 where goodscar_id = ".$obj->{'carBeans'}[$i]->{'goodscar_id'});
        //}
	 $response ["success"] = 0;
		 // }else {
			  //失败
			//  $response ["success"] = 1;
		 // }
		  
	} else {
		//提交失败
		$response ["success"] = 1;
	}
	
	$str = json_encode($response);                           //将数组转化为json格式的字符串  
			  
    echo ($str);

?>