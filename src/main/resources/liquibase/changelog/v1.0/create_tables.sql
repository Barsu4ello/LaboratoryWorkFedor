create table roles
(
    id           serial PRIMARY KEY,
    role varchar(256) NOT NULL
);

create table countries
(
    id           bigserial PRIMARY KEY,
    country_name varchar(256) NOT NULL
);

create table cities
(
    id         bigserial PRIMARY KEY,
    city_name  varchar(256) NOT NULL,
    country_id bigint       NOT NULL,
    FOREIGN KEY (country_id) REFERENCES countries (id)
);

create table concerts
(
    id           bigserial PRIMARY KEY,
    title        varchar(256) NOT NULL,
    performer    varchar(256) NOT NULL,
    concert_date date         NOT NULL,
    city_id      bigint       NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities (id)
);

create table subscriptions
(
    id          bigserial PRIMARY KEY,
    name        varchar(256) NOT NULL,
    price       int          NOT NULL,
    description text         NOT NULL
);

create table authors
(
    id          bigserial PRIMARY KEY,
    name        varchar(256) NOT NULL,
    description varchar      NOT NULL
);

create table audios
(
    id        bigserial PRIMARY KEY,
    title     varchar(256) NOT NULL,
--     Потом поставить NOT NULL
    audio     bytea default NULL,
    author_id bigint       NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

create table users
(
    id          bigserial PRIMARY KEY,
    username    varchar(256) NOT NULL,
    password    varchar(256) NOT NULL,
    user_status varchar(128) NOT NULL,
    city_id     bigint      ,
    author      bigint      ,
    role      int      ,
    FOREIGN KEY (role) REFERENCES roles (id),
    FOREIGN KEY (city_id) REFERENCES cities (id),
    FOREIGN KEY (author) REFERENCES authors (id)
);

create table tickets
(
    id         bigserial PRIMARY KEY,
    price      int    NOT NULL,
    concert_id bigint NOT NULL,
    user_id    bigint NOT NULL,
    FOREIGN KEY (concert_id) REFERENCES concerts (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

create table user_playlists
(
    id          bigserial PRIMARY KEY,
    title       varchar(256) NOT NULL,
    description varchar(256) NOT NULL,
    user_id     bigint       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

create table user_subscriptions
(
    id              bigserial PRIMARY KEY,
    is_valid        boolean NOT NULL,
    host_user_id    bigint,
    subscription_id bigint  NOT NULL,
    FOREIGN KEY (host_user_id) REFERENCES users (id),
    FOREIGN KEY (subscription_id) REFERENCES subscriptions (id)
);

create table uploaded_by_users
(
    user_id  bigserial,
    audio_id bigserial,
    PRIMARY KEY (user_id, audio_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (audio_id) REFERENCES audios (id)
);

