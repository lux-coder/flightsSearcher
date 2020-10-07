DROP TABLE IF EXISTS airports, apiResponse;
CREATE TABLE airports(id serial PRIMARY KEY, iata VARCHAR(255), name VARCHAR(255), location VARCHAR(255));
CREATE TABLE apiresponse(id serial PRIMARY KEY, request TEXT, response JSONB);