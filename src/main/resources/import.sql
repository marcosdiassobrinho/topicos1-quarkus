-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
 insert into pessoa (id, nome, cpf) values(nextval('hibernate_sequence'), '05714832167', 'Marcos Dias Sobrinho');