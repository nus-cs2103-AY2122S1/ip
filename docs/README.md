# User Guide

## Features 

### Add a task
- **Todo** task: a task that you want to do
```
todo DESCRIPTION
```
- **Deadline** task: a task with a specific deadline
```
deadline DESCRIPTION /by DD-MM-YYYY
```
- **Event** task: an event happens at a specific time
```
event DESCRIPTION /at DD-MM-YYYY hhmm
```

### List all tasks
```
list
```
Returns the list of task with their respective status in the task list
### Tag a task
```
tag TASK_INDEX TASK_TAG
```
Tags a task with the given task index and tag.

### Delete a task
```
delete TASK_NUMBER
```
Deletes a task at the index given
### Find a task by description
```
find KEYWORD [MORE KEYWORDS]
```
- The search matches partial word, i.e searching for `John` will return `JohnDoe`
- If a task matches the keyword, it will be returned in the result. The order of the keywords does not matter.

### Exit the bot
```
bye
```

### Save the data
Data is always saved when a task is created/an operation is executed on the task list.

## Preview
<img src="Ui.PNG" height="500">