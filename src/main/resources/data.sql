-- Inserta la marca
INSERT INTO brand (id, name) VALUES (1, 'ZARA');
INSERT INTO brand (id, name) VALUES (2, 'ADIDAS');
INSERT INTO brand (id, name) VALUES (3, 'NIKE');

-- Inserta precios
INSERT INTO PRICES (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');
(5, 2, '2020-06-16 17:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 39.95, 'EUR');
(6, 3, '2020-06-17 18:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 40.95, 'EUR');
