# InferiorDuke

Welcome, ***stalker***, to my *personal* rendition of ~~Duke~~ aka InferiorDuke, the task list bot more insecure than you!

Please be patient with him as he's kind of slow and stupid, and sometimes he doesn't perform things that he should but with enough patient, love and warmth, InferiorDuke will one day flourish into the beautiful flower he can be!

> "If it ain't broke, don't fix it" - Thomas Bertram Lance ([Source](https://digital.hagley.org/Nationbiz_197705#page/30/mode/2up))
---
# Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
  - [Adding Tasks](#adding-tasks)
    - [ToDo](#todo)
    - [Deadline](#deadline)
    - [Event](#event)
  - [Viewing the Task List](#viewing-the-task-list)
  - [Editing the Task List](#editing-the-task-list)
    - [Mark as Done](#mark-as-done)
    - [Undo Task](#undo-task)
    - [Delete Task](#delete-task)
  - [Exiting InferiorDuke](#exiting-inferiorduke)

---
# Getting Started 🔧

Before running InferiorDuke, ensure that you have the a ```data/duke.txt``` file located in the same directory as the the JAR executable ```duke-2.0.jar```. An example of a properly configured file setup is shown below:

```
Desktop
├── folder
     ├── duke-2.0.jar
     ├── data
          ├── duke.txt
```
To actually run InferiorDuke, execute the following command from the Command Line Interface in the directory that the ```duke-2.0.jar``` is located:

```
java -jar duke-2.0.jar
```


---
# Features ✔️

InferiorDuke is a task list bot that can help you track up to 3 different types of tasks: ToDos, Events and Deadlines.

---
## Adding Tasks

As mentioned above, there are three types of tasks and each will be added to your task list differently!

### ToDo 🗒️

A task without a set date for completion and can be added with the following command:

    todo <Description>

### Deadline ⏰

A task with a set date for completion and can be added with the following command:
    
    deadline <Description> /by <Date>

### Event 🥳

An event is an occurrence at a certain time and can be added with the following command:

    event <Description> /at <Date>

---
## Viewing the Task List

InferiorDuke is able to display the tasks in your task list with the following command:

    list

---
## Editing the Task List

In order to edit entries in the task list, you have to use the following commands with respect to the position of the task you wish to edit in the task list.

### Mark as Done

You can mark a task as done with the following command:


    done <Index>

### Undo Task

You can mark a task as undone with the following command:


    undo <Index>

### Delete Task

You can delete a task from the task list with the following command:


    delete <Index>

--- 

## Exiting InferiorDuke

You can exit program by running the following command:


    bye    
