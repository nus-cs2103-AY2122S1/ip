# Melody User Guide
Melody is a task management application with Command Line Interface(CLI). 

## Features 
- Show useable command keyword
- Add a task to task list: add a todo/event/deadline event to the list
- Mark a task as done
- Delete a task
- Display tasks
- Snooze a task to a later date (if applicable) 
- Find all tasks that match the given keyword
- Exit the application and save data

## Usage

### `help` - Show help 

Example of usage: 

- `help`

Expected outcome:

Help text that looks like the following: 
```
- to enter a task:
 'todo' + description
 'event' + description +'/' + date(yyyy-MM-dd)
 'deadline' + description + '/' + date(yyyy-MM-dd)
- to mark a task as done:
  'done' + taskNumber
- to delete a task:
  'delete' + taskNumber
- to display all tasks:
  'list'
- to search for a task:
  'find' + searchWord
- to save all tasks and exit:
  'bye'
- to snooze a task/deadline:
  'snooze' + taskNumber + date(yyyy-MM-dd)
```
  
### `add todo [DESCRIPTION]`- Add a todo to the list

Example of usage: 

- `add todo have dinner`

Expected outcome:
```
Ok~ I've added the task: 
T| | have dinner
```

### `add event [DESCRIPTION] / [DATE]` - Add an event with a date 
Example of usage: 

- `add event meeting / 2021-09-21`
 
Expected outcome:
```
Ok~ I've added the task: 
E| | meeting / Sep 21 2021
```

### `add deadline [DESCRIPTION] / [DATE]` - Add a deadline with a date 
Example of usage: 

- `add deadline assignment / 2021-09-21`
 
Expected outcome:
```
Ok~ I've added the task: 
D| | assignment / Sep 21 2021
```

### `done [TASK_NUMBER]` - Mark a task as done
Example of usage: 

- `done 1`
 
Expected outcome:
```
Yayyyy done ~~
T|X| have dinner
```

### `delete [TASK_NUMBER]` - Delete a task
Example of usage: 

- `delete 1`
 
Expected outcome:
```
Ok~ I've deleted the task: 
T|X| have dinner
```

### `list` - Display current tasks in the list
Example of usage: 

- `list`
 
Expected outcome:
```
Here are your tasks ~ OwO
1. E| | meeting / Sep 21 2021
2. D| | assignment / Sep 21 2021
```

### `snooze [TASK_NUMBER]` - Postpone a deadline/event to another date
Example of usage: 

- `snooze 1 2021-10-21`
 
Expected outcome:
```
Ok~ I've added the task: 
E| | meeting / Oct 21 2021
```


### `find [KEYWORD]` - Find all tasks that match the given keyword

Example of usage: 

- `find assignment`
 
Expected outcome:
```
Here are your tasks ~ OwO
D| | assignment / Sep 21 2021
```

### `bye` - Save newly edited task list and exit
Example of usage:

- `bye`

Expected outcome:
```
Byebye ~ nya
```



