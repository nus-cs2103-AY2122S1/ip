# Do Understand Know Empower (duke)

Hello 😄 This is Ryan Ong's evolution of the **duke Chatbot** with the purpose of keeping track of the tasks *you* ~~die die~~ need to do.
> Quidquid latine dictum sit, altum videtur

## Features

 * Add tasks
 * Delete tasks
 * Complete tasks
 * Local storage

## Tasks

Tasks are available in the following types:

1. ToDo
2. Event
3. Deadline

## Example code

Here's an example of the `code` for the ToDo task!

```java
public static class ToDo extends Task {

        public ToDo(String name) throws DukeException.NoNameException {
            super(name, "#ToDo");
        }

        public ToDo(String name, boolean done) throws DukeException.NoNameException {
            super(name, "#ToDo", done);
        }
    }
```

## Have you tried all the features?

 - [ ] Added three tasks (one of each type)
 - [ ] Completed task 2
 - [ ] Deleted task 1
 - [ ] Closed the program
 - [ ] Restarted the program to see your list is saved!

## Guess what page I'm referring to now?

[This page!](https://nus-cs2103-ay2122s1.github.io/website/schedule/week4/project.html)
