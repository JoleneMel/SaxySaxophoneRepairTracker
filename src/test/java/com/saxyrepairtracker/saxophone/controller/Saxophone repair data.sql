-- DROP DATABASE repairsaxophone;
use repairsaxophone;

DROP TABLE IF EXISTS service_line_item_status;
DROP TABLE IF EXISTS service_line_item;
DROP TABLE IF EXISTS service_ticket;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS saxophones;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  phone varchar(20),
  PRIMARY KEY (customer_pk)
);
CREATE TABLE saxophones (
  saxophones_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_fk int unsigned NOT NULL,
  serial_number int NOT NULL,
  manufacturer varchar(40) NOT NULL,
  series varchar(50),
  type enum ('SOPRANO', 'ALTO', 'TENOR', 'BARITONE', 'OTHER') NOT NULL,
  PRIMARY KEY (saxophones_pk),
  FOREIGN KEY (customer_fk) REFERENCES customer(customer_pk),
  UNIQUE KEY (serial_number, manufacturer, series, type)
);
CREATE TABLE employee (
employee_pk int unsigned NOT NULL AUTO_INCREMENT,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
pay_rate decimal(9, 2) NOT NULL,
PRIMARY KEY (employee_pk)
);
CREATE TABLE service_ticket (
service_pk int unsigned NOT NULL AUTO_INCREMENT,
customer_fk int unsigned NOT NULL,
description varchar(400) NOT NULL,
status enum('AWAITING_ARRIVAL', 'WAITING_FOR_REPAIR', 'BEING_REPAIRED',
			'WAITING_FOR_PARTS', 'READY_FOR_PICKUP', 'PICKED_UP') NOT NULL,
estimated_cost decimal(9, 2) NOT NULL,
actual_cost decimal(9, 2),
PRIMARY KEY (service_pk),
FOREIGN KEY (customer_fk) REFERENCES customer(customer_pk)
-- UNIQUE KEY (service_pk, description, status, estimated_cost, actual_cost)
);
CREATE TABLE service_line_item (
line_item_pk int unsigned NOT NULL AUTO_INCREMENT,
service_fk int unsigned NOT NULL,    -- unsigned = 0 to 4294967294
saxophones_fk int unsigned NOT NULL, -- signed  = -2147483647 to 2147483647
employee_fk int unsigned,
labor_hours decimal(7, 2),
repair_type enum('CLEANING', 'TUNE_UP', 'OVERHAUL', 'RESTORATION', 'REPAIR'),
part_cost decimal(9, 2),
additional_fees decimal (9, 2),
total_cost decimal(9, 2),
PRIMARY KEY (line_item_pk),
FOREIGN KEY (employee_fk) REFERENCES employee(employee_pk),
FOREIGN KEY (saxophones_fk) REFERENCES saxophones(saxophones_pk),
FOREIGN KEY (service_fk) REFERENCES service_ticket(service_pk)
);
CREATE TABLE service_line_item_status (
update_pk int unsigned NOT NULL AUTO_INCREMENT,
service_fk int unsigned NOT NULL,
line_item_fk int unsigned NOT NULL,
updates varchar(400) NOT NULL,
update_time timestamp default current_timestamp,
Primary Key (update_pk),
FOREIGN KEY (line_item_fk) REFERENCES service_line_item(line_item_pk),
FOREIGN KEY (service_fk) REFERENCES service_ticket(service_pk)
);


INSERT INTO customer (first_name, last_name, phone) VALUES('Kim', 'Kimminy', '755.223.5969');
INSERT INTO customer (first_name, last_name, phone) VALUES('Jorbjorn', 'Lindholm', '256.399.4665');
INSERT INTO customer (first_name, last_name, phone) VALUES('Reinhardt', 'Wilhelm', NULL);
INSERT INTO customer (first_name, last_name, phone) VALUES('Brigette', 'Lindholm', '328.993.3774');
INSERT INTO customer ( first_name, last_name, phone) VALUES('Zenyatta', 'Mandatta', '399.377.3854');
INSERT INTO customer (first_name, last_name, phone) VALUES('Bom', 'Trady', NULL);
INSERT INTO customer ( first_name, last_name, phone) VALUES('Bell', 'Billacheck', '647.399.2610');
INSERT INTO customer ( first_name, last_name, phone) VALUES('Dave', 'Lee', '219.355.6407');
INSERT INTO customer ( first_name, last_name, phone) VALUES('Bert', 'Ernie', '766.388.3663');
INSERT INTO customer ( first_name, last_name, phone) VALUES('James', 'Earl', '635,968.2611');
INSERT INTO customer (first_name, last_name, phone) VALUES('Irene', 'Ely', NULL);
INSERT INTO customer (first_name, last_name, phone) VALUES('Stephanie', 'Lynn', '766.958.3995');
INSERT INTO customer (first_name, last_name, phone) VALUES('Norm', 'Ely', '133.846.2775');
INSERT INTO customer (first_name, last_name, phone) VALUES('Gavin', 'Van', '733.378.9511');
INSERT INTO customer (first_name, last_name, phone) VALUES('Garth', 'Brocks', '312.753.9994');

