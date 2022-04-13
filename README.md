# Duke Personal Chatbot

Duke is a personal chatbot that acts as a task manager. With a helpful and open personality, Duke helps keep track of your tasks. Users who are great at typing will enjoy Duke's easiness.

It's 
* text-based
* easy to learn
* *SUPER FAST* to use!

AND it is **FREE**! 🥰

## Features

### Managing Tasks
Duke supports three main types of tasks:
1. ToDo
2. Deadline
3. Event

#### Deadline
A Deadline is defined as a task that has a specific date of completion. These tasks have to be completed by the specified date. 

#### Event
An Event is a task that occurs at a particular date. Events are tasks where the user has to attend. 

#### ToDo
A ToDo is defined as a task that does not have a specific date of completion. They are meant as a quick way to record tasks that do not have a hard date of completion, or if the date of completion is pending. 

### Sorting Tasks

#### Date
Duke supports sorting of tasks by the earliest date. Since ToDo tasks do not have a date associated with them, they are automatically appended to the back of the list upon sorting.

Note: only dates in the format ```YYYY-MM-DD``` are accepted. 


## Usage (How-it-works)

### Commands

```bye``` - Exits chatbot

Example of usage:
```java
bye
```

```delete``` - Deletes a task from the task list, indicated by the index

Example of usage:
```java
delete 1
```

```done``` - Marks a task, indicated by the index, as done

Example of usage:
``` java
done 1
```

```deadline``` - Adds a Deadline task

Example of usage:

``` java
deadline school assignment /by 2021-12-02
```

```event``` - Adds an Event task

Example of usage: 

``` java
event Google Day /at 2021-09-15
```

```find``` - Finds a task based on the given keyword

Example of usage: 

``` java
find school
```

```list``` - Lists all tasks stored in the task list

Example of usage:
``` java
list
```

```sort``` - Sorts all tasks from earliest date to latest date, followed by ToDo

Example of usage:
``` java
sort
```

```todo``` - Adds a Todo task

Example of usage:

``` java
todo read book 
```

