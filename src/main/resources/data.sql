-- Insert statements for clients
INSERT INTO clients (email, name, surname, password) VALUES
('john.doe@example.com', 'John', 'Doe', 'password123'),
('jane.smith@example.com', 'Jane', 'Smith', 'password456'),
('alice.johnson@example.com', 'Alice', 'Johnson', 'password789'),
('elen.road@example.com', 'Elen', 'Road', 'dfr5467s');

-- Insert statements for articles
INSERT INTO articles (name, description, available_quantity, price) VALUES
('Laptop', 'High-performance laptop', 10, 999.99),
('Smartphone', 'Latest model smartphone', 25, 699.99),
('Headphones', 'Noise-cancelling headphones', 50, 199.99);

-- Insert statements for carts
INSERT INTO carts (id_client, total_price, state) VALUES
(1, 199.00, 'SAVED'),
(2, 299.99, 'SAVED'),
(3, 499.99, 'SAVED');

-- Insert statements for articles_has_carts
INSERT INTO articles_has_carts (id_cart, id_article, quantity) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 3, 1);

-- Insert statements for orders
INSERT INTO orders (id_cart, id_client, state, payment_type) VALUES
(1, 1, 'CONFIRMED', 'CREDIT_CARD'),
(2, 2, 'CLOSED', 'PAYPAL'),
(3, 3, 'CONFIRMED', 'BANK_TRANSFER');
