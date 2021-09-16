# User Guide

## Features

Pre-reading: The syntax of every command that is in `CAPITAL_LETTER` is the value that must be supplied by
the user.

### Adding Tasks

Our Duke program is able to add your tasks into the Death Note! Please do the tasks that you have written there,
or else, you have to bear the consequences. :japanese_ogre:

Duke can handle three different kinds of tasks. They are:

#### 1. Todo Tasks
- Format: `todo TASK`
- Params: 
  - `TASK`: the task name
- Functionality: Adds `TASK` into the task list as todo task.
- Example: `todo Drink water`

#### 2. Deadline Tasks
- Format: `deadline TASK /by DATE"`
- Params:
  - `TASK`: the task name
  - `DATE`: the deadline date in format `YYYY-MM-DD`
- Functionality: Adds `TASK` into the task list with deadline by `DATE`
- Example: `deadline Do iP /by 2021-10-30`

#### 3. Event Tasks
- Format: `event EVENT /at DATE"`
- Params:
    - `EVENT`: the event name
    - `DATE`: the event date in format `YYYY-MM-DD`
- Functionality: Adds event `EVENT` into the task list that will be held at `DATE`
- Example: `event RunNUS /at 2021-10-30`

### Deleting Tasks

Duke can help delete tasks that are irrelevant.

- Format: `delete INDEX`
- Params:
  - `INDEX`: The index of task to be deleted
- Example: `delete 1`

### Listing Tasks

Duke can list all the task that you have now.

- Format: `list`
- Params: none
- Example: `list`


### Prioritizing Tasks

Duke can help prioritize certain task that you want. By default, when adding  new tasks, they are
assigned priority of `MEDIUM`.

- Format: `prioritize INDEX PRIORITY`
- Params:
    - `INDEX`: The index of task to be changed its priority
    - `PRIORITY`: The priority to be set. Must be one of `HIGH`, `MEDIUM`, or `LOW`
- Example: `prioritize 1 HIGH`

### Finding Tasks

Duke can help you find certain task in your list!

- Format: `find KEYWORD`
- Params:
  - `KEYWORD`: the keyword (note that space is included)
- Example: `find HIGH`

### Shutting Down

If you're afraid of using Duke, you can shut it down! It exits the program normally.

- Format: `bye`
- Params: none
- Example: `bye`
