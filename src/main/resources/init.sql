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

INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 7', '56H212-01', 'CH-DAL', 64, '2007-03-06', '2007-03-06');
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 7', '56H212-01', 'CH-DAL', 64, '2007-03-06', null);
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-01', 'CH-DAL', 64, '2007-03-06', '2007-03-06');
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-01', 'CH-DAL', 64, '2007-03-06', null);
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-02', 'CH-DAL', 64, '2007-03-06', '2007-03-06');
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-02', 'CH-DAL', 64, '2007-03-06', null);
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-01', 'CH-MAL', 64, '2007-03-06', '2007-03-06');
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-01', 'CH-MAL', 64, '2007-03-06', null);
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-01', 'CH-MAL', 30, '2007-03-06', '2007-03-06');
INSERT INTO public.parts(part_name, part_number, vendor, qty, shipped, receive) VALUES('HPC Blade 8', '56H212-01', 'CH-MAL', 30, '2007-03-06', null);