<!DOCTYPE html>
<html>
<body>

<h1>My First Heading</h1>
<p>My first paragraph.</p>
 <div>
    <?php 
        $type = "pgsql";
        $host = "localhost";
        $db = "myDb";
        $port = "5432";

        $user = "postgres";
        $psw = "caramella";

        try{
            $pdo = new PDO("$type:host=$host;dbname=$db", $user, $psw);
        }catch (PDOException $e) {
            echo 'Connection failed: ' . $e->getCode() . '&nbsp;' . $e->getMessage();
        }
    ?>

</div>

</body>
</html> 