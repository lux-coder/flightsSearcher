DROP TABLE IF EXISTS airports;
CREATE TABLE airports(id serial PRIMARY KEY, iata VARCHAR(255), name VARCHAR(255), location VARCHAR(255));