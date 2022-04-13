# User Guide

## Introduction
Duke is a chatbot that manages your tasks. You can add tasks, delete them,
mark them as done and search through your list of tasks.

## Interface
![Image of Interface](https://github.com/jianh0ng/ip/blob/master/docs/Ui.png)

## Features 

### `list`

#### List of all tasks in the list.<br/>
Format:
```
list
```
### `places`

#### List of all places in the list.<br/>
Format:
```
places
```

### `todo`

#### Adds a todo task to the list, with optional location.<br/>
Format:
```
todo {description} 
OR
todo {description} > {location}
```
Example:
```
todo return book
todo return book > Central Library
```

### `deadline`

#### Adds a deadline task to the list, with due date and with optional location.<br/>
Format:
```
deadline {description} /by {YYYY-MM-DD} 
OR
deadline {description} /by {YYYY-MM-DD} > {location}
```
Example:
```
deadline return book /by 2021-09-21
deadline return book /by 2021-09-21 > Central Library
```

### `event`

#### Adds an event task to the list, with event date and optional location.<br/>
Format:
```
event {description} /at {YYYY-MM-DD}
OR
event {description} /at {YYYY-MM-DD} > <location>
```
Example:
```
event return book /at 2021-09-21
event return book /at 2021-09-21 > Central Library
```

### `done`

#### Marks a task in the list as done.<br/>
Format:
```
done {task number}
```
Example:
```
done 4
```

### `delete-task`

#### Deletes a task from the list.<br/>
Format:
```
delete-task {task number}
```
Example:
```
delete-task 3
```

### `delete-place`

#### Deletes a place from the list.<br/>
Format:
```
delete-place {place number}
```
Example:
```
delete-place 3
```

### `find`

#### List of tasks with relevant searches.<br/>
Format:
```
find {search term}
```
Example:
```
find assignment
```

### `bye`

#### Exits the chatbot.<br/>
Format:
```
bye
```