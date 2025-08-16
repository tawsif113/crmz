

alter table contacts
    add column email varchar(255),
    add column first_name varchar(255),
    add column last_name varchar(255);

alter table contacts
    drop column user_id;