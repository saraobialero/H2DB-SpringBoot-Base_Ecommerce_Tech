INSERT INTO clients (id_client, name, surname, password) VALUES
(UUID(), 'John', 'Doe', 'password123'),
(UUID(), 'Jane', 'Doe', 'password456');

INSERT INTO articles (id_article, name_article, description, quantity, price) VALUES
(UUID(), 'Laptop', 'High performance laptop', 50, 999.99),
(UUID(), 'Smartphone', 'Latest model smartphone', 100, 699.99);

INSERT INTO carts (id_cart, id_client, payment_type, state, total_price) VALUES
(UUID(), (SELECT id_client FROM clients WHERE name='John' LIMIT 1), 'CREDIT_CARD', 'IN_PROGRESS', 0.0),
(UUID(), (SELECT id_client FROM clients WHERE name='Jane' LIMIT 1), 'PAYPAL', 'IN_PROGRESS', 0.0);

INSERT INTO carts_articles (id_cart, id_article, quantity) VALUES
((SELECT id_cart FROM carts WHERE id_client=(SELECT id_client FROM clients WHERE name='John' LIMIT 1) LIMIT 1), (SELECT id_article FROM articles WHERE name_article='Laptop' LIMIT 1), 1),
((SELECT id_cart FROM carts WHERE id_client=(SELECT id_client FROM clients WHERE name='John' LIMIT 1) LIMIT 1), (SELECT id_article FROM articles WHERE name_article='Smartphone' LIMIT 1), 2),
((SELECT id_cart FROM carts WHERE id_client=(SELECT id_client FROM clients WHERE name='Jane' LIMIT 1) LIMIT 1), (SELECT id_article FROM articles WHERE name_article='Laptop' LIMIT 1), 1);
