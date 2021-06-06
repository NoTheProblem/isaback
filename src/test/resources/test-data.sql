INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYSADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');

insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date) values ('ROLE_SYSADMIN','Aleksa','Milenovic','aleksaMilenovic','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE01', 'Paracetamol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE02', 'Pandol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE03', 'Aspirin', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE04', 'Analgin', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');

INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka Jankovic', 'Srbija', 'Novi Sad', 'Narodnog fronta 10', 'Apoteka', 2.0, 1000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka Benu Merkator', 'Srbija', 'Novi Sad', 'Bulevar Oslobođenja 102 ', 'Apoteka', 0.0, 2000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Benu Apoteka', 'Srbija', 'Novi Sad', 'Narodnog fronta 10', 'Apoteka', 1.0, 3000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka 409', 'Srbija', 'Novi Sad', 'Bulevar Mihajla Pupina 9', 'Apoteka', 5.0, 1000.0);

insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, evaluation_grade) values ('ROLE_DERMATOLOGIST','Aleksa','Milenovic','Derma','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska1@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07', 1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id) values ('ROLE_ADMIN','Branislav','Lazarevic','slav','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','branislavlazarevic@gmail.com','Srbija','Sabac','Marsala Tita 140',true, '2021-01-02 18:57:58.508-07',1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Kristijan','Savic','ris','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risfizika@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',1,0);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date,loyalty_score) values ('ROLE_USER','Zorana','Stamenkovic','zor','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','zoranastamenkovic@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07',0);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, evaluation_grade) values ('ROLE_DERMATOLOGIST','Dermatolog','Dermatologovic','Derma99','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska11@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07', 1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Farmaceut','farmaceutic','ris99','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risfizidsfka@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',null ,0);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id) values ('ROLE_ADMIN','Branislav','Lazarevic','slav12','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','branislavlazarevic12@gmail.com','Srbija','Sabac','Marsala Tita 140',true, '2021-01-02 18:57:58.508-07',1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, evaluation_grade) values ('ROLE_SUPPLIER','Aleksa','Milenovic','sup','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','supleksa@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07', 6);

insert into absence_request(request_text, start_date,end_date, status,type_of_absence, employee_id,type_of_employee, employee_name,pharmacy_id) values ('zahtev za godisnji', '2021-01-11', '2021-03-30','nov', 'Godisnji odmor', 4,'ROLE_PHARMACIST', 'Kristijan Savic - ris',1);
insert into absence_request(request_text, start_date,end_date, status,type_of_absence, employee_id,type_of_employee, employee_name,pharmacy_id) values ('zahtev za godisnji', '2021-05-05', '2021-07-07','Odobreno', 'Godisnji odmor', 4,'ROLE_PHARMACIST', 'Kristijan Savic - ris',1);

insert into pharmacy_dermatologist(pharmacy_id, dermatologist_id) values (1,2);

insert into pharmacy_pharmacists(pharmacy_id, pharmacists_id) values(1,4);

insert into promotion(title,text,start_date,end_date,type,pharmacy_id) values('Bekutan promocija','Za sve bekutan proizvode vazi promocija 2+1','2021-01-11','2021-07-30','promocija',1);

