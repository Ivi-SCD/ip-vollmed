# IP-VollMed | API Developed for Medical Systems

The REST API for Medical System was developed during the Java and Springboot formation course at [Alura](https://www.alura.com.br). This API was created with the intention of providing an efficient medical system that meets the needs of of patients or physicians using the API.

## 💻 Technologies Used

Below is a table with the technologies that i used in the development of this API. 
|Technology|Description
|---|---
|Java|Programming language used for API development|
|Spring Boot|Framework used for API development|
|Spring Security|Framework used to control the authentication and authorization in the API
|JWT|Authentication protocol used in the API|
|Bean Validation|Framework used for API data validation|
|Jackson|Library used for serialization and deserialization of objects in tests|
|Mockito|Framework used for creating integration tests|
|Flyway|Framework used for database migration control in PostgreSQL|
|Swagger - Springdoc|Framework used for API documentation

## Lessons Learned and Technologies Used
### API Architecture
This API architecture was developed with a focus on separating business rules from application implementation, using a domain-driven architecture. This means that the API was designed to meet the needs of users, without worrying about the application implementation.
##
### Authentication and Authorization
This API uses the Spring Security framework to perform user authentication and authorization. The protocol used for authentication is JWT (JSON Web Token), which allows for secure information exchange between client and server.
##
### Data validation
This API uses the Bean Validation framework to validate input data. This ensures that user-provided data complies with the application's business rules.
##
### Testing
The API tests were performed using the Mockito framework, which allows for the creation of unit tests for the application. Additionally, the Jackson library was used to serialize and deserialize objects in tests.
##
### Database Managment by Migration's Control
The API uses the Flyway framework to control migrations in the PostgreSQL database. This allows for changes to the database to be managed in a simple and efficient way.
##
### Documentation
The API documentation was created using the Swagger - Springdoc framework. This tool allows API users to clearly and objectively view the information provided by the application.
##
### How to Execute the Project
To download and run the project, follow these steps:
* Clone the repository to your local machine using the HTTPS link provided above.
```bash
git clone https://github.com/Ivi-SCD/IP-VollMed.git
```
* Open the project in your preferred Integrated Development Environment (IDE). I usually use IntelliJ IDE. 
* Set up a PostgreSQL or other relational database, create and cofigure a new database, it might be called by `vollmed`
* Update the `application.properties` file with your database credentials, located at `src/main/resources/application.properties`.
* Build and run the project using your IDE.
* Once the application is running, you can access the API documentation by navigating to `http://localhost:8080/swagger-ui.html`.

And finished! You have successfully downloaded and started the IP-VollMed developed for Medical Systems. You can now use the API to manage medical system data in a secure and efficient way.
