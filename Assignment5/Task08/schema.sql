CREATE TABLE bus_pass(
 pass_id VARCHAR(20) PRIMARY KEY, register_no VARCHAR(30) NOT NULL,
 name VARCHAR(100) NOT NULL, route_no INT NOT NULL, boarding_stop VARCHAR(100),
 validity_date DATE NOT NULL, amount_paid DECIMAL(10,2) NOT NULL CHECK(amount_paid>=0)
);
