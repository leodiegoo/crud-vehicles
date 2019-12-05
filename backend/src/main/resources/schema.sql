DROP TABLE IF EXISTS VEHICLE_TYPE, VEHICLE;


create table if not exists VEHICLES_TYPES
(
    ID     bigint primary key auto_increment not null,
    "DESC" VARCHAR(255),
    NAME   VARCHAR(255)
);

create table if not exists  VEHICLES
(
    ID              bigint not null
        constraint vehicles_pk
            primary key auto_increment,
    "DESC"          varchar(255),
    ID_VEHICLE_TYPE bigint,
    NAME            varchar(255),
    PLATE           varchar(255)
);

