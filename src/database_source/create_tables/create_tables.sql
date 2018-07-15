CREATE TABLE if NOT EXISTS product(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  product_name VARCHAR(128),
  price DECIMAL(15, 2)
  produser_id INT
);

CREATE TABLE if NOT EXISTS producer(
  id BIGINT PRIMARY KEY not NULL auto_increment,
  producer_name VARCHAR (128)
)