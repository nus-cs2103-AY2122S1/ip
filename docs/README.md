<div align = "center">
<img align="center" height="150" src="https://github.com/crypto-code/ip/blob/master/docs/img/Main_Logo.png?raw=true">
</div>

<div align = "center">

  <h1> User Guide For WhoBot </h1>

</div>

<p align = "center"> The WhoBot is your Revolutionary Personal Assistant that makes Managing All Tasks Simple and Efficent. </p>



<div align = "center">
  <img height = "300" src="https://github.com/crypto-code/ip/blob/master/docs/img/Main_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "600" src="https://github.com/crypto-code/ip/blob/master/docs/img/Main_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br> 
<hr>
<br>

## Getting Started

It's simple to get up and running. follow these steps to start using WhoBot  

1. Download the JAR file from [here](https://github.com/crypto-code/ip/releases/download/A-BetterGui/WhoBot.jar).
2. You can start the progrom by running the following command
```
java -jar WhoBot.jar
```
3. To use the cli version you can run it using the **--cli** argument
```
java -jar WhoBot.jar --cli
```
4. Enjoy :)

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

### - [Easily Personizable](#developers)

If you are a Developer, you can easily personalize the WhoBot with custom responses

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
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <div float="left">
    <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_GUI1.png?raw=true">
    <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_GUI2.png?raw=true">
    <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/list_GUI3.png?raw=true">
  </div>
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

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/todo_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/todo_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

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

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/event_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/event_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

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

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/deadline_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/deadline_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

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

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/show_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/show_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

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

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/find_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/find_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br>

<h3 id = 'done'></h3> 

### `done` - Marks Task at #index in the List as completed.
This command will mark the task at **#index** as done. **#index** is required.

Example of usage: 

```
done #index
```

<br>

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/done_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/done_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br>

<h3 id = 'undo'></h3>

### `undo` - Marks Task at #index in the List as incomplete.
This command will mark the task at **#index** as not done. **#index** is required.

Example of usage: 

```
undo #index
```

<br>

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/undo_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/undo_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br>

### `delete` - Delete Task at #index in the List.
This command will delete the task at #index. #index is required.

Example of usage: 

```
delete #index
```

<br>

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/delete_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/delete_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

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

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/tag_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/tag_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br>

### `untag` - Untags Task at #index in the List.
This command will untag the task at #index. #index is required.

The task will be reset to the default tag of "Others".

Example of usage: 

```
untag #index
```

<br>

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/untag_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/untag_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br>

### `bye` - Quits the ChatBot.
This command will exit the ChatBot and the tasks will be saved to memory for later use.

Example of usage: 

```
bye
```

<br>

<div align = "center">
  <img width = "1000" src="https://github.com/crypto-code/ip/blob/master/docs/img/bye_CLI.png?raw=true">
  <p align = "center"> CLI Interface </p>
  <img align = "center" height = "450" src="https://github.com/crypto-code/ip/blob/master/docs/img/bye_GUI.png?raw=true">
  <p align = "center"> GUI Interface </p>
</div>

<br> 
<hr>
<br>

<h2 id = 'developers'></h2> 

## For Developers

WhoBot is an Open-Source modular project and can be customized by interested developers.

You can always modify the responses to make it more tailored towards you by editing the `ui.echo()` statements. 

For example, to modify the greeting message (for GUI) you can edit the below function. You can also modify the memory file too.

```java
  public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DisplayBuffer.setParent(dialogContainer);
        DisplayBuffer.setUserInput(userInput);
        DisplayBuffer.setSendButton(sendButton);
        this.parser = new Parser();
        this.ui = new UI();
        try {
            String filename = "." + File.separator + "data" + File.separator + "WhoBotData.txt";
            this.storage = new Storage(filename);
            this.taskList = new TaskList(storage);
        } catch (WhoBotException ex) {
            ui.echo(ex.getMessage(), UI.Type.ERROR);
            System.exit(0);
        }

        scrollPane.setOnMouseEntered(e -> {
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        });
        scrollPane.setOnMouseExited(e -> {
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });

        ui.echo("Hello! I'm the WhoBot.", UI.Type.START);
        ui.echo("What can I do for you?", UI.Type.END);
  }
```

You can even modify the command specific responses by editing the `ui.echo()` in their respective functions in the **TaskList** class. Such as the function below:

```java
public void addTodo(String command, UI ui) throws WhoBotException {
        assert ui != null;
        try {
            Todo task = new Todo(command.substring(5));
            Anomaly anomalies = checkAnomalies(task);
            if (anomalies == Anomaly.EXISTENT) {
                throw new WhoBotException("This event already exists in your list!");
            }
            if (list.add(task)) {
                taggedList.get(NO_TAG).add(task);
                ui.echo("I have added this ToDo Task to the list: \"" + task.getDescription() + "\"", UI.Type.START);
                ui.echo("You now have " + list.size() + " task(s) in the list.", UI.Type.END);
            } else {
                throw new WhoBotException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"todo #description\"."
                    + " The description can not be empty.");
        }
    }
```

You can have fun personalizing WhoBot.

<br> 
<hr>
<br>

# G00D LUCK

For doubts email me at:
[atin.s@u.nus.edu](mailto:atin.s@u.nus.edu)

<br> 
<hr>
<br>
