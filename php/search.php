<?php

    require_once 'db-connection.php';

    // constant
    $rowsPerPage = 3;

    if (isset($_POST['search'])) {

        $search = $_POST['search'];

            
            if ($search === '*') {
                $sql = "SELECT *, COUNT(*) OVER() AS total
                            FROM books
                            LIMIT 3
                            OFFSET $offset;";
            } else {
                
                // @SQL wildcard
                $search = "%$search%";

                $sql = "SELECT *, COUNT(*) OVER() AS total,
                            CASE
                              WHEN title LIKE :s THEN 'title'
                              WHEN description LIKE :s THEN 'description'
                              WHEN isbn LIKE :s THEN 'isbn'
                            END AS matching
                        FROM books
                        WHERE title LIKE :s OR description LIKE :s OR isbn LIKE :s
                        LIMIT 3
                        OFFSET $offset;";
            }

            $stmt = $db->prepare($sql); // prepare the statement

            if ($search != '*') {
                $stmt->bindParam(':s', $search);  // bind our search string with the placeholder :s
            }
            
            $stmt->execute();

            // while its find result that match the search string
            $count = 0;
            $total = 0;
            $delay = 0;

            while($row = $stmt->fetch()) {
                $count++;

                $title = $row['title'];
                $isbn = $row['isbn'];
                $description = $row['description'];
                $matching = $row['matching'];
                $total = $row['total'];

                // compute and ship the delay of the query
                $delay = 133;

                $titleActive = $matching == 'title' ? 'active' : '';
                $descriptionActive = $matching == 'description' ? 'active' : '';
                $isbnActive = $matching == 'isbn' ? 'active' : '';

                echo "<div class='book' data-matching='$matching'>
                        <h1 class='title'>$title</h1>
                        <p class='desc'>$description</p>
                        <div></div>
                        <p class='isbn'>ISBN: $isbn</p>

                        <div class='tags'>

                            <div id='tag-isbn' " . $isbnActive . ">
                                ISBN
                            </div>
                
                            <div id='tag-title' " . $titleActive . ">
                                Title
                            </div>
                
                            <div id='tag-desc' " . $descriptionActive . ">
                                Desc
                            </div>
                        </div>

                     </div>";
            }

    }

?>