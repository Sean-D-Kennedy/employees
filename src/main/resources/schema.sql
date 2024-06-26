CREATE TABLE IF NOT EXISTS `employee` (
    `id` int AUTO_INCREMENT  PRIMARY KEY,
    `first_name` varchar(100) NOT NULL,
    `surname` varchar(100) NOT NULL,
    `dept` varchar(100) NOT NULL,
    `age` integer NOT NULL,
    `staff_id` integer NOT NULL
    );
