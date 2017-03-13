<?php

	include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
	
	if ($obj->{'user_id'} == 0){
		
		$sql1 = "select * from goods limit 20";
	    $result1=mysql_query($sql1);
		
		$arr = array();
		$arr1 = array();
     
		while($row1 = mysql_fetch_array($result1,MYSQL_ASSOC)){   //查询出来sql
		    $arr[] = $row1;                                   //将查询出来的结果赋给数组$arr
		}
        $arr1['goodslist'] = $arr;
		$str = json_encode($arr1);                           //将数组转化为json格式的字符串  
			  
		echo ($str);
		
	}else{
	   $sql = "select * from browserecord where user_id = ".$obj->{'user_id'}." order by browserecord_id desc";
	   $result=mysql_query($sql);
	   if (mysql_num_rows($result) < 1){
		   $sql1 = "select * from goods limit 20";
			$result1=mysql_query($sql1);
			
			$arr = array();
			$arr1 = array();
		 
			while($row1 = mysql_fetch_array($result1,MYSQL_ASSOC)){   //查询出来sql
				$arr[] = $row1;                                   //将查询出来的结果赋给数组$arr
			}
			$arr1['goodslist'] = $arr;
			$str = json_encode($arr1);                           //将数组转化为json格式的字符串  
				  
			echo ($str);
	    }else{
		   $row = mysql_fetch_array($result,MYSQL_ASSOC);
		   $arr = array();
		   $arr1 = array();
		   $sql1 = "select *from goods where goods_id = ".$row['goods_id'];
		   $result1=mysql_query($sql1);
		   $row1 = mysql_fetch_array($result1,MYSQL_ASSOC);
		   $sql2 = "select *from goods where type_id = ".$row1['type_id'];
		   $result2=mysql_query($sql2);
		   while($row2 = mysql_fetch_array($result2,MYSQL_ASSOC)){			
				   $arr[] = $row2;                                   //将查询出来的结果赋给数组$arr
												
			}
			   
			
			$arr1['goodslist'] = $arr;
			$str = json_encode($arr1);                           //将数组转化为json格式的字符串  
				  
			echo ($str);
		}
	}

?>