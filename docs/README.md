# User Guide
Duke is a desktop application that helps people to manage different kinds of tasks in their life.
The application is optimized for use via command line interface, which would improve the efficiency
for those who could type fast

## Features

### Types of task

1. Todo
2. Deadline
3. Event
4. Recurring task

## Command

### List all the tasks
    list

### Add Todo task command
This command is for todo task, only task name is required

    todo [task name]
    
#### Example

    todo do 2103 homework
    
### Add Deadline task command
This command is for the task that needs to be done by a deadline, task name and deadline are required.
The deadline you enter should follow the format yyyy-mm-dd [more specific time]

    deadline [task name] /by [task time]
    
#### Example

    deadline finish tutorial /by 2021-11-08 4pm
    
### Add Event task command
This command is for the task that happens at specific time, task name and time are required.
The time you enter should follow the format yyyy-mm-dd [more specific time]

    event [task name] /at [task time]
    
#### Example

    event tutorial /at 2021-11-08 5pm
    
### Add Recurring task command
This command is for the recurring task like the task happens every Monday, task name and specific time and sessions are required.

    recur [task name] /at [task time] /[task counter]
    
#### Example

    recur lecture /at Mon 2pm /10
    
### Mark a task as done
This command is used to mark a task as finished but still keep the task in your record. For recurring task, every time your use this command,
the number of sessions will decrease by 1. Task index is required.

    done [task index]
    
#### Example

    done 1

### Delete a task
This command is used to delete a task from record, task index is required.

    delete [task index] 
    
#### Example

    delete 1

### Find a class by task name
This command is used to find a task according to the keyword you entered

    find [task name]
    
#### Example

    find run

### Exit the application
This command is used to exit the Duke application, no additional information required

    bye

