CREATE USER part_login WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'part_password';

CREATE DATABASE parts
    WITH
    OWNER = part_login
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE public.parts
(
    part_name character varying(200),
    part_number character varying(100),
    vendor character varying(200),
    qty integer,
    shipped date,
    receive date
);

ALTER TABLE public.parts OWNER to part_login;