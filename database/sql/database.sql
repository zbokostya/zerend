create table "user"
(
    id       uuid        not null
        constraint user_pk
            primary key,
    login    varchar(50) not null,
    password varchar(200),
    email    varchar(40)
);

alter table "user"
    owner to zbokostya;

create unique index user_email_uindex
    on "user" (email);

create unique index user_id_uindex
    on "user" (id);

create unique index user_login_uindex
    on "user" (login);

create table role
(
    name varchar(20) not null
        constraint role_pk
            primary key
);

alter table role
    owner to zbokostya;

create unique index role_name_uindex
    on role (name);

create table user_role
(
    user_id   uuid
        constraint user_role_user_id_fk
            references "user",
    user_role varchar(20)
        constraint user_role_role_name_fk
            references role
);

alter table user_role
    owner to zbokostya;

create table project
(
    id    uuid    not null
        constraint project_pk
            primary key,
    name  varchar not null,
    url   varchar not null,
    owner uuid    not null
        constraint project_user_id_fk
            references "user"
);

alter table project
    owner to zbokostya;

create unique index project_id_uindex
    on project (id);

create unique index project_url_uindex
    on project (url);

create table ability
(
    id      uuid    not null
        constraint ability_pk
            primary key,
    name    varchar not null,
    url     varchar not null,
    type    varchar,
    project uuid    not null
        constraint ability_project_id_fk
            references project
);

alter table ability
    owner to zbokostya;

create unique index ability_id_uindex
    on ability (id);

create unique index ability_url_uindex
    on ability (url);


create table apikey
(
    project uuid
        constraint apikey_project_id_fk
            references project,
    apikey  varchar not null,
    role    varchar,
    id      uuid    not null
        constraint apikey_pk
            primary key
);

alter table apikey
    owner to zbokostya;

create unique index apikey_id_uindex
    on apikey (id);

