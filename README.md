# User Guide

### Features:
- [x] Save and delete different types of tasks: todo, deadline, event;
- [x] Set a saved task to be done;
- [x] Autosave to and autoload user's data from a default directory;
- [x] Allows user to save/load data from a specified directory;
- [x] Search for task according to a specified key word.
- [x] Update a task
- [x] An in-app user guideline

### Syntax List:
>Legend for datatype:
>1. **bolded** String
>2. *italic* int
- todo <**description**>: create and save a todo task;
- deadline <**description**> /by <**time in format yyyy-mm-dd**>: create and save a deadline task;
- event <**description**> /at <**time in format yyyy-mm-dd**>: create and save an event task;
- save <**directory**>: save the current task list to the specified directory;
- load <**directory**>: load the task list from the specified directory;
- done <*number*>: mark the task with *number* index as done;
- delete <*number*>: delete the task with *number* index;
- deleteAll: delete all saved tasks in the task list;
- find <**keyword**>: find all tasks with description containing the **keyword**;
- update <*index*> /to <**Task formatted in ToDo/Deadline/Event**>;
- help: shows the list of available command.
