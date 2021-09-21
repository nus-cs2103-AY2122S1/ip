# Duke ChatBot User Guide
***
Duke is a chatbot application (based on the generic project called Project Duke) designed to help you manage and keep track of your tasks.

## Features 
***
1. Adding Task
   - Todo Task
   - Deadline Task
   - Event Task
2. Deleting Task
3. Finding Task
4. Listing Tasks
5. Marking Task As Completed
6. Sorting Deadline Tasks By Due Date
7. Exiting

## Usage
***

##1. Adding Task
### `todo` - Adding a Todo Task

Adds a todo task to the list of tasks. 

Format: `todo [TASK DESCRIPTION]`

Example of usage: `todo read book`

Expected outcome: 
```
Got it! I've added this task:
[T][] read book
Now you have 1 tasks in the list.
```
### `deadline` - Adding a Deadline Task

Adds a deadline task to the list of tasks.

Format: `deadline [TASK DESCRIPTION] /by [YYYY-MM-DD]`

Example of usage: `deadline return book /by 2021-09-21`

Expected outcome:
```
Got it! I've added this task:
[D][] return book(by: Sep 21, 2021)
Now you have 2 tasks in the list.
```
### `event` - Adding an Event Task

Adds an event task to the list of tasks.

Format: `event [TASK DESCRIPTION] /at [DAY]`

Example of usage: `event attend meeting /at Monday`

Expected outcome:
```
Got it! I've added this task:
[E][] attend meeting(at: Monday)
Now you have 3 tasks in the list.
```
##2. Deleting Task
### `delete` - Deleting Task From the List

deleting a todo, deadline, or event task specified at INDEX from the list.

Format: `delete [INDEX]`

Example of usage: `delete 1`


Expected outcome:
```
Noted. I've now removed this task:
[E][] attend meeting(at: Monday)                 
Now you have 5 tasks in the list.
```

##3. Finding Task
### `find` - Finding Specific Task(s)

Finds task that has a specified keyword.

Format: `find [KEYWORD]`

Example of usage: `find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] read book
2. [D][] return book(by: Sep 21, 2021)
```
##4. Listing Tasks
### `list` - Listing All Tasks

Lists all the todo, deadline, and event tasks.

Format: `list`

Example of usage: `list`

Expected outcome:
```
Here are the tasks on your list:
1. [T][] read book
2. [D][] return book(by: Sep 21, 2021)
3. [E][] attend meeting(at: Monday)
```
##5. Marking Task As Completed
### `done` - Marking Task as Done

Marking a todo, deadline, or event task specified at INDEX as completed.

Format: `done [INDEX]`

Example of usage: `done 5 `

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] read book
```

##6. Listing Sorted Deadlines
### `L sort items` - Listing Sorted Deadlines

Listing all tasks with deadline tasks sorted by due date.

Format: `L sort items`

Example of usage: `L sort items `

Expected outcome:
```
Here are the tasks on your list:
1. [T][X] read book
2. [D][] return book(by: Sep 21, 2021)
3. [D][] CS3243 assignment (by: Sep 25, 2021)
4. [D][] CS2103T assignment(by: Sep 30, 2021)
5. [D][] submit work (by: Sep 21, 2022)
```
##7. Exiting
### `bye` - Exiting Program

Exits the application. 

Format: `bye`

Example of usage: `bye`

Expected outcome:
```
Bye! Hope to see you again soon!
```