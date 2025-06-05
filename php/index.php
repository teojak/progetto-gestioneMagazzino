<!DOCTYPE html>
<html>   
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
            padding:10%;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        form{
            display: grid;
        }

        button {
            
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px;           
        }

        br{
            padding-bottom: 10px;
        }

    </style>
</head>
<body>
    <div>
        <?php
            require_once 'db-connection.php';
        ?>
    </div>
    <div class="container">
        <form method="post">
            <?php $mydate=getdate(date("U"));
            echo "$mydate[weekday], $mydate[month] $mydate[mday], $mydate[year]";
            ?><br><br>
            <button type="submit" name="add">Aggiungi Prodotto</button><br>
            <button type="submit"  name="delete">Vendita Prodotto</button><br>
            <button type="submit"  name="visualize">Visualizza prodotti</button>
        </form>
    </div>
</body>
</html>