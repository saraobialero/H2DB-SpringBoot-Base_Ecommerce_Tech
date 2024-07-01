-- Inserimento dei clients
INSERT INTO clients (id_client, email, name, surname, password) VALUES
('b12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'john@gmail.com', 'John', 'Doe', '12f45gt6'),
('b12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'jane.dow@email.com', 'Jane', 'Doe', 'Sdrft65w'),
('b12b0459-9daa-4c07-9b2f-40dd0cfa5ff2', 'emily.dow@email.com', 'Emily', 'Brown', '78rft65w'),
('b12b1459-9daa-4c07-9b2f-40dd0cfa5fs8', 'david.gray@email.com', 'David', 'Gray', '78rft695');


-- Inserimento degli articles
INSERT INTO articles (id_article, name_article, description, available_quantity, price) VALUES
('a12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'Laptop', 'High performance laptop', 50, 999.99),
('a12b0459-9daa-4c07-9b2f-40dd0cfa5hg5', 'Smartphone', 'Latest model smartphone', 100, 699.99),
('a12b0459-9daa-4c07-9b2f-40dd0cfa3fg9', 'Smartwatch', 'Latest model smartwatch', 200, 399.99);

-- Inserimento dei carts
INSERT INTO carts (id_cart, id_client, payment_type, state, total_price) VALUES
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'b12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'CREDIT_CARD', 'IN_PROGRESS', 0.0),
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'b12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'PAYPAL', 'IN_PROGRESS', 0.0),
('c12b0459-9daa-4c07-9b2f-40dd0cfa9sa1', 'b12b1459-9daa-4c07-9b2f-40dd0cfa5fs8', 'PAYPAL', 'CLOSED', 399.99);

-- Inserimento dei articoli nei carts
INSERT INTO articles_has_carts (id_cart, id_article, quantity) VALUES
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'a12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 1),
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 'a12b0459-9daa-4c07-9b2f-40dd0cfa5hg5', 2),
('c12b0459-9daa-4c07-9b2f-40dd0cfa5ff5', 'a12b0459-9daa-4c07-9b2f-40dd0cfa5ff4', 1),
('c12b0459-9daa-4c07-9b2f-40dd0cfa9sa1', 'a12b0459-9daa-4c07-9b2f-40dd0cfa3fg9', 1);
