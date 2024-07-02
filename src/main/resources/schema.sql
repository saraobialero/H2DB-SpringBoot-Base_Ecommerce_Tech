-- Ordine corretto per eliminare le tabelle
DROP TABLE articles_has_carts IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE carts IF EXISTS;
DROP TABLE articles IF EXISTS;
DROP TABLE clients IF EXISTS;

-- Definizione delle tabelle
CREATE TABLE IF NOT EXISTS clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(36) NOT NULL UNIQUE,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    password VARCHAR(12) NOT NULL
);

CREATE TABLE IF NOT EXISTS articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(100) NOT NULL,
    available_quantity INT NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT NOT NULL UNIQUE,
    state ENUM('IN_PROGRESS', 'SAVED') NOT NULL,
    total_price DOUBLE NOT NULL,
    FOREIGN KEY (id_client) REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS articles_has_carts (
    id_cart INT NOT NULL,
    id_article INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id_cart, id_article),
    FOREIGN KEY (id_cart) REFERENCES carts(id),
    FOREIGN KEY (id_article) REFERENCES articles(id)
);

CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cart INT NOT NULL,
    id_client INT NOT NULL,
    state ENUM ('CONFIRMED', 'CLOSED') NOT NULL,
    payment_type ENUM('NOT_DEFINED', 'CREDIT_CARD', 'GOOGLE_PAY', 'PAYPAL', 'BANK_TRANSFER') NOT NULL,
    FOREIGN KEY (id_cart) REFERENCES carts(id),
    FOREIGN KEY (id_client) REFERENCES clients(id)
);
