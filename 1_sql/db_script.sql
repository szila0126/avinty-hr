--PostgreSQL

CREATE TABLE hr_code
(
    id          serial       not null,
    codebook    int          not null,
    code        int          not null,
    name        varchar(255) not null,
    created_by  int          not null,
    created_at  timestamp    not null,
    modified_by int,
    modified_at timestamp,
    CONSTRAINT pk_hr_code primary key (id),
    CONSTRAINT uc_hr_code unique (code, codebook)
);
CREATE TABLE hr_address
(
    id           serial,
    postcode     varchar(4)  not null,
    city         varchar(60) not null,
    street       varchar(45) not null,
    house_number int         not null,
    created_by   int         not null,
    created_at   timestamp   not null,
    modified_by  int,
    modified_at  timestamp,
    CONSTRAINT pk_hr_address primary key (id)
);
CREATE TABLE hr_patients
(
    id             serial,
    first_name     varchar(100) not null,
    last_name      varchar(255) not null,
    mothers_name   varchar(255) not null,
    sex_id         int          not null,
    date_of_birth  date         not null,
    date_of_death  date,
    place_of_birth varchar(50)  not null,
    address_id     bigint       not null,
    email          varchar(255) not null,
    created_by     int          not null,
    created_at     timestamp    not null,
    modified_by    int,
    modified_at    timestamp,
    CONSTRAINT pk_hr_patient PRIMARY KEY (id),
    CONSTRAINT fk_hr_patient_address FOREIGN KEY (address_id)
        references hr_address (id),
    CONSTRAINT fk_hr_patient_sex FOREIGN KEY (sex_id)
        references hr_code (id),
    constraint uc_hr_patient_email unique (email),
    constraint uc_hr_patient_ unique (first_name, last_name, mothers_name, date_of_birth, place_of_birth)
);

CREATE TABLE hr_relationship
(
    id                       serial    not null,
    current_patient_id       int       not null,
    relationship_patient_id  int       not null,
    type_id                  int       not null,
    quality_id               int       not null,
    distance                 smallint  not null,
    start_date_of_connection date      not null,
    end_date_of_connection   date,
    created_by               int       not null,
    created_at               timestamp not null,
    modified_by              int,
    modified_at              timestamp,
    constraint pk_hr_relationship primary key (id),
    constraint fk_hr_relationship_type foreign key (type_id)
        references hr_code (id),
    constraint fk_hr_relationship_quality foreign key (quality_id)
        references hr_code (id),
    constraint fk_hr_relationship_cur_patient foreign key (current_patient_id)
        references hr_patients (id),
    constraint fk_hr_relationship_patient foreign key (relationship_patient_id)
        references hr_patients (id)
);
alter table hr_relationship
    add constraint chk_hr_rel_distance
        check (distance between 1 and 10);
CREATE INDEX IF NOT EXISTS ix_hr_code_id on hr_code (id);
CREATE INDEX IF NOT EXISTS ix_hr_code_code on hr_code (code);
CREATE INDEX IF NOT EXISTS ix_hr_code_name on hr_code (name);
CREATE INDEX IF NOT EXISTS ix_hr_code_codebook on hr_code (codebook);

CREATE INDEX IF NOT EXISTS ix_hr_patients_id on hr_patients (id);
CREATE INDEX IF NOT EXISTS ix_hr_patients_email on hr_patients (email);
CREATE INDEX IF NOT EXISTS ix_hr_patients_first_name on hr_patients (first_name);
CREATE INDEX IF NOT EXISTS ix_hr_patients_last_name on hr_patients (last_name);

CREATE INDEX IF NOT EXISTS ix_hr_relationship_id on hr_relationship (id);
CREATE INDEX IF NOT EXISTS ix_hr_relationship_quiality on hr_relationship (quality_id);
CREATE INDEX IF NOT EXISTS ix_hr_relationship_type on hr_relationship (type_id);
CREATE INDEX IF NOT EXISTS ix_hr_relationship_start on hr_relationship (start_date_of_connection);

