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

$fileMagazzino = 'magazzino_' . $_SESSION['mail'] . '.txt';
$mess = "";
$modificato = false; // Flag per indicare se una riga è stata modificata

if (isset($_POST['sell'])) {
    $barcode = trim($_POST['barcode']);
    $marca = trim($_POST['brand']);
    $quantitaVenduta = trim($_POST['quantity']);
    $nuovoContenuto = "";

    if (file_exists($fileMagazzino) && ($file = fopen($fileMagazzino, 'r'))) {
        while (($line = fgets($file)) !== false) {
            list($barcodeSTRD,$prodottoSTRD , $marcaSTRD,$modelloSTRD ,$prezzoSTRD , $quantitaSTRD) = explode(';', trim($line));

            if ($barcodeSTRD == $barcode && $marcaSTRD == $marca) {
                if ($quantitaSTRD < $quantitaVenduta) {
                    $mess = "<p>Errore: quantità inserita maggiore della quantità presente nel magazzino</p>";
                } else {
                    $nuovaQuantita = $quantitaSTRD - $quantitaVenduta;
                    $nuovoContenuto .= "$barcodeSTRD;$prodottoSTRD;$marcaSTRD;$modelloSTRD;$prezzoSTRD;$nuovaQuantita\n";
                    $modificato = true;
                }
            } else {
                $nuovoContenuto .= $line; // Mantieni la riga inalterata
            }
        }
        fclose($file);

        if ($modificato) {
            file_put_contents($fileMagazzino, $nuovoContenuto);
            $mess = "<p>Vendita registrata con successo. Quantità aggiornata.</p>";
        } elseif (empty($mess) && isset($_POST['sell'])) {
            $mess = "<p>Errore: Prodotto con barcode e marca specificati non trovato nel magazzino.</p>";
        }
    } else {
        $mess = "<p>Errore: Impossibile aprire il file del magazzino.</p>";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendi Prodotto</title>
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
        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .message {
            margin-top: 15px;
            font-weight: bold;
        }

        .error {
            color: red;
        }

        .success {
            color: green;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Vendi Prodotto</h2>
        <form method="post">
            <label for="barcode">Barcode:</label>
            <input type="text" id="barcode" name="barcode" required>

            <label for="brand">Marca:</label>
            <input type="text" id="brand" name="brand" required>

            <label for="quantity">Quantità da vendere:</label>
            <input type="number" id="quantity" name="quantity" min="1" required>
            <div class="button-group">
                <button type="submit" name="sell">Vendi</button>
                <button class="Return" type="submit" name="back">Indietro</button>
            </div>
        </form>
        <div class="message <?php echo (strpos($mess, 'Errore') !== false) ? 'error' : 'success'; ?>">
            <?php echo $mess; ?>
        </div>
    </div>
</body>
</html>


