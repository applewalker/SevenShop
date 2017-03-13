<?php

include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
		
	$sql = "select * from goods where goods_id = ".$obj->{'goods_id'};
	
    $result=mysql_query($sql);
	
	if (mysql_num_rows($result) < 1){
		$response ["success"] = 1;
	}else {
    
        $arr = array();
		$row = mysql_fetch_array($result,MYSQL_ASSOC);
		$arr = $row;
		//查找特征种类
		$sql1 = "select * from scharacherist where goods_id = ".$obj->{'goods_id'};
	    $result1=mysql_query($sql1);
		
		$arr3 = array();
		$arr1 = array();
		while($row1 = mysql_fetch_array($result1,MYSQL_ASSOC)){   //查询出来sql
			
			//特征细节
			$sql2 = "select * from scharacteristdetial where scharacherist_id = ".$row1['scharacherist_id'];
	        $result2=mysql_query($sql2);
			$arr2 = array();
			while($row2 = mysql_fetch_array($result2,MYSQL_ASSOC)){
				$arr2[] = $row2;
			}
			$arr3['lable'] = $arr2;
			$arr3['name'] = $row1['name'];
			$arr1[] = $arr3;
		}
		
		$arr['type'] = $arr1;
		$str = json_encode($arr);                           //将数组转化为json格式的字符串
		
			  
		echo ($str);
		if ($obj->{'user_id'} != 0){
			$result1= mysql_query ( "INSERT INTO browserecord VALUES ('',".$obj->{'user_id'}.","
				.$obj->{'goods_id'}.")");	
		}
    }



?>