<?php
	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
		
	/*$sql = "select * from goods where goods_name like ".$obj->{'search_name'};
	
    $result=mysql_query($sql);*/
	$sql = "select * from type where type_name like '%".$obj->{'goods_name'}."%'";
	$result=mysql_query($sql);
	
	if (mysql_num_rows($result) < 1){
		
		$sql1 = "select * from goods where goods_name like '%".$obj->{'goods_name'}."%'";
	    $result1=mysql_query($sql1);
		
		$arr = array();
		$arr1 = array();
     
		while($row1 = mysql_fetch_array($result1,MYSQL_ASSOC)){   //查询出来sql
		    $arr[] = $row1;                                   //将查询出来的结果赋给数组$arr
		}
        $arr1['goodslist'] = $arr;
		$str = json_encode($arr1);                           //将数组转化为json格式的字符串  
			  
		echo ($str);
		
	}else {
        $arr = array();
        $arr1 = array();
		while($row = mysql_fetch_array($result,MYSQL_ASSOC)){   //查询出来sql
		   $sql1 = "select *from goods where type_id = ".$row['type_id'];
	       $result1=mysql_query($sql1);
		   while($row1 = mysql_fetch_array($result1,MYSQL_ASSOC)){
			   $arr[] = $row1;                                   //将查询出来的结果赋给数组$arr
		   }
		   
		}
        $arr1['goodslist'] = $arr;
		$str = json_encode($arr1);                           //将数组转化为json格式的字符串  
			  
		echo ($str);
}





?>