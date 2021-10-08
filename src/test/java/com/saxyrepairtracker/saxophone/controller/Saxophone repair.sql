-- DROP DATABASE repairsaxophone;
-- CREATE DATABASE repairsaxophone;
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
status enum('AWAITING ARRIVAL', 'WAITING FOR REPAIR', 'BEING REPAIRED', 
			'WAITING FOR PARTS', 'READY FOR PICK-UP', 'PICKED-UP') NOT NULL,
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
labor_hours decimal(2, 2),
repair_type enum('CLEANING', 'TUNE-UP', 'OVERHAUL', 'RESTORATION', 'REPAIR'),
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
)