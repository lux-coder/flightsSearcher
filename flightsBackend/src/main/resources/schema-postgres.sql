DROP TABLE IF EXISTS airports, apiResponse;
CREATE TABLE airports(id serial PRIMARY KEY, iata VARCHAR(255), name VARCHAR(555), location VARCHAR(255), country VARCHAR(255));
CREATE TABLE apiresponse(id serial PRIMARY KEY, request TEXT, response JSONB);
COPY airports(iata, name, location, country) FROM 'F:\\airports.csv' DELIMITER ',' CSV HEADER;
