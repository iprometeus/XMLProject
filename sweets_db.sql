CREATE 	DATABASE IF NOT EXISTS sweets;

USE sweets;

CREATE TABLE type (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE value (
	id INT NOT NULL AUTO_INCREMENT,
	proteins INT,
	carbohydrates INT,
	fats INT,
    PRIMARY KEY (id)
);

CREATE TABLE chocolate (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE ingredients (
	id INT NOT NULL AUTO_INCREMENT,
	water INT,
	sugar INT,
	fruit_sugar INT,
	vanillin INT,
	chocolate_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (chocolate_id) REFERENCES chocolate(id)
);

CREATE TABLE production (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE candy (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	energy INT,
	type_id INT,
	ingredients_id INT,
	value_id INT,
	production_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (type_id) REFERENCES type(id),
    FOREIGN KEY (ingredients_id) REFERENCES ingredients(id),
    FOREIGN KEY (value_id) REFERENCES value(id),
    FOREIGN KEY (production_id) REFERENCES production(id)
);