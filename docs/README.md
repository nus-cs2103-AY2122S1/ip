# User Guide
Winston *frees* your mind of having to remember things you need to do. It's,
* text-based
* easy to learn
* Fast to use

All you need to do is,
1. ~~ask for the download link~~
2. download it from [here](https://github.com/Imerbear/ip)
2. double click it
3. add tasks
4. let it manage tasks for you :star_struck:

And it is **FREE!**

## Features 
*[x] Manages tasks
*[ ] Manages deadlines (Coming soon)
*[ ] Sends reminders (Coming soon)

### Manages tasks
Winston is able to help you manage your tasks through commands fed in through text. The commands to use Winston can be found under the header Usage.

## Usage

### `todo` - Adds a todo task

Adds a todo task to the list

Example of usage: 

`todo (task description)`

task description: The task you would like to add

Expected outcome:

Winston will reply with the number of uncompleted tasks left, where x will be the number of tasks left

```
x Uncompleted Tasks Left
```

### `deadline` - Adds a task with a deadline
Adds a task with a given deadline to the list

Note:

*Ensure that there is a " " in front of the date

*Use - to seperate the year, months and days

*Even if the month can be written as 9, use 09 instead

Example of usage: 

`deadline (task description) /by (Date in YYYY-MM-DD format)`

task description: The task you would like to add

Expected outcome:

Winston will reply with the number of uncompleted tasks left, where x will be the number of tasks left

```
x Uncompleted Tasks Left
```

### `event` - Adds an event
Adds an event to the list

Note:

*Ensure that there is a " " in front of the date

*Use - to seperate the year, months and days

*Even if the month can be written as 9, use 09 instead

Example of usage: 

`event (task description) /at (Date in YYYY-MM-DD format)`

task description: The task you would like to add

Expected outcome:

Winston will reply with the number of uncompleted tasks left, where x will be the number of tasks left

```
x Uncompleted Tasks Left
```

### `list` - Lists all the tasks currently saved
Shows all the tasks that have been saved by winston

Example of usage: 

`list`

Expected outcome:

A list of all the tasks that you have saved and their current completion status

### `findstring` - Finds all tasks that have the given string as part of its description
Shows a list of all tasks that have a description with the given substring

Example of usage: 

`findstring (search string)`

search string: A word or a line that you are looking for

Expected outcome:

Winston will return a list of all matching tasks

### `done` - Mark a task as complete
Marks a task as complete based on the index of the task found in the list

Example of usage: 

`done 3`

Expected outcome:

The task will show [X] instead of [ ]

Winston will give a confirmation that he has marked the task

### `delete` - Delete task from the list
Deletes a task based on the index of the task found in the list

Example of usage: 

`delete 3`

Expected outcome:

Winston will give a confirmation that he has deleted the task and display the list again


### `update` - Updates a task thats alr in the list
Updates a task with another task to the list

Example of usage: 

`update (list index) (task)`

task: Either todo, event or deadline. With their respective syntax as shown in the other parts of the UG

list index: The position of the desired update

Expected outcome:

Winston will reply with the updated list and the the task index that has been updated

### `bye` - Terminates winston
Terminates the winston chatbot, winston will stop listening to commands upon termination

Example of usage: 

`bye`

Expected outcome:

Winston will give a confirmation that the program has terminated