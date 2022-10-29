insert into roles (role)
values
    ('ADMIN'),('USER');

insert into countries (country_name)
values
('Russian'),('America');

insert into cities (city_name, country_id)
values
    ('SPB', 1), ('Moscow', 1),
    ('LA', 2), ('New York', 2);

insert into concerts (title, performer, concert_date, city_id)
values
    ('First LP Concert', 'Linkin Park', '01.01.2000',1),
    ('Second LP Concert', 'Linkin Park', '01.01.2001',2),
    ('First TDG Concert', 'Three Days Grace', '01.06.2001',3),
    ('Second TDG Concert', 'Three Days Grace', '01.02.2003',4);

insert into subscriptions (name, price, description)
values
    ('First subscription', 1000, 'First subscription description'),
    ('Second subscription', 2000, 'Second subscription description'),
    ('Third subscription', 500, 'Third subscription description'),
    ('Fourth subscription', 1500, 'Fourth subscription description');

insert into authors (name, description)
values
    ('Vlad', 'Vlad description'),
    ('Fedor', 'Fedor description'),
    ('Pavel', 'Pavel description');

insert into audios (title, author_id)
values
    ('First audio', 1),
    ('Second audio', 2),
    ('Third audio', 3),
    ('Fourth audio', 3);

insert into users (username, password, user_status, role, city_id, author)
values
    ('Aleksandr', 'Aleksandr', 'ACTIVE', 1, 1, 1),
    ('Danil', 'Danil', 'ACTIVE', 2, 2, 1),
    ('Egor', 'Egor', 'ACTIVE', 2, 3, 2),
    ('Anna', 'Anna', 'ACTIVE', 2, 4, 3);

insert into tickets (price, concert_id, user_id)
values
    (200, 1, 1),
    (300, 2, 1),
    (300, 2, 2),
    (250, 3, 3),
    (100, 4, 4);

insert into user_playlists (title, description, user_id)
values
    ('Aleksandr playlist1', 'Aleksandr playlist', 1),
    ('Boris playlist2', 'Boris playlist', 2),
    ('Danil playlist', 'Danil playlist', 3),
    ('Egor playlist', 'Egor playlist', 4);

insert into user_subscriptions (is_valid, host_user_id, subscription_id)
values
    (true, 1, 1),
    (true, 2, 1),
    (true, 3, 3),
    (true, 4, 4);

insert into uploaded_by_users (user_id, audio_id)
values
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 3),
    (4, 1),
    (4, 4);