--teszt adat ha kellene
INSERT INTO partner.hr_code ( codebook, code, name, createdby, createdat, modifiedby, modifiedat) VALUES ( 1, 1, 'Male', 1,  now(), null, null);
INSERT INTO partner.hr_code ( codebook, code, name, createdby, createdat, modifiedby, modifiedat) VALUES ( 1, 2, 'Female', 1,  now(), null, null);
INSERT INTO partner.hr_code ( codebook, code, name, createdby, createdat, modifiedby, modifiedat) VALUES ( 2, 1, 'Parent', 1,  now(), null, null);
INSERT INTO partner.hr_code ( codebook, code, name, createdby, createdat, modifiedby, modifiedat) VALUES ( 2, 2, 'Sibling', 1,  now(), null, null);
INSERT INTO partner.hr_code ( codebook, code, name, createdby, createdat, modifiedby, modifiedat) VALUES ( 2, 3, 'Neighbor', 1,  now(), null, null);
INSERT INTO partner.hr_code ( codebook, code, name, createdby, createdat, modifiedby, modifiedat) VALUES ( 3, 1, 'positive', 1,  now(), null, null);
INSERT INTO partner.hr_code ( codebook, code, name, createdby, createdat, modifiedby, modifiedat) VALUES ( 3, 2, 'negative', 1,  now(), null, null);

INSERT INTO partner.hr_address ( postcode, city, street, house_number, createdby, createdat, modifiedby, modifiedat) VALUES ( '7700', 'Mohács', 'Erika', 3, 1,  now(), null, null);
INSERT INTO partner.hr_address ( postcode, city, street, house_number, createdby, createdat, modifiedby, modifiedat) VALUES ( '7632', 'Pécs', 'Jókai', 2, 1, now(), null, null);

INSERT INTO partner.hr_patients ( first_name, last_name, mothers_name, sex_id, date_of_birth, date_of_death, place_of_birth, address_id, email, createdby, createdat, modifiedby, modifiedat) VALUES ( 'Teszt', 'Mátyás', 'Eva', 1, '1994-02-11', null, 'Pécs', 1, 't@tt.com', 1, now(), null, null);
INSERT INTO partner.hr_patients ( first_name, last_name, mothers_name, sex_id, date_of_birth, date_of_death, place_of_birth, address_id, email, createdby, createdat, modifiedby, modifiedat) VALUES ( 'Teszt', 'Éva', 'Ildikó', 3, '1998-06-22', null, 'Pécs', 1, 't1@tt.com', 1, now(), null, null);
INSERT INTO partner.hr_patients ( first_name, last_name, mothers_name, sex_id, date_of_birth, date_of_death, place_of_birth, address_id, email, createdby, createdat, modifiedby, modifiedat) VALUES ( 'Teszt', 'Elek', 'Maria', 1, '1994-02-28', null, 'Mohács', 1, 't@t.com', 1, now(), null, null);

--3.task
CREATE OR REPLACE FUNCTION hrStartDateOfConnection()
    RETURNS trigger AS
$$
declare
    dateOfBirth date;
BEGIN
    IF NEW.current_patient_id IS NOT NULL THEN
        select date_of_birth into dateOfBirth from hr_patients where id = NEW.current_patient_id;
        NEW.start_date_of_connection := dateOfBirth;
    END IF;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;
create trigger hr_relationship_before
    before insert or update
    on hr_relationship
    for each row
execute procedure hrStartDateOfConnection();

--4.task
CREATE OR REPLACE FUNCTION hrDateOfDeath()
    RETURNS trigger AS
$$
declare
    rlShip hr_relationship%ROWTYPE;
BEGIN
    IF NEW.date_of_death IS NOT NULL THEN
        FOR rlShip IN select * from hr_relationship where current_patient_id = NEW.id
            LOOP
                IF rlShip.end_date_of_connection is null or rlShip.end_date_of_connection > new.date_of_death THEN
                    UPDATE hr_relationship r
                    set end_date_of_connection =new.date_of_death,
                        modified_at=now()
                    WHERE r.id = rlShip.id;
                end if;
            end loop;
    END IF;

    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;
create trigger hr_patient_after
    after insert or update
    on hr_patients
    for each row
execute procedure hrDateOfDeath();
