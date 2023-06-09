-- H2 2.1.214;
-- SCRIPT TO 'dump.sql';
;             
CREATE USER IF NOT EXISTS "SA" SALT '5e0eaa532de1376a' HASH '7c860aa1bf74790fbb6f4f1d96015096b2b196f6ee8089c50a83b7f0819b8a54' ADMIN;         
CREATE MEMORY TABLE "PUBLIC"."CLIENTS"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 6) NOT NULL,
    "ACTIVE" BOOLEAN,
    "DOCNUMBER" CHARACTER VARYING(15),
    "LASTNAME" CHARACTER VARYING(20),
    "NAME" CHARACTER VARYING(20)
);  
ALTER TABLE "PUBLIC"."CLIENTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" PRIMARY KEY("ID");      
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.CLIENTS; 
INSERT INTO "PUBLIC"."CLIENTS" VALUES
(1, TRUE, '1234567890', 'Perez', 'Juan'),
(2, TRUE, '1234567891', 'Gomez', 'Pedro'),
(3, TRUE, '1234567892', 'Gonzalez', 'Maria'),
(4, TRUE, '1234567893', 'Lopez', 'Jose'),
(5, TRUE, '1234567894', 'Martinez', 'Ana');           
CREATE MEMORY TABLE "PUBLIC"."INVOICE_DETAILS"(
    "INVOICE_DETAILS_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 25) NOT NULL,
    "AMOUN" INTEGER,
    "UNIT_PRICE" FLOAT(53),
    "PRODUCT_ID" BIGINT NOT NULL,
    "INVOICE_ID" BIGINT
);  
ALTER TABLE "PUBLIC"."INVOICE_DETAILS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("INVOICE_DETAILS_ID");              
-- 24 +/- SELECT COUNT(*) FROM PUBLIC.INVOICE_DETAILS;        
INSERT INTO "PUBLIC"."INVOICE_DETAILS" VALUES
(1, 1, 100.0, 1, 1),
(2, 2, 115.0, 2, 1),
(3, 3, 100.5, 3, 1),
(4, 4, 100.0, 4, 1),
(5, 2, 115.0, 2, 2),
(6, 3, 100.5, 3, 2),
(7, 4, 100.0, 4, 2),
(8, 5, 90.0, 5, 2),
(9, 3, 100.5, 3, 3),
(10, 4, 100.0, 4, 3),
(11, 5, 90.0, 5, 3),
(12, 6, 30.15, 6, 3),
(13, 3, 100.5, 3, 4),
(14, 4, 100.0, 4, 4),
(15, 5, 90.0, 5, 4),
(16, 6, 30.15, 6, 4),
(17, 3, 100.5, 3, 5),
(18, 4, 100.0, 4, 5),
(19, 5, 90.0, 5, 5),
(20, 6, 30.15, 6, 5),
(21, 4, 100.0, 4, 6),
(22, 5, 90.0, 5, 6),
(23, 6, 30.15, 6, 6),
(24, 7, 80.0, 7, 6);        
CREATE MEMORY TABLE "PUBLIC"."INVOICES"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 7) NOT NULL,
    "CREATED_AT" DATE,
    "TOTAL" FLOAT(53),
    "CLIENT_ID" BIGINT
); 
ALTER TABLE "PUBLIC"."INVOICES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_54" PRIMARY KEY("ID");    
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.INVOICES;
INSERT INTO "PUBLIC"."INVOICES" VALUES
(1, DATE '2023-04-07', 400.0, 1),
(2, DATE '2023-04-08', 450.0, 1),
(3, DATE '2023-04-09', 180.89999999999998, 2),
(4, DATE '2023-04-09', 180.89999999999998, 2),
(5, DATE '2023-04-09', 180.89999999999998, 2),
(6, DATE '2023-04-10', 560.0, 3);               
CREATE MEMORY TABLE "PUBLIC"."PRODUCTS"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 14) NOT NULL,
    "ACTIVE" BOOLEAN,
    "CODE" CHARACTER VARYING(6),
    "DESCRIPTION" CHARACTER VARYING(50),
    "PRICE" FLOAT(53),
    "STOCK" INTEGER
);        
ALTER TABLE "PUBLIC"."PRODUCTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID");     
-- 13 +/- SELECT COUNT(*) FROM PUBLIC.PRODUCTS;               
INSERT INTO "PUBLIC"."PRODUCTS" VALUES
(1, TRUE, '123456', 'Gaseosa cola 1lt', 100.0, 10),
(2, TRUE, '123457', 'Gaseosa lima 1lt', 115.0, 15),
(3, TRUE, '123458', 'Gaseosa pomelo 1lt', 100.5, 20),
(4, TRUE, '123459', 'Gaseosa cola 1lt', 100.0, 10),
(5, TRUE, '123460', 'Papas fritas 1kg', 90.0, 100),
(6, TRUE, '123461', 'Palitos salados 500g', 30.15, 50),
(7, TRUE, '123462', 'Dulce de leche 500g', 80.0, 11),
(8, TRUE, '123463', 'Manteca 500g', 60.0, 15),
(9, TRUE, '123464', 'Dulce de membrillo 1k', 75.0, 13),
(10, TRUE, '123465', 'Yerba mate 1kg', 150.0, 10),
(11, TRUE, '123466', U&'Fideos mo\00f1o 1kg', 80.0, 100),
(12, TRUE, '123467', 'ketchul manhells 500gr', 75.0, 50),
(13, TRUE, '123468', 'asado de unicornio 1kg', 1025.0, 100);            
ALTER TABLE "PUBLIC"."CLIENTS" ADD CONSTRAINT "PUBLIC"."UK_DYALI6YIU88FE1X2BOT4YYLJO" UNIQUE("DOCNUMBER");    
ALTER TABLE "PUBLIC"."PRODUCTS" ADD CONSTRAINT "PUBLIC"."UK_57IVHY5AJ3QFMDVL6VXDFJS4P" UNIQUE("CODE");        
ALTER TABLE "PUBLIC"."INVOICES" ADD CONSTRAINT "PUBLIC"."FK9IOQM804URBGY986PDTWQTL0X" FOREIGN KEY("CLIENT_ID") REFERENCES "PUBLIC"."CLIENTS"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."INVOICE_DETAILS" ADD CONSTRAINT "PUBLIC"."FK439LFPBC6J1K0CN26WTP8F96R" FOREIGN KEY("INVOICE_ID") REFERENCES "PUBLIC"."INVOICES"("ID") NOCHECK;          
ALTER TABLE "PUBLIC"."INVOICE_DETAILS" ADD CONSTRAINT "PUBLIC"."FKCHHYDD0D280RUIG3HMARS76WA" FOREIGN KEY("PRODUCT_ID") REFERENCES "PUBLIC"."PRODUCTS"("ID") NOCHECK;          
