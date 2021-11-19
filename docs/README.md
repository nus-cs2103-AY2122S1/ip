# User Guide
Duke is a Command Line Application which helps to keep track of user's tasks.

## Features 
Duke is able to categorize the user's tasks into different categories:
* Todo 
* Deadline
* Event
* DoAfter

After adding tasks, you may set them as done using the "done" command and subsequently delete them using the "delete" command.

You may view all of your existing tasks using the "list" command.

### Feature-Different-Types-of-Tasks

As stated there are currently 4 types of supported tasks:
1. Todo
    * For basic tasks that needs to be done without additional information
2. Deadline
    * For tasks that has to be completed by a certain date
3. Event
    * For tasks that has to be done at a certain location
4. DoAfter
    * For tasks that needs to be completed after another task

### Usage
* Todo:
    ```
        todo {task-to-be-completed}
    ```
    Example usage: `todo Make breakfast for Danny`

* Deadline:
    ```
        deadline {task-to-be-completed} /by {dd/MM/YYYY HHMM} 
    ```
    Example usage: `deadline CS2100 Assignment 1 /by 14092021 2359`

* Event:
    ```
        event {task-to-be-completed} /at {location}
    ```
    Example usage: `event CS2100 Midterms /at MPSH5`

* DoAfter:
    ```
        doafter {task-to-be-completed} /after {previous-task}
    ```
    Example usage: `doafter CS2100 Tutorial 4 /after Watch CS2100 lecture`

### Feature-Done-Delete-List-Find

Duke supports Done, Delete and List functionality.
* Done: sets a selected task to be marked as done
    ```
        done {index-of-target-task}
    ```
    Example usage: `done 2` (deletes 2nd item on the list)

* Delete: deletes a selected task
    ```
        delete {index-of-target-task}
    ```
    Example usage: `delete 1` (deletes the 1st item on the list)

* List: lists out all the current tasks
    ```
        list
    ```
    Example usage: `list`

* Find: finds all the tasks that matches the specified search term
    ```
        find {search-term}
    ```
    Example usage: `find homework`

Click [here](https://github.com/muhammad-faruq/ip) for the github repo :grinning: