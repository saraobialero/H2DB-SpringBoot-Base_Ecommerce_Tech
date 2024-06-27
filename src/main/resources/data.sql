-- Inserimento dei clients
INSERT INTO clients (id_client, email, name, surname, password) VALUES
('b12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'john.dow@email.com', 'John', 'Doe', 'hg5FTkl2'),
('b12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'jane.dow@email.com', 'Jane', 'Doe', 'sd5FTkl2');

-- Inserimento degli articles
INSERT INTO articles (id_article, name_article, description, quantity, price) VALUES
('a12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'Laptop', 'High performance laptop', 50, 999.99),
('a12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'Smartphone', 'Latest model smartphone', 100, 699.99);

-- Inserimento dei carts
INSERT INTO carts (id_cart, id_client, payment_type, state, total_price) VALUES
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'b12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'CREDIT_CARD', 'IN_PROGRESS', 0.0),
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'b12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'PAYPAL', 'IN_PROGRESS', 0.0);

-- Inserimento dei articoli nei carts
INSERT INTO articles_has_carts (id_cart, id_article, quantity) VALUES
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'a12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 1),
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'a12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 2),
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'a12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 1);
