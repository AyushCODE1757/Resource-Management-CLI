# Resource Management System (CLI)

A professional, layered Java application for managing hardware and office resources. This project demonstrates core Java principles, efficient file-based persistence, and a clean separation of concerns.

## 🚀 Key Features

- **Comprehensive CRUD Operations**: Add, update, view, and delete resources with ease.
- **Advanced Search**: Filter resources by name or allocated user using Java Streams.
- **Persistent Storage**: Automated data persistence to `resources.txt` using custom serialization.
- **Resource Tracking**: Manage resource types (Laptop, Monitor, etc.), allocation status, and maintenance history.
- **User-Friendly CLI**: Interactive menu-driven interface with formatted table outputs.

## 🛠️ Tech Stack

- **Language**: Java 17+
- **Persistence**: Flat-file database (`resources.txt`)
- **API/Libraries**: 
  - `java.util.stream` for data processing.
  - `java.time` for modern date/time handling.
  - `java.io` for robust file operations.
  - `java.util.UUID` for unique resource identification.

## 🏛️ System Architecture

The project follows a **Layered Architecture** pattern to ensure maintainability and scalability.

```mermaid
graph TD
    A[ResourceCLI - Presentation Layer] --> B[ResourceService - Business Logic]
    B --> C[ResourceStore - Data Access Layer]
    B --> D[Models - Data Transfer Objects]
    C --> E[(resources.txt - Persistence)]
    
    subgraph Models
        D1[Resource]
        D2[ResourceType/Status/AllocationType]
    end
```

### Components:
1.  **Presentation Layer (`ResourceCLI`)**: Handles user interaction, input validation, and data formatting.
2.  **Service Layer (`ResourceService`)**: Contains business logic, including search filtering and orchestration between storage and UI.
3.  **Storage Layer (`ResourceStore`)**: Manages low-level File I/O, serialization, and deserialization of objects.
4.  **Model Layer**: Defines the core data structures and enums used across the system.

## 🧠 Core Concepts Applied

- **Object-Oriented Programming (OOP)**: Heavy use of encapsulation, class relations, and state management.
- **Layered Pattern**: Separation of concerns between UI, Logic, and Data.
- **File Serialization**: Implementation of custom pipe-delimited (`|`) serialization for efficient data storage.
- **Streams API**: Utilization of functional programming concepts for searching and processing collections.
- **Singleton-like Behavior**: Managed lifecycle of services and stores within the CLI context.

## 📂 Project Structure

```text
src/main/java/com/aayush/cli/
├── ResourceCLI.java (Main Entry)
├── models/
│   ├── Resource.java
│   ├── ResourceType.java
│   ├── ResourceStatus.java
│   └── AllocationType.java
├── services/
│   └── ResourceService.java
└── storage/
    └── ResourceStore.java
```

## 🚥 Getting Started

### Prerequisites
- JDK 17 or higher
- IDE (IntelliJ IDEA, VS Code, or Eclipse)

### Compilation & Execution
1.  Navigate to the project root.
2.  Compile the source files:
    ```bash
    javac -d bin src/main/java/com/aayush/cli/**/*.java
    ```
3.  Run the application:
    ```bash
    java -cp bin com.aayush.cli.ResourceCLI
    ```

---
Developed by **Aayush** as part of the Java Programming Curriculum.
