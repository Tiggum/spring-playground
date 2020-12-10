# setup.sql

# create the table
CREATE TABLE `cars` (
    `car_id` BIGINT NOT NULL AUTO_INCREMENT,
    `year` INTEGER NULL,
    `make` VARCHAR(50) NULL,
    `model` VARCHAR(50) NULL,
    `style` VARCHAR(50) NULL,
    PRIMARY KEY (`car_id`)
);

# insert the data
INSERT INTO cars (year, make, model, style) VALUES (1962, 'Ford', 'Mustang', 'MUSCLE CAR');
INSERT INTO cars (year, make, model, style) VALUES (1966, 'Alfa Romeo', 'Spider', 'SPORTS CAR');
INSERT INTO cars (year, make, model, style) VALUES (1959, 'Cadillac', 'Eldorado', 'SEDAN');
INSERT INTO cars (year, make, model, style) VALUES (2012, 'Jeep', 'Wrangler', 'SUV');
INSERT INTO cars (year, make, model, style) VALUES (2020, 'Tesla', 'Model 3', 'ELECTRIC SEDAN');