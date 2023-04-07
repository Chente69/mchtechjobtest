
Tehnical assement for MCH Tech for Senior Fullstack Developer Position

Spring Boot Application for TODO with CRUD REST services methods  

To run the application the user must compile the maven project that contains the code sources using the command:
mvn spring-boot:run

Once the application is running you can use a tool like postman to access the entry points of the deployed web services as shown below:

1. use this URL and HTTP GET METHOD to querry all todos:
http://localhost:8080/api/todos

2. to create one todo you mus run the HTTP POST METHOD with the given URL and enter the json data on the body request:
{
    "id": 1,
    "task": "Do the dishes",
    "completed": false
}

3. To update one todo you mus run the HTTP PUT METHOD  with the given URL ( http://localhost:8080/api/todos/1)  and enter the json data on the body request:
{
    "id": 1,
    "task": "Do the dishes Mod",
    "completed": true
}

4.To delete one todo you mus run the HTTP DELETE METHOD with the given URL ( http://localhost:8080/api/todos/1)  with the id number of the todo entity to remove

5. To query one todo by identifier you mus run the HTTP GET METHOD with the given URL ( http://localhost:8080/api/todos/1)  with the id number of the todo entity to remove


