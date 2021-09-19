# ChadBot

ChadBot is a chatbot named Chad designed to help you manage your tasks.

![ChadBot UI](docs/Ui.png)

## Table of Contents
* [Quick Start](#quick-start)
* [Key Features](#key-features)
* [About](#about)
* [More information](#more-information)

## Quick Start

1. Ensure you have [Java 11 or above installed](https://java.com/en/download/help/version_manual.html). If it is not
   installed, then [install it](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html).
2. Download the latest [JAR](https://en.wikipedia.org/wiki/JAR_(file_format)) file from
   [here](https://github.com/jayasting98/ip/releases).
3. Start the app by double-clicking the JAR file.
4. Type a command into the command text field and click the Send button or press Enter to execute the command. For
   example, to add a to-do task to your tasks list, type the command `todo {task description}` like `todo wash shirts`.

[[Back to table of contents]](#table-of-contents)

## Key Features

### Add different types of tasks

You can add [to-do tasks](https://jayasting98.github.io/ip/#todo---add-to-do-task),
[deadline tasks](https://jayasting98.github.io/ip/#deadline---add-deadline-task) which are tasks that
must be done by a deadline, and [event tasks](https://jayasting98.github.io/ip/#event---add-event-task) which are tasks
that occur at a certain point in time.

![Add tasks demonstration](docs/images/demo_add_tasks.gif)

### See your tasks

You can see your tasks by [listing](https://jayasting98.github.io/ip/#list---list-tasks) them all out, or by 
[finding](https://jayasting98.github.io/ip/#find---find-tasks) tasks with matching keywords.

![See tasks demonstration](docs/images/demo_see_tasks.gif)

### Mark your tasks as done

Once finished with your tasks, you can [mark them as done](https://jayasting98.github.io/ip/#done---mark-task-done).

![Mark task done demonstration](docs/images/demo_mark_task_done.gif)

### Responsive user interface

The UI adjusts accordingly when the app window is resized.

![Responsive UI demonstration](docs/images/demo_responsive_ui.gif)

### Persistent storage

Even after exiting, the data of all the tasks is automatically saved. This is loaded once the app is started again.

[[Back to table of contents]](#table-of-contents)

## About

ChadBot is a greenfield individual project for CS2103T Software Engineering, a module that was a part of the Computer
Science curriculum of the National University of Singapore (NUS) for those who matriculated in the NUS Academic Year
(AY) 19/20. ChadBot was done in the [AY 21/22 Semester 1 version of CS2103T Software Engineering](
https://nus-cs2103-ay2122s1.github.io/website/admin/index.html). ChadBot was written in Java, using the JavaFX library
for its GUI, and JUnit for its unit tests. ChadBot is based off of
[Project Duke](https://nus-cs2103-ay2122s1.github.io/website/se-book-adapted/projectDuke/index.html).

[[Back to table of contents]](#table-of-contents)

## More information

For more information about this app, see the [user guide](https://jayasting98.github.io/ip/).

[[Back to table of contents]](#table-of-contents)