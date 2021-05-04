CREATE TABLE books(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO books(name) VALUES('Outlander');