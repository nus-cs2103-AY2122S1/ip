# User Guide for The Duke TaskBot

> "A project is complete when it starts working for you, rather than you working for it" - Scott Allen [[source]](https://www.pinterest.com/pin/397864948305207722/)

## Features 

### Feature 1 - Adding tasks

The Duke TaskBot allows you to add various types of tasks with a simple command, which are:
1. ToDo
2. Deadline
3. Event

### Feature 2 - Marking Done

The Duke TaskBot also allows you to mark your favourite tasks as done, when you are done! 

### Feature 3 - Finding Tasks

The Duke TaskBot further allows you to find your favourite tasks easily!

## Usage

### `add` - Adds a task to your tasklist

Adds a task to your tasklist, depending on the next few arguments input.

Example of usage: 

1. `add todo [description]`
2. `add deadline [description] /by YYYY-MM-DD (optional 24h time)`


Expected outcome:

Task added successfully will be mentioned and can be viewed in the tasklist after addition.

```
Task added successfully!
```
### `done` - Marks an existing task in the tasklist as done

Marks a task as done

Example of usage: 

1. `done 1`


Expected outcome:

Task marked succesfully will be notified with a success message.

```
Task is marked as done! 
```

### `find` - Finds a task in your tasklist

Finds a task in your tasklist depending on the keyword provided. Partial matching is accepted as well.

Example of usage: 

1. `find eat`


Expected outcome:

Tasks that matches the keyword provided will be shown in a new tasklist.

```
Tasks that matches the keyword are:
-----------------------------------
1. [D][ ] Eat breakfast
```


Simply run the code in the following class to start the application!
```java
public class DukeLauncher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```

## Enjoy tracking your tasks!
