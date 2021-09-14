# User Guide

## Introduction

### HomlesJJ の Duke

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

**HomlesJJ の Duke** frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ _SUPER_ FAST to use

## User Interface
![UI](Ui.png)

## Quick Start

1. download [CLI](https://github.com/HolmesJJ/ip/releases/tag/v0.4) or [GUI](https://github.com/HolmesJJ/ip/releases/tag/v0.5) version.
2. Open terminal.
3. Use `cd` command to navigate to the directory of jar file.
4. Enter `java -jar duke-cli.jar` for CLI or `java -jar duke-gui.jar` for GUI (Prerequisites: [jdk](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) installed).
5. add your tasks.
6. let it manage your tasks for you 😉

And it is **FREE!**

## Features

1. Adds task: adds a task to the system.
    1. [**`todo`**](#1.1): adds a todo task that without any date/time attached to it.
    2. [**`deadline`**](#1.2): adds a deadline task that need to be done before a specific date/time.
    3. [**`event`**](#1.3): adds an event task that start at a specific time and ends at a specific time.
2. [**`list`**](#2): lists all tasks in the system.
3. [**`done`**](#3): marks a task as done by task number of the full tasks list.
4. [**`find`**](#4): lists all tasks by searching for a keyword.
5. [**`coming`**](#5): lists all coming tasks within 3 hours.
6. [**`delete`**](#6): deletes a task by task number of the full tasks list.
7. [**`clear`**](#7): clear all tasks in the system.
8. [**`bye`**](#8): exits the system.

## Usage

### <label id="1.1">1.1<label> `todo <TASK_DESCRIPTION>` - Adds a todo task.

Adds a todo task to the system.

Example of usage: 
```
todo read book
```

Expected outcome:

```
Got it. I've added this task:
 [T][ ] read book
Now you have 1 task in the list.
```

### <label id="1.2">1.2<label> `deadline <DESCRIPTION /by yyyy-MM-dd HH:mm>` - Adds a deadline task.

Adds a deadline task to the system.

Example of usage: 
```
deadline return book /by 2021-08-08 10:00
```

```
Got it. I've added this task:
 [D][ ] return book (by: Jun 06 2021 06:00 PM)
Now you have 2 tasks in the list.
```

### <label id="1.3">1.3<label> `event <DESCRIPTION /at yyyy-MM-dd HH:mm HH:mm>` - Adds a event task.

Adds an event task to the system.

Example of usage: 
```
event project meeting /at 2021-09-08 19:00 21:00
```

Expected outcome:

```
Got it. I've added this task:
 [E][ ] project meeting (at: Sep 08 2021 07:00 PM-09:00 PM)
Now you have 3 tasks in the list.
```

### <label id="2">2<label> `list` - Lists all tasks.

Lists all tasks in the system.

Example of usage: 
```
list
```

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Jun 06 2021 06:00 PM)
3. [E][ ] project meeting (at: Sep 08 2021 07:00 PM-09:00 PM)
```

### <label id="3">3<label> `done <TASK_NUMBER>` - Marks a task as done by task number of the full tasks list.

Marks a task as done by task number of the full tasks list in the system.

Example of usage: 
```
done 1
```

Expected outcome:

```
Nice! I've marked this task as done: 
 [T][X] read book
```

### <label id="4">4<label> `find <KEYWORD>` - Lists all tasks by searching for a keyword.

Lists all tasks by searching for a keyword in the system.

Example of usage: 
```
find book
```

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: Jun 06 2021 06:00 PM)
```

### <label id="5">5<label> `coming` - Lists all coming tasks within 3 hours.

Lists all coming tasks within 3 hours in the system.

Example of usage: 
```
coming
```

Expected outcome:

```
Here are the coming tasks in your list:
3. [E][ ] project meeting (at: Sep 08 2021 07:00 PM-09:00 PM)
```

### <label id="6">6<label> `delete <TASK_NUMBER>` - Deletes a task by task number of the full tasks list.

Deletes a task by task number of the full tasks list in the system.

Example of usage: 
```
delete 3
```

Expected outcome:

```
Noted. I've removed this task:
 [E][ ] project meeting (at: Sep 08 2021 07:00 PM-09:00 PM)
Now you have 2 tasks in the list.
```

### <label id="7">7<label> `clear` - Clears all tasks.

Clears all tasks in the system.

Example of usage: 
```
clear
```

Expected outcome:

```
All tasks are cleared.
```

### <label id="8">8<label> `bye` - Exits the system.

Exits the system.

Example of usage: 
```
bye
```

Expected outcome:

System exits.

## Development

If you Java programmer, you can use it to practice Java too. Here's the main method:

For CLI
```
public class Duke implements Message {
    // ...
    public static void main(String[] args) {
        new Duke().run();
    }
}
```

For GUI
```
public class Main {
    public static void main(String[] args) {
        Application.launch(DukeApp.class, args);
    }
}
```

You can also find the source code at [here](https://github.com/HolmesJJ/ip). 🤩 
