create table resume
(
    uuid varchar not null
        constraint resume_pk
            primary key,
    full_name text not null
);

create table contact
(
    id serial not null
        constraint contact_pk
            primary key,
    resume_uuid varchar not null
        constraint contact_resume_uuid_fk
            references resume
            on update restrict on delete cascade,
    value text not null,
    type text not null
);

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

create table sections
(
    id serial not null
        constraint sections_pk
            primary key,
    resume_uuid VARCHAR not null
        constraint sections_resume_uuid_fk
            references resume
            on update restrict on delete cascade,
    section_type text not null,
    section_name text not null,
    content text not null
);

comment on table sections is 'TextSection & ListSection';


