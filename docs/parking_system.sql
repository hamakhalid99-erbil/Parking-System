create table if not exists customer
(
    id         varchar(255) not null
        primary key,
    balance    integer      not null,
    email      varchar(355) not null
        constraint uk_dwk6cx0afu8bs9o4t536v1j5v
            unique,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    pass       varchar(255) not null
);

create table if not exists floor
(
    floor_number   integer not null
        primary key,
    floor_capacity integer not null
);

create table if not exists report
(
    uuid         serial
        primary key,
    date         timestamp not null,
    total_amount integer   not null,
    total_cars   integer   not null
);

create table if not exists slot
(
    floor       integer not null,
    slot_number integer not null,
    status      integer not null
);

create table if not exists ticket
(
    id            varchar(255) not null
        primary key,
    created_time  timestamp    not null,
    price         integer      not null,
    ticket_status integer      not null
);

create table if not exists receipt
(
    uuid         varchar(128) not null
        primary key,
    total_amount integer      not null,
    description  varchar(255) not null,
    created_time timestamp    not null
);
