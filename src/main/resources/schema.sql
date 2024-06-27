-- Ordine corretto per eliminare le tabelle
DROP TABLE articles_has_carts IF EXISTS;
DROP TABLE carts IF EXISTS;
DROP TABLE articles IF EXISTS;
DROP TABLE clients IF EXISTS;

-- Definizione delle tabelle
CREATE TABLE IF NOT EXISTS clients (
    id_client CHAR(36) PRIMARY KEY,
    email VARCHAR(36) NOT NULL UNIQUE,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    password VARCHAR(12) NOT NULL
);

CREATE TABLE IF NOT EXISTS articles (
    id_article CHAR(36) PRIMARY KEY,
    name_article VARCHAR(20) NOT NULL,
    description VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS carts (
    id_cart CHAR(36) PRIMARY KEY,
    id_client CHAR(36) NOT NULL,
    payment_type ENUM('CREDIT_CARD', 'GOOGLE_PAY', 'PAYPAL', 'BANK_TRANSFER') NOT NULL,
    state ENUM('IN_PROGRESS', 'UPDATED', 'SAVED') NOT NULL,
    total_price DOUBLE NOT NULL,
    FOREIGN KEY (id_client) REFERENCES clients(id_client)
);

CREATE TABLE IF NOT EXISTS articles_has_carts (
    id_cart CHAR(36),
    id_article CHAR(36),
    quantity INT NOT NULL,
    PRIMARY KEY (id_cart, id_article),
    FOREIGN KEY (id_cart) REFERENCES carts(id_cart),
    FOREIGN KEY (id_article) REFERENCES articles(id_article)
);
