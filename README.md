# NotesApp Backend

A Spring Boot backend application for managing notes, using MongoDB as the database. This project demonstrates CRUD operations, execution time tracking, and a structured response format. Designed for learning purposes and interview preparation.

## Technologies Used

* Java 17
* Spring Boot
* Spring Security (JWT for authentication)
* MongoDB (using MongoTemplate)
* Maven
* GitLab / GitHub for version control
* Postman for API testing
* AWS (for deployment)

## Project Structure

```
notesapp/
├── src/main/java/com/example/notesapp
│   ├── controller/
│   ├── service/
│   ├── model/
│   └── config/
├── src/main/resources/
│   ├── application.properties
├── pom.xml
└── README.md
```

## Features

* Create a new note
* Read all notes or a single note by ID
* Update an existing note (partial updates supported)
* Delete a note by ID
* Execution time tracking for each request
* Structured JSON response
* Ready for JWT-based security
* Can be deployed on AWS

## Sample Request/Response

### Create Note

**POST** `/notes`

```json
{
  "title": "My First Note",
  "content": "This is a test note."
}
```

**Response:**

```json
{
  "resultCode": "200",
  "resultDescription": "Note created successfully",
  "resultObj": {
    "id": "68a751a1daf1752245fa4214",
    "title": "My First Note",
    "content": "This is a test note.",
    "createdAt": "2025-08-21T12:30:00"
  },
  "executionTime": 32
}
```

### Update Note

**PUT** `/notes/{id}`

```json
{
  "title": "Updated Note Title"
}
```

**Response:**

```json
{
  "resultCode": "200",
  "resultDescription": "Updated Successfully",
  "resultObj": {
    "id": "68a751a1daf1752245fa4214",
    "title": "Updated Note Title",
    "content": "This is a test note.",
    "createdAt": "2025-08-21T12:30:00"
  },
  "executionTime": 47
}
```

### Delete Note

**DELETE** `/notes/{id}`

**Response:**

```json
{
  "resultCode": "200",
  "resultDescription": "Deleted Successfully",
  "resultObj": null
}
```

## Setup Instructions

1. Clone the repository:

```bash
git clone <your-repo-url>
```

2. Configure MongoDB connection in `application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/notesdb
```

3. Build the project:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

5. Test APIs using Postman.

## Future Enhancements

* JWT-based authentication
* Role-based access control
* AWS deployment scripts
* Logging and metrics
* Batch operations
