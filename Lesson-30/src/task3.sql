BEGIN;

WITH inserted_book AS (
  INSERT INTO books (title, author, published_year, genre)
  VALUES ('The Midnight Library', 'Matt Haig', 2020, 'Fantasy')
  RETURNING id
)
INSERT INTO borrowed_books (book_id, reader_id, borrow_date, return_date, status)
SELECT id, 1, '2024-06-01', NULL, 'borrowed'
FROM inserted_book;

SELECT * FROM books;
SELECT * FROM borrowed_books;

ROLLBACK;

BEGIN;

WITH inserted_book AS (
  INSERT INTO books (title, author, published_year, genre)
  VALUES ('The Midnight Library', 'Matt Haig', 2020, 'Fantasy')
  RETURNING id
)
INSERT INTO borrowed_books (book_id, reader_id, borrow_date, return_date, status)
SELECT id, 1, '2024-06-01', NULL, 'borrowed'
FROM inserted_book;

COMMIT;

