<div align = "center">
<img align="center" height="150" src="https://github.com/crypto-code/ip/blob/master/docs/img/Main_Logo.png?raw=true">
</div>

<div align = "center">

  <h1> User Guide For WhoBot </h1>

</div>

<p align = "center"> The WhoBot is your Revolutionary Personal Assistant that makes Managing All Tasks Simple and Efficent. </p>

<br> 
<hr>
<br>

<div align = "center">
  <img height = "300" src="https://github.com/crypto-code/ip/blob/master/docs/img/Main_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "600" src="https://github.com/crypto-code/ip/blob/master/docs/img/Main_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br> 
<hr>
<br>

## Features 

### - [Get Help](#help)

You can get Instant Help

### - [Maintain ToDos](#todo)

You can Add and Delete your ToDos

### - [Maintain Deadlines](#deadline)

You can Add and Delete your Tasks with Deadline

### - [Maintain Events](#event)

You can Add and Delete your Events that have specific timings

### - [Search Tasks by Name](#find)

You have the Ability to Search for Different Tasks by using just a part of their Name

### - [Search Tasks by Date](#show)

You also have the Ability to Search the Tasks by the Deadline/Timing

### - [Mark Tasks](#done)

You can Mark your Tasks as done as you complete them.

### - [Tag Tasks](#tag)

You have the Power to Tag Tasks based on their different categories

### - [List Tasks](#list)

You can List all Tasks or even List Tasks belonging to a speicific Tag

<br> 
<hr>
<br>

## Usage

<h3 id = 'list'></h3> 

### `list` - Prints out the List of Tasks.
This command will print all tasks in your list if no tagname is specified.
It will be ordered based on timing and whether completed.

If **#tagname** is specified then all the tasks under that tag will be displayed.
Others is the default tag for tasks that aren't tagged.

Example of usage: 

```
list [#tagname]
```

<br>

<div align = "center">
  <img height = "300" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <div float="left">
    <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_GUI1.png?raw=true">
    <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_GUI2.png?raw=true">
    <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_GUI3.png?raw=true">
  </div
  <p align = "center"> GUI Interface </p>
</div>


<br>

<h3 id = 'help'></h3> 

### `help` - Prints out Command specific help page.
This command will print out a list of commands.

If **#command** is specified then it will display the command specific command.

Example of usage: 

```
help [#command]
```

<br>

<h3 id = 'todo'></h3> 

### `todo` - Adds a ToDo Task to the List.
This command will add a new ToDo Task with the given description.

The description is required and this type of task has no timing.

Example of usage: 

```
todo #description
```

<br>

<h3 id = 'event'></h3> 

### `event` - Adds an Event Task to the List.
This command command will add a new Event Task with the given description and timing

The description and timing are required. The timing should be of the format d/m/yyyy HH:mm.

Example of usage: 

```
event #description /at #timing
```

<br>

<h3 id = 'deadline'></h3>

### `deadline` - Adds a Deadline Task to the List.	
This command will add a new Deadline Task with the given description and deadline.

The description and deadline are required and should be of the format  d/m/yyyy HH:mm or d/m/yyyy.

Example of usage: 

```
deadline #description /by #deadline
```

<br>

<h3 id = 'show'></h3> 

### `show` - Prints out the List of Tasks on Given Date.
This command will show you on tasks on given date.

#date is required and must be in the format d/m/yyyy

Example of usage: 

```
show /on #date
```

<br>

<h3 id = 'find'></h3> 

### `find` - Prints out the List of Tasks containing String.
This command will show you on tasks that contain the given search string. 

#string is required and is case insensitive.

Example of usage: 

```
find #string
```

<br>

<h3 id = 'done'></h3> 

### `done` - Marks Task at #index in the List as completed.
This command will mark the task at **#index** as done. **#index** is required.

Example of usage: 

```
done #index
```

<br>

<h3 id = 'undo'></h3>

### `undo` - Marks Task at #index in the List as incomplete.
This command will mark the task at **#index** as not done. **#index** is required.

Example of usage: 

```
undo #index
```

<br>

### `delete` - Delete Task at #index in the List.
This command will delete the task at #index. #index is required.

Example of usage: 

```
delete #index
```

<br>

<h3 id = 'tag'></h3> 

### `tag` - Tags Task at #index in the List with the given #tagname.
This command will tag the task at #index with the given #tagname.

Both #index and #tagname are required.

Example of usage: 

```
tag #index /as #tagname
```

<br>

### `untag` - Untags Task at #index in the List.
This command will untag the task at #index. #index is required.

The task will be reset to the default tag of "Others".

Example of usage: 

```
untag #index
```

<br>

### `bye` - Quits the ChatBot.
This command will exit the ChatBot and the tasks will be saved to memory for later use.

Example of usage: 

```
bye
```