INSERT INTO saxophones (customer_fk, serial_number, manufacturer, series , type)  VALUES (1, 15236654, 'Yamaha', 'Custom Z', 'TENOR');
INSERT INTO saxophones (customer_fk, serial_number, manufacturer, series , type)  VALUES (2, 4561323, 'Selmer', 'Custom', 'ALTO');
INSERT INTO saxophones (customer_fk, serial_number, manufacturer, series , type)  VALUES (3, 458226565, 'Yamaha', 'Yazz', 'SOPRANO');
INSERT INTO saxophones (customer_fk, serial_number, manufacturer, series , type)  VALUES (4, 954151233, 'Yamaha', 'Student', 'BARITONE');
INSERT INTO saxophones (customer_fk, serial_number, manufacturer, series , type)  VALUES (5, 75369852, 'Selmer', '65', 'TENOR');

INSERT INTO employee (first_name, last_name, pay_rate)  VALUES ( 'David', 'Bailey', 35);
INSERT INTO employee ( first_name, last_name, pay_rate)  VALUES ( 'John', 'Smith', 22);
INSERT INTO employee ( first_name, last_name, pay_rate)  VALUES ( 'Sam', 'James', 21);
INSERT INTO employee ( first_name, last_name, pay_rate)  VALUES ( 'Brent', 'Jamie', 18);
INSERT INTO employee ( first_name, last_name, pay_rate)  VALUES ( 'Nate', 'Great', 15);
INSERT INTO employee ( first_name, last_name, pay_rate)  VALUES ( 'Alison', 'Abbes', 25);
INSERT INTO employee ( first_name, last_name, pay_rate)  VALUES ( 'Charlie', 'Chop', 20);

INSERT INTO service_ticket ( customer_fk, description, status, estimated_cost) VALUES (4, 'things are sticking', 'WAITING_FOR_REPAIR', '45');
INSERT INTO service_ticket ( customer_fk, description, status, estimated_cost) VALUES (3, 'needs new pads', 'BEING_REPAIRED', '85');
INSERT INTO service_ticket ( customer_fk, description, status, estimated_cost) VALUES (5, 'Broken', 'AWAITING_ARRIVAL', '75');
INSERT INTO service_ticket ( customer_fk, description, status, estimated_cost) VALUES (6, 'key fell off', 'WAITING_FOR_PARTS', '90');
INSERT INTO service_ticket ( customer_fk, description, status, estimated_cost, actual_cost) VALUES (7, 'cleaning', 'PICKED_UP', '25', '25');
INSERT INTO service_ticket ( customer_fk, description, status, estimated_cost, actual_cost) VALUES (1, 'key bent fell off', 'READY_FOR_PICKUP', '85', '115');

INSERT INTO service_line_item (service_fk, saxophones_fk, employee_fk, labor_hours, repair_type, part_cost, additional_fees, total_cost) 
VALUES (1, 1, 1, 1, 'CLEANING', 12.00, 13.00, 25.00);
INSERT INTO service_line_item (service_fk, saxophones_fk, employee_fk, labor_hours, repair_type, part_cost, additional_fees, total_cost) 
VALUES (2, 5, 3, 1, 'TUNE_UP', 22.00, 34.00, 56.00);
INSERT INTO service_line_item (service_fk, saxophones_fk, employee_fk, labor_hours, repair_type, part_cost, additional_fees, total_cost) 
VALUES (3, 3, 2, 7, 'OVERHAUL', 150.00, 45.00, 195.00);
INSERT INTO service_line_item (service_fk, saxophones_fk, employee_fk, labor_hours, repair_type, part_cost, additional_fees, total_cost) 
VALUES (4, 2, 4, 9, 'RESTORATION', 120.00, 124.00, 244.00);
INSERT INTO service_line_item (service_fk, saxophones_fk, employee_fk, labor_hours, repair_type, part_cost, additional_fees, total_cost) 
VALUES (5, 4, 6, 2, 'REPAIR', 20.00, 34.00, 54.00);
SELECT * FROM saxophones;