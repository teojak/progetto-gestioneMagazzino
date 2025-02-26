<?php

    // $port = "5432";  default postgres port

    try {
        $db = new PDO('postgresql:host=localhost;dbname=myDb; charset=utf8','postgres', 'caramella');
    } catch (PDOException $e) {
        echo "@CONNECTION Failed >> " . $e->getMessage();
        die();
    }

?>