INSERT INTO producer (producer_name) VALUES
  ('Cube'),
  ('Mongoose'),
  ('Tourist');

INSERT INTO product (product_name, price, producer_id) VALUES
  ('City bicycle', 1500, 1),
  ('Touring bicycle', 2000, 1),
  ('Sport bicycle', 1700, 1),
  ('City bicycle', 2200, 2),
  ('Sport bicycle', 3000, 2),
  ('Touring bicycle', 1550, 2),
  ('Hybrid bicycle', 2300, 2),
  ('Hybrid bicycle', 3000, 1),
  ('Roadster bicycle', 1800, 2),
  ('Touring bicycle', 1000, 3);

INSERT INTO user (active, email, first_name, last_name, password) VALUES
  (1, 'admin', 'Admin', 'Adminchuk', "$2a$10$MAs1BAegX3US17DM9Mlepeso0O7yXd/4LGZEMAMAR80JW1i56LgAW"),
  (1, 'user@ukr.net', 'User', 'Userko', "$2a$10$x62xKZmGnXXKgjFqvHc.M.m29/2jU6Wy8vBiXqUuEqb.aIHY1Z0Oe");

INSERT INTO role (id, role) VALUES
  (1, 'ADMIN'),
  (2, 'USER');

INSERT INTO user_role (user_id, role_id) VALUES
  (1, 1),
  (2, 2);

