# User Guide

&#10004; Fields in UPPERCASE are parameters supplied by the user.

&#10004; Items in [square bracket] are optional.

&#10004; Date/Time format used is `DD-MM-YYYY hhmm`, where `hhmm` is in 24-hour format, e.g. 1030 for 10.30am, and 2115 for 9.15pm.

## Features 

### Add a task
- **Todo** task
```
todo DESCRIPTION
```
- **Deadline** task
```
deadline DESCRIPTION /by DD-MM-YYYY hhmm
```
- **Event** task
```
event DESCRIPTION /at DD-MM-YYYY hhmm [to DD-MM-YYYY hhmm]
```

### List all tasks
```
list
```

### Update task details
```
update TASK_NUMBER [-d DESCRIPTION] [-by DD-MM-YYYY hhmm] [-at DD-MM-YYYY hhmm] [-to DD-MM-YYYY hhmm]
```
- At least one field to update must be specified
- Fields can be specified in any order
- Non-applicable fields are ignored (e.g. if the task to be updated is a deadline task, specifying `-at` field has no effect)
- If a field is specified multiple times, its value will be taken as the last value

### Delete a task
```
delete TASK_NUMBER
```

### Find a task by description
```
find KEYWORD [MORE KEYWORDS]
```
- The search is case-insensitive and matches partial words, i.e. finding `PRO` will return `project`
- The order of the keywords does not matter
- Only the description is searched
- Tasks do not need to match all keywords; as long as a task matches one keyword, it will be returned

### Exit the bot
```
bye
```

### Save the data
Task data is saved to a `duke.txt` file in the app's directory **only** upon exit. So closing the window mid-execution will discard all changes. The task data is loaded from the same `duke.txt` file when starting the app.


## Usage Summary

### `Keyword` - Describe action

Command | Syntax 
--------|--------
Add | **Todo** task<br>`todo DESCRIPTION`<br>**Deadline** task<br>`deadline DESCRIPTION /by DD-MM-YYYY hhmm`<br>**Event** task<br>`event DESCRIPTION /at DD-MM-YYYY hhmm [to DD-MM-YYYY hhmm]` | 
List | `list` |
Update | `update TASK_NUMBER [-d DESCRIPTION] [-by DD-MM-YYYY hhmm] [-at DD-MM-YYYY hhmm] [-to DD-MM-YYYY hhmm]` |
Delete | `delete TASK_NUMBER` |
Find | `find KEYWORD [MORE KEYWORDS]` |
Exit | `bye` |

## Sample view
<img src="Ui.png" height="600">
