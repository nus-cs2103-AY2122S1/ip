# User Guide

## Table of Content
- [Screenshots](#screenshots)
- [Features](#features)
  - [Add Tasks](#add-tasks)
    - [`todo`](#todo)
    - [`event`](#event)
    - [`deadline`](#deadline)
  - [View Tasks](#view-tasks)
    - [`list`](#list)
    - [`find`](#find)
  - [Update Tasks](#update-tasks)
    - [`done`](#done)
  - [Delete Tasks](#delete-tasks)
    - [`delete`](#delete)
    - [`clear`](#clear)
  - [Auxiliary Features](#auxiliary-features)
    - [`help`](#help)
    - [`save`](#save)
    - [Input History Navigation](#input-history-navigation)
- [Coming your way](#coming-your-way)

## Screenshots
🎉🎉🎉  
**Meet Hiko!**  
🎉🎉🎉
<div style="display:flex">
<img src="https://user-images.githubusercontent.com/39845424/133389724-1e7a69a0-2620-4859-ad94-072784fe5301.png" height="440">
<img src="https://user-images.githubusercontent.com/39845424/133390611-2c4d7572-e16f-4dfc-8960-1af9292b2baa.png" height="440">
<img src="https://user-images.githubusercontent.com/39845424/133391138-a80db12b-6a10-4db5-91b9-5cd06ac372b4.png" height="440">
</div>


## Features 

### Add Tasks
Adds a task to your list of task.  
A Task has a **completion status**, which can be marked done by [`done`](#done).  
A task can be the following **types**:  [`todo`](#todo), [`event`](#event), [`deadline`](#deadline)

#### `todo`
A `Todo` task is the simplest kind of task.  
You can use it to track any task status.

Format: `todo [description]`
| required | arguement | remark |
|----------|-----------|--------|
| ✅ | [description] | the description of the task. |


#### `event`
An `Event` task is a task with an event date.  
You can use it to track your event schedules.

Format: `event [description] /at [yyyy-mm-dd]`
| required | arguement | remark |
|----------|-----------|--------|
| ✅ | [description] | the description of the task. |
| ✅ | /at [yyyy-mm-dd] | the date of the event. |

#### `deadline`
An `Deadline` task is a task with an deadline. 
You can use it to remind you about deadlines.

Format: `deadline [description] /by [yyyy-mm-dd]`
| required | arguement | remark |
|----------|-----------|--------|
| ✅ | [description] | the description of the task. |
| ✅ | /by [yyyy-mm-dd] | the date of the deadline. |

### View Tasks
Viewing the list of task that you have is essential.  
Currently, **Hiko** supports `list` and `find`.

#### `list`
You can use it to list all the tasks.  
Format: `list`

#### `find`
A not-so-good search function
You can use it to find your tasks.

Format: `find [search string]`
| required | arguement | remark |
|----------|-----------|--------|
| ✅ | [search string] | the string that is contained in a task description. |

### Update Tasks
#### `done`
Mark a task in the list as done.  

Format: `done [index]`
| required | arguement | remark |
|----------|-----------|--------|
| ✅ | [index] | the index of the course starting with 1. |

### Delete Tasks
#### `delete`
Delete a task in the list.  

Format: `delete [index]`
| required | arguement | remark |
|----------|-----------|--------|
| ✅ | [index] | the index of the course starting with 1. |

#### `clear`
Clears all tasks in the list.  
Format: `clear`

### Auxiliary Features
#### `help`
View the help information with all the command.  
Format: `help`

#### `save`
Save the tasks into your local file `/data/duke.txt`.  
So when you open next time the tasks will be loaded.

Foramt: `save`

#### Input History Navigation
Simply press ⬆️⬇️ button on your keyboard to navigate throught your input history, similar to most command line interface.

## Coming your way

- modify save task path.
- list the tasks in different mode, such as chronological mode, filter by type mode
- improve `find` feature to search for variable keywords.
- find free time
- etc
