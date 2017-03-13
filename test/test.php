<?php 
   include("conn.php");
        $json="{\"evaluates\":[{\"evaluate\":\"好东西\",\"user_name\":\"白天睡觉\",\"goods_id\":2,\"evaluate_id\":0}]}";
       //$date=timetostring(date("Y-m-d-h-m-s"));
	    //echo $date;
		 $obj = json_decode ( $json );
	$showtime=date("Y-m-d H:i:s");
	$count_json = count($obj->{'evaluates'});
	for ($i = 0; $i < $count_json; $i++)
	{
			$result0 = mysql_query ( "INSERT INTO evaluate VALUES('', 
		'".$obj->{'evaluates'}[$i]->{'goods_id'}."','".$obj->{'evaluates'}[$i]->{'evaluate'}."','".$showtime."','".$obj->{'evaluates'}[$i]->{'user_name'}."')" );
	} 
	
?>

