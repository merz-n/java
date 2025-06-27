SELECT
b.title,
b.author,
bb.reader_id,
bb.borrow_date
FROM
books b
INNER JOIN
borrowed_books bb
ON
b.id = bb.book_id
where
bb.status = 'borrowed';

SELECT
r.name,
COUNT(bb.id) AS total_borrowed
FROM
readers r
Left JOIN
borrowed_books bb
ON
r.id = bb.reader_id
GROUP BY
r.name;

SELECT
r.name,
COUNT(bb.id) AS total_borrowed
FROM
readers r
Left JOIN
borrowed_books bb
ON
r.id = bb.reader_id
GROUP BY
r.name
HAVING
COUNT(bb.id) >2;


SELECT
b.title,
b.author,
bb.borrow_date,
bb.return_date,
bb.status
FROM
books b
LEFT JOIN
borrowed_books bb
ON
b.id = bb.book_id
WHERE
b.genre = 'Fantasy';



