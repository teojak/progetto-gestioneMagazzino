<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">

    <title>Warehouse main</title>
    <meta name="description" content="Local warehouse main">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="mobile-web-app-capable" content="yes">

    <link rel="icon" href="/favicon.ico" sizes="any">
    <link rel="icon" href="/favicon.svg" type="image/svg+xml">

    <style>

      :root {
        --main-color: gold;
        --text1: hsl(200, 65%, 15%);
      }

      /* reset browser defaults */
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }


      html {
        /* block-size: 100%; */
        min-block-size: 100vh;
      }

      body  {
        min-block-size: 100vh;
        font-family: system-ui, sans-serif;
        font-size: 1.9rem;

        display: grid;
        /* justify-items + align-items */
        place-items: center;
        gap: 5rem;
      }

      header {
        width: 100%;
        height: 5rem;

        position: fixed;
        top: 0;

        background-color: var(--main-color);
      }

      nav {
        display: flex;
        justify-content: center;
      }

      nav a {
        margin-right: 3rem;
        margin-top: 1rem;
      }

      section {
        width: 100%;
        height: 100vh;

        display: grid;
        place-items: center;
      }

      .red {
        background-color: crimson;
      }
      
      @media only screen and (min-width: 767px) {

      }
      
    </style>
    
  </head>

  <body>

    <header>
      <nav>
        <a href="/">Home</a>
        <a href="/php/db-connection.php">Connection</a>
        <a href="/php/help.php">Help</a>
      </nav>
    </header>

    <!-- section -->
    <section>
      <h1>Warehouse main</h1>
      <h2>Section MAIN</h2>
    </section>

    <section class="red">
      <h2>Section TWO</h2>
    </section>

    <noscript>
      Please enable JavaScript to view this website.
    </noscript>

    <script>

      window.addEventListener('DOMContentLoaded', (e) => {
        const appVersion = '0.2.0'
        console.log('WebApplication: ', appVersion)

      })

    </script>

  </body>
</html>