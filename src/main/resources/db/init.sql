create table if not exists wallet (
    id serial primary key,
    name varchar(50) not null unique,
    balance integer not null
);

create table if not exists transactions (
    id serial primary key,
    transaction_type varchar(10) not null,
    amount integer not null,
    wallet_id integer references wallet(id) not null,
    created_at timestamp not null default now()
);