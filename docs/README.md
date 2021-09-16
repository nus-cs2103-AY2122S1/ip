# Duke User Guide
Duke is a Personal Assistant CLIbot with a simple GUI system that helps a person to keep track of various things.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest Duke.jar from [here](https://github.com/KT27Learn/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for your AddressBook.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   
   ![Image of Duke](ReadmeScreenshot.png)
   
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window. Some example commands you can try:
    - **`list`**: Lists all tasks.
    - `todo test123`: Adds a task named test123 to the task list.
    - `mark 1`: Marks the 1st task shown in the current list as done.
    - **`exit`**: Exits the app.
6. Refer to the [Features](https://github.com/KT27Learn/ip/tree/master/docs#features) below for details of each command.

## Features

###Adding tasks:

Duke supports 3 different type of tasks to the task list for Duke to keep track of:

####Todo

Todo is a task without any time or dates assigned to it. To add a todo to the task list:

Format: `todo {taskname}`

Examples:

todo run

####Deadline

Deadline is a task that has an associated time and date that the task needs to be completed by. To add a deadline to the task list:

Format: `deadline {taskname} /by {due_date} {due_time}`

Acceptable `{due_date}` formats is as below:

- dd-MMM-yyyy (eg. 06-Dec-2020)
- yyyy-MMM-dd (eg. 2020-Dec-06)
- dd-MM-yyyy (eg. 06-12-2020)
- mm-DD-yyyy (eg. 12-23-2020)
- yyyy-MM-dd (eg. 2020-12-06)
- yyyy-DD-mm (eg. 2020-23-12)

Acceptable `{due_time}` format is as below:

- HH:mm (time in the 24 hour format)

Example:

- deadline do assignment /by 12-Dec-2020 12:00

####Event

Event is a task that has an associated start time and date as well as a end time and date for the task. To add a event to the task list:

Format: `event {taskname} /at {start_date} {start_time} to {end_date} {end_time}`

Acceptable `{start_date}` / `{end_date}` formats is as below:

- dd-MMM-yyyy (eg. 06-Dec-2020)
- yyyy-MMM-dd (eg. 2020-Dec-06)
- dd-MM-yyyy (eg. 06-12-2020)
- mm-DD-yyyy (eg. 12-23-2020)
- yyyy-MM-dd (eg. 2020-12-06)
- yyyy-DD-mm (eg. 2020-23-12)

Acceptable `{start_time}` / `{end_time}` format is as below:

- HH:mm (time in the 24 hour format)

Example:

- event do assignment /at 12-Dec-2020 12:00 to 2020-12-23 12:00

###Listing all persons : list

Shows a list of all tasks in the task list.

Format: `list`

### Updating a task : update

Edits an existing person in the address book.

Format: `update {index} {field_to_update} {new_value}`

- Updates the details of the task at the specified `index`. The index refers to the task number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …
- The update feature is limited to updating the description(`desc`), starting date time (`sdt`) or ending date time (`edt`) fields only.
- Existing values will be updated to the new_value.

Examples:

- `edit 2 desc test123 e/johndo` Edits the description of the 2nd task to be `test123.`
- `edit 1 sdt 12-Dec-2020 14:00` Edits the starting date time of the 1st task to be `12-Dec-2020 14:00` .

### Deleting a person : `delete`

Deletes the specified task from the task list

Format: `delete {index}`

- Deletes the person at the specified `index`.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:

- `delete 2` deletes the 2nd task in the task list

### Locating tasks by description: `find`

Finds tasks whose description contain the given keywords.

Format: `find {keyword}`

- The search is case-insensitive. e.g tests will match Tests
- Only the description is searched.
- Only full words will be matched e.g.test will not match tests

Examples:

- `find run` return tasks whose description could be "run now" and "run far"

### Marking tasks as done: `done`

Mark a specific task in the task list as done

Format: `done {index}`

- Marks the task at the specified `index` as completed.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:

- `done 2` marks the 2nd task in the task list as completed

### Exiting the program : `bye`

Exits the program.

Format: `bye`
