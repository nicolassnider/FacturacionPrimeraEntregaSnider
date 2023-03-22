--mysql code
--create a database called "mybd" if not exists
CREATE SCHEMA IF NOT EXISTS mybd;

--use the database "mybd"
USE mybd;

--create a table called "clients" with the following fields: id, name, lastname, docnumber
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) NOT NULL,
  `lastname` varchar(75) NOT NULL,
  `docnumber` varchar(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `docnumber_UNIQUE` (`docnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--create a table called "products" with the following fields: id, description, code, stock, price
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(150) NOT NULL,
  `code` varchar(50) NOT NULL,
  `stock` int(11) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--create a table called "invoice" with the following fields: id, client_id, created_at, total
--client_id is a FK to the clients table
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id_idx` (`client_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--create a table called "invoice_details" with the following fields: invoice_id, invoice_detail_id, amoun, product_id, price
--invoice_id is a FK to the invoice table
--product_id is a FK to the products table
CREATE TABLE `invoice_details` (
  `invoice_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_id` int(11) NOT NULL,
  `amoun` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`invoice_detail_id`),
  KEY `invoice_id_idx` (`invoice_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `invoice_id` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- create a constraint that product stock should be greater or equal than 0
ALTER TABLE `products` ADD CONSTRAINT `stock` CHECK (`stock` >= 0);

-- create a constraint that product price should be greater or equal than 0
ALTER TABLE `products` ADD CONSTRAINT `price` CHECK (`price` >= 0);

-- create a constrait that amoun should be greater COMMENT
ALTER TABLE `invoice_details` ADD CONSTRAINT `amoun` CHECK (`amoun` > 0);