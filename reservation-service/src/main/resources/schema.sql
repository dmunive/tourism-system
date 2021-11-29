CREATE TABLE reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    to_date DATETIME,
    from_date DATETIME,
    number_of_people INT,
    id_owner INT,
    number_of_rooms INT,
    number_of_children INT
);