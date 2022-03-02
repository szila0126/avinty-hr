CREATE TABLE  role (
    id int not null,
    name varchar(45) not null
);

CREATE TABLE user_roles (
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references employees(id),
    foreign key (role_id) references role(id)
);
INSERT INTO role(id, name) VALUES ( 1,'ADMIN' );
INSERT INTO role(id, name) VALUES ( 2,'USER' );
INSERT INTO user_roles(user_id, role_id) VALUES ( 1,1 );
INSERT INTO user_roles(user_id, role_id) VALUES ( 2,2 );
