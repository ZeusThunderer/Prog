<?php
$start = microtime(true);
$isValid = false;
$x = $_GET["x"];
$y = $_GET["y"];
$r = $_GET["r"];
$data = "";
$maxlen = 10;

date_default_timezone_set("Europe/Moscow");

function check($x, $y, $r){
    if ($x <= 0 && $y >= 0 && $x * $x + $y * $y <= $r * $r / 4 ||
		$x <= 0 && $y <= 0 && $x + $y <= -$r / 2 ||
		$x >= 0 && $y <= 0 && $x <= $r && $y >= -$r / 2)
        return "True";
    else
        return "False";
}

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (-3 <= $x && $x <= 5 && round($x) == $x && strlen($x) <= $maxlen &&
        -5 <= $y && $y <= 3 && round($y) == $y && strlen($y) <= $maxlen &&
        1 <= $r && $r <= 5 && round($r) == $r && strlen($r) <= $maxlen) 
        $isValid = true;

    if ($isValid)
        $data = array($x, $y, $r, check($x, $y, $r), date("H:i:s"), microtime(true) - $start);
    else {
        http_response_code(400);
        exit;
    }
}
?>
<tr>
    <td><?php echo $data[0] ?></td>
    <td><?php echo $data[1] ?></td>
    <td><?php echo $data[2] ?></td>
    <td><?php echo $data[3] ?></td>
    <td><?php echo $data[4] ?></td>
    <td><?php echo $data[5] ?></td>
</tr>