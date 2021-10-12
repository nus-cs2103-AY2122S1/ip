# User Guide

### Features:
- [x] Save and delete different types of tasks: todo, deadline, event;
- [x] Set a saved task to be done;
- [x] Autosave to and autoload user's data from a default directory;
- [x] Allows user to save/load data to/from a specified directory;
- [x] Search for task by its key word.
- [x] Update a task
- [x] In-app command list

### Syntax List:
>Legend for datatype:
>1. **bolded** String
>2. *italic* int
- todo <**description**>: create and save a todo task;
  * Example: todo Study for test
- deadline <**description**> /by <**time in format yyyy-mm-dd**>: create and save a deadline task;
  * Example: deadline Assignment /by 2021-09-10
- event <**description**> /at <**time in format yyyy-mm-dd**>: create and save an event task;
  * Example: event Meeting /at 2021-09-11
- save <**directory**>: save the current task list to the specified directory;
  * Example: save data/myRecord
- load <**directory**>: load the task list from the specified directory;
  * Example: load data/myRecord
- done <*number*>: mark the task with *number* index as done;
  * Example: done 1
- delete <*number*>: delete the task with *number* index;
  * Example: delete 1
- deleteAll: delete all saved tasks in the task list;
  * Example: deleteAll
- find <**keyword**>: find all tasks with description containing the **keyword**;
  * Example:  
    - todo CS2100 assignment
    - todo CS2030 assignment
    - find assignment //show both entries
- update <*index*> /to <**Task formatted in ToDo/Deadline/Event**>;
  * Example: update 1 /to todo assignment 
- help: shows the list of available command.
  * Example: help
