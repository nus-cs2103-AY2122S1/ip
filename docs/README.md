# User Guide
This README briefly documents the many things you can do with `JarVIS` – the fake, inauthentic 
CS2103 JARVIS clone.

## Command Overview
`JarVIS` provides the following functions and features:

| Command                  | Description                                                                                                 |
|--------------------------|-------------------------------------------------------------------------------------------------------------|
| `JarVIS?`                | Pulls up the user manual with all available commands.                                                       |
| `todo DESC`              | Creates a ToDo task with `DESC` message                                                                     |
| `event DESC /at TIME`    | Creates an Event task with `DESC` message that takes place at `TIME` in `yyyy-MM-dd HH:mm` format           |
| `deadline DESC /by TIME` | Creates a Deadline task with `DESC` message that should be completed by `TIME` in `yyyy-MM-dd HH:mm` format |
| `done IDX`               | Marks the task at index `IDX` (starts from 1) as done                                                       |
| `delete IDX`             | Deletes the task at index `IDX` (starts from 1) from list                                                   |
| `bye`                    | Saves data and quits the chatbot application                                                                |

> Sometimes, `JarVIS` tends to get a little cocky. Ignore him :/

## Full Commands
### `JarVIS?`
This returns the user manual that documents all the features offered.

**Input**: `JarVIS?`
**Output**: 
```
list
    lists all items in the memory

todo <task_name>
    creates a ToDo task with no deadline
  
deadline <task_name> /by yyyy-MM-dd HH:mm
    creates a Deadline task with a timestamp deadline
    
event  <task_name> /at yyyy-MM-dd HH:mm
    creates an Event task taking place at a timestamp

delete <task_idx>
    deletes the task found at the given index

done <task_idx>
    sets the specified task's status to done

find <keyword>
    finds the tasks containing a given keyword
```

### `todo`

**Input**: `todo DESC` <br>
**Example**: `todo Homework` <br>
**Output**:
```
Got it. I've added this task:
    [T][ ] Homework
Now you have 1 task(s) in the list.    
```

### `event`

**Input**: `event DESC /at TIME` <br>
**Example**: `event Dance Class /at 2021-12-12 12:12` <br>
**Output**:
```
Got it. I've added this task:
    [E][ ] Dance Class (at: Dec 12 2021 12:12 PM)
Now you have 1 task(s) in the list.    
```

### `deadline`

**Input**: `deadline DESC /by TIME` <br>
**Example**: `deadline CS2103 iP /by 2021-12-12 12:12` <br>
**Output**:
```
Awesome! I've added this task:
    [D][ ] CS2103 iP (by: Dec 12 2021 12:12 PM)
Now you have 1 task(s) in the list.    
```

### `done`

**Input**: `done IDX` <br>
**Example**: `done 1` <br>
**Output**:
```
Nice! I've marked this task as done:
    [T][X] Homework   
```

### `delete`

**Input**: `delete IDX` <br>
**Example**: `delete 1` <br>
**Output**:
```
Understood. I've removed,
    [T][X] Homework
You now have 2 tasks to totally ignore.    
```

### `bye`

**Input**: `bye` <br>
**Output**:
```
Goodbye! As always, a pleasure working with you.
```

The bot then proceeds to shut down completely.

## Usage

1. Clone this repo and `cd` into it:
```bash
git clone https://github.com/rish-16/ip.git
cd ip
```

2. Open up the `ip` codebase in **IntelliJ** and wait for the Gradle build and processes to end.

3. Press the *Play* button at the top-right corner to launch the application.

