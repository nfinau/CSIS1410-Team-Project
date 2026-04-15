# Student Task Tracker

## Overview
The Student Task Tracker is an application designed to help students organize and manage their academic tasks. Users can create tasks, assign priority levels, mark tasks as complete, and filter tasks to focus on important work.

This project is being developed as part of a team assignment for CSIS 1410 and follows object-oriented programming principles.

---

## Features (Planned)
- Add new tasks with title, description, and priority
- Edit and delete existing tasks
- Mark tasks as complete or incomplete
- Filter tasks by priority (Low, Medium, High)
- Save and load task data

---

## UML Design

The application is structured using the following main components:

- **Task**
  - Represents an individual task
  - Attributes include title, description, priority, completion status, and task ID

- **TaskManager**
  - Manages a collection of tasks
  - Responsible for creating, updating, deleting, and storing tasks
  - Handles reading and writing task data

- **GuiTaskTracker**
  - Provides the user interface for interacting with the system
  - Allows users to add tasks, filter tasks, mark tasks as complete, and cancel actions

- **TaskPriority (Enum)**
  - Defines priority levels:
    - LOW
    - MEDIUM
    - HIGH

---

## Relationships
- A **TaskManager** manages multiple **Task** objects (1 to many)
- The **GuiTaskTracker** interacts with a single **TaskManager**
- Each **Task** has a **TaskPriority**

---

## Project Structure (Planned)
src/
├── Task.java
├── TaskManager.java
├── GuiTaskTracker.java
├── TaskPriority.java
└── Main.java

---

## Team Members
- Naomi Finau
- Emily Guertler

---

## Status
🚧 Design phase complete, implementation in progress

---

## Notes
This project will continue to evolve as features are implemented in future assignments.
