CREATE DATABASE IF NOT EXISTS lab9;

USE lab9;

-- Bảng 'product'
CREATE TABLE IF NOT EXISTS product (
  product_id INT PRIMARY KEY AUTO_INCREMENT,
  brand VARCHAR(255),
  price INT,
  product_name VARCHAR(255),
  color VARCHAR(255)
);

-- Bảng 'order'
CREATE TABLE IF NOT EXISTS order (
  order_id INT PRIMARY KEY AUTO_INCREMENT,
  day_create DATE,
  status VARCHAR(255)
);

-- Bảng 'orderproduct'
CREATE TABLE IF NOT EXISTS orderproduct (
  order_id INT,
  product_id INT,
  quantity INT,
  PRIMARY KEY (order_id, product_id),
  FOREIGN KEY (order_id) REFERENCES `order`(order_id),
  FOREIGN KEY (product_id) REFERENCES product(product_id)
);