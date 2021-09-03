# InferiorDuke

Welcome, ***stalker***, to my *personal* rendition of ~~Duke~~ aka InferiorDuke, where I try to follow requirements for my iP in hopes of passing this module!

> "If it ain't broke, don't fix it" - Thomas Bertram Lance ([Source](https://digital.hagley.org/Nationbiz_197705#page/30/mode/2up))

# Features

As this is a work in progress, most of the features currently available are still being worked upon with many more planned for the future!

## Available Features ✔️

* Able to add 3 different types of tasks (Event, Deadline and ToDo)
* View your Task list with the list command, with your tasks stored locally on your computer
* Mark tasks as done or undone with the done and undo commands
* Delete tasks from the Task list

## Upcoming Key Features 📝

1. Implement Graphical User Interface (GUI) 
2. Implement Gradle
3. Add JavaDocs to all public methods availble

## Additional Optionals

- [X] Add JavaDocs to all public methods
- [ ] All code 100% CheckStyle
- [ ] Make cute logo for InferiorDuke

# Code Quality

Hopefully, my code is in accordance to the quality acceptable by our tutors and professors. An sample would be as follows:

```java
public static void main(String[] args) {
    new Duke("data/duke.txt").run();
}
```

## Errors

One thing you might notice about my code is that I chose to create my errors as DukeErrors as seen below:

```java
    public class DukeException extends Exception
```

I used to use DukeErrors as an enumeration but I decided that making it extend the Exception class would make the flow of logic cleaner.
