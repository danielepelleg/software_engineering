<!-- PROJECT LOGO -->
  <br />
    <p align="center">
  <a href="https://github.com/danielepelleg/software_engineering">
    <img src="./src/main/resources/images/racket.png" alt="Logo" width="130" height="130">
  </a>
  <h1 align="center">Sport Club</h1>
  <p align="center">
    Java implementation of a JavaFX software representing a sport club with courses and races.
  </p>
  <p align="center">
    Maven Project
  </p>
  
  <!-- TABLE OF CONTENTS -->
  ## Table of Contents
  
  - [Table of Contents](#table-of-contents)
  - [About The Project](#about-the-project)
  - [Getting Started](#getting-started)
    - [Updates](#updates)
  - [License](#license)
  - [Contributors](#contributors)
   
   <!-- ABOUT THE PROJECT -->
   ## About The Project
   **Sport CLub** simulates a club with different courses and races to subscribe to. 
   
   The system is made up of a MySQL Database (connection made using jdbc), which has the task to keep the personal data 
   of the members, the administrators, the course, the races and their subscriptions. A member can subscribe, 
   or unsubscribe to an activity. An administrator can do some management operations.
   
   Every change is stored on the database.
   
   Every member has a different username, and every course or race has a different name the others.
   
   A new member can register to the system by giving his personal data and choosing an username which must be not 
   already taken. The system provides to forbid the assignment of a username if a person with that same username already 
   exists. In this case, it provides to notify the error to the member. Once he signs in, he can subscribe or unsubscribe
   to activities and see the list of the race/course.
   
   Administrators can register themselves by inserting a username which must not to belong to others admins and log in 
   the system. They can see the list of the members registered to the club, the list of the courses and the races, the 
   list of subscriptions and can update every sort of data.
   
   The system has a GUI, that starts with the login/registration form.
    
    - You have to select if you want to sign in as a member or as administrator. Once you insert the right credentials
       the system will open your menu.
    
    - You can register yourself to the database by inserting an username, which as primary key of the table must be 
       different from anyone else. Your password will be stored hashed, once you complete the registration. From now
      you can login with your new credentials.
    
    - Once logged you will be redirected to your menu, where the buttons with the images will guide you to the 
       different operations you are allowed to perform. From this window you can also choose to disconnect, to come 
      back to the login/registration form.
    
    
   
  <!-- UML DIAGRAMS  -->
   
   <!-- GETTING STARTED -->
   ## Getting Started
   You can just clone this repository.
   
   ### Updates
   Pull this repository for updates.
   
   <!-- LICENSE -->
   ## LICENSE
   Distributed under the GPL License. See `LICENSE` for more information.
   <div>Icons made by <a href="https://www.flaticon.com/authors/nikita-golubev" title="Nikita Golubev">Nikita Golubev</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
      
   <!-- CONTRIBUTORS -->
   ## CONTRIBUTORS
   [Daniele Pellegrini](https://github.com/danielepelleg) - 285240
   
   [Riccardo Fava](https://github.com/BeleRicks11) - 287516