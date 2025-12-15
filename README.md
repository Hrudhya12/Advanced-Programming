Pet Adoption App

Overview
This is a Java console application designed based on Object-Oriented Programming (OOP) principles.
It creates a pet adoption app where users can:
- Register and log in
- View available pets
- See detailed pet information
- Adopt pets
- Search pets by name
- Staff can view adoption records in their dashboard
The application focuses on principles like inheritance, polymorphism, and exception handling.

System Architecture
This application follows a clear and simple structure:
- Pet(Superclass): Stores all the common attributes and behaviors shared by all the pets.
- Subclasses: Dog, Cat, Bird, Fish, Rabbit and Hamster each with its own specific characteristics.
- User class: Managing details of adopters and staff.
- PetAdoptionApp (Main class): The main class handles menus, user navigation and the whole adoption processes.

  Principles used
  -Inheritance: all the pet types extend from the parent class Pet.
  -Polymorphism: methods like pet sound is overridden in different pet classes.
  -Exception Handling: User's inputs are checked by try-catch blocks to prevent errors and smooth run.

Implementation
  -Programming Language: Java (console-based application)
  -Development Environment: Java IDE, IntelliJ IDEA

Challenges and solutions
  - used structured loops and exception handling to have smooth menu navigation.
  - Added a readInt() method to prevent program from crashing with invalid inputs.
  - Applied inheritance and polymorphism to hancle different pet characteristics.

Future Work
- Add a long term data storage for pet and user information.
- Add GUI for better user experience.
- Add payment methods or adoption statistics.

  
