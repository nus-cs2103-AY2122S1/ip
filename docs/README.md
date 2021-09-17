# User Guide

## Features 

Command category | Command Keywords
------------ | -------------
Task creation | **todo** </br> **event**<br/> **deadline**
View Tasks first column | **list** </br> **find**
Modify Tasks | **done** </br> **delete**
Others | **help** </br> **bye**

### Task Creation
* **todo**, **event**, **deadline**
1. todo
   - command syntax: `todo \<desc\>`
2. event
    - command syntax: `event \<desc\> /at \<time\>`
3. deadline
    - command syntax: `deadline \<desc\> /by \<time\>`
    - \<time\> syntax: `dd-mm-yyyy hhmm` 
      <u>(eg. 01-01-2020 2359)</u>

```
todo projectWork
```

### View Tasks
* **list**, **find**
4. list
   - command syntax: `list`
   - usage: list out all tasks
5. find: 
   - command syntax: `find <keyword>`
   - usage: filter task by keyword

### Modify Tasks
* **done**, **delete**
4. done
    - comman syntax: `done <taskIndex>`
    - usage: toggle selected task as done
5. delete:
    - command syntax: `delete <taskIndex>`
    - usage: filter task by keyword

### Other Commands
* **help**, **bye**
4. help
    - comman syntax: `help`
    - usage: print out all commands and syntax to GUI
5. bye:
    - command syntax: `bye`
    - usage: closes application
    
## Example Usage

### `Potential use case` 


```Java
list //view all tasks
done 2 //toggle 2nd task done
todo projectWork //add todo task
deadline projectSubmission /at 01-01-2021 2359 //add deadline
//after projectSubmission
list //view all tasks - take note of index i of projectSubmission (eg. i = 4)
delete 4 //delete projectSubmission task

```
