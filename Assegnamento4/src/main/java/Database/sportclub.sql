create database if not exists sportclub;
use sportclub;

drop table if exists course;
create table course(
    courseid int primary key auto_increment,
    name varchar(24));

drop table if exists race;
create table race(
    raceid int primary key auto_increment,
    name varchar(24));

drop table if exists member;
create table member(
    memberid int primary key auto_increment,
    name varchar(24),
    surname varchar(24),
    username varchar(24),
    hashed_password varchar(32));

drop table if exists administrator;
create table administrator(
    adminid int primary key auto_increment,
    name varchar(24),
    surname varchar(24),
    username varchar(24),
    hashed_password varchar(32));

/*
insert into person(name, surname, username, hashed_password, admin) VALUES
('Giacomo', 'Pini', 'Jack', 'winelover', false),
('Giovanna', 'Sirati', 'Giovy', 'sparklingwine', false),
('Marco', 'Maggi', 'Mark', 'proseccolover', false),
('Maria', 'Faresi', 'Mary', '0FaresiWine1', true);
 */

drop table if exists activity_course;
create table activity_course(
    course_id int,
    member_id int,
    foreign key (course_id) references course(courseid),
    foreign key  (member_id) references member(memberid),
    primary key (course_id, member_id));

drop table if exists activity_course;
create table activity_course(
    race_id int,
    member_id int,
    foreign key (race_id) references race(raceid),
    foreign key  (member_id) references member(memberid),
    primary key (race_id, member_id));

