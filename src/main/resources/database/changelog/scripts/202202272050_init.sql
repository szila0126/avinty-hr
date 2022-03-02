DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE employees
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    email      varchar(255) not null,
    password   varchar(255) not null,
    full_name  varchar(255) not null,
    dep_id     int ,
    created_at timestamp not null,
    created_by int not null,
    updated_at timestamp,
    updated_by int
);

CREATE TABLE departments
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name      varchar(100)  not null,
    manager_id     int  not null,
    created_at timestamp  not null,
    created_by int  not null,
    updated_at timestamp ,
    updated_by int
);

ALTER TABLE employees
    add foreign key (dep_id)
references departments(id);

INSERT INTO departments(id, name, manager_id, created_at, created_by)
VALUES (1, 'Company1', 1, now(), 1);
INSERT INTO departments(id, name, manager_id, created_at, created_by)
VALUES (2, 'Company2', 3, now(), 1);
INSERT INTO departments(id, name, manager_id, created_at, created_by)
VALUES (3, 'Department', 1, now(), 1);

INSERT INTO employees(id, email, password, full_name, dep_id, created_at, created_by)
VALUES (1, 'tt1@tt.com', 'teszt1_pass', 'Teszt Elek', 1, now(), 1);
INSERT INTO employees(id, email, password, full_name, dep_id, created_at, created_by)
VALUES (2, 'tt2@tt.com', 'teszt2_pass', 'Teszt Barna', 1, now(), 1);
INSERT INTO employees(id, email, password, full_name, dep_id, created_at, created_by)
VALUES (3, 'tt3@tt.com', 'teszt3_pass', 'Teszt József', 2, now(), 1);


