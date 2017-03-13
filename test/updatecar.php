<?php

    include("conn.php");
	// $json=$_GET ['json'];
	$json = file_get_contents ( 'php://input' );
	$obj = json_decode ( $json );
	
	
	if ($obj->{'state'} == 0){  //添加购物车
		$result0 = mysql_query ("SELECT * FROM goodscar WHERE goods_id ='". $obj->{'goods_id'}."'");
		if (mysql_num_rows($result) < 1){
			
			$result1= mysql_query ( "INSERT INTO goodscar VALUES ('','".$obj->{'goods_id'}."','"
			.$obj->{'user_id'}."','".$obj->{'number'}."','0','".$obj->{'imagepath'}."','".$obj->{'goodsdescribe'}."','".$obj->{'goods_name'}.
			"','".$obj->{'price'}."')");
			if ($result1) {
				//添加成功
				$response ["success"] = 0;
			} else {
				//添加失败
				$response ["success"] = 1;
			}
		}else {
			$response ["success"] = 3;
		}
		header ( 'Content-type: application/json' );
		echo json_encode ( $response );
		

	}else if ($obj->{'state'} == 3){    //更新数量
		$result0 = mysql_query ( "UPDATE goodscar SET number = ".$obj->{'number'}." WHERE goodscar_id = ".$obj->{'goodscar_id'} );
		if (mysql_affected_rows()) {
			//数量修改成功
			$response ["success"] = 0;
		} else {
			//数量修改添加失败
			$response ["success"] = 1;
		}
		header ( 'Content-type: application/json' );
		echo json_encode ( $response );
	}
	
	


?>