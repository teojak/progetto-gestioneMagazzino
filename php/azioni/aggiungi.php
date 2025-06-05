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
$mess="";
$fileMagazzino='magazzino_'.$_SESSION['mail'].'.txt';

if(isset($_POST['aggiungi'])){
    $barcode =trim($_POST['barcode']);   
    $prodotto=trim($_POST['product']);
    $marca=trim($_POST['brand']);
    $modello=trim($_POST['model']);
    $prezzo=trim($_POST['price']);
    $quantita=trim($_POST['quantity']);

    $exist=false;
    if (file_exists($fileMagazzino) && ($file = fopen($fileMagazzino, 'c+'))){
        while (($line = fgets($file)) !== false){
            list($barcodeSTRD,,,,,) = explode(';', trim($line));
            if($barcodeSTRD==$barcode){
                $mess="<p>Errore: il prodotto inserito è gia presente</p>";
                $exist=true;
                break;
            }    
        }
        if(!$exist){
           file_put_contents($fileMagazzino, "$barcode;$prodotto;$marca;$modello;$prezzo;$quantita\n", FILE_APPEND);
            $mess="<p>Prodotto inserito nel magazzino</p>"; 
        }    
        fclose($file);
    }
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
        </style>
</head>
<body>
    <div class="container">
        <h2>Aggiungi prodotto</h2>
        <form class="form1" method="post">
            <div style="padding-bottom:15px;">
                <label for="barcode">Barcode:</label><br>
                <input type="number" id="barcode" name="barcode" required>
            </div>  
            <div style="padding-bottom:15px;">
                <label for="product">Prodotto:</label><br>
                <input type="text" id="product" name="product" required>
            </div>  
            <div style="padding-bottom:15px;">
                <label for="brand">Marca:</label><br>
                <input type="text" id="brand" name="brand" required>
            </div>  
            <div style="padding-bottom:15px;">
                <label for="model">Modello:</label><br>
                <input type="text" id="model" name="model" required>
            </div>  
            <div style="padding-bottom:15px;">
                <label for="price">Prezzo:</label><br>
                <input type="number" id="price" name="price" required>
            </div>  
            <div style="padding-bottom:10px;">
                <label for="quantity">Quantità:</label><br>
                <input type="number" id="quantity" name="quantity" required>
            </div>         
            <div class="button-group">
                <button class="Add" type="submit" name="aggiungi">Aggiungi</button>
                <button class="Return" type="submit" name="back">Indietro</button>
            </div>
            <?php
                if(isset($mess)){
                    echo $mess;
                }
            ?>
        </form>
         
    </div>
</body>
</html>