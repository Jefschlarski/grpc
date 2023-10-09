CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    stock INTEGER NOT NULL,
    CONSTRAINT id UNIQUE(id)
);

INSERT INTO product (name, price, stock) VALUES ('Product A', 10.99, 10);
INSERT INTO product (name, price, stock) VALUES ('Product B', 10.99, 10);