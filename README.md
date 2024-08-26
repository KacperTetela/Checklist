## Overview

This project includes two controllers for managing checklists and related rooms. The API allows for CRUD operations on checklists and provides endpoints to retrieve information about them.

## Controllers

### ChecklistController

Manages operations related to checklists.

#### **Endpoint**: `/api/v1/checklists`

- **POST** `/api/v1/checklists`
  - **Description**: Creates a new checklist.
  - **Request Body**: A `Checklist` object in JSON format. Example:
    ```json
    {
      "rooms": [
        {
          "roomNumber": 101,
          "complete": false,
          "signer": "John Doe",
          "comment": "Initial check"
        }
      ]
    }
    ```
  - **Response**: The created `Checklist` object in JSON format.

- **GET** `/api/v1/checklists/last`
  - **Description**: Retrieves the most recently created checklist.
  - **Response**: The most recent `Checklist` object in JSON format.
 
  - - **PUT** `/api/v1/checklists/{id}`
  - **Description**: Updates an existing checklist with the specified `id`.
  - **Path Parameter**:
    - `id`: The ID of the checklist to update.
  - **Request Body**: A `Checklist` object in JSON format. Example:
    ```json
    {
      "rooms": [
        {
          "roomNumber": 102,
          "complete": false,
          "signer": "John Doe",
          "comment": "Initial check"
        }
      ]
    }
    ```
  - **Response**: The updated `Checklist` object in JSON format.

- **DELETE** `/api/v1/checklists/{id}`
  - **Description**: Deletes the checklist with the specified `id`.
  - **Path Parameter**:
    - `id`: The ID of the checklist to delete.
  - **Response**: `true` if deletion was successful, `false` otherwise.

### ChecklistInfoController

Provides information about checklists.

#### **Endpoint**: `/api/v1/checklists/info`

- **GET** `/api/v1/checklists/info`
  - **Description**: Retrieves all checklists.
  - **Response**: A list of `Checklist` objects in JSON format.

- **GET** `/api/v1/checklists/info/{id}`
  - **Description**: Retrieves a checklist with the specified `id`.
  - **Path Parameter**:
    - `id`: The ID of the checklist to retrieve.
  - **Response**: The `Checklist` object with the specified ID in JSON format.

## Model Classes

### Checklist

Represents a checklist entity.

- **Fields**:
  - `id` (Long): The unique identifier of the checklist.
  - `rooms` (List<ChecklistRoom>): A list of `ChecklistRoom` objects associated with the checklist.
  - `createdAt` (LocalDateTime): The date and time when the checklist was created.
  - `updatedAt` (LocalDateTime): The date and time when the checklist was last updated.

- **Methods**:
  - `getId()`: Returns the ID of the checklist.
  - `getRooms()`: Returns the list of rooms associated with the checklist.
  - `getCreatedAt()`: Returns the creation date and time of the checklist.
  - `getUpdatedAt()`: Returns the last update date and time of the checklist.
  - `setRooms(List<ChecklistRoom> rooms)`: Sets the list of rooms and updates the `checklist` reference in each room.
  - `timeUpdate()`: Updates the `updatedAt` field with the current date and time.

### ChecklistRoom

Represents a room in a checklist.

- **Fields**:
  - `id` (Long): The unique identifier of the room (auto-generated).
  - `checklist` (Checklist): The checklist to which this room belongs.
  - `roomNumber` (Short): The number of the room.
  - `complete` (Boolean): Indicates whether the room check is complete.
  - `signer` (String): The name of the person who signed off on the room.
  - `comment` (String): Any comments related to the room.

- **Methods**:
  - `getId()`: Returns the ID of the room.
  - `getRoomNumber()`: Returns the room number.
  - `isComplete()`: Returns whether the room check is complete.
  - `getSigner()`: Returns the name of the signer.
  - `getComment()`: Returns the comment related to the room.
  - `getChecklist()`: Returns the checklist associated with the room.
  - `setChecklist(Checklist checklist)`: Sets the checklist associated with the room.

## Example JSON Responses

### Response for `GET /api/v1/checklists/last`
```json
{
  "id": 1,
  "rooms": [
    {
      "roomNumber": 101,
      "complete": false,
      "signer": "John Doe",
      "comment": "Initial check"
    }
  ],
  "createdAt": "2024-08-26T12:34:56",
  "updatedAt": "2024-08-26T12:34:56"
}
