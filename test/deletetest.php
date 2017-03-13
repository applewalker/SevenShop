<?php   
         include("conn.php");
         $json="{\"carBeans\":[{\"goodscar_id\":1,\"goods_id\":0,\"isChoosed\":false,\"number\":0,\"price\":0.0,\"state\":0,\"user_id\":0}]}";
		 $json1 = json_decode($json);
         $count_json = count($json1->{'carBeans'});
        for ($i = 0; $i < $count_json; $i++)
        {
                $result0 = mysql_query ( "UPDATE goodscar SET state = 1 where goodscar_id = '".$json1->{'carBeans'}[$i]->{'goodscar_id'});
        }    

?>