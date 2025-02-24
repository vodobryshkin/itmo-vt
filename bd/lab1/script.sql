-- Удаление старых таблиц (если существуют) и их зависимостей перед созданием новых
DROP TABLE IF EXISTS scientist_observation_ratio CASCADE;
DROP TABLE IF EXISTS scientist CASCADE;
DROP TABLE IF EXISTS scientist_card CASCADE;
DROP TABLE IF EXISTS observation CASCADE;
DROP TABLE IF EXISTS creature CASCADE;
DROP TABLE IF EXISTS species CASCADE;
DROP TABLE IF EXISTS planet CASCADE;
DROP TABLE IF EXISTS galaxy CASCADE;
DROP TABLE IF EXISTS journal_article CASCADE;

-- Удаление старых типов данных (если существуют)
DROP TYPE IF EXISTS PLANET_TYPE CASCADE;
DROP TYPE IF EXISTS SIZE_CHAR CASCADE;
DROP TYPE IF EXISTS COLOR CASCADE;
DROP TYPE IF EXISTS MOOD CASCADE;

-- Создание enum'ов для полей таблицы
CREATE TYPE PLANET_TYPE AS ENUM ('земной', 'газовый гигант', 'карликовая планета');
CREATE TYPE SIZE_CHAR AS ENUM ('огромное', 'большое', 'маленькое', 'очень маленькое');
CREATE TYPE COLOR AS ENUM ('красное', 'оранжевое', 'жёлтое', 'зелёное', 'голубое', 'синее', 'фиолетовое');
CREATE TYPE MOOD AS ENUM ('очень хорошие', 'хорошие', 'смешанные', 'плохие', 'ужасные');

-- Создание таблицы galaxy
CREATE TABLE galaxy (
    galaxy_id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    age BIGINT,
    number_of_planets BIGINT
);

-- Создание таблицы planet
CREATE TABLE planet (
    planet_id SERIAL PRIMARY KEY,
    planet_type PLANET_TYPE,
    name VARCHAR(32) NOT NULL,
    is_life_exists BOOLEAN,
    has_satellite BOOLEAN,
    galaxy_id INTEGER REFERENCES galaxy(galaxy_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL
);

-- Создание таблицы species
CREATE TABLE species (
    species_id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    average_weight REAL,
    average_height REAL,
    is_mammal BOOLEAN
);

-- Создание таблицы creature
CREATE TABLE creature (
    creature_id SERIAL PRIMARY KEY,
    form TEXT NOT NULL,
    color COLOR NOT NULL,
    age BIGINT,
    species_id INTEGER REFERENCES species(species_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы scientist_card
CREATE TABLE scientist_card (
    scientist_card_id SERIAL PRIMARY KEY,
    quality VARCHAR(32),
    start_date_of_work DATE,
    is_nobel_prize_owner BOOLEAN
);

-- Создание таблицы scientist
CREATE TABLE scientist (
    scientist_id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    date_of_birth DATE,
    scientist_card_id INTEGER REFERENCES scientist_card(scientist_card_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL UNIQUE
);

-- Создание таблицы journal_article
CREATE TABLE journal_article (
    journal_article_id SERIAL PRIMARY KEY,
    reviews MOOD,
    start_page SMALLINT NOT NULL,
    end_page SMALLINT NOT NULL,
    got_any_prizes BOOLEAN
);

-- Создание таблицы observation
CREATE TABLE observation (
    observation_id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    budget MONEY,
    planet_id INTEGER REFERENCES planet(planet_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
    creature_id INTEGER REFERENCES creature(creature_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
    journal_article_id INTEGER REFERENCES journal_article(journal_article_id)ON DELETE CASCADE ON UPDATE CASCADE  NOT NULL UNIQUE
);

-- Создание таблицы scientist_observation_ratio
CREATE TABLE scientist_observation_ratio (
    time_start TIME NOT NULL,
    time_end TIME NOT NULL,
    scientist_id INTEGER REFERENCES scientist(scientist_id) ON DELETE CASCADE ON UPDATE CASCADE,
    observation_id INTEGER REFERENCES observation(observation_id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY(observation_id, scientist_id)
);

-- Добавление в таблицу galaxy
INSERT INTO galaxy (name, age, number_of_planets) VALUES 
('Млечный путь', 13600000000, 150000000000),
('Андромеда', 10000000000, 10000000000000),
('Галактика Треугольник', 10000000000, 50000000000),
('Галактика Сомбреро', 9500000000, 120000000000);

-- Добавление в таблицу planet
INSERT INTO planet (galaxy_id, planet_type, name, is_life_exists, has_satellite) VALUES 
(1, 'земной', 'Земля', TRUE, TRUE),
(1, 'газовый гигант', 'Юпитер', FALSE, TRUE),
(2, 'земной', 'Андромеда-IV', TRUE, FALSE),
(3, 'карликовая планета', 'Треугольник-IX', FALSE, FALSE),
(4, 'земной', 'Сомбреро-V', TRUE, TRUE);

-- Добавление в таблицу species
INSERT INTO species (name, average_weight, average_height, is_mammal) VALUES 
('Дракон', 70.5, 175.0, TRUE),
('Летающий орк', 50.0, 200.0, FALSE),
('Космические Хищники', 30.0, 120.0, TRUE),
('Космические пастухи', 80.0, 250.0, FALSE);

-- Добавление в таблицу creature
INSERT INTO creature (species_id, form, color, age) VALUES 
(1, 'аэростат', 'голубое', 30),
(2, 'самолёт', 'зелёное', 100),
(3, 'вертолёт', 'красное', 50),
(4, 'подводная лодка', 'фиолетовое', 200);

-- Добавление в таблицу scientist_card
INSERT INTO scientist_card (quality, start_date_of_work, is_nobel_prize_owner) VALUES 
('высокое', '2006-10-20', TRUE),
('среднее', '2013-06-20', FALSE),
('низкое', '1989-11-18', FALSE),
('высокое', '2023-02-18', TRUE);

-- Добавление в таблицу scientist
INSERT INTO scientist (scientist_card_id, name, surname, date_of_birth) VALUES 
(1, 'Жак', 'Уэбстер II', '1986-10-24'),
(2, 'Раким', 'Маерс', '1988-10-03'),
(3, 'Уолтер', 'Уайт', '1958-09-07'),
(4, 'Обри', 'Грэм', '1986-10-24');

-- Добавление в таблицу journal_article
INSERT INTO journal_article (reviews, start_page, end_page, got_any_prizes) VALUES 
('очень хорошие', 1, 10, TRUE),
('хорошие', 11, 20, TRUE),
('смешанные', 21, 30, TRUE),
('плохие', 31, 40, FALSE);

-- Добавление в таблицу observation
INSERT INTO observation (planet_id, creature_id, journal_article_id, date, budget) VALUES 
(1, 1, 1, '2025-01-01', 100000000000.00),
(2, 2, 2, '2024-01-01', 200000000000.00),
(3, 3, 3, '2023-01-01', 15000000000.00),
(4, 4, 4, '2022-01-01', 2500000000.00);

-- Добавление в таблицу scientist_observation_ratio
INSERT INTO scientist_observation_ratio (time_start, time_end, observation_id, scientist_id) VALUES 
('10:00:00', '12:00:00', 1, 1),
('14:00:00', '16:00:00', 1, 2),
('09:00:00', '11:00:00', 2, 3),
('13:00:00', '15:00:00', 3, 3),
('10:00:00', '12:00:00', 3, 1),
('10:00:00', '12:00:00', 1, 4);