# User Guide

Duke allows you to track the tasks you have to do. There are three types of tasks:

* todo
* deadline
* event

There are 5 actions you can perform:

* List tasks
* Add a task
* Delete tasks
* Mark tasks as done
* Find tasks

## Usage

### List out tasks

Lists your current tasks.

Instruction format:

    list

Example of usage:

    list

### Add a task

Adds a task to your list depending on type specified in command. The format should be as follows depending on the type:

Instruction format:

    `todo` : todo < task >
    
    `deadline` : deadline < task > /by < yyyy-mm-dd hhmm >
    
    `event` : event < task > /at < yyyy-mm-dd hhmm >

Example of usage: 

    todo homework

    deadline homework /by 1999-02-02 1234

    event party /at 1999-03-03 1111

### Delete tasks

Deletes one or more tasks. 

Instruction format:

    delete < task nunbers >

Example of usage: 

    delete 1 3 4

### Mark tasks as done

Marks one or more tasks as done.

Instruction format:

    done < task nunbers >

Example of usage:

    done 1 3 4

### Find tasks

Search for tasks according to a key word.

Instruction format:

    find < key word >

Example of usage:

    find homework
