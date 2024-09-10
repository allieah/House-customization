# House Customization Application in Java

This project is a graphical **House Customization** application built in Java using **Swing**. Users can create a custom house layout with multiple rooms, assign each room a name, set a color, and add furniture items within each room. The application allows real-time interaction through graphical elements and input dialogs.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [How to Run the Project](#how-to-run-the-project)
- [Class Breakdown](#class-breakdown)
  - [Furniture](#furniture)
  - [Room](#room)
  - [House](#house)
  - [HousePanel](#housepanel)
  - [HouseFrame](#houseframe)
  - [Main](#main)


## Project Overview

The **House Customization Application** allows users to create a house with multiple rooms, each with its own size, color, and name. Users can add furniture to any room by specifying its position and name. The application provides a graphical interface to visualize the house layout and manage individual rooms and their properties.

## Features

- **Create Rooms**: Users can dynamically add rooms by specifying the width for each room.
- **Set Room Color**: Change the color of a specific room using a color picker.
- **Set Room Name**: Rename rooms by specifying a new name.
- **Add Furniture**: Add furniture to rooms by specifying the position (x, y coordinates) and the furniture name.
- **Navigate Rooms**: Click on any room in the GUI to display its dimensions.
- **Interactive UI**: Real-time updates of room layout and properties.

## Technologies Used

- **Java**: Programming language
- **Swing**: For graphical user interface (GUI) components
- **AWT (Abstract Window Toolkit)**: For event handling and graphics rendering

## How to Run the Project

### Prerequisites

- Ensure you have **Java** installed on your machine.
- **JDK** version 8 or above is required to compile and run the program.

### Steps to Run

1. Clone or download the repository:
   ```bash
   git clone https://github.com/yourusername/house-customization-java.git
   cd house-customization-java

2. Compile the Java files:
3. Run the application:
4. When prompted, input the number of rooms to create and specify the width of each room. You can then interact with the application to customize the house layout.

   ## Class Breakdown

### Furniture

- **Description**: Represents a furniture item that can be placed inside a room with specified coordinates and a name.
- **Key Properties**:
  - `x`: X-coordinate for the furniture position.
  - `y`: Y-coordinate for the furniture position.
  - `name`: Name of the furniture item (e.g., "Chair", "Table").

### Room

- **Description**: Defines a room in the house, with attributes such as width, height, color, and a list of furniture items.
- **Key Methods**:
  - `addFurniture(Furniture furniture)`: Adds furniture to the room.
  - `setName(String name)`: Sets a name for the room.
  - `setColor(Color color)`: Assigns a color to the room.

### House

- **Description**: Manages the overall structure of the house, containing multiple rooms.
- **Key Methods**:
  - `setRoom(int index, int width, int height, Color color, String name)`: Creates a room with specific dimensions, color, and name.
  - `getRoom(int index)`: Returns the room at a specific index.
  - `getRoomCount()`: Returns the total number of rooms in the house.

### HousePanel

- **Description**: Handles the graphical representation of the house and its rooms, allowing for navigation and interaction (e.g., setting colors, adding furniture).
- **Key Methods**:
  - `setColorForRoom(int roomIndex)`: Allows the user to select and set a color for a specific room.
  - `setNameForRoom(int roomIndex)`: Sets a new name for the room.
  - `addFurnitureToRoom(int roomIndex, int x, int y, String furnitureName)`: Adds furniture to the room at specified coordinates.

### HouseFrame

- **Description**: The main window of the application that holds the **HousePanel** and provides interactive buttons for room customization (set name, set color, add furniture).
- **Key Components**:
  - `JButton setColorButton`: Button to open a color picker and assign a color to a room.
  - `JButton setNameButton`: Button to rename a specific room.
  - `JButton addFurnitureButton`: Button to add furniture to a specific room.

### Main

- **Description**: The entry point for the application. Prompts the user to input the number of rooms and their dimensions, then launches the main window (**HouseFrame**).
