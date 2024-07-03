-- Ordine corretto per eliminare le tabelle
DROP TABLE order_detail_articles IF EXISTS;
DROP TABLE order_details IF EXISTS;
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
    id_client INT NOT NULL,
    state ENUM ('ACTIVE', 'CLOSED', 'ANNULLED') NOT NULL,
    FOREIGN KEY (id_client) REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS order_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_order INT NOT NULL UNIQUE,
    total_price DOUBLE NOT NULL,
    payment_type ENUM('NOT_DEFINED', 'CREDIT_CARD', 'GOOGLE_PAY', 'PAYPAL', 'BANK_TRANSFER') NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_order) REFERENCES orders(id)
);

CREATE TABLE IF NOT EXISTS order_detail_articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_order_detail INT NOT NULL,
    id_article INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (id_order_detail) REFERENCES order_details(id),
    FOREIGN KEY (id_article) REFERENCES articles(id)
);

-- Indici per ottimizzare le query
CREATE INDEX idx_clients_email ON clients(email);
CREATE INDEX idx_carts_id_client ON carts(id_client);
CREATE INDEX idx_articles_has_carts_id_cart ON articles_has_carts(id_cart);
CREATE INDEX idx_articles_has_carts_id_article ON articles_has_carts(id_article);
CREATE INDEX idx_orders_id_client ON orders(id_client);
CREATE INDEX idx_order_details_id_order ON order_details(id_order);
CREATE INDEX idx_order_detail_articles_id_order_detail ON order_detail_articles(id_order_detail);
CREATE INDEX idx_order_detail_articles_id_article ON order_detail_articles(id_article);
