# Note
 A RESTful API that allows users to create, retrieve, update, and delete notes

## Getting Started
 To get started, make sure you have the following installed:
 - Java 21 or later. If not, download it from [Oracle](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
 - Gradle

 This web framework uses an in-memory array as a database.

## Development
 To run the code, navigate to the project's root folder and run:
 ```bash
 gradlew bootRun
 ```
 <!--This may take a few minutes as it downloads necessary resources.-->

## Production
 To build the source code into an executable JAR file for production, run the following commands from the root directory:
 ```bash
 gradlew bootJar
 ```
 Then, run the JAR file:
 ```bash
 java -jar .\build\libs\note-0.0.1-SNAPSHOT.jar
 ```

## API
 Every response has the following structure
 | key | description |
 |-----|-------------|
 | code | The Response code
 | message | Response message
 | data | Response data

## Endpoints

 ### Create a Note

 `POST notes/` Create a new note.

 **Parameters**
 | key | type | description |
 |-----|------|-------------|
 | title | *String* | Note title
 | description | *String* | Note description

 **Sample Response**

 Response containing status and created note
 ```json
{
    "code": 200,
    "message": "Note: 0 has been successfully added!",
    "data": [
        {
            "id": 0,
            "title": "note title",
            "description": "note description"
        }
    ]
}
 ```

 ### Retrieve All Notes

 `GET /notes` Retrieve all notes.

  **Sample Response**

  Response containing status and list of all notes
 ```json
{
    "code": 200,
    "message": "All Notes",
    "data": [
        {
            "id": 0,
            "title": "note title",
            "description": "note description"
        }
    ]
}
 ```

 ### Retrieve Note by ID

 `GET /notes/:id` Retrieve a specific note by ID.

 **Sample Response**

 Response containing status and retrieved note
 ```json
{
    "code": 200,
    "message": "Note: 0",
    "data": {
        "id": 0,
        "title": "note title",
        "description": "note description"
    }
}
 ```

 ### Update a Note

 `PUT /notes/:id` Update a specific note by ID.

 **Parameters**
 | key | type | description |
 |-----|------|-------------|
 | id | *int* | Note ID
 | title | *String* | Updated note title
 | description | *String* | Updated note description

 **Sample Response**

 Response containing status and updated note
 ```json
{
    "code": 200,
    "message": "Note: 0 has been updated successfully!",
    "data": {
        "id": 0,
        "title": "updated note title",
        "description": "updated note description"
    }
}
```

  ### Delete a Note

 `DELETE /notes/:id` Delete a specific note by ID.

 **Sample Response**
 
 Response containing status
 ```json
{
    "code": 200,
    "message": "Note: 0 has been deleted!",
    "data": null
}
```