<?php
session_start();

require_once 'db-connection.php';

if(!isset($_SESSION['mail'])){
    header('Location: signin.php');
    exit;
}


if(isset($_POST['back'])){
    header('Location: ../magazzino.php');
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        
        body {
            font-family: sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: white;
            padding:100px;
            border-radius: 8px;
            box-shadow: 10px 4px 6px rgba(120, 217, 55, 0.1);
        }

        .form1{
            display: grid;
        }

        .form2{
            float: left;
            width: 50%;
            padding: 5px;
        }
        button {
            
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        button:hover{
            background-color:rgb(60, 161, 238);
        }
        input{
            border-radius:5px;
        }

        .Add{
            justify-self:baseline;
        }

        .Return{
            justify-self: right;
        }

        br{
            padding-bottom: 10px;
        }
        .button-group {
                display: flex;
                gap: 10px;
                padding-top: 10px;
            }
        label{
            text-align: center;
            margin-left: 20px;
        }
        </style>
</head>
<body>
    
<div class="container">
        <h2>Visualizza Prodotto</h2>
        <form class="form1" method="post">
        <div style="padding-bottom:15px;">
                <label for="product">Prodotto:</label><br>
                <input type="text" id="product" name="product" >
            </div>  
            <div style="padding-bottom:15px;">
                <label for="brand">Marca:</label><br>
                <input type="text" id="brand" name="brand" >
            </div>  
        <div class="form2">
            <div style="display: flex; gap: 20px; justify-content: center;">
                <label>
                <?php
                    $fileMagazzino='magazzino_'.$_SESSION['mail'].'.txt';

                    if(isset($_POST['visualizza'])){
                        $prodotto=trim($_POST['product']);
                        $marca=trim($_POST['brand']);
                    
                        if (file_exists($fileMagazzino) && ($file = fopen($fileMagazzino, 'r'))){
                            echo "<table border='1'>";
                            while (($line = fgets($file)) !== false){ 
                                list($barcodeSTRD,$prodottoSTRD,$marcaSTRD,$modelloSTRD,$prezzoSTRD,$quantitaSTRD) = explode(';', trim($line));
                                if($prodotto==$prodottoSTRD && $marca==""){
                                    echo"<tr>
                                    <th>$barcodeSTRD</th>
                                    <th>$prodottoSTRD</th>
                                    <th>$marcaSTRD</th>
                                    <th>$modelloSTRD</th>
                                    <th>$prezzoSTRD</th>
                                    <th>$quantitaSTRD</th>
                                    </tr>";
                                }elseif($prodotto=="" && $marca==$marcaSTRD){
                                    echo"<tr>
                                    <th>$barcodeSTRD</th>
                                    <th>$prodottoSTRD</th>
                                    <th>$marcaSTRD</th>
                                    <th>$modelloSTRD</th>
                                    <th>$prezzoSTRD</th>
                                    <th>$quantitaSTRD</th>
                                    </tr>";
                                }elseif($prodotto==$prodottoSTRD && $marca==$marcaSTRD){
                                    echo"<tr>
                                    <th>$barcodeSTRD</th>
                                    <th>$prodottoSTRD</th>
                                    <th>$marcaSTRD</th>
                                    <th>$modelloSTRD</th>
                                    <th>$prezzoSTRD</th>
                                    <th>$quantitaSTRD</th>
                                    </tr>";
                                }
                            }
                            echo "</table>";

                            fclose($file);
                        }
                    }
                ?>
            </div>
            <div class="button-group">
                <button class="Add" type="submit" name="visualizza">Visualizza</button>
                <button class="Return" type="submit" name="back">Indietro</button>
            </div>
        </div><br>
        </form>
    </div>
</body>
</html>