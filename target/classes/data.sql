-- Insert statements for clients
INSERT INTO clients (email, name, surname, password) VALUES
('john.doe@example.com', 'John', 'Doe', 'sd12Thj8'),
('jane.smith@example.com', 'Jane', 'Smith', '2312Thj0'),
('alice.johnson@example.com', 'Alice', 'Johnson', 's7f5hj8'),
('elen.road@example.com', 'Elen', 'Road', 'df4g467'),
('test@example.com', 'User', 'Surname', 'test');

-- Insert statements for articles
INSERT INTO articles (name, feature, description, available_quantity, price, image_path) VALUES
('Laptop', 'High-performance laptop', 'High-performance laptop for work and play, featuring the latest processors and graphics.', 10, 999.99, 'https://purepng.com/public/uploads/large/purepng.com-laptopelectronicslaptopcomputer-941524676166s0nuj.png'),
('Smartphone', 'Latest model smartphone', 'Latest model smartphone with advanced camera features, long battery life, and sleek design.', 25, 699.99, 'https://pngimg.com/d/smartphone_PNG8519.png'),
('Headphones', 'Noise-cancelling headphones', 'Noise-cancelling headphones for an immersive sound experience, perfect for music and calls.', 50, 199.99, 'https://i.pinimg.com/originals/86/c1/ac/86c1ac8bac43ea337f7fe9da5c87a7fd.png'),
('Tablet', '10-inch tablet with high-resolution display', '10-inch tablet with high-resolution display, ideal for browsing, reading, and entertainment.', 15, 299.99, 'https://freepngimg.com/save/1938-tablet-png-image/1500x1109'),
('Smart Watch', 'Fitness tracking smart watch', 'Fitness tracking smart watch with heart rate monitor, GPS, and customizable watch faces.', 30, 179.99, 'https://static.vecteezy.com/system/resources/previews/009/379/846/original/smartwatch-clipart-design-illustration-free-png.png'),
('Wireless Earbuds', 'Bluetooth wireless earbuds', 'Bluetooth wireless earbuds with crystal clear sound, comfortable fit, and long battery life.', 40, 149.99, 'https://motorolaus.vtexassets.com/arquivos/ids/161415-800-auto?width=800&height=auto&aspect=true'),
('Gaming Console', 'Next-gen gaming console', 'Next-gen gaming console with ultra-fast load times, stunning visuals, and immersive gameplay.', 20, 499.99, 'https://assets.xboxservices.com/assets/34/72/34728a56-e88e-409d-9e6f-1e89aa82b25f.png?n=Console-Hub_Content-Placement-0_SeriesS-Duo_740x555.png'),
('Digital Camera', 'Professional DSLR camera', 'Professional DSLR camera with high resolution, fast autofocus, and versatile lens options.', 8, 1499.99, 'https://purepng.com/public/uploads/large/purepng.com-digital-photo-cameracameraphotodigital-cameravideophoto-machinephoto-maker-231519335184dzqjr.png'),
('Bluetooth Speaker', 'Portable Bluetooth speaker', 'Portable Bluetooth speaker with powerful sound, long battery life, and water-resistant design.', 35, 99.99, 'https://i.pinimg.com/originals/0e/56/64/0e56640a49ab8af1ffd44f06a9bd0985.png'),
('Gaming Mouse', 'Ergonomic gaming mouse', 'Ergonomic gaming mouse with customizable buttons, RGB lighting, and high-precision sensor.', 60, 59.99, 'https://media.steelseriescdn.com/thumbs/filer_public/79/38/793810c0-be38-4840-ab60-0657e7ecd973/purchase-gallery-650wl-top.png__1920x1080_crop-fit_optimize_subsampling-2.png'),
('4K TV', 'Ultra HD 4K TV', 'Ultra HD 4K TV with stunning picture quality, smart features, and multiple connectivity options.', 12, 799.99, 'https://www.pngall.com/wp-content/uploads/5/Full-HD-LED-TV-PNG-Image.png'),
('Fitness Tracker', 'Activity tracking device', 'Activity tracking device with heart rate monitor, sleep tracking, and multi-sport modes.', 45, 129.99, 'https://png.pngtree.com/png-vector/20220930/ourmid/pngtree-fitness-tracker-or-smart-watch-display-screen-interface-png-image_6226335.png'),
('Router', 'High-speed Wi-Fi router', 'High-speed Wi-Fi router with dual-band support, advanced security features, and easy setup.', 25, 89.99, 'https://i.pinimg.com/originals/4d/6e/e3/4d6ee35efaf1cc622ab654c0d7a86de7.png'),
('External Hard Drive', 'Portable external hard drive', 'Portable external hard drive with large storage capacity, fast data transfer, and durable design.', 20, 109.99, 'https://purepng.com/public/uploads/large/purepng.com-hdd-hard-disk-driveelectronics-hard-disk-hard-drive-941524662478iqnno.png'),
('Drone', 'Compact aerial drone', 'Compact aerial drone with HD camera, easy controls, and stable flight performance.', 10, 499.99, 'https://pngimg.com/uploads/drone/drone_PNG52.png'),
('VR Headset', 'Virtual reality headset', 'Virtual reality headset with immersive visuals, comfortable fit, and wide compatibility.', 15, 399.99, 'https://static.vecteezy.com/system/resources/previews/024/724/520/non_2x/virtual-reality-or-vr-headset-isolated-on-transparent-background-vr-glasses-for-360-environment-games-or-simulation-training-generative-ai-free-png.png'),
('E-Reader', 'E-ink display e-reader', 'E-ink display e-reader with adjustable lighting, long battery life, and lightweight design.', 18, 139.99, 'https://www.pngkey.com/png/full/966-9661180_kindle-ebook-from-amazon-e-book-readers.png'),
('Printer', 'Wireless all-in-one printer', 'Wireless all-in-one printer with high-quality printing, scanning, and copying capabilities.', 12, 149.99, 'https://pngimg.com/uploads/printer/printer_PNG7748.png'),
('Smart Home Hub', 'Voice-controlled smart hub', 'Voice-controlled smart hub for managing smart home devices, playing music, and getting information.', 22, 129.99, 'https://blog.smartthings.com/wp-content/uploads/2023/03/Smart-Home-Hub-HG.png'),
('Projector', 'Portable mini projector', 'Portable mini projector with HD resolution, multiple input options, and built-in speaker.', 10, 299.99, 'https://www.viewsonic.com/it/imgHandler/?country=it&mobile=1&file=slides/0projector/PA503SP/scaled/PA503SP_LF02_m.png'),
('Electric Scooter', 'Foldable electric scooter', 'Foldable electric scooter with powerful motor, long range, and compact design for easy storage.', 5, 499.99, 'https://pngimg.com/d/electric_scooter_PNG51.png'),
('Monitor', 'Ultra-wide monitor', 'Ultra-wide monitor with high resolution, fast refresh rate, and adjustable stand for ergonomic use.', 14, 299.99, 'https://purepng.com/public/uploads/large/purepng.com-monitormonitorscomputer-displayvisual-displayelectronicdisplay-devicecrystal-displaylcdledamoled-1701528362222joubs.png'),
('Keyboard', 'Mechanical gaming keyboard', 'Mechanical gaming keyboard with customizable RGB lighting, durable switches, and ergonomic design.', 50, 79.99, 'https://pngimg.com/d/keyboard_PNG101839.png'),
('Smart Thermostat', 'Programmable smart thermostat', 'Programmable smart thermostat with energy-saving features, remote control, and easy installation.', 10, 199.99, 'https://cdn11.bigcommerce.com/s-dfnvu55jjs/images/stencil/357x476/products/192/511/blob__76445.1702661171.png?c=1');

-- Insert statements for carts
INSERT INTO carts (id_client, total_price) VALUES
(1, 199.00),
(2, 299.99),
(3, 499.99);

-- Insert statements for articles_has_carts
INSERT INTO articles_has_carts (id_cart, id_article, quantity) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 3, 1);

-- Insert statements for orders
-- INSERT INTO orders (id_cart, id_client, state, payment_type) VALUES
-- (1, 1, 'CONFIRMED', 'CREDIT_CARD'),
-- (2, 2, 'CLOSED', 'PAYPAL'),
-- (3, 3, 'CONFIRMED', 'BANK_TRANSFER');
