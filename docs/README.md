# User Guide


## Bob

Bob is a sarcastic and slightly annoying yet helpful chatbot that helps you keep track of all the things you need to do.
If you are a quick typer, Bob allows you to manage your tasks much faster than regular to-do-list applications.



## Features 

### Managing regular tasks

Add and delete todo tasks, and mark them as completed.


### Managing deadlines

Add and delete deadlines, and mark them as completed.


### Managing events

Add and delete events, and mark them as completed.


### Viewing all tasks

View all saved tasks.


### Searching for tasks

View tasks containing a specific keyword.


### Viewing help

Be directed to this User Guide.


### Saving data

Automatically save task list in text file, and reload data the next time Bob is launched.



## Commands

### `help` - Viewing help

Get a link to this page.

Example of usage: 

`help`

Expected outcome:

A message that leads users to this page when they click on it.

```
HA HA do you not know how to talk to me? 
Click on this message to view my guide then! 
(You'll need internet access though :p)
```


### `todo` - Adding todo task

Add a new task to keep track of that has no specific date or deadline.

Example of usage:

`todo eat bread`

Expected outcome:

A new uncompleted todo task "eat bread" will be added to the list of tasks.

```
Okay okay I've added the task:
[T] [ ] eat bread
Yay 2 tasks!
```


### `deadline` - Adding deadline task

Add a new task to keep track of that has a specific deadline.

Example of usage:

`deadline review bread /by 2021-02-09`

Expected outcome:

A new uncompleted deadline task "review bread" with the date "9 Feb 2021" will be added the list of tasks.

```
Okay okay I've added the task:
[D] [ ] review bread (by: Feb 9 2021)
Yay 3 tasks!
```


### `event` - Adding event task

Add a new task to keep track of that has a specific date.

Example of usage:

`event make bread /at 2021-02-12`

Expected outcome:

A new uncompleted event task "make bread" with the date "12 Feb 2021" will be added the list of tasks.

```
Okay okay I've added the task:
[E] [ ] make bread (at: Feb 12 2021)
Yay 4 tasks!
```


### `list` - Displaying all tasks

Displays all the tasks in the list.

Example of usage:

`list`

Expected outcome:

All the tasks in the list will be shown.

```
Here's your tasks! Wow I'm so helpful!
1.[T] [ ] drink milo
2.[T] [ ] eat bread
3.[D] [ ] review bread (by: Feb 9 2021)
4.[E] [ ] make bread (at: Feb 12 2021)
```


### `find` - Searching for tasks

Search for all the tasks in the list that contain a specific keyword.

Example of usage:

`find bread`

Expected outcome:

Returns all the tasks in the list that contain the word "bread".

```
Are any of these tasks the one you're looking for?
1.[T] [ ] eat bread
2.[D] [ ] review bread (by: Feb 9 2021)
3.[E] [ ] make bread (at: Feb 12 2021)
```


### `done` - Completing task

Mark a task in the list as completed.

Example of usage:

`done 2`

Expected outcome:

Marks the task with index number 2 in the list as a completed task.

```
Wow you finally did something productive!
[T] [X] eat bread
```


### `delete` - Removing task

Delete a task from the list.

Example of usage:

`delete 1`

Expected outcome:

Removes the task with index number 1 from the list, and shifts the remaining tasks accordingly.

```
Okay task yeeted away :D
[T] [ ] drink milo
Yay 3 tasks!
```



## Acknowledgements

GUI inspiration from Fantasia by @soaza

Images from Among Us and Kimi no Nawa