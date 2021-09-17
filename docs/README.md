# User Guide
Duke bot is a simple bot that you can use via the command line to help you keep track of your todo, evens and deadlines. 

## How this bot works:
You can add any todo, event and deadline to your current lists of tasks. After which you can mark some as done or even delete them. You can also search for tasks. Each kind of task will have their own format to follow for the input, which I will show you below. 

## Features to add new items to the list: 

### To-Do 
- input: `todo task /p 1`
 - expected: 
```
Got it. I have added this task: 
[T][] task [Priority 1]
Now you have _ tasks in the list.
```
A todo task will have an 'task' field to describe the task, and a 'priority' field to describe the priority (can be any number)

### Event 
- input: `event activity /at 2020-09-12 /p 1`
- expected:
```
Got it. I have added this task: 
[E][] activity (at: Sept 12 2020) [Priority 1]
Now you have _ tasks in the list.
```
A event task will have an 'activity' field to describe the event, a 'date' field to describe when the event is, and a 'priority' field to describe the priority (can be any number)

### Deadline 
- input: `deadline activity /at 2020-09-12 /p 1`
- expected:
```
Got it. I have added this task: 
[D][] activity (at: Sept 12 2020) [Priority 1]
Now you have _ tasks in the list.
```
A deadline task will have an 'activity' field to describe the event, a 'date' field to describe when the event is, and a 'priority' field to describe the priority (can be any number)

## Features to modify existing items in the list:

### Done 
- input: `done 1`
- expected:
```
Nice! I've marked this task as done: 
[D][X] activity (at: Sept 12 2020) [Priority 1]
```
This will mark an "X" in the second pair of `[]`

### Delete 
- input: `delete 1`
- expected:
```
Got it. I have ermoved this task:
[D][X] activity (at: Sept 12 2020) [Priority 1]
Now you have _ tasks in the list
```
This will show you the task you have deleted, together with the new number of tasks in your list. 

## Other features  

### Find 
- input: `find target`
- expected:
```
Here are the matching tasks in your list: 
[D][X] target (at: Sept 12 2020) [Priority 1]
```
This will show you the task(s) that contains the 'target' you have entered.

### List 

- input: `list`
- expected:
```
Here are the tasks in your list:
[T][] task [Priority 1]
[D][X] target (at: Sept 12 2020) [Priority 1]
```
This will show you all the tasks you have. 

### Bye 
- input: `bye`
- expected:
```
Bye! Hope to see you again soon.
```
This will allow the bot to save your current list of tasks, which will still be intact the next time you launch the bot. 
