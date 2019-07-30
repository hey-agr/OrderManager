INSERT INTO products (name, price) VALUES ('IPhone 5S',23900.00);
INSERT INTO products (name, price) VALUES ('MacBook PRO',120000.00);
INSERT INTO orders (number, customeremail, ordersum, ordertime) VALUES (1228, 'ivan@gmail.ru', 30000.00, '2019-07-29 18:00:00.000');
INSERT INTO orders (number, customeremail, ordersum, ordertime) VALUES (3470, 'marina@gmail.ru', 30000.00, '2019-07-29 18:00:00.000');
INSERT INTO ordercontent(count, price, sum, order_id, product_id) VALUES (2, 23900.00, 45800.00, 1, 1);
INSERT INTO ordercontent(count, price, sum, order_id, product_id) VALUES (1, 120000.00, 120000.00, 1, 2);