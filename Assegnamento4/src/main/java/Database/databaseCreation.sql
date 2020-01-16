/**
  SportClub Database Creation
  Crate the database to store the sportclub data.

  @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
  @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
drop database if exists sportclub;
create database if not exists sportclub;
use sportclub;

/**
  Table Course
  Store courses data: name
 */
drop table if exists course;
create table course (
    name varchar(24) primary key);

/**
  Insert some courses
 */
insert into course(name) VALUES
#       new SportClub.Course("Jnana Yoga")
('Jnana Yoga'),
#       new SportClub.Course("Percorso Nascita")
('Percorso Nascita');

/**
  Table Race
  Store races data: name
 */
drop table if exists race;
create table race(
    name varchar(24) primary key);

/**
  Insert some races
 */
insert into race(name) VALUES
#       new SportClub.Race("Tour de France")
('Tour de France'),
#       new SportClub.Race("GranPrix F1")
( 'GrandPrix F1');


/**
  Table Member
  Store members data: name, surname, username and hashed_password
 */
drop table if exists member;
create table member(
    name varchar(24),
    surname varchar(24),
    username varchar(24) primary key,
    hashed_password varchar(32),
    role varchar(24));

/**
  Insert some members
 */
insert into member(name, surname, username, hashed_password, role) VALUES
('Tommaso', 'Boni', 'Tom', '827ccb0eea8a706c4c34a16891f84e7b', 'Member'),
#       new SportClub.Member("Tommaso", "Boni", "Tom", "12345")
('Luca', 'Perini', 'Luke', 'c37bf859faf392800d739a41fe5af151', 'Member'),
#       new SportClub.Member("Luca", "Perini", "Luke", "98765")
('Matilde', 'Tanzi', 'Maty', 'd8578edf8458ce06fbc5bb76a58c5ca4', 'Member'),
#       new SportClub.Member("Matilde", "Tanzi", "Maty", "qwerty")
('Pietro', 'Poli', 'Pie', 'c4ded2b85cc5be82fa1d2464eba9a7d3', 'Member'),
#       new SportClub.Member("Pietro", "Poli", "Pie", "45678");
('Camilla', 'Bacchi', 'Cami', '8b09ae7767db2cb77bd2a1ee4107a020', 'Member'),
#       new Sportclub.Member("Camilla", "Bacchi", "Cami", "noteasy");
('Giacomo', 'Neri', 'Jack', '5cc7a8cad0c3ef6834ff6bd9f734e741', 'Admin'),
#       new SportClub.Admin("Giacomo", "Neri", "Jack", "hardtoguess")
('Chiara', 'Zanetti', 'Chicca', 'aa7dcd799df5136c89931152a274c449', 'Admin'),
#       new SportClub.Admin("Chiara", "Zanetti", "Chicca", "hardtofind")
('Matteo', 'Binelli', 'Matte', 'cfc1e5e69ceb8c3597d62a80663b7990', 'Admin');
#       new SportClub.Member("Matteo", "Binelli", "Matte", "strongman")

/**
  Table Administrator
  Store administrators data: name, surname, username and hashed_password

drop table if exists administrator;
create table administrator(
    name varchar(24),
    surname varchar(24),
    username varchar(24) primary key,
    hashed_password varchar(32));


  Insert some administrators


insert into administrator(name, surname, username, hashed_password) VALUES
('Giacomo', 'Neri', 'Jack', '5cc7a8cad0c3ef6834ff6bd9f734e741'),
#       new SportClub.Admin("Giacomo", "Neri", "Jack", "hardtoguess")
('Chiara', 'Zanetti', 'Chicca', 'aa7dcd799df5136c89931152a274c449'),
#       new SportClub.Admin("Chiara", "Zanetti", "Chicca", "hardtofind")
('Matteo', 'Binelli', 'Matte', 'cfc1e5e69ceb8c3597d62a80663b7990');
#       new SportClub.Member("Matteo", "Binelli", "Matte", "strongman")
*/

/**
  Table Activity Course
  For each subscription to a course, show the name of the course and
  the username of the member subscribed.
 */
drop table if exists activity_course;
create table activity_course(
    course_name varchar(24),
    member_username varchar(24),
    foreign key (course_name) references course(name),
    foreign key  (member_username) references member(username),
    primary key (course_name, member_username));

/**
  Table Activity Race
  For each subscription to a race, show the name of the race and
  the username of the member subscribed.
 */
drop table if exists activity_race;
create table activity_race(
    race_name varchar(24),
    member_username varchar(24),
    foreign key (race_name) references race(name),
    foreign key  (member_username) references member(username),
    primary key (race_name, member_username));

/**
  View numberSubscribersRace
  For each race, show the number of subscribers.
 */
drop view if exists numberSubscribersRace;
CREATE VIEW numberSubscribersRace AS
SELECT race.name AS RaceName, COUNT(activity_race.member_username) AS NumberSubscribers
FROM race LEFT JOIN activity_race ON race.name = activity_race.race_name
GROUP BY race.name;

/**
  View numberSubscribersCourse
  For each course, show the number of subscribers.
 */
drop view if exists numberSubscribersCourse;
CREATE VIEW numberSubscribersCourse AS
SELECT course.name AS CourseName, COUNT(activity_course.member_username) AS NumberSubscribers
FROM course LEFT JOIN activity_course ON course.name = activity_course.course_name
GROUP BY course.name;

/**
  View NumberSubscribers
  For each activity, course and race, show the number of subscribers.
 */
CREATE VIEW NumberSubscribers AS
SELECT numbersubscribersrace.RaceName AS ActivityName, numbersubscribersrace.NumberSubscribers AS NumberSubscribers
FROM numbersubscribersrace
UNION
SELECT numbersubscriberscourse.CourseName AS ActivityName, numbersubscriberscourse.NumberSubscribers AS NumberSubscribers
FROM numbersubscriberscourse