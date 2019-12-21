create database if not exists sportclub;
use sportclub;

drop table if exists course;
create table course (
    courseid int primary key auto_increment,
    name varchar(24));

insert into course(name) VALUES
#       new Course("Jnana Yoga")
('Jnana Yoga');

drop table if exists race;
create table race(
    raceid int primary key auto_increment,
    name varchar(24));

insert into race(name) VALUES
#       new Race("Tour de France")
('Tour de France');

drop table if exists member;
create table member(
    memberid int primary key auto_increment,
    name varchar(24),
    surname varchar(24),
    username varchar(24),
    hashed_password varchar(32));

insert into member(name, surname, username, hashed_password) VALUES
('Tommaso', 'Boni', 'Tom', '827ccb0eea8a706c4c34a16891f84e7b'),
#       new Member("Tommaso", "Boni", "Tom", "12345")
('Luca', 'Perini', 'Luke', 'c37bf859faf392800d739a41fe5af151');
#       new Member("Luca", "Perini", "Luke", "98765")

drop table if exists administrator;
create table administrator(
    adminid int primary key auto_increment,
    name varchar(24),
    surname varchar(24),
    username varchar(24),
    hashed_password varchar(32));

insert into administrator(name, surname, username, hashed_password) VALUES
('Giacomo', 'Neri', 'Jack', '5cc7a8cad0c3ef6834ff6bd9f734e741'),
#       new Admin("Giacomo", "Neri", "Jack", "hardtoguess")
('Chiara', 'Zanetti', 'Chicca', 'aa7dcd799df5136c89931152a274c449');
#       new Admin("Chiara", "Zanetti", "Chicca", "hardtofind")

drop table if exists activity_course;
create table activity_course(
    course_id int,
    member_id int,
    foreign key (course_id) references course(courseid),
    foreign key  (member_id) references member(memberid),
    primary key (course_id, member_id));

drop table if exists activity_race;
create table activity_race(
    race_id int,
    member_id int,
    foreign key (race_id) references race(raceid),
    foreign key  (member_id) references member(memberid),
    primary key (race_id, member_id));

