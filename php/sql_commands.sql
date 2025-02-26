-- Create the books table in books db MariaDB [books]>
CREATE TABLE books(
    bookID int NOT NULL,
    title varchar(255),
    description varchar(255),
    author varchar(255),
    cost int,
    PRIMARY KEY (bookID));

-- Insert a row in the books table MariaDB [books]>
INSERT INTO books(bookID, title, description, author, cost) VALUES (3, 'Vita e Destino', 'Dall autore di
 Stalingrado e di Vita e destino, un immagine nitida e sconvolgente dello sterminio degli ebrei in Unione Sovietica, a guerra ancora in corso.', 'Vasilij Grossman', 17);

 -- Query by title or description
 SELECT * FROM books WHERE title LIKE :s OR description LIKE :s;

 -- Easy SELECT example (TO TEST)
 SELECT * FROM books WHERE title LIKE '%La %' OR description LIKE '%La %' ORDER BY cost DESC LIMIT 1 OFFSET ;

 -- WORKING
 SELECT *,
    CASE
        WHEN title LIKE :s THEN 'title'
        WHEN description LIKE :s THEN 'description'
    END AS matching
FROM books
WHERE title LIKE :s OR description LIKE :s
LIMIT 3;

-- SELECT
SELECT bookID, isbn, title FROM books;

-- UPDATE the field
UPDATE books SET isbn=9788804745631 WHERE bookID=7;
 
