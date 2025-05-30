# Help-Desk-Management-System
# Help Desk Management System

A Java Swing-based application for managing help desk tickets with user authentication. This system allows help desk operators to create, assign, and close support tickets while tracking their status.

## Features

### User Authentication
- Login system with username and password
- New user registration
- Secure password handling

### Ticket Management
- Create new support tickets with customer information
- Assign tickets to staff (change status to "in progress")
- Close resolved tickets
- Display all tickets with their current status

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Java Swing library (included in standard JDK)

### Installation

1. Clone or download this repository to your local machine
2. Navigate to the project directory
3. Compile the Java files:
   ```
   javac *.java
   ```
4. Run the application:
   ```
   java HelpDeskManagementSystemGUI
   ```

## Usage

### Login
When you start the application, you'll be presented with a login screen:
- Default credentials: Username: `USER`, Password: `USER@123`
- You can also register a new user account

### Creating Tickets
1. Enter the customer name in the "Customer Name" field
2. Click "Create Ticket"
3. A new ticket will be created with a unique ID and "open" status

### Managing Tickets
- To assign a ticket: Enter the ticket ID and click "Assign Ticket"
- To close a ticket: Enter the ticket ID and click "Close Ticket"
- To view all tickets: Click "Display Tickets"

## Project Structure

- `HelpDeskManagementSystemGUI.java` - Main application class with UI components and ticket management logic
- `Ticket.java` - Class representing a help desk ticket with properties like ID, customer name, and status

## Future Enhancements

- Add technician assignment functionality
- Implement ticket priority levels
- Add ticket description and comments
- Implement data persistence (database integration)
- Add reporting features

## License

This project is available for personal and educational use.
