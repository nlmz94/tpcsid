drop table IF EXISTS repository;
drop table IF EXISTS stats;

CREATE TABLE repository(
	name varchar(255),
	owner varchar(255),
	issues Integer,
	forks Integer
);

CREATE TABLE stats(
	id serial,
	repository_name varchar(128),
	entry_type varchar(32),
	date_time varchar(128),
	value Integer,
	PRIMARY KEY(id)
);

insert into repository values('romain', 'student', 10, 5);
insert into repository values('geoffrey', 'professor', 25, 2000);
insert into repository values('tpspring', 'nlmz94', 420, 69);
insert into stats values(default, 'tpspring','issues', 'je connais pas le format date_time', 123);

/* psql -h localhost -U root -d springdb */