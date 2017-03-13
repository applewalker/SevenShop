<?php

include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
		
	$sql = "select * from goods limit 10";
	
    $result=mysql_query($sql);
	
	if (mysql_num_rows($result)<1){
		$response ["success"] = 1;
	}else {
    
		$arr = array();

		while($row = mysql_fetch_array($result,MYSQL_ASSOC)){   //查询出来sql
		$arr[] = $row;                                   //将查询出来的结果赋给数组$arr
		}

		$str = json_encode($arr);                           //将数组转化为json格式的字符串  
			  
		echo ($str);
}



?>