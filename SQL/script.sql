--create a database called "mybd"
CREATE DATABASE mybd;

--create a table called "clients" with the following fields: id, name, lastname, docnumber
CREATE TABLE clients (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    lastname VARCHAR(250) NOT NULL,
    docnumber VARCHAR(250) NOT NULL,
    PRIMARY KEY (id)
);

--create a table called "products" with the following fields: id, description, code, stock, price
CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(250) NOT NULL,
    code VARCHAR(250) NOT NULL,
    stock INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);

--create a table called "invoice_details" with the following fields: invoice_id, invoice_detail_id, amoun, product_id, price
--invoice_id is a FK to the invoice table
--product_id is a FK to the products table

CREATE TABLE invoice_details (
    invoice_id INT NOT NULL,
    invoice_detail_id INT NOT NULL AUTO_INCREMENT,
    amoun INT NOT NULL,
    product_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (invoice_detail_id)
	-- set foreign key
	FOREIGN KEY (invoice_id) REFERENCES invoice(id)
	FOREIGN KEY (product_id) REFERENCES products(id)
);

--create a table called "invoice" with the following fields: id, client_id, created_at, total
--client_id is a FK to the clients table
CREATE TABLE invoice (
    id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    created_at DATETIME NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
	-- set foreign key
	FOREIGN KEY (client_id) REFERENCES clients(id)
);


