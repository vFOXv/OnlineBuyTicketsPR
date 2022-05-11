CREATE SCHEMA `new_online_ticket_db` ;

use `new_online_ticket_db` ;

create table users(
                      id	 		bigint(20) not null AUTO_INCREMENT,
                      username	varchar(30) not null,
                      password	varchar(80) not null,
                      email		varchar(50) not null UNIQUE,
                      active		boolean not null,
                      primary key(id)
);

create table roles(
                      id			bigint(20) not null AUTO_INCREMENT,
                      name		varchar(50) not null,
                      primary key(id));

create table users_roles(
                            user_id		bigint(20) not null,
                            role_id		bigint(20) not null,
                            primary key(user_id, role_id),
                            foreign key(user_id) references users(id),
                            foreign key(role_id) references roles(id));

insert into roles(name)
values('ROLE_USER'), ('ROLE_ADMIN');

insert into users(username, password, email, active)
values
    ('user','$2a$12$4GXpzEIsAjROEcBqX2eoneTTyQMLYK3j974OLu9PlcD0lO3x0UEhq', 'user@gmail.com', true),
    ('user1','$2a$12$6y9.MMQw7Tq8CKB.C3TdNuhdkLHWKSzwfbxzQLUi9OHQmjeJ2t8GG', 'user1@gmail.com', true),
    ('admin','$2a$12$SHJ5F9suWxKhgddB6jVgWu/UYyVbbBfPe.z2KUFa147VEQYBd2S/O','admin@gmail.com', true);

insert into users_roles(user_id, role_id) values (1,1),(2,1),(3,2);

CREATE TABLE `new_online_ticket_db`.`bus_flights` (
                                                      `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                      `finish_city` VARCHAR(45) NOT NULL,
                                                      `flight_departure` DATETIME NOT NULL,
                                                      `seats` INT NOT NULL,
                                                      `free_seats` INT(11) NULL,
                                                      PRIMARY KEY (`id`));

INSERT INTO `new_online_ticket_db`.`bus_flights` (`finis_city`, `flight_departure`, `seats`, `free_seats`) VALUES ('Kyiv', '2022-04-25 10:20:00', '30', '27');
INSERT INTO `new_online_ticket_db`.`bus_flights` (`finis_city`, `flight_departure`, `seats`, `free_seats`) VALUES ('Lviv', '2022-04-26 11:10:00', '20', '17');
INSERT INTO `new_online_ticket_db`.`bus_flights` (`finis_city`, `flight_departure`, `seats`, `free_seats`) VALUES ('Odessa', '2022-04-27 09:00:00', '20', '19');

CREATE TABLE `new_online_ticket_db`.`tickets` (
                                                  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                  `place` INT NOT NULL,
                                                  `bus_flight_id` BIGINT(20) NOT NULL,
                                                  `user_id` BIGINT(20) NOT NULL,
                                                  PRIMARY KEY (`id`),
                                                  INDEX `fk_bus_flight_id_idx` (`bus_flight_id` ASC),
                                                  INDEX `fk_user_id_idx` (`user_id` ASC),
                                                  CONSTRAINT `fk_bus_flight_id`
                                                      FOREIGN KEY (`bus_flight_id`)
                                                          REFERENCES `new_online_ticket_db`.`bus_flights` (`id`)
                                                          ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
                                                  CONSTRAINT `fk_user_id`
                                                      FOREIGN KEY (`user_id`)
                                                          REFERENCES `new_online_ticket_db`.`users` (`id`)
                                                          ON DELETE CASCADE
                                                          ON UPDATE CASCADE);

CREATE TABLE `new_online_ticket_db`.`passengers` (
                                                     `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                     `lastname` VARCHAR(45) NOT NULL,
                                                     `name` VARCHAR(45) NOT NULL,
                                                     `ticket_id` BIGINT(20) NOT NULL,
                                                     PRIMARY KEY (`id`),
                                                     INDEX `fk_ticket_id_idx` (`ticket_id` ASC),
                                                     CONSTRAINT `fk_ticket_id`
                                                         FOREIGN KEY (`ticket_id`)
                                                             REFERENCES `new_online_ticket_db`.`tickets` (`id`)
                                                             ON DELETE CASCADE
                                                             ON UPDATE CASCADE);

INSERT INTO `new_online_ticket_db`.`tickets` (`place`, `bus_flight_id`, `user_id`) VALUES ('1', '1', '1');
INSERT INTO `new_online_ticket_db`.`tickets` (`place`, `bus_flight_id`, `user_id`) VALUES ('2', '1', '1');
INSERT INTO `new_online_ticket_db`.`tickets` (`place`, `bus_flight_id`, `user_id`) VALUES ('3', '1', '1');
INSERT INTO `new_online_ticket_db`.`tickets` (`place`, `bus_flight_id`, `user_id`) VALUES ('1', '2', '3');
INSERT INTO `new_online_ticket_db`.`tickets` (`place`, `bus_flight_id`, `user_id`) VALUES ('2', '2', '3');
INSERT INTO `new_online_ticket_db`.`tickets` (`place`, `bus_flight_id`, `user_id`) VALUES ('3', '2', '3');
INSERT INTO `new_online_ticket_db`.`tickets` (`place`, `bus_flight_id`, `user_id`) VALUES ('4', '3', '2');

INSERT INTO `new_online_ticket_db`.`passengers` (`lastname`, `name`, `ticket_id`) VALUES ('LastName_1', 'Name_1', '1');
INSERT INTO `new_online_ticket_db`.`passengers` (`lastname`, `name`, `ticket_id`) VALUES ('LastName_2', 'Name_2', '2');
INSERT INTO `new_online_ticket_db`.`passengers` (`lastname`, `name`, `ticket_id`) VALUES ('LastName_3', 'Name_3', '3');
INSERT INTO `new_online_ticket_db`.`passengers` (`lastname`, `name`, `ticket_id`) VALUES ('LastName_4', 'Name_4', '4');
INSERT INTO `new_online_ticket_db`.`passengers` (`lastname`, `name`, `ticket_id`) VALUES ('LastName_5', 'Name_5', '5');
INSERT INTO `new_online_ticket_db`.`passengers` (`lastname`, `name`, `ticket_id`) VALUES ('LastName_6', 'Name_6', '6');
INSERT INTO `new_online_ticket_db`.`passengers` (`lastname`, `name`, `ticket_id`) VALUES ('LastName_7', 'Name_7', '7');