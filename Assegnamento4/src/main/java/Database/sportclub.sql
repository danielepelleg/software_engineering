drop database if exists sportclub;
create database if not exists sportclub;
use sportclub;

drop table if exists course;
create table course (
    name varchar(24) primary key);

insert into course(name) VALUES
#       new SportClub.Course("Jnana Yoga")
('Jnana Yoga');

drop table if exists race;
create table race(
    name varchar(24) primary key);

insert into race(name) VALUES
#       new SportClub.Race("Tour de France")
('Tour de France');

drop table if exists member;
create table member(
    name varchar(24),
    surname varchar(24),
    username varchar(24) primary key,
    hashed_password varchar(32));

insert into member(name, surname, username, hashed_password) VALUES
('Tommaso', 'Boni', 'Tom', '827ccb0eea8a706c4c34a16891f84e7b'),
#       new SportClub.Member("Tommaso", "Boni", "Tom", "12345")
('Luca', 'Perini', 'Luke', 'c37bf859faf392800d739a41fe5af151');
#       new SportClub.Member("Luca", "Perini", "Luke", "98765")

drop table if exists administrator;
create table administrator(
    name varchar(24),
    surname varchar(24),
    username varchar(24) primary key,
    hashed_password varchar(32));

insert into administrator(name, surname, username, hashed_password) VALUES
('Giacomo', 'Neri', 'Jack', '5cc7a8cad0c3ef6834ff6bd9f734e741'),
#       new SportClub.Admin("Giacomo", "Neri", "Jack", "hardtoguess")
('Chiara', 'Zanetti', 'Chicca', 'aa7dcd799df5136c89931152a274c449');
#       new SportClub.Admin("Chiara", "Zanetti", "Chicca", "hardtofind")

drop table if exists activity_course;
create table activity_course(
    course_name varchar(24),
    member_username varchar(24),
    foreign key (course_name) references course(name),
    foreign key  (member_username) references member(username),
    primary key (course_name, member_username));

drop table if exists activity_race;
create table activity_race(
    race_name varchar(24),
    member_username varchar(24),
    foreign key (race_name) references race(name),
    foreign key  (member_username) references member(username),
    primary key (race_name, member_username));

