-- todo id powinien byc autoinkremented
CREATE TABLE IF NOT EXISTS orders (
                                       id SERIAL PRIMARY KEY,
                                       name varchar(250) NOT NULL,
    count INT not null,
    PRIMARY KEY (id)
    );
