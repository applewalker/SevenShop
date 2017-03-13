<?php

	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
	
		
	$sql = "select * from goodscar where user_id = ".$obj->{'user_id'}." and state = 0";
	
    $result=mysql_query($sql);
	
	if (mysql_num_rows($result) < 1){
		$response ["success"] = 1;
	}else {
    
	    $arr = array();
		$arr1 = array();
		while($row = mysql_fetch_array($result,MYSQL_ASSOC)){			//查询出来sql
			$arr[] = $row; 			//将查询出来的结果赋给数组$arr
		}
        $arr1['carBeans'] = $arr;
		$str = json_encode($arr1);                           //将数组转化为json格式的字符串  	  
		echo ($str);
    }

?>