CREATE TABLE IF NOT EXISTS orders (
                                       id uuid not null default gen_random_uuid(),
                                       name varchar(250) NOT NULL,
                                       number varchar(250) NOT NULL
    );
