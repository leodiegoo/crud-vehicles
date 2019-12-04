DROP TABLE IF EXISTS VEHICLE_TYPE, VEHICLE;


create table VEHICLE_TYPE
(
    ID     bigint,
    "DESC" VARCHAR(255),
    NAME   VARCHAR(255)
);

create unique index VEHICLE_TYPE_ID_uindex
    on VEHICLE_TYPE (ID);

alter table VEHICLE_TYPE
    add constraint VEHICLE_TYPE_pk
        primary key (ID);

create table VEHICLE
(
    ID              bigint not null
        constraint vehicle_pk
            primary key
        constraint vehicle_vehicle_type_id_fk
            references vehicle_type,
    "DESC"          varchar(255),
    ID_VEHICLE_TYPE integer,
    NAME            varchar(255),
    PLATE           varchar(255)
);

alter table VEHICLE owner to postgres;

create unique index VEHICLE_ID_uindex
    on VEHICLE (ID);
