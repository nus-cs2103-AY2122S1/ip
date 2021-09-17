# User Guide
Duke is a cute todo app that helps you manage tasks. <br />
![UI](Ui.png)

## Quick Start
1. Ensure that you have ```Java 11``` or above installed in your computer.
2. Download the latest ```duke.jar``` from [here](https://github.com/luodan01/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file to start the app. 

## Features

### Add tasks
Duke supports 3 kinds of tasks: *Todos*, *Events* and *Deadlines*. After being added, tasks
are displayed in the following format: <br />
```[Task type][Completion status] Task details``` <br />
For instance, <br />
```[T][X] wash the dishes``` indicates the task is a ```todo``` that has been marked as done.

#### Add a Todo task
- ```todo``` tasks are tasks which are not time-sensitive. To add one,
say ```todo [task description]```
- You can replace ```todo``` with ```t```.
- E.g. ```t wash the dishes```

#### Add an Event task
- ```event``` tasks are tasks with a specific date and time. To add one,
  say  <br /> ```event [event name] /at [yyyy-mm-dd HH:MM]```
- You can replace ```event``` with ```e```.
- E.g. ```e CS3241 finals /at 2021-12-01 13:00```

#### Add a Deadline task
- ```deadline``` tasks are due on a specific date. To add one,
  say  <br /> ```deadline [deadline name] /by [yyyy-mm-dd]```
- You can replace ```deadline``` with ```dl```.
- E.g. ```dl catch up on CS3241 /by 2021-09-26```

### List tasks
- List all tasks in your task list by saying ```list```
- You can replace ```list``` with ```ls``` <br />

### Mark a task as done
- Mark a task as done by saying ```done [task number]```, where 
```[task number]``` is the position of the task in your task list. 
- You can replace ```done``` with ```d```.

### Delete a task
- Delete a task by saying ```delete [task number]```, where
  ```[task number]``` is the position of the task in your task list. 
- You can replace ```delete``` with ```del```.

### Find a task
- Find tasks whose description contains a ```keyword``` by saying ```find [keyword]```
- You can replace ```find``` with ```f```.

### Archive a task
- Archive a task by saying ```archive [task number]```, where
  ```[task number]``` is the position of the task in your task list. The task will now be moved to your archive list.
- You can replace ```archive``` with ```a```.

### Unarchive a task
- Unarchive a task by saying ```unarchive [task number]```, where
  ```[task number]``` is the position of the task in your archive list. The task will now be appended to the back of your task list.
- You can replace ```unarchive``` with ```ua```.

### List archives
- List your archived tasks by saying ```list -archives```
- You can use ```ls -a ``` as a shortcut.

### Exit the program
- Say ```bye``` to exit the program.

### View supported commands
- Say ```help``` to see all supported commands.

### Secret command
Duke has a **secret command** which is up to you to figure out!<br />
>Q: *How do I get started?* <br />
>A: Try out each of the commands until you see something suspicious! <br /><br />
>Q: *What's this secret command useful for?* <br />
>A: Absolutely nothing

## Acknowledgements
Duke is made for entertainment purposes only. All image and GIF rights go to San-X.
<br /> Images and GIFs used can be found [here](https://www.line-stickers.com/corocoro-coronya/).