INSERT INTO books (title, author, published_year, genre)
VALUES
('1984', 'George Orwell', 1949, 'Dystopia'),
('Brave New World', 'Aldous Huxley', 1932, 'Science Fiction'),
('The Hobbit', 'J.R.R. Tolkien', 1937, 'Fantasy'),
('Fahrenheit 451', 'Ray Bradbury', 1953, 'Science Fiction');

INSERT INTO readers (name, email, phone)
VALUES
('Anna Müller', 'anna.mueller@example.com', '491234567890'),
('Max Mustermann', 'max.mustermann@example.com', '491234567891'),
('John Doe', 'john.doe@example.com', '491234567892');

INSERT INTO borrowed_books (book_id, reader_id, borrow_date, return_date, status)
VALUES
(1, 1, '2024-06-01', NULL, 'borrowed'),
(2, 1, '2024-05-15', '2024-06-10', 'returned'),
(3, 2, '2024-06-05', NULL, 'borrowed'),
(4, 3, '2024-06-10', NULL, 'borrowed');

SELECT * FROM books;
SELECT * FROM readers;
SELECT * FROM borrowed_books;

UPDATE books
SET genre ='Classic Science Fiction'
where title ='Brave New World';

UPDATE borrowed_books
Set status = 'returned', return_date = '2024-06-20'
where book_id = 1 and reader_id = 1;

DELETE FROM borrowed_books
WHERE reader_id = 2;

DELETE FROM readers
WHERE id = 2;

DELETE FROM borrowed_books
WHERE book_id = 2;

