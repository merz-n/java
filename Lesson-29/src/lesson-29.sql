-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    id integer NOT NULL DEFAULT nextval('books_id_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author character varying(255) COLLATE pg_catalog."default" NOT NULL,
    published_year integer,
    genre character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT books_pkey PRIMARY KEY (id),
    CONSTRAINT check_year CHECK (published_year > 0)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
    OWNER to admin;
    -- Index: idx_books_title

    -- DROP INDEX IF EXISTS public.idx_books_title;

    CREATE INDEX IF NOT EXISTS idx_books_title
        ON public.books USING btree
        (title COLLATE pg_catalog."default" ASC NULLS LAST)
        WITH (deduplicate_items=True)
        TABLESPACE pg_default;

    -- Table: public.readers

    -- DROP TABLE IF EXISTS public.readers;

    CREATE TABLE IF NOT EXISTS public.readers
    (
        id integer NOT NULL DEFAULT nextval('readers_id_seq'::regclass),
        name character varying(100) COLLATE pg_catalog."default" NOT NULL,
        email character varying(255) COLLATE pg_catalog."default" NOT NULL,
        phone character varying(15) COLLATE pg_catalog."default",
        CONSTRAINT readers_pkey PRIMARY KEY (id),
        CONSTRAINT email UNIQUE (email),
        CONSTRAINT phone UNIQUE (phone)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS public.readers
        OWNER to admin;

        -- Table: public.borrowed_books

        -- DROP TABLE IF EXISTS public.borrowed_books;

        CREATE TABLE IF NOT EXISTS public.borrowed_books
        (
            id integer NOT NULL DEFAULT nextval('borrowed_books_id_seq'::regclass),
            book_id integer NOT NULL,
            reader_id integer NOT NULL,
            borrow_date date NOT NULL,
            return_date date,
            status character varying(20) COLLATE pg_catalog."default",
            CONSTRAINT borrowed_books_pkey PRIMARY KEY (id),
            CONSTRAINT fk_book FOREIGN KEY (book_id)
                REFERENCES public.books (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,
            CONSTRAINT fk_reader FOREIGN KEY (reader_id)
                REFERENCES public.readers (id) MATCH SIMPLE
                ON UPDATE NO ACTION
                ON DELETE NO ACTION,
            CONSTRAINT status CHECK (status::text = ANY (ARRAY['borrowed'::character varying, 'returned'::character varying]::text[]))
        )

        TABLESPACE pg_default;

        ALTER TABLE IF EXISTS public.borrowed_books
            OWNER to admin;

        -- Index: idx_borrowed_reader_status

        -- DROP INDEX IF EXISTS public.idx_borrowed_reader_status;

        CREATE INDEX IF NOT EXISTS idx_borrowed_reader_status
            ON public.borrowed_books USING btree
            (reader_id ASC NULLS LAST, status COLLATE pg_catalog."default" ASC NULLS LAST)
            WITH (deduplicate_items=True)
            TABLESPACE pg_default;
